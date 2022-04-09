package com.example.groomer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.FragmentManager
import com.example.groomer.ui.home.HomeFragment
import com.example.groomer.ui.services.completeCareFragment

class SignInActivity : AppCompatActivity() , View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val btnMoveActivity: Button = findViewById(R.id.btn_sign_up)
        btnMoveActivity.setOnClickListener(this)

        val btnMoveActivityMenu: Button = findViewById(R.id.btn_join)
        btnMoveActivityMenu.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_sign_up ->{
                val moveIntent = Intent(this@SignInActivity, SignUpActivity::class.java)
                startActivity(moveIntent)
            }
            R.id.btn_join ->{
                val moveIntentMainActivity = Intent(this@SignInActivity, MainActivity::class.java)
                startActivity(moveIntentMainActivity)
            }
        }
    }
}