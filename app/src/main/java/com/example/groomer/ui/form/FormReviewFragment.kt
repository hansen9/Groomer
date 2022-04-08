package com.example.groomer.ui.form

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.groomer.R
import com.example.groomer.databinding.FragmentNotificationsBinding
import com.example.groomer.ui.notifications.NotificationsViewModel

class FormReviewFragment : Fragment() {

    private lateinit var formreviewViewModel: FormReviewFragment
    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form_review , container , false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}