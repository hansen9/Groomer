package com.example.groomer.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.example.groomer.R
import com.example.groomer.adapter.ReviewAdapter
import com.example.groomer.databinding.FragmentHomeBinding
import com.example.groomer.ui.form.FormReviewFragment
import com.example.groomer.ui.services.*

class HomeFragment : Fragment() , View.OnClickListener{

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnCompleteCate: Button = view.findViewById(R.id.btn_menu_complete_care)
        val btnBathing: Button = view.findViewById(R.id.btn_menu_bathing)
        val btnHaircut: Button = view.findViewById(R.id.btn_menu_haircut)
        val btnEarCleaning: Button = view.findViewById(R.id.btn_menu_ear_cleaning)
        val btnNailTrimming: Button = view.findViewById(R.id.btn_menu_nail_trimming)
        val btnFormReview: Button = view.findViewById(R.id.btn_menu_consument_review)

        btnCompleteCate.setOnClickListener(this)
        btnBathing.setOnClickListener(this)
        btnHaircut.setOnClickListener(this)
        btnNailTrimming.setOnClickListener(this)
        btnEarCleaning.setOnClickListener(this)
        btnFormReview.setOnClickListener(this)
    }

    override fun onClick(v: View){
        when(v.id){
            R.id.btn_menu_bathing ->{
                val mBathingFragment = BathingFragment()
                val mFragmentManager = fragmentManager as FragmentManager
                mFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.nav_host_fragment, mBathingFragment,
                        BathingFragment::class.java.simpleName
                    )
                    .addToBackStack(null)
                    .commit()
            }
            R.id.btn_menu_haircut ->{
                val mHaircutFragment = HaircutFragment()
                val mFragmentManager = fragmentManager as FragmentManager
                mFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.nav_host_fragment, mHaircutFragment,
                        HaircutFragment::class.java.simpleName
                    )
                    .addToBackStack(null)
                    .commit()
            }
            R.id.btn_menu_ear_cleaning ->{
                val mEarCleanFragment = EarCleanFragment()
                val mFragmentManager = fragmentManager as FragmentManager
                mFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.nav_host_fragment, mEarCleanFragment,
                        EarCleanFragment::class.java.simpleName
                    )
                    .addToBackStack(null)
                    .commit()
            }
            R.id.btn_menu_nail_trimming ->{
                val mNailTrimFragment = NailTrimFragment()
                val mFragmentManager = fragmentManager as FragmentManager
                mFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.nav_host_fragment, mNailTrimFragment,
                        NailTrimFragment::class.java.simpleName
                    )
                    .addToBackStack(null)
                    .commit()
            }
            R.id.btn_menu_consument_review ->{
                val mFormReviewFragment = FormReviewFragment()
                val mFragmentManager = fragmentManager as FragmentManager
                mFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.nav_host_fragment, mFormReviewFragment,
                        FormReviewFragment::class.java.simpleName
                    )
                    .addToBackStack(null)
                    .commit()
            }
        }
//        if(v.id == R.id.btn_menu_complete_care){
//            val mCompleteCFragment = completeCareFragment()
//            val mFragmentManager = fragmentManager as FragmentManager
//            mFragmentManager
//                .beginTransaction()
//                .replace(R.id.container, mCompleteCFragment,
//                    completeCareFragment::class.java.simpleName)
//                .addToBackStack(null)
//                .commit()
//        } else if(v.id == R.id.btn_menu_bathing) {
//
//        } else if(v.id == ) {
//
//        } else if(v.id == ) {
//
//        } else if(v.id == ) {
//
//        } else if(v.id == ){
//
//        }
    }
}
