package com.example.groomer.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.groomer.CustomOnItemClickListener
import com.example.groomer.R
import com.example.groomer.entity.Review
import com.example.groomer.entity.Review2
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.item_review.view.*

class ReviewAdapter() : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {
    private val databaseReference = FirebaseDatabase.getInstance(
    "https://groomer-ead4a-default-rtdb.asia-southeast1.firebasedatabase.app/"
    ).getReference("reviews")
    var listReviews = ArrayList<Review2>()
    set(listReviews) {
        if(listReviews.size > 0){
            this.listReviews.clear()
        }
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot!!.exists()){
                    for (rev in snapshot.children){
                        val review = rev.getValue(Review2::class.java)
                        Log.w("the rev is ",rev.toString())
                        listReviews.add(review!!)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        this.listReviews.addAll(listReviews)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false)
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder , position: Int) {
        holder.bind(listReviews[position])
    }

    override fun getItemCount(): Int = this.listReviews.size

    inner class ReviewViewHolder(itemView: View):
        RecyclerView.ViewHolder(itemView) {
        fun bind(review: Review2){
            with(itemView){
                tv_item_pemilik.text = review.username
                tv_item_komentar.text = review.comment
            }
        }
    }
}