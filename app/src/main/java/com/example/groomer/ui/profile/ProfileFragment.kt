package com.example.groomer.ui.profile

import android.content.SharedPreferences
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.groomer.User
import com.example.groomer.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment() : Fragment() {
    private lateinit var database: DatabaseReference
    private lateinit var mAuth: FirebaseAuth
    private var _binding: FragmentProfileBinding? = null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        database = FirebaseDatabase.getInstance(
            "https://groomer-ead4a-default-rtdb.asia-southeast1.firebasedatabase.app/"
        ).getReference("transactions")

        mAuth = Firebase.auth
        val binding = FragmentProfileBinding.inflate(inflater, container, false)
        _binding = binding

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        database = FirebaseDatabase.getInstance("https://groomer-ead4a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("users")
        val user = FirebaseAuth.getInstance().currentUser
        val uid = user!!.uid

        database.child(uid).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                txt_nama?.text = snapshot.child("name").value.toString()
                txt_email?.text = snapshot.child("email").value.toString()
//                txt_nama.text = "wiiiii"
//                txt_email?.text = "wiiii"
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

}
