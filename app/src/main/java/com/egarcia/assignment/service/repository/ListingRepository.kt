package com.egarcia.assignment.service.repository

import com.egarcia.assignment.service.model.Listing
import com.egarcia.assignment.utils.BASE_URL
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Contains the implementation details for retrieving Listings from the network.
 */
class ListingRepository {

    private val listingApi: ListingApi

    companion object {
        private lateinit var listingRepository : ListingRepository

        fun getInstance() : ListingRepository {
            if (!::listingRepository.isInitialized) {
                listingRepository = ListingRepository()
            }
            return listingRepository
        }
    }

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        listingApi = retrofit.create(ListingApi::class.java)
    }

    fun getListings(start : Int, count : Int, callback: Callback<List<Listing>>) {
        listingApi.getListings(start, count).enqueue(callback)
    }

}