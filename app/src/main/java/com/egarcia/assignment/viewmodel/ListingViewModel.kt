package com.egarcia.assignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.egarcia.assignment.service.model.Listing
import com.egarcia.assignment.service.repository.ListingDataSource
import com.egarcia.assignment.service.repository.ListingRepository

/**
 * A collection of Listings
 */
class ListingViewModel : BaseViewModel() {

    private val mListings : LiveData<PagedList<Listing>>
    private lateinit var mLoadingStatus : MutableLiveData<String>

    companion object {
        const val PAGE_SIZE = 5
    }

    init {
        val config = PagedList.Config.Builder()
                .setInitialLoadSizeHint(ListingViewModel.PAGE_SIZE)
                .setPageSize(ListingViewModel.PAGE_SIZE)
                .build()

        mListings = listBuilder(config).build()
    }

    fun paginatedListings() : LiveData<PagedList<Listing>> {
        return mListings
    }

    fun loadingStatus() : LiveData<String> {
        return mLoadingStatus
    }

    private fun listBuilder(config : PagedList.Config) : LivePagedListBuilder<Int, Listing> {

        val dataSourceFactory = object : DataSource.Factory<Int, Listing>() {
            override fun create(): DataSource<Int, Listing> {
                val dataSource = ListingDataSource()
                mLoadingStatus = dataSource.progressStatus()
                return dataSource
            }
        }

        return LivePagedListBuilder<Int, Listing>(dataSourceFactory, config).setInitialLoadKey(0)
    }

}