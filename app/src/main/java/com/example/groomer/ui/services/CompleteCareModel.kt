package com.example.groomer.ui.services

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CompleteCareModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is complete care Fragment"
    }
    val text: LiveData<String> = _text
}