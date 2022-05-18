package com.example.groomer.helper

import android.database.Cursor
import com.example.groomer.database.DatabaseContract
import com.example.groomer.entity.Review

object MappingHelper {
    fun mapCursorToArrayList(reviewsCursor: Cursor?): ArrayList<Review>{
        val reviewList = ArrayList<Review>()
        reviewsCursor?.apply {
            while (moveToNext()){
                val nama = getString(getColumnIndexOrThrow(DatabaseContract.ReviewColumns.Pemilik))
                val komentar = getString(getColumnIndexOrThrow(DatabaseContract.ReviewColumns.Komentar))
            }
        }
        return reviewList
    }
}