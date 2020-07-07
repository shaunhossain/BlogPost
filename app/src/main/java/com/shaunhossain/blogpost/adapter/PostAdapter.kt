package com.shaunhossain.blogpost.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shaunhossain.blogpost.R
import com.shaunhossain.blogpost.model.PostsItem
import kotlinx.android.synthetic.main.item_quote.view.*

class PostAdapter: RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner  class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<PostsItem>() {
        override fun areItemsTheSame(oldItem: PostsItem, newItem: PostsItem): Boolean {
            return oldItem.userId == newItem.userId
        }

        override fun areContentsTheSame(oldItem: PostsItem, newItem: PostsItem): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_quote,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = differ.currentList[position]
        holder.itemView.apply {
            text_view_quote.text = post.body
            text_view_author.text = post.title
        }

    }

}