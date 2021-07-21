package com.givekesh.places.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.givekesh.places.BR
import com.givekesh.places.databinding.FragmentDetailsBinding
import com.givekesh.places.domain.util.PlacesIntent
import com.givekesh.places.presentation.places.PlacesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PlacesViewModel by activityViewModels()
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            setVariable(BR.place, args.place)
            back.setOnClickListener {
                findNavController().popBackStack()
            }
            favorite.setOnClickListener {
                sendIntent(
                    PlacesIntent.SetFavorites(
                        id = args.place.id,
                        isFavorite = favorite.isChecked
                    )
                )
                requestFilteredData(args.isFiltered)
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

    private fun sendIntent(placesIntent: PlacesIntent) {
        lifecycleScope.launch {
            viewModel.channel.send(placesIntent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}