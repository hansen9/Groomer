package com.example.groomer.ui.services

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentManager
import com.example.groomer.Communicator
import com.example.groomer.R
import com.example.groomer.databinding.FragmentEarCleanBinding
import com.example.groomer.databinding.FragmentNailTrimBinding
import com.example.groomer.ui.payment.PaymentFragment
import kotlinx.android.synthetic.main.form_basic.view.*
import java.text.SimpleDateFormat
import java.util.*

class EarCleanFragment : Fragment(){

    private lateinit var comm: Communicator
    private var earBinding: FragmentEarCleanBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEarCleanBinding.inflate(inflater, container, false)
        earBinding = binding

        var dateTime = ""
        val c = Calendar.getInstance()
        val startYear = c.get(Calendar.YEAR)
        val startMonth = c.get(Calendar.MONTH)
        val startDay = c.get(Calendar.DAY_OF_MONTH)
        val startHour = c.get(Calendar.HOUR_OF_DAY)
        val startMinute = c.get(Calendar.MINUTE)

        binding.earCleaningLayout.formBasic.pickup_date_btn.setOnClickListener({
            DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { _, year, month, day ->
                TimePickerDialog(requireContext(), TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                    dateTime = "" + day.toString() + "/" + month.toString() + "/" + year.toString() + " at " + hour.toString() + ":" + minute.toString()
                    binding.earCleaningLayout.formBasic.pickup_date_txt.text = dateTime
                }, startHour, startMinute, true).show()
            }, startYear, startMonth, startDay).show()

        })

        comm = requireActivity() as Communicator
        val id = binding.earCleaningLayout
        binding.earCleaningLayout.formBasic.btn_book.setOnClickListener(){
            comm.passDataCom(
                binding.earCleaningLayout.formBasic.pet_name.text.toString(),
                binding.earCleaningLayout.formBasic.pickup_loc.text.toString(),
                dateTime,
                binding.earCleaningLayout.formBasic.pet_type.text.toString(),
                binding.earCleaning.id.toString()
            )
        }
        return binding.root
    }

}