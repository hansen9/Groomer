package com.example.groomer.ui.payment

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.groomer.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.example.groomer.entity.Transaction
import com.example.groomer.databinding.ActivityPaymentBinding
import com.google.firebase.database.*

class PaymentActivity : AppCompatActivity(), ValueEventListener {
    private var _binding: ActivityPaymentBinding? = null
    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private var petName: String? = ""
    private var pickupLoc: String? = ""
    private var pickupTime: String? = ""
    private var petType: String? = ""
    private var serviceType: String? = ""
    private var name: String? = ""
    private lateinit var petNamePay: TextView
    private lateinit var petTypePay: TextView
    private lateinit var pickupLocPay: TextView
    private lateinit var pickupDatePay: TextView
    private lateinit var priceText: TextView
    private lateinit var payBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        database = FirebaseDatabase.getInstance(
            "https://groomer-ead4a-default-rtdb.asia-southeast1.firebasedatabase.app/"
        ).getReference("transactions")

        mAuth = Firebase.auth

        var price = 0

        petName = intent.getStringExtra("pet_name")
        pickupLoc = intent.getStringExtra("pickup_loc")
        pickupTime = intent.getStringExtra("pickup_time")
        petType = intent.getStringExtra("pet_type")
        serviceType = intent.getStringExtra("service_type")

        petNamePay = findViewById(R.id.pet_name_pay)
        petTypePay = findViewById(R.id.pet_type_pay)
        pickupDatePay = findViewById(R.id.pickup_time_pay)
        pickupLocPay = findViewById(R.id.pickup_loc_pay)
        priceText = findViewById(R.id.price)
        payBtn = findViewById(R.id.pay)

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
        }

        petNamePay.text = "" + petNamePay.text + " : " + petName
        pickupLocPay.text = "" + pickupLocPay.text + " : " + pickupLoc
        pickupDatePay.text = "" + pickupDatePay.text + " : " + pickupTime
        petTypePay.text = "" + petTypePay.text + " : " + petType
        priceText.text = "" + priceText.text + " : " + price.toString()

        payBtn.setOnClickListener(){
            bookService(
                petName.toString(),
                pickupLoc.toString(),
                pickupTime.toString(),
                petType.toString(),
                serviceType.toString(),
                price.toString()
            )
        }
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
                id  = database.push().getKey().toString()

                database.child(id).setValue(transaction)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    this@PaymentActivity,
                    "something's wrong! Try Again!",
                    Toast.LENGTH_LONG
                ).show()
            }
        })


    }

    override fun onDataChange(snapshot: DataSnapshot) {
    }

    override fun onCancelled(error: DatabaseError) {
    }


}