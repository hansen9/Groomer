package com.example.groomer.database

import android.provider.BaseColumns

internal class DatabaseContract {
    internal class ReviewColumns : BaseColumns{
        companion object{
            const val TABLE_NAME = "review"
            const val _ID = "_id"
            const val Pemilik = "pemilik"
            const val Jenis = "jenis"
            const val Treatment = "treatment"
            const val Komentar = "komentar"
        }
    }

    internal class CustomersColumns : BaseColumns{
        companion object{
            const val _ID = "_id"
            const val Name = "name"
            const val Email = "email"
            const val Password = "password"
            const val Number = "no_telp"
        }
    }


}