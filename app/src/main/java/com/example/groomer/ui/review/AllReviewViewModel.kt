package com.example.groomer.ui.review

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase

class AllReviewViewModel : ViewModel() {
    private val dbreviews = FirebaseDatabase.getInstance(
        "https://groomer-ead4a-default-rtdb.asia-southeast1.firebasedatabase.app/"
    ).getReference("reviews")

    val _result = MutableLiveData<Exception?>()
    val result: LiveData<Exception?> get() = _result

    private val _review = MutableLiveData<com.example.groomer.entity.Review>()
    val review: LiveData<com.example.groomer.entity.Review>get() = _review

    private val childEventListener = object: ChildEventListener{
        override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
            TODO("Not yet implemented")
        }

        override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
            TODO("Not yet implemented")
        }

        override fun onChildRemoved(snapshot: DataSnapshot) {
            TODO("Not yet implemented")
        }

        override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
            TODO("Not yet implemented")
        }

        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }

    }
}