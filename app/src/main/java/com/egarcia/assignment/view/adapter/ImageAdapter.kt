package com.egarcia.assignment.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.egarcia.assignment.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.image_list_item.view.*

/**
 * Adapter for the horizontal scrollable preview of listing images.
 */
class ImageAdapter: RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    private lateinit var mImages : List<String>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.image_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return if (::mImages.isInitialized) mImages.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mImages[position])
    }

    fun update(listings : List<String>) {
        mImages = listings
        notifyDataSetChanged()
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(listing: String) {
            Picasso.with(itemView.context).load(listing).into(itemView.image_preview)
        }
    }

}