package com.example.groomer.ui.form

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.groomer.R
import com.example.groomer.Review
import com.example.groomer.Transaction
import com.example.groomer.databinding.FragmentFormReviewBinding
import com.example.groomer.databinding.FragmentNotificationsBinding
import com.example.groomer.ui.notifications.NotificationsViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class FormReviewFragment : Fragment() {

    private lateinit var formreviewViewModel: FormReviewFragment
    private var _binding: FragmentFormReviewBinding? = null
//    private var _binding: FragmentNotificationsBinding? = null
//    private val binding get() = _binding!!
    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private var name: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        database = FirebaseDatabase.getInstance(
            "https://groomer-ead4a-default-rtdb.asia-southeast1.firebasedatabase.app/"
        ).getReference("reviews")

        mAuth = Firebase.auth
        val binding = FragmentFormReviewBinding.inflate(inflater, container, false)
        _binding = binding

        val user = FirebaseAuth.getInstance().currentUser
        val reference = FirebaseDatabase.getInstance(
            "https://groomer-ead4a-default-rtdb.asia-southeast1.firebasedatabase.app/"
        ).getReference("users")
        val uid = user?.uid.toString()

        reference.child(uid).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                name = snapshot.child("name").getValue().toString()
                val review = Review(
                    name.toString(),
                    binding.jenis.text.toString(),
                    binding.treatment.text.toString(),
                    binding.comment.text.toString()
                )

                val id: String
//                id  = database.push().getKey().toString()
//
//                database.child(id).setValue(transaction)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}