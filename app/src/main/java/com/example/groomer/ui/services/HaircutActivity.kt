package com.example.groomer.ui.services

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.groomer.R
import com.example.groomer.ui.payment.PaymentActivity
import kotlinx.android.synthetic.main.form_basic.view.*
import java.util.*

class HaircutActivity : AppCompatActivity() {

    private lateinit var pickUpDateBtn: Button
    private lateinit var bookBtn: Button
    private lateinit var pickUpDateText: TextView
    private lateinit var petName: EditText
    private lateinit var pickUpLoc: EditText
    private lateinit var petType: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_haircut)

        pickUpDateBtn = findViewById(R.id.pickup_date_btn)
        pickUpDateText = findViewById(R.id.pickup_date_txt)
        bookBtn = findViewById(R.id.btn_book)
        petName = findViewById(R.id.pet_name)
        petType = findViewById(R.id.pet_type)
        pickUpLoc = findViewById(R.id.pickup_loc)

        var dateTime = ""
        val c = Calendar.getInstance()
        val startYear = c.get(Calendar.YEAR)
        val startMonth = c.get(Calendar.MONTH)
        val startDay = c.get(Calendar.DAY_OF_MONTH)
        val startHour = c.get(Calendar.HOUR_OF_DAY)
        val startMinute = c.get(Calendar.MINUTE)

        pickUpDateBtn.setOnClickListener({
            DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, year, month, day ->
                TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                    dateTime = "" + day.toString() + "/" + month.toString() +
                            "/" + year.toString() +
                            " at " + hour.toString() + ":" + minute.toString()
                    pickUpDateText.text = dateTime
                }, startHour, startMinute, true).show()
            }, startYear, startMonth, startDay).show()
        })



        bookBtn.setOnClickListener(){
            val intent = Intent(this@HaircutActivity, PaymentActivity::class.java).apply {
                putExtra("pet_name", petName.text.toString())
                putExtra("pickup_time", dateTime)
                putExtra("pickup_loc", pickUpLoc.text.toString())
                putExtra("pet_type", petType.text.toString())
                putExtra("service_type", "haircut")
            }
            startActivity(intent)

        }
    }

}
