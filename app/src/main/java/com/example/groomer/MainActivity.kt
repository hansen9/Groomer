package com.example.groomer

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.groomer.databinding.ActivityMainBinding
import com.example.groomer.ui.home.HomeFragment
import com.example.groomer.ui.profile.ProfileFragment
import com.example.groomer.ui.review.AllReviewFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firstFragment = HomeFragment()
        val secondFragment =  AllReviewFragment()
        val thirdFragment = ProfileFragment()

        setCurrentFragment(firstFragment)
        val mFragmentManager = supportFragmentManager
        val mHomeFragment = HomeFragment()
        val fragment = mFragmentManager.findFragmentByTag(HomeFragment::class.java.simpleName)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.home -> {
                    setCurrentFragment(firstFragment)
                    mFragmentManager.beginTransaction().add(R.id.container, mHomeFragment, HomeFragment::class.java.simpleName).commit()
                }
                R.id.preview->setCurrentFragment(secondFragment)
                R.id.account->setCurrentFragment(thirdFragment)
            }
            true
        }

//        val mFragmentManager = supportFragmentManager
//        val mHomeFragment = HomeFragment()
//        val fragment = mFragmentManager.findFragmentByTag(HomeFragment::class.java.simpleName)
//
//        if (fragment !is HomeFragment) {
//            Log.d("MyFlexibleFragment", "Fragment Name :" + HomeFragment::class.java.simpleName)
//            mFragmentManager.beginTransaction().add(R.id.container, mHomeFragment, HomeFragment::class.java.simpleName).commit()
//        }
    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply{
            replace(R.id.flFragment, fragment)
            commit()
        }

}