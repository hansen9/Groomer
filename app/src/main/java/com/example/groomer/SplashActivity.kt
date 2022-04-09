package com.example.groomer

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.groomer.databinding.ActivityMainBinding


class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Handler().postDelayed(Runnable {
            val intent = Intent(this@SplashActivity , SignUpActivity::class.java)
            startActivity(intent)
            finish()
        } , 1000)
    }
}