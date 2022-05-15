package com.example.groomer.ui.form

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.groomer.Review
import com.example.groomer.databinding.FragmentFormReviewBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class FormReviewFragment : Fragment() {

    private lateinit var formreviewViewModel: FormReviewFragment
    private var _binding: FragmentFormReviewBinding? = null
    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private var name: String? = ""
    private var jenis: String? = ""
    private var treatment: String? = ""
    private var comment: String? = ""

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

        jenis = binding.jenis.text.toString()
        treatment = binding.treatment.text.toString()
        comment = binding.comment.text.toString()

        binding.post.setOnClickListener(){
            postReview(
                binding.jenis.text.toString(),
                binding.treatment.text.toString(),
                binding.comment.text.toString()
            )

            Log.w("jenis", binding.jenis.text.toString())
            Log.w("treatment", binding.treatment.text.toString())
            Log.w("comment", binding.comment.text.toString())

        }


        return binding.root
    }

    private fun postReview(
        jenisPet: String,
        treatment: String,
        comment: String
    ) {
        val user = FirebaseAuth.getInstance().currentUser
        val reference = FirebaseDatabase.getInstance(
            "https://groomer-ead4a-default-rtdb.asia-southeast1.firebasedatabase.app/"
        ).getReference("users")
        val uid = user?.uid.toString()
        Log.w("jenis", jenisPet)
        Log.w("treatment", treatment)
        Log.w("comment", comment)

        reference.child(uid).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                name = snapshot.child("name").getValue().toString()
                val review = Review(
                    name.toString(),
                    jenisPet,
                    treatment,
                    comment
                )


                val id: String
                id  = database.push().getKey().toString()

                database.child(id).setValue(review)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    activity,
                    "failed to register! Try Again!",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}