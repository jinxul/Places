package com.givekesh.places.presentation.places

import android.content.Context
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.StringRes
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
                    requestFilteredData(toolbar.filter.isChecked)
                }

                override fun onClickListener(place: Place) {
                    findNavController().navigate(
                        PlacesFragmentDirections.actionPlacesToDetails(
                            place = place,
                            isFiltered = toolbar.filter.isChecked
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
        binding.toolbar.apply {
            filter.setOnCheckedChangeListener { _, isChecked ->
                val res = when (isChecked) {
                    true -> R.drawable.dashboard_to_bookmark
                    false -> R.drawable.bookmark_to_dashboard
                }
                filter.setBackgroundResource(res)
                val animatedVectorDrawable = filter.background.current as AnimatedVectorDrawable
                animatedVectorDrawable.start()
                requestFilteredData(isChecked)
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
        binding.toolbar.apply {
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
            binding.toolbar.search.windowToken,
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
                        val placeholderVisibility = when (dataState.data.isEmpty()) {
                            true -> View.VISIBLE
                            false -> View.GONE
                        }
                        handlePlaceholder(
                            stringRes = R.string.data_not_found,
                            visibility = placeholderVisibility
                        )
                        adapter.updateList(dataState.data)
                    }
                    is DataState.Failed -> {
                        binding.apply {
                            animationView.visibility = View.GONE
                            placesList.visibility = View.GONE
                            handlePlaceholder(
                                stringRes = R.string.unexpected_error,
                                visibility = View.VISIBLE
                            )
                        }
                    }
                }
            }
        }
    }

    private fun handleLoadingAnimation(isLoading: Boolean) {
        val animationVisibility = when (isLoading) {
            true -> View.VISIBLE
            false -> View.GONE
        }
        val placesVisibility = when (isLoading) {
            true -> View.GONE
            false -> View.VISIBLE
        }
        binding.apply {
            animationView.visibility = animationVisibility
            placesList.visibility = placesVisibility
        }
    }

    private fun handlePlaceholder(@StringRes stringRes: Int, visibility: Int) {
        binding.apply {
            placeholderLayout.root.visibility = visibility
            placeholderLayout.placeholderText.text = getString(stringRes)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        uiJob?.cancel()
    }
}