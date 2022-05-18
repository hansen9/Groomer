package com.example.groomer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.groomer.databinding.ItemReviewBinding
import com.example.groomer.entity.Review

class ReviewAdapter(private val listReview: ArrayList<Review>) :
    RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup , viewType: Int): ReviewViewHolder {
        val binding = ItemReviewBinding.inflate(LayoutInflater.from(viewGroup.context),
        viewGroup,false)
        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder , position: Int) {
        holder.bind(listReview[position])
    }

    override fun getItemCount(): Int = this.listReview.size

    inner class ReviewViewHolder(private val binding: ItemReviewBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(review: Review){
            with(binding){
                binding.tvItemPemilik.text = "" + binding.tvItemPemilik.text + ": " + review.username
                binding.tvItemTreatment.text = "" + binding.tvItemTreatment.text + ": " + review.treatment
                binding.tvItemKomentar.text = "" + binding.tvItemKomentar.text + ":\n" + review.comment
            }
        }
    }
}