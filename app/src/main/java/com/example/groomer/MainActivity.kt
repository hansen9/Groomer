package com.example.groomer

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.groomer.databinding.ActivityMainBinding
import com.example.groomer.ui.home.HomeFragment
import com.example.groomer.ui.payment.PaymentFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), Communicator {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        val mFragmentManager = supportFragmentManager
//        val mHomeFragment = HomeFragment()
//        val fragment = mFragmentManager.findFragmentByTag(HomeFragment::class.java.simpleName)
//
//        if (fragment !is HomeFragment) {
//            Log.d("MyFlexibleFragment", "Fragment Name :" + HomeFragment::class.java.simpleName)
//            mFragmentManager
//                .beginTransaction()
//                .add(R.id.container, mHomeFragment,
//                    HomeFragment::class.java.simpleName)
//                .commit()
//        }

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