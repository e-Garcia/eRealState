package com.egarcia.assignment.view.callback

import androidx.recyclerview.widget.DiffUtil
import com.egarcia.assignment.service.model.Listing

class ListingDiffCallback : DiffUtil.ItemCallback<Listing>() {
    override fun areItemsTheSame(oldItem: Listing, newItem: Listing): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Listing, newItem: Listing): Boolean {
        return oldItem.listingType == newItem.listingType
                && oldItem.price == newItem.price
                && oldItem.bathrooms == newItem.bathrooms
                && oldItem.squareFeet == newItem.squareFeet
                && oldItem.bedrooms == newItem.bedrooms
                && oldItem.latitude == newItem.latitude
                && oldItem.longitude == newItem.longitude
                && oldItem.numberOfPhotos == newItem.numberOfPhotos
                && oldItem.stateCode == newItem.stateCode
                && oldItem.city == newItem.city
                && oldItem.streetName == newItem.streetName
                && oldItem.streetNumber == newItem.streetNumber
                && oldItem.zipCode == newItem.zipCode
                && oldItem.neighborhood == newItem.neighborhood
    }


}