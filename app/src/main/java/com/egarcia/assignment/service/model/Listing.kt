package com.egarcia.assignment.service.model

/**
 * Class which provides a model for a listing
 * @constructor Sets all properties for this listing
 * @property id the unique identifier for this listing.
 * ...
 */
data class Listing(val id: String, val listingType: String, val price: Int, val bathrooms: Float,
                   val squareFeet: Int, val bedrooms: Int,
                   val latitude: Float, val longitude: Float,
                   val numberOfPhotos: Int, val photos: List<String>?,
                   val stateCode: String, val city:String, val streetName:String,
                   val streetNumber: String, val zipCode: String, val neighborhood: String) {

    companion object {
        const val SALE_LISTING = "for sale"
        const val RENT_LISTING = "for rent"
    }
}