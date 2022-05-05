package com.example.groomer.ui.services

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.groomer.Communicator
import com.example.groomer.databinding.FragmentNailTrimBinding
import kotlinx.android.synthetic.main.form_basic.view.*
import java.text.SimpleDateFormat
import java.util.*

class NailTrimFragment : Fragment() {

    private lateinit var comm: Communicator
    private var nailBinding: FragmentNailTrimBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentNailTrimBinding.inflate(inflater, container, false)
        nailBinding = binding

        var dateTime = ""
        val c = Calendar.getInstance()
        val startYear = c.get(Calendar.YEAR)
        val startMonth = c.get(Calendar.MONTH)
        val startDay = c.get(Calendar.DAY_OF_MONTH)
        val startHour = c.get(Calendar.HOUR_OF_DAY)
        val startMinute = c.get(Calendar.MINUTE)

        binding.nailTrimLayout.formBasic.pickup_date_btn.setOnClickListener({
            DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { _, year, month, day ->
                TimePickerDialog(requireContext(), TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                    dateTime = "" + day.toString() + "/" + month.toString() + "/" + year.toString() + " at " + hour.toString() + ":" + minute.toString()
                    binding.nailTrimLayout.formBasic.pickup_date_txt.text = dateTime
                }, startHour, startMinute, true).show()
            }, startYear, startMonth, startDay).show()

        })

        comm = requireActivity() as Communicator
        val id = binding.nailTrimLayout
        binding.nailTrimLayout.formBasic.btn_book.setOnClickListener(){
            comm.passDataCom(
                binding.nailTrimLayout.formBasic.pet_name.text.toString(),
                binding.nailTrimLayout.formBasic.pickup_loc.text.toString(),
                dateTime,
                binding.nailTrimLayout.formBasic.pet_type.text.toString(),
                binding.nailTrim.id.toString()
            )
        }
        return binding.root
    }

}