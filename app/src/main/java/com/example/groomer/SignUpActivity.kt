package com.example.groomer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.groomer.entity.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() , View.OnClickListener {

    private lateinit var userName: EditText
    private lateinit var userEmail: EditText
    private lateinit var userPassword: EditText
    private lateinit var mAuth: FirebaseAuth
    private lateinit var userDB: DatabaseReference
    private lateinit var btnJoin: Button

    companion object{
        private const val TAG = "EmailPassword"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        userDB = FirebaseDatabase.getInstance(
            "https://groomer-ead4a-default-rtdb.asia-southeast1.firebasedatabase.app/"
        ).getReference("users")
        mAuth = Firebase.auth

        val currentUser = mAuth.currentUser
        userName = findViewById(R.id.edit_username)
        userEmail = findViewById(R.id.edit_email)
        userPassword = findViewById(R.id.edit_password)

        btnJoin = findViewById(R.id.btn_join)
        btnJoin.setOnClickListener(this)

        val btnMoveActivity: Button = findViewById(R.id.btn_sign_in)
        btnMoveActivity.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_sign_in ->{
                val moveIntent = Intent(this@SignUpActivity, SignInActivity::class.java)
                startActivity(moveIntent)
            }
            R.id.btn_join ->{
                signUp()
            }
        }

    }

    private fun signUp() {
        val email = userEmail.getText().toString().trim()
        val password = userPassword.getText().toString().trim()
        val name = userName.getText().toString().trim()

        if(name.isEmpty()){
            userName.setError("name is required")
            userName.requestFocus()
        }

        if(email.isEmpty()){
            userEmail.setError("email is required")
            userEmail.requestFocus()
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            userEmail.setError("please provide email")
            userEmail.requestFocus()
        }

        if(password.isEmpty()){
            userPassword.setError("password is required")
            userPassword.requestFocus()
        }

        if(password.length < 6){
            userPassword.setError("min length should be 6 characters")
            userPassword.requestFocus()
        }

        mAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){task->
                if(task.isSuccessful()){
                    val  user = User(name,email)
                    val currUser = mAuth.currentUser
                    Log.d(TAG, "createUserWithEmail:success")
                    if (currUser != null) {
                        userDB.child(currUser.uid.toString()).setValue(user)
                    }
//                    ProgressBar.setVisibility(View.GONE)
                }else{
                    Log.w(TAG, "createUserWithEmail:Failure", task.exception)
                    Toast.makeText(
                        this@SignUpActivity,
                        "failed to register! Try Again!",
                        Toast.LENGTH_LONG
                    ).show()
//                    ProgressBar.setVisibility(View.GONE)
                }
            }
    }

}

