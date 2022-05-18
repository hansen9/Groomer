package com.example.groomer.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.groomer.R
import com.example.groomer.databinding.FragmentHomeBinding
import com.example.groomer.ui.form.FormReviewActivity
import com.example.groomer.ui.review.ShowReviewActivity
import com.example.groomer.ui.services.*

class HomeFragment : Fragment() , View.OnClickListener{

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

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
        val btnCompleteCate: Button = view.findViewById(R.id.btn_show_review)
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
                val intent = Intent(activity, BathingActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_menu_haircut ->{
                val intent = Intent(activity, HaircutActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_menu_ear_cleaning ->{
                val intent = Intent(activity, EarCleanActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_menu_nail_trimming ->{
                val intent = Intent(activity, NailTrimActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_show_review ->{
                val intent = Intent(activity, ShowReviewActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_menu_consument_review ->{
                val intent = Intent(activity, FormReviewActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
