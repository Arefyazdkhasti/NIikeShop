package com.example.nikeshop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nikeshop.R
import com.example.nikeshop.dataClass.DataComments
import com.example.nikeshop.net.ApiService
import kotlinx.android.synthetic.main.item_comment.view.*
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class RecyclerCommentAdapter(private val data: List<DataComments>) :
    RecyclerView.Adapter<RecyclerCommentAdapter.CommentsViewHolder>(){

    inner class CommentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val txtTitle = itemView.item_comment_title
        private val txtAuthor = itemView.item_comment_author
        private val txtTime = itemView.item_comment_time
        private val txtText = itemView.item_comment_content

        fun setData(data: DataComments) {
            txtTitle.text = data.title
            txtAuthor.text = data.author
            txtTime.text = data.created_at
            txtText.text = data.content

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder =
        CommentsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_comment,
                    parent,
                    false
                )
        )

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        holder.setData(data[position])
    }

    override fun getItemCount(): Int = data.size
}