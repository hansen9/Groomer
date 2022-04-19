package com.example.groomer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.groomer.adapter.ReviewAdapter
import com.example.groomer.database.ReviewHelper
import com.example.groomer.entity.Review
import com.example.groomer.helper.MappingHelper
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_all_review.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class AllReviewActivity : AppCompatActivity() {
    private lateinit var adapter: ReviewAdapter
    private lateinit var reviewHelper: ReviewHelper

    companion object {
        private const val EXTRA_STATE = "EXTRA_STATE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_review)

        supportActionBar?.title = "All Review Customers"
        rv_reviews.layoutManager = LinearLayoutManager(this)
        rv_reviews.setHasFixedSize(true)
        adapter =  ReviewAdapter(this)
        rv_reviews.adapter = adapter

        fab_add.setOnClickListener{
            val intent = Intent(this@AllReviewActivity, ReviewAddUpdateActivity::class.java)
            startActivityForResult(intent, ReviewAddUpdateActivity.REQUEST_ADD)
        }

        reviewHelper = ReviewHelper.getInstance(applicationContext)
        reviewHelper.open()

        if (savedInstanceState == null) {
            loadReviewsAsync()
        } else {
            val list = savedInstanceState.getParcelableArrayList<Review>(EXTRA_STATE)
            if (list != null) {
                adapter.listReviews = list
            }
        }
    }

    private fun loadReviewsAsync() {
        GlobalScope.launch(Dispatchers.Main){
            progressbar.visibility = View.VISIBLE
            val deferredNotes = async(Dispatchers.IO) {
                val cursor = reviewHelper.queryAll()
                MappingHelper.mapCursorToArrayList(cursor)
            }
            progressbar.visibility = View.INVISIBLE
            val reviews = deferredNotes.await()
            if (reviews.size > 0) {
                adapter.listReviews = reviews
            } else {
                adapter.listReviews = ArrayList()
                showSnackbakMessage("Tidak ada data saat ini")
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(EXTRA_STATE, adapter.listReviews)
    }

    override fun onActivityResult(requestCode: Int , resultCode: Int , data: Intent?) {
        super.onActivityResult(requestCode , resultCode , data)
        if (data != null){
            when(requestCode){
                ReviewAddUpdateActivity.REQUEST_ADD -> if (resultCode == ReviewAddUpdateActivity.RESULT_ADD)
                {
                    val review = data.getParcelableExtra<Review>(ReviewAddUpdateActivity.EXTRA_NOTE)
                    rv_reviews.smoothScrollToPosition(adapter.itemCount - 1)
                    showSnackbakMessage("Review berhasil ditambahkan")
                }
                ReviewAddUpdateActivity.REQUEST_UPDATE -> when(requestCode){
                    ReviewAddUpdateActivity.RESULT_UPDATE -> {
                        val review = data.getParcelableExtra<Review>(ReviewAddUpdateActivity.EXTRA_NOTE)
                        val position = data.getIntExtra(ReviewAddUpdateActivity.EXTRA_POSITION, 0)
                        if (review != null) {
                            adapter.updateItem(position, review)
                        }
                        rv_reviews.smoothScrollToPosition(position)
                        showSnackbakMessage("Review berhasil di ubah")
                    }
                    ReviewAddUpdateActivity.RESULT_DELETE -> {
                        val position =  data.getIntExtra(ReviewAddUpdateActivity.EXTRA_POSITION, 0)
                        adapter.removedItem(position)
                        showSnackbakMessage("Review berhasil dihapus")
                    }
                }

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        reviewHelper.close()
    }

    private fun showSnackbakMessage(message: String) {
        Snackbar.make(rv_reviews, message, Snackbar.LENGTH_SHORT).show()
    }
}