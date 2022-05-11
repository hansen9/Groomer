package com.example.groomer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.groomer.databinding.ActivityMainBinding
import com.example.groomer.ui.home.HomeFragment
import com.example.groomer.ui.payment.PaymentFragment
import com.example.groomer.ui.profile.ProfileFragment
import com.example.groomer.ui.review.AllReviewFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.form_basic.*
import kotlinx.android.synthetic.main.fragment_home.*
import com.example.groomer.ButtonMenu

class MainActivity : AppCompatActivity(), Communicator {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this, ButtonMenu::class.java)
        intent.putExtra("key", R.id.activity_bottom_menu)
        startActivity(intent)
    }

    override fun passDataCom(
        petName: String,
        pickupLoc: String,
        pickupTime: String,
        petType: String,
        serviceType: String
    ) {
        val bundle = Bundle()
        bundle.putString("pet_name", petName)
        bundle.putString("pickup_loc", pickupLoc)
        bundle.putString("pickup_time", pickupTime)
        bundle.putString("pet_type", petType)

        when(serviceType){
            R.id.haircut.toString() ->{
                bundle.putString("service_type", "haircut")
            }
            R.id.nail_trim.toString() ->{
                bundle.putString("service_type", "nail trimming")
            }
            R.id.ear_cleaning.toString() ->{
                bundle.putString("service_type", "ear cleaning")
            }
            R.id.bathing.toString() ->{
                bundle.putString("service_type", "bathing")
            }
        }

        val transaction = this.supportFragmentManager.beginTransaction()
        val paymentFragment = PaymentFragment()
        paymentFragment.arguments = bundle

        transaction.replace(R.id.container, paymentFragment)
        transaction.addToBackStack(null)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()
    }

    override fun passDataCom(
        petName: String,
        pickupLoc: String,
        pickupTime: String,
        petType: String,
        serviceType: String,
        services: ArrayList<String>,
    ) {
        val bundle = Bundle()
        bundle.putString("pet_name", petName)
        bundle.putString("pickup_loc", pickupLoc)
        bundle.putString("pickup_time", pickupTime)
        bundle.putString("pet_type", petType)
        bundle.putStringArrayList("services_picked", services)

        val transaction = this.supportFragmentManager.beginTransaction()
        val paymentFragment = PaymentFragment()
        paymentFragment.arguments = bundle

        transaction.replace(R.id.container, paymentFragment)
        transaction.addToBackStack(null)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()
    }

}
