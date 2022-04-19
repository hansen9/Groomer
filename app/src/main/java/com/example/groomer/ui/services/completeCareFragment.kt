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
import com.example.groomer.ui.home.CompleteCareModel
import com.example.groomer.ui.payment.PaymentFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [completeCareFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class completeCareFragment : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private lateinit var completeCareModel: CompleteCareModel
    private var _binding: FragmentCompleteCareBinding? = null

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
                    PaymentFragment::class.java.simpleName)
                .addToBackStack(null)
                .commit()
        }
    }

}