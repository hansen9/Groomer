package com.example.groomer.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Customer(
    var id: Int = 0,
    var nama: String? =null,
    var email: String? = null,
    var password :String? = null,
    var no_telp : String? = null
) : Parcelable