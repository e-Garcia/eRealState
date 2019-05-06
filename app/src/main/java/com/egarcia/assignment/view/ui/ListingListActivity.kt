package com.egarcia.assignment.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.egarcia.assignment.R

import com.egarcia.assignment.view.adapter.ListingAdapter
import com.egarcia.assignment.viewmodel.ListingViewModel
import kotlinx.android.synthetic.main.activity_house_list.*
import kotlinx.android.synthetic.main.house_list.*

/**
 * Displays a list of Listings
 */
class ListingListActivity : AppCompatActivity() {

    private lateinit var mViewModel : ListingViewModel
    private lateinit var mAdapter : ListingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_house_list)
        setSupportActionBar(toolbar)
        toolbar.title = title

        mViewModel = ViewModelProviders.of(this).get(ListingViewModel::class.java)
        setupRecyclerView(house_list)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {

        mAdapter = ListingAdapter()
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val listingsLiveData = mViewModel.paginatedListings()

        listingsLiveData.observe(this, Observer { pagedList ->
            mAdapter.submitList(pagedList)
        })

    }



}
