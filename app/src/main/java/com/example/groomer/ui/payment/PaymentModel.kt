package com.example.groomer.ui.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PaymentModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "your pet name is :"
    }
    val text: LiveData<String> = _text
}