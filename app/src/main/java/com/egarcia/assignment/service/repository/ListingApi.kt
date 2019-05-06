package com.egarcia.assignment.service.repository

import com.egarcia.assignment.service.model.Listing
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *  The interface for the listing web service
 */
interface ListingApi {

    /**
     * Get the list of unit listings from the API
     */
    @GET("/listings")
    fun getListings(@Query("start") start: Int,
                    @Query("count") count: Int): Call<List<Listing>>

    /**
     * Get the list of unit listings from the API
     */
    @GET("/listings")
    fun getAllListings(): Call<List<Listing>>

}