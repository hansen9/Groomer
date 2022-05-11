package com.example.groomer.ui.review

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.groomer.R
import com.example.groomer.databinding.FragmentAllReviewBinding
import com.example.groomer.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.item_review.*

class AllReviewFragment : Fragment() {
    private lateinit var databaseReference: DatabaseReference
    private lateinit var mAuth : FirebaseAuth
    private var _binding: FragmentAllReviewBinding? = null

    companion object {
        fun newInstance() = AllReviewFragment()
    }

    private lateinit var viewModel: AllReviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        databaseReference = FirebaseDatabase.getInstance(
            "https://groomer-ead4a-default-rtdb.asia-southeast1.firebasedatabase.app/"
        ).getReference("transactions")

        mAuth = Firebase.auth
        val binding = FragmentAllReviewBinding.inflate(inflater, container, false)
        _binding = binding

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AllReviewViewModel::class.java)
        // TODO: Use the ViewModel

        databaseReference = FirebaseDatabase.getInstance("https://groomer-ead4a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("reviews")
        val review = FirebaseAuth.getInstance().currentUser
        val uid = review!!.uid

        databaseReference.child(uid).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
//                tv_item_pemilik?.text = snapshot.child("username").value.toString()
//                tv_item_komentar.text = snapshot.child("comment").value.toString()
                  tv_item_pemilik.text = "femi"
                  tv_item_komentar.text = "terimakasih groomer app"
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


    }

}