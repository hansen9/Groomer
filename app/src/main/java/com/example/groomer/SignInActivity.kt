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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInActivity : AppCompatActivity() , View.OnClickListener {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var userNameEdit: EditText
    private lateinit var passwordEdit: EditText

    companion object {
        private const val TAG = "EmailPassword"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        mAuth = Firebase.auth
        userNameEdit = findViewById(R.id.edit_username)
        passwordEdit = findViewById(R.id.edit_password)

        val btnMoveActivity: Button = findViewById(R.id.btn_sign_up)
        btnMoveActivity.setOnClickListener(this)

        val btnMoveActivityMenu: Button = findViewById(R.id.btn_join)
        btnMoveActivityMenu.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_sign_up ->{
                userLogin()
                val moveIntent = Intent(this@SignInActivity, SignUpActivity::class.java)
                startActivity(moveIntent)
            }
            R.id.btn_join ->{
                userLogin()

            }
        }
    }
    private fun userLogin() {
        val email = userNameEdit.getText().toString().trim()
        val password = passwordEdit.getText().toString().trim()

        if(email.isEmpty()){
            userNameEdit.setError("Email is required")
            userNameEdit.requestFocus()
        }

        if(password.isEmpty()){
            passwordEdit.setError("Password is required")
            passwordEdit.requestFocus()
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            userNameEdit.setError("Please enter a valid email")
            userNameEdit.requestFocus()
        }

//        progressBar.setVisibility(View.VISIBLE)

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){ task->
                if (task.isSuccessful){
                    Log.d(TAG, "logIn success")
                    val user = mAuth.currentUser
                    val moveIntentMainActivity = Intent(this@SignInActivity, MainActivity::class.java)
                    startActivity(moveIntentMainActivity)
                }else{
                    Log.w(TAG, "login failed", task.exception)
                    Toast.makeText(baseContext, "authentication failed",
                        Toast.LENGTH_SHORT).show()
                }
            }


    }
}