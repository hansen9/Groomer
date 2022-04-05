package com.example.groomer

import android.R
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.groomer.databinding.ActivityMainBinding
import com.example.groomer.ui.home.HomeFragment


class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val mFragmentManager = supportFragmentManager
        val mSplashActivity = SplashActivity()
        val fragment = mFragmentManager.findFragmentByTag(SplashActivity::class.java.simpleName)
        Handler().postDelayed(Runnable {
            val intent = Intent(this@SplashActivity , MainActivity::class.java)
            startActivity(intent)
            finish()
        } , 1000)
    }
}