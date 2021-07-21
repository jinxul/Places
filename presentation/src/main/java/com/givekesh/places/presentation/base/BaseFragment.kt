package com.givekesh.places.presentation.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.givekesh.places.domain.util.PlacesIntent
import com.givekesh.places.presentation.places.PlacesViewModel
import kotlinx.coroutines.launch

abstract class BaseFragment : Fragment() {
    val viewModel: PlacesViewModel by activityViewModels()

    fun sendIntent(placesIntent: PlacesIntent) {
        lifecycleScope.launch {
            viewModel.channel.send(placesIntent)
        }
    }

    fun requestFilteredData(isFiltered: Boolean) {
        if (isFiltered) {
            sendIntent(PlacesIntent.GetFavorites)
        } else {
            sendIntent(PlacesIntent.GetPlaces)
        }
    }
}