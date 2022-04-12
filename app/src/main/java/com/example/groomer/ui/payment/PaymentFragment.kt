package com.example.groomer.ui.payment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.groomer.R
import com.example.groomer.databinding.FragmentNotificationsBinding
import com.example.groomer.databinding.FragmentPaymentBinding
import com.example.groomer.ui.notifications.NotificationsViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [PaymentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PaymentFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var paymentModel: PaymentModel
    private var _binding: FragmentPaymentBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        paymentModel =
            ViewModelProvider(this).get(PaymentModel::class.java)

        _binding = FragmentPaymentBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.petNamePay
//        paymentModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PaymentFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PaymentFragment().apply {

            }
    }
}