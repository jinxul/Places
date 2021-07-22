package com.givekesh.places.presentation.util

import androidx.recyclerview.widget.DiffUtil
import com.givekesh.places.domain.entity.Place

class PlacesDiffUtil(
    private val oldList: List<Place>,
    private val newList: List<Place>,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean = oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean = oldList[oldItemPosition] == newList[newItemPosition]
}