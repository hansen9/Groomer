package com.example.groomer.ui.review

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.groomer.adapter.ReviewAdapter
import com.example.groomer.databinding.ActivityShowReviewBinding
import com.example.groomer.entity.Review
import com.google.firebase.database.*

class ShowReviewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShowReviewBinding
    private val listReview = ArrayList<Review>()
    private var title = "reviews"
    private lateinit var databaseReference: DatabaseReference
    companion object{
        private const val STATE_TITLE = "state_string"
        private const val STATE_LIST = "state_list"
        private const val STATE_MODE = "state_mode"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShowReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvReviews.setHasFixedSize(true)
        if (savedInstanceState == null){
            setActionBarTitle(title)
            listReview.addAll(listReview)
            showRecyclerList()
        }

        databaseReference = FirebaseDatabase.getInstance(
            "https://groomer-ead4a-default-rtdb.asia-southeast1.firebasedatabase.app/"
        ).getReference("reviews")

        databaseReference.addValueEventListener(object : ValueEventListener {
            val listofReview = ArrayList<Review>()
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot!!.exists()){
                    for (rev in snapshot.children){
                        val review = rev.getValue(Review::class.java)
                        Log.w("the rev is ",rev.toString())
                        listofReview.add(review!!)
                    }
                    listReview.addAll(listofReview)
                    showRecyclerList()
                    setActionBarTitle(title)
                }
            }
            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
            }

        })

    }

    private fun setActionBarTitle(title: String?) {
        supportActionBar?.title = title
    }
    private fun showRecyclerList() {
        binding.rvReviews.layoutManager = LinearLayoutManager(this)
        val reviewAdapter = ReviewAdapter(listReview)
        binding.rvReviews.adapter = reviewAdapter
    }


}