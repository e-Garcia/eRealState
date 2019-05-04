package com.egarcia.assignment.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.egarcia.assignment.R
import com.egarcia.assignment.service.model.Listing
import com.egarcia.assignment.view.callback.ListingDiffCallback
import kotlinx.android.synthetic.main.listing_list_item.view.*

/**
 * Used to display Listing objects
 */
class ListingAdapter: PagedListAdapter<Listing, ListingAdapter.ViewHolder>(ListingDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.listing_list_item, parent, false)
        val imagesListAdapter = ImageAdapter()

        return ViewHolder(view, imagesListAdapter)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { item-> holder.bind(item) }
    }

    class ViewHolder(itemView: View, var imagesAdapter: ImageAdapter)
        : RecyclerView.ViewHolder(itemView) {

        fun bind(listing: Listing) {

            val resources = itemView.context.resources
            when (listing.listingType) {
                Listing.RENT_LISTING -> {
                    itemView.listing_type.text =
                        itemView.context.getText(R.string.listing_type_rent_header)
                }

                Listing.SALE_LISTING -> {
                    itemView.listing_type.text =
                            itemView.context.getText(R.string.listing_type_sale_header)
                }

                else -> {
                    itemView.listing_type.visibility = View.INVISIBLE
                }

            }
            itemView.price.text = resources.getString(R.string.price, listing.price)
            itemView.bedrooms.text = resources.getString(R.string.bedrooms, listing.bedrooms)
            itemView.bathrooms.text = resources.getString(R.string.bathrooms, listing.bathrooms)
            itemView.size.text = resources.getString(R.string.size, listing.squareFeet)
            itemView.address.text = resources.getString(R.string.street, listing.streetNumber, listing.streetName)
            itemView.city.text = resources.getString(R.string.city,listing.city, listing.stateCode)

            // Listing images horizontal RecyclerView:
            listing.photos?.let { imagesAdapter.update(listing.photos) }
            itemView.images_list.adapter = imagesAdapter
            itemView.images_list.layoutManager = LinearLayoutManager(itemView.context,
                    LinearLayoutManager.HORIZONTAL, false)
        }
    }
}