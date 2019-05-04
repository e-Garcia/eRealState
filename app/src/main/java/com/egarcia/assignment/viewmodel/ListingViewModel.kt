package com.egarcia.assignment.viewmodel

import androidx.lifecycle.LiveData
import com.egarcia.assignment.service.model.Listing
import com.egarcia.assignment.service.repository.ListingRepository

/**
 * A collection of Listings
 */
class ListingViewModel : BaseViewModel() {
    companion object {
        const val PAGE_SIZE = 5
    }

    fun allListings() : LiveData<List<Listing>> {
        return ListingRepository.getInstance().getAllListings()
    }

    fun listings(start: Int) : LiveData<List<Listing>> {
        return ListingRepository.getInstance().getListings(start, PAGE_SIZE)
    }

}