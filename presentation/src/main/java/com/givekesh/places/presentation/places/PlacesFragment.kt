package com.givekesh.places.presentation.places

import android.content.Context
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.givekesh.places.R
import com.givekesh.places.databinding.FragmentPlacesBinding
import com.givekesh.places.domain.entity.Place
import com.givekesh.places.domain.util.DataState
import com.givekesh.places.domain.util.PlacesIntent
import com.givekesh.places.presentation.base.BaseFragment
import com.givekesh.places.presentation.util.GridItemDecoration
import com.givekesh.places.presentation.util.ItemCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlacesFragment : BaseFragment() {
    private var _binding: FragmentPlacesBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: PlacesAdapter
    private var uiJob: Job? = null

    private var lastSearchState = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlacesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPlacesList()
        setupToolbar()
        requestData()
        subscribeObserver()
    }

    private fun setupPlacesList() {
        binding.apply {
            adapter = PlacesAdapter()
            adapter.setOnItemCallback(object : ItemCallback {
                override fun onFavoritesChanged(id: Int, isFavorite: Boolean) {
                    sendIntent(PlacesIntent.SetFavorites(id, isFavorite))
                    requestFilteredData(filter.isChecked)
                }

                override fun onClickListener(place: Place) {
                    findNavController().navigate(
                        PlacesFragmentDirections.actionPlacesToDetails(
                            place = place,
                            isFiltered = filter.isChecked
                        )
                    )
                }
            })
            val itemDecoration = GridItemDecoration()
            placesList.adapter = adapter
            placesList.addItemDecoration(itemDecoration)
        }
    }

    private fun setupToolbar() {
        binding.apply {
            filter.setOnCheckedChangeListener { _, isChecked ->
                requestFilteredData(isChecked)
                placesList.smoothScrollToPosition(0)
            }
            search.addTextChangedListener {
                sendIntent(
                    PlacesIntent.SearchPlaces(
                        searchQuery = it.toString(),
                        filterByFavorites = filter.isChecked
                    )
                )
                if (it.isNullOrEmpty()) {
                    handleSearchAnimation(false)
                    searchBar.setStartIconOnClickListener { }
                } else {
                    handleSearchAnimation(true)
                    searchBar.setStartIconOnClickListener {
                        search.setText("")
                        hideKeyboard()
                    }
                }
            }
        }
    }

    private fun handleSearchAnimation(isSearching: Boolean) {
        if (lastSearchState == isSearching)
            return
        lastSearchState = isSearching
        val drawableRes = when (isSearching) {
            true -> R.drawable.search_to_close
            false -> R.drawable.close_to_search
        }
        binding.apply {
            searchBar.setStartIconDrawable(drawableRes)
            val animatedVectorDrawable = searchBar.startIconDrawable as AnimatedVectorDrawable
            animatedVectorDrawable.start()
        }
    }

    private fun requestData() {
        sendIntent(PlacesIntent.GetPlaces)
    }

    private fun hideKeyboard() {
        val inputManager = requireContext().getSystemService(
            Context.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        inputManager.hideSoftInputFromWindow(
            binding.search.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }

    private fun subscribeObserver() {
        lifecycleScope.launch {
            viewModel.dataState.collect { dataState ->
                when (dataState) {
                    DataState.Idle -> Unit
                    DataState.Loading -> handleLoadingAnimation(true)
                    is DataState.Success -> {
                        handleLoadingAnimation(false)
                        adapter.updateList(dataState.data)
                    }
                    is DataState.Failed -> Unit
                }
            }
        }
    }

    private fun handleLoadingAnimation(isLoading: Boolean) {
        val visibility = when (isLoading) {
            true -> View.VISIBLE
            false -> View.GONE
        }
        binding.animationView.visibility = visibility
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        uiJob?.cancel()
    }
}