package com.givekesh.places.presentation.places

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.givekesh.places.BR
import com.givekesh.places.databinding.ItemPlaceBinding
import com.givekesh.places.domain.entity.Place
import com.givekesh.places.presentation.util.ItemCallback
import com.givekesh.places.presentation.util.PlacesDiffUtil

class PlacesAdapter : RecyclerView.Adapter<PlacesAdapter.PlacesViewHolder>() {

    private val items = mutableListOf<Place>()
    private lateinit var listener: ItemCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPlaceBinding.inflate(inflater, parent, false)
        return PlacesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlacesViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size

    private fun getItem(position: Int): Place = items[position]

    fun updateList(data: List<Place>) {
        val diffCallback = PlacesDiffUtil(items, data)
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(diffCallback)
        items.clear()
        items.addAll(data)
        diffResult.dispatchUpdatesTo(this)
    }

    fun setOnItemCallback(listener: ItemCallback) {
        this.listener = listener
    }

    inner class PlacesViewHolder(
        private val binding: ItemPlaceBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(place: Place) {
            binding.apply {
                setVariable(BR.place, place)
                itemFavoriteIndicator.setOnClickListener {
                    listener.onFavoritesChanged(place.id, !place.isFavorite)
                }
                root.setOnClickListener {
                    listener.onClickListener(place)
                }
            }
        }
    }
}