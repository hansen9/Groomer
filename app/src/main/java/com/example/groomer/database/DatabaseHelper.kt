package com.example.groomer.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.groomer.database.DatabaseContract.ReviewColumns.Companion.TABLE_NAME

internal class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        private const val DATABASE_NAME = "dbgroomer"
        private const val DATABASE_VERSION = 1
        private val SQL_CREATE_TABLE_NOTE = "CREATE TABLE $TABLE_NAME" +
                "(${DatabaseContract.ReviewColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${DatabaseContract.ReviewColumns.Pemilik} TEXT NOT NULL," +
                "${DatabaseContract.ReviewColumns.Jenis} TEXT NOT NULL," +
                "${DatabaseContract.ReviewColumns.Treatment} TEXT NOT NULL," +
                "${DatabaseContract.ReviewColumns.Komentar} TEXT NOT NULL)"
    }

    override fun onCreate(database: SQLiteDatabase) {
        database.execSQL(SQL_CREATE_TABLE_NOTE)
    }

    override fun onUpgrade(database: SQLiteDatabase , oldVersion: Int, newVersion: Int) {
        database.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(database)
    }
}