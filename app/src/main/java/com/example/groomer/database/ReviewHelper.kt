package com.example.groomer.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.groomer.database.DatabaseContract.ReviewColumns.Companion.TABLE_NAME
import com.example.groomer.database.DatabaseContract.ReviewColumns.Companion._ID
import java.sql.SQLException

class ReviewHelper (context: Context) {
    private var databaseHelper: DatabaseHelper = DatabaseHelper(context)
    private lateinit var db : SQLiteDatabase

    companion object{
        private const val DATABASE_TABLE = TABLE_NAME
        private var INSTANCE: ReviewHelper? = null

        fun getInstance(context: Context) : ReviewHelper = INSTANCE?: synchronized(this){
            INSTANCE?: ReviewHelper(context)
        }
    }

    @Throws(SQLException::class)
    fun open(){
        db = databaseHelper.writableDatabase
    }

    fun close(){
        databaseHelper.close()

        if (db.isOpen)
            db.close()
    }

    fun queryAll(): Cursor{
        return db.query(
            DATABASE_TABLE,
            null,
            null,
            null,
            null,
            null,
            null,
            "$_ID ASC"
        )
    }

    fun insert(values: ContentValues?): Long {
        return db.insert(DATABASE_TABLE, null, values)
    }

    fun update(id: String, values: ContentValues?): Int{
        return db.update(DATABASE_TABLE, values, "$_ID =?", arrayOf(id))
    }

    fun deleteById(id: String): Int {
        return db.delete(DATABASE_TABLE, "$_ID = '$id'", null)
    }
}