package com.egarcia.assignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.egarcia.assignment.service.model.Listing
import com.egarcia.assignment.service.repository.ListingDataSource
import com.egarcia.assignment.service.repository.ListingDataSourceFactory

/**
 * A collection of Listings
 */
class ListingViewModel : BaseViewModel() {

    private val mListings : LiveData<PagedList<Listing>>
    private var mLoadingStatus : LiveData<String>
    private val mFactory: ListingDataSourceFactory

    companion object {
        const val PAGE_SIZE = 5
    }

    init {
        val config = PagedList.Config.Builder()
                .setInitialLoadSizeHint(ListingViewModel.PAGE_SIZE)
                .setPageSize(ListingViewModel.PAGE_SIZE)
                .build()

        mFactory = ListingDataSourceFactory()
        mLoadingStatus = Transformations.switchMap(mFactory.mDataSource, ::loadNetworkState)
        mListings = listBuilder(config, mFactory).build()

    }

    fun refresh() {
        mFactory.mDataSource.value?.invalidate()
    }

    fun paginatedListings() : LiveData<PagedList<Listing>> {
        return mListings
    }

    fun loadingStatus() : LiveData<String> {
        return mLoadingStatus
    }

    private fun listBuilder(config : PagedList.Config, dataSourceFactory : DataSource.Factory<Int, Listing>)
            : LivePagedListBuilder<Int, Listing> {

        return LivePagedListBuilder<Int, Listing>(dataSourceFactory, config).setInitialLoadKey(0)
    }

    private fun loadNetworkState(dataSource : ListingDataSource) : MutableLiveData<String> {
        return dataSource.progressStatus()
    }

}