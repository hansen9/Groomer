package com.example.groomer.ui.payment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.example.groomer.databinding.FragmentPaymentBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.example.groomer.Transaction
import com.example.groomer.User
import com.google.firebase.database.*

class PaymentFragment : Fragment(), ValueEventListener {
    private var _binding: FragmentPaymentBinding? = null
    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private var petName: String? = ""
    private var pickupLoc: String? = ""
    private var pickupTime: String? = ""
    private var petType: String? = ""
    private var serviceType: String? = ""
    private var name: String? = ""
    private var servicesPicked = arrayListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        database = FirebaseDatabase.getInstance(
            "https://groomer-ead4a-default-rtdb.asia-southeast1.firebasedatabase.app/"
        ).getReference("transactions")

        mAuth = Firebase.auth

        val binding = FragmentPaymentBinding.inflate(inflater, container, false)
        _binding = binding

        var price = 0

        petName = arguments?.getString("pet_name")
        pickupLoc = arguments?.getString("pickup_loc")
        pickupTime = arguments?.getString("pickup_time")
        petType = arguments?.getString("pet_type")
        serviceType = arguments?.getString("service_type")

        when(serviceType){
            "haircut" ->{
                price = 24000
            }
            "nail trimming" ->{
                price = 27500
            }
            "ear cleaning" ->{
                price = 30000
            }
            "bathing" ->{
                price = 32000
            }
            "complete care" ->{

                servicesPicked = arguments?.getStringArrayList("services_picked") as ArrayList<String>
                for (i in servicesPicked!!){
                    if (i == "haircut"){
                        price += 24000
                    } else if(i == "bathing"){
                        price += 32000
                    }else if(i == "nail trimming"){
                        price += 27500
                    }else if(i == "ear cleaning"){
                        price += 30000
                    }
                }

            }
        }

        binding.petNamePay.text = "" + binding.petNamePay.text + " : " + petName
        binding.pickupLocPay.text = "" + binding.pickupLocPay.text + " : " + pickupLoc
        binding.pickupTimePay.text = "" + binding.pickupTimePay.text + " : " + pickupTime
        binding.petTypePay.text = "" + binding.petTypePay.text + " : " + petType
        binding.price.text = "" + binding.price.text + " : " + price.toString()

        binding.pay.setOnClickListener(){
            Log.w("servicesPicked Payment",servicesPicked.toString())
            bookService(
                petName.toString(),
                pickupLoc.toString(),
                pickupTime.toString(),
                petType.toString(),
                serviceType.toString(),
                price.toString()
            )
        }
        return binding.root
    }

    private fun bookService(
        petName: String,
        pickupLoc: String,
        pickupTime: String,
        petType: String,
        serviceType: String,
        price: String
    ) {
        val user = FirebaseAuth.getInstance().currentUser
        val reference = FirebaseDatabase.getInstance(
            "https://groomer-ead4a-default-rtdb.asia-southeast1.firebasedatabase.app/"
        ).getReference("users")
        val uid = user?.uid.toString()

        reference.child(uid).addListenerForSingleValueEvent(object :  ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                name = snapshot.child("name").getValue().toString()
                val transaction = Transaction(
                    name.toString(),
                    petName,
                    pickupLoc,
                    pickupTime,
                    petType,
                    serviceType,
                    price
                )
                val id: String
//                id  = database.push().getKey().toString()
//
//                database.child(id).setValue(transaction)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })


    }

    override fun onDataChange(snapshot: DataSnapshot) {
    }

    override fun onCancelled(error: DatabaseError) {
        TODO("Not yet implemented")
    }


}