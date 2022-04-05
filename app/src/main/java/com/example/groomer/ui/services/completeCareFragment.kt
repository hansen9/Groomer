package com.example.groomer.ui.services

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentManager
import com.example.groomer.R
import com.example.groomer.databinding.FragmentCompleteCareBinding
import com.example.groomer.ui.payment.PaymentFragment

// TODO: Rename parameter arguments, choose names that match

class completeCareFragment : Fragment(), View.OnClickListener {
    private lateinit var completeCareModel: CompleteCareModel
    private var _binding: FragmentCompleteCareBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_complete_care, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnBook: Button = view.findViewById(R.id.btn_book)
        btnBook.setOnClickListener(this)
    }

    override fun onClick(v: View){
        if(v.id == R.id.btn_book){
            val mPaymentFragment = PaymentFragment()
            val mFragmentManager = fragmentManager as FragmentManager
            mFragmentManager
                .beginTransaction()
                .replace(R.id.container, mPaymentFragment,
                    completeCareFragment::class.java.simpleName)
                .addToBackStack(null)
                .commit()
        }
    }
}