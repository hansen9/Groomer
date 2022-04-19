package com.example.groomer.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.groomer.CustomOnItemClickListener
import com.example.groomer.R
import com.example.groomer.ReviewAddUpdateActivity
import com.example.groomer.databinding.ActivityMainBinding
import com.example.groomer.entity.Review
import kotlinx.android.synthetic.main.item_review.view.*

class ReviewAdapter(private val activity: Activity) : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    var listReviews = ArrayList<Review>()
    set(listReviews) {
        if(listReviews.size > 0){
            this.listReviews.clear()
        }
        this.listReviews.addAll(listReviews)
        notifyDataSetChanged()
    }

    fun addItem(review: Review){
        this.listReviews.add(review)
        notifyItemInserted(this.listReviews.size-1)
    }

    fun updateItem(position: Int, review: Review){
        this.listReviews[position] = review
        notifyItemChanged(position, review)
    }
    fun removedItem(position: Int){
        this.listReviews.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, this.listReviews.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false)
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder , position: Int) {
        holder.bind(listReviews[position])
    }

    override fun getItemCount(): Int = this.listReviews.size

    inner class ReviewViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(review: Review){
            with(itemView){
                val tv_item_pemilik = review.nama_pemilik
                val tv_item_komentar = review.komentar

                cv_item_review.setOnClickListener(CustomOnItemClickListener(adapterPosition, object : CustomOnItemClickListener.OnItemClickCallback{
                    override fun onItemClicked(view: View , position: Int) {
                        val intent = Intent(activity, ReviewAddUpdateActivity::class.java)
                        intent.putExtra(ReviewAddUpdateActivity.EXTRA_POSITION, position)
                        intent.putExtra(ReviewAddUpdateActivity.EXTRA_POSITION, review)
                        activity.startActivityForResult(intent, ReviewAddUpdateActivity.REQUEST_UPDATE)
                    }
                }))
            }
        }
    }
}