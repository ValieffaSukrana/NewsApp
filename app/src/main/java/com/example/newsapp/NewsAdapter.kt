package com.example.newsapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.databinding.ItemNewsBinding

class NewsAdapter(private var articleList: List<Article>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsItem = articleList[position]
        holder.binding.newsTitle.text = newsItem.title
        holder.binding.newsDesc.text = newsItem.description

        Glide.with(holder.itemView.context)
            .load(newsItem.imageUrl)
            .placeholder(R.drawable.news)
            .error(R.drawable.aparici)
            .into(holder.binding.newsImage)
    }

    inner class NewsViewHolder(val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    fun updateList(newList: List<Article>) {
        articleList = newList
        notifyDataSetChanged()
    }
}
