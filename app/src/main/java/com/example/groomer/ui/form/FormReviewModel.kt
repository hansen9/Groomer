package com.example.groomer.ui.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FormReviewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is form review Fragment"
    }
    val text: LiveData<String> = _text
}