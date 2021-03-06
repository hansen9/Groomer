package com.example.groomer.ui.review

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.groomer.databinding.FragmentAllReviewBinding
import com.example.groomer.entity.Review
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class AllReviewFragment : Fragment() {
    private lateinit var databaseReference: DatabaseReference
    private lateinit var mAuth : FirebaseAuth
    private var _binding: FragmentAllReviewBinding? = null
    private lateinit var listReview: ArrayList<Review>


    private lateinit var viewModel: AllReviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
//        listReview = arrayListOf<Review>()
//        databaseReference = FirebaseDatabase.getInstance(
//            "https://groomer-ead4a-default-rtdb.asia-southeast1.firebasedatabase.app/"
//        ).getReference("reviews")
//
//        mAuth = Firebase.auth
        val binding = FragmentAllReviewBinding.inflate(inflater, container, false)
//        _binding = binding
//
//        binding.rvReviews.setHasFixedSize(true)
//        binding.rvReviews.layoutManager = LinearLayoutManager(activity)
//        val adapter = ReviewAdapter()
//        binding.rvReviews.adapter = adapter

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AllReviewViewModel::class.java)
        // TODO: Use the ViewModel

        databaseReference = FirebaseDatabase.getInstance("https://groomer-ead4a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("reviews")
        val review = FirebaseAuth.getInstance().currentUser
        val uid = review!!.uid

    }


}