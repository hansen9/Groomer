package com.example.groomer.ui.form

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.groomer.R
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.groomer.ButtonMenu
import com.example.groomer.entity.Review
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class FormReviewActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private var name: String? = ""
    private lateinit var jenis: EditText
    private lateinit var treatment: EditText
    private lateinit var comment: EditText
    private lateinit var btnPost: Button
    private lateinit var btnCancel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_review)
        database = FirebaseDatabase.getInstance(
            "https://groomer-ead4a-default-rtdb.asia-southeast1.firebasedatabase.app/"
        ).getReference("reviews")

        mAuth = Firebase.auth

        jenis = findViewById(R.id.jenis)
        treatment = findViewById(R.id.treatment)
        comment = findViewById(R.id.comment)
        btnPost = findViewById(R.id.post)
        btnCancel = findViewById(R.id.cancel)

        btnPost.setOnClickListener() {
            postReview(
                jenis.text.toString(),
                treatment.text.toString(),
                comment.text.toString()
            )
        }
        btnCancel.setOnClickListener {
            val intent = Intent(this@FormReviewActivity, ButtonMenu::class.java)
            startActivity(intent)
        }
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
                id = database.push().getKey().toString()

                database.child(id).setValue(review)

                Toast.makeText(
                    this@FormReviewActivity,
                    "review posted",
                    Toast.LENGTH_LONG
                ).show()
                val intent = Intent(this@FormReviewActivity, ButtonMenu::class.java)
                startActivity(intent)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    this@FormReviewActivity,
                    "post failed!",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }
}