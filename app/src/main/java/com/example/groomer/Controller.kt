package com.example.groomer

import android.view.View
import android.widget.Button
import android.widget.EditText

class Controller : View.OnClickListener {
    public fun transanctions(view: View){
        val petName: EditText = view.findViewById(R.id.pet_name)
        val btnBook: Button = view.findViewById(R.id.btn_book)
        btnBook.setOnClickListener(this)
    }
    public fun getReview(){

    }
    public fun setReview(){

    }
    public fun getUserData(){

    }
    public fun createUser(){

    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}