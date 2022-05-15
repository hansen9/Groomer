package com.example.groomer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.groomer.ui.home.HomeFragment
import com.example.groomer.ui.payment.PaymentFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class ButtonMenu : AppCompatActivity(), Communicator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button_menu)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.navigation_home, R.id.navigation_review, R.id.navigation_profile
        ).build()
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

    override fun passDataCom(
        petName: String,
        pickupLoc: String,
        pickupTime: String,
        petType: String,
        serviceType: String,
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

        transaction.replace(R.id.nav_host_fragment, paymentFragment)
        transaction.addToBackStack(null)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()
    }

}