package com.example.groomer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class SignUpActivity : AppCompatActivity() , View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val btnMoveActivity: Button = findViewById(R.id.btn_sign_in)
        btnMoveActivity.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_sign_in ->{
                val moveIntent = Intent(this@SignUpActivity, SignInActivity::class.java)
                startActivity(moveIntent)
            }
        }
    }

}

