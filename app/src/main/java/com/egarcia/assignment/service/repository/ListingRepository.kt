package com.egarcia.assignment.service.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.egarcia.assignment.service.model.Listing
import com.egarcia.assignment.utils.BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

    fun getAllListings() : LiveData<List<Listing>> {
        val listings = MutableLiveData<List<Listing>>()
        listingApi.getAllListings().enqueue(object : Callback<List<Listing>> {

            override fun onResponse(call: Call<List<Listing>>?, response: Response<List<Listing>>?) {
                listings.value = response?.body()
            }

            override fun onFailure(call: Call<List<Listing>>?, t: Throwable?) {
                t.toString()
            }

        })

        return listings
    }

    fun getListings(start : Int, count : Int) : LiveData<List<Listing>> {
        val listings = MutableLiveData<List<Listing>>()
        listingApi.getListings(start, count).enqueue(object : Callback<List<Listing>> {

            override fun onResponse(call: Call<List<Listing>>?, response: Response<List<Listing>>?) {
                listings.value = response?.body()
            }

            override fun onFailure(call: Call<List<Listing>>?, t: Throwable?) {
                t.toString()
            }

        })

        return listings
    }

    fun getListings(start : Int, count : Int, callback: Callback<List<Listing>>) {
        listingApi.getListings(start, count).enqueue(callback)
    }

}