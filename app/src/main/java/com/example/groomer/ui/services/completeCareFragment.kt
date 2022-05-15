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
import com.example.groomer.databinding.FragmentCompleteCareBinding
import com.example.groomer.databinding.FragmentHaircutBinding
import com.example.groomer.ui.home.CompleteCareModel
import com.example.groomer.ui.payment.PaymentFragment
import kotlinx.android.synthetic.main.form_basic.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class completeCareFragment : Fragment() {

    private lateinit var comm: Communicator
    private var ccBinding: FragmentCompleteCareBinding? = null
    private lateinit var servicesPicked: ArrayList<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCompleteCareBinding.inflate(inflater, container, false)
        ccBinding = binding
        servicesPicked = arrayListOf<String>()
        var dateTime = ""
        val c = Calendar.getInstance()
        val startYear = c.get(Calendar.YEAR)
        val startMonth = c.get(Calendar.MONTH)
        val startDay = c.get(Calendar.DAY_OF_MONTH)
        val startHour = c.get(Calendar.HOUR_OF_DAY)
        val startMinute = c.get(Calendar.MINUTE)

        binding.pickupDateBtn.setOnClickListener({
            DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { _, year, month, day ->
                TimePickerDialog(requireContext(), TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                    dateTime = "" + day.toString() + "/" + month.toString() + "/" + year.toString() + " at " + hour.toString() + ":" + minute.toString()
                    binding.pickupDateTxt.text = dateTime
                }, startHour, startMinute, true).show()
            }, startYear, startMonth, startDay).show()

        })

        binding.earCleanOpt.setOnCheckedChangeListener{_,isChecked ->
            if (isChecked){
                servicesPicked.add("ear cleaning")
            }else if(isChecked == false){
                servicesPicked.remove("ear cleaning")
            }

        }
        binding.bathOpt.setOnCheckedChangeListener{_,isChecked ->
            servicesPicked.add("bathing")
        }
        binding.nailTrimOpt.setOnCheckedChangeListener{_,isChecked ->
            servicesPicked.add("nail trimming")
        }
        binding.haircutOpt.setOnCheckedChangeListener{_,isChecked ->
            servicesPicked.add("haircut")
        }
        comm = requireActivity() as Communicator
        binding.btnBook.setOnClickListener(){
            Log.w("servicesPicked ", servicesPicked.toString())
            comm.passDataCom(
                binding.petName.text.toString(),
                binding.pickupLoc.text.toString(),
                dateTime,
                binding.petType.text.toString(),
                binding.completeCare.id.toString(),

            )
        }
        return binding.root
    }


}