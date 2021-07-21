package com.givekesh.places.presentation.places

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.givekesh.places.R
import com.givekesh.places.databinding.FragmentPlacesBinding
import com.givekesh.places.domain.util.DataState
import com.givekesh.places.domain.util.PlacesIntent
import com.givekesh.places.presentation.util.GridItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlacesFragment : Fragment() {
    private var _binding: FragmentPlacesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PlacesViewModel by viewModels()

    private lateinit var adapter: PlacesAdapter
    private var uiJob: Job? = null

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
        adapter = PlacesAdapter()
        adapter.setOnFavoriteClickListener { id, isFavorite ->
            sendIntent(PlacesIntent.SetFavorites(id, isFavorite))
            requestFilteredData(binding.filter.isChecked)
        }
        val itemDecoration = GridItemDecoration()
        binding.apply {
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
                    searchBar.setStartIconDrawable(R.drawable.ic_baseline_search)
                    searchBar.setStartIconOnClickListener { }
                } else {
                    searchBar.setStartIconDrawable(R.drawable.ic_baseline_close)
                    searchBar.setStartIconOnClickListener { search.setText("") }
                }
            }
        }
    }

    private fun requestFilteredData(isFiltered: Boolean) {
        if (isFiltered) {
            sendIntent(PlacesIntent.GetFavorites)
        } else {
            sendIntent(PlacesIntent.GetPlaces)
        }
    }

    private fun requestData() {
        sendIntent(PlacesIntent.GetPlaces)
    }

    private fun sendIntent(placesIntent: PlacesIntent) {
        lifecycleScope.launch {
            viewModel.channel.send(placesIntent)
        }
    }

    private fun subscribeObserver() {
        lifecycleScope.launch {
            viewModel.dataState.collect { dataState ->
                when (dataState) {
                    DataState.Idle -> Unit
                    DataState.Loading -> Unit
                    is DataState.Success -> adapter.updateList(dataState.data)
                    is DataState.Failed -> Unit
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        uiJob?.cancel()
    }
}