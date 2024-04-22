package com.stu74538.onlineshopingapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Product (
    val id: Int? = null,
    val img: String? = null,
    val name: String? = null,
    val price: Double? = null,
    val category: String? = null
): Parcelable

data class User(
    val email: String? = null,
    val addressNumber: Int? = null,
    val city: String? = null,
    val firstName: String? = null,
    val geolocationLat: Double? = null,
    val geolocationLong: Double? = null,
    val img: String? = null,
    val lastName: String? = null,
    val phone: String? = null,
    val street: String? = null,
    val zipcode: Int? = null
)

@Parcelize
class CartItem (
    val productId: Int? = null,
    val img: String? = null,
    val name: String? = null,
    val price: Double? = null,
    var numberOfItem: Int = 0,
): Parcelable