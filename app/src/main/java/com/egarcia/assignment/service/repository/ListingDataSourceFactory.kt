package com.egarcia.assignment.service.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.egarcia.assignment.service.model.Listing

class ListingDataSourceFactory : DataSource.Factory<Int, Listing>() {

    val mDataSource = MutableLiveData<ListingDataSource>()

    override fun create(): DataSource<Int, Listing> {
        val dataSource = ListingDataSource()
        mDataSource.postValue(dataSource)
        return dataSource
    }
}
