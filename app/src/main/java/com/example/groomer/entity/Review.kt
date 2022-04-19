package com.example.groomer.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Review(
    var id: Int = 0,
    var nama_pemilik: String? =null,
    var jenis_hewan: String? = null,
    var jenis_treatment :String? = null,
    var komentar : String? = null
) : Parcelable