package com.egarcia.assignment.service.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.egarcia.assignment.service.model.Listing
import com.egarcia.assignment.utils.ERROR
import com.egarcia.assignment.utils.SUCCESS
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Source of listings for the pagination library
 */
class ListingDataSource : PageKeyedDataSource<Int, Listing>() {

    private val mProgressStatus : MutableLiveData<String> = MutableLiveData()

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Listing>) {
        // Not required
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Listing>) {
        val initialPage = 0
        ListingRepository.getInstance()
                .getListings(initialPage, params.requestedLoadSize, object : Callback<List<Listing>> {
                    override fun onFailure(call: Call<List<Listing>>?, t: Throwable?) {
                        mProgressStatus.postValue(ERROR)
                    }

                    override fun onResponse(call: Call<List<Listing>>?, response: Response<List<Listing>>?) {
                        response?.body()?.let {
                            listings -> callback.onResult(listings, initialPage, initialPage + response.body().size)
                            mProgressStatus.postValue(SUCCESS)
                        } ?: mProgressStatus.postValue(ERROR)
                    }
                })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Listing>) {
        ListingRepository.getInstance()
                .getListings(params.key, params.requestedLoadSize, object : Callback<List<Listing>> {
                    override fun onFailure(call: Call<List<Listing>>?, t: Throwable?) {
                        mProgressStatus.postValue(ERROR)
                    }

                    override fun onResponse(call: Call<List<Listing>>?, response: Response<List<Listing>>?) {
                        response?.body()?.let {
                            listings -> callback.onResult(listings, params.key + response.body().size)
                            mProgressStatus.postValue(SUCCESS)
                        } ?: mProgressStatus.postValue(ERROR)
                    }
                })
    }

    fun progressStatus() : MutableLiveData<String> {
        return mProgressStatus
    }

}