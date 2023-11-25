package com.example.major_assignment2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.major_assignment2.database.Movies
import com.example.major_assignment2.databinding.MoviesRowBinding
import com.example.major_assignment2.ui.interfaces.MoviesInterface

class MoviesListAdapter(
    private var context: Context
) : ListAdapter<Movies, MoviesListAdapter.MyHolder>(DiffutilCallBack) {

    object DiffutilCallBack: DiffUtil.ItemCallback<Movies>(){
        override fun areItemsTheSame(oldItem: Movies, newItem: Movies): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Movies, newItem: Movies): Boolean {
            return oldItem.id == newItem.id
        }

    }
    inner class MyHolder(private val binding: MoviesRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Movies?) {
            Glide.with(context)
                .load(post?.thumbnail)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.ivMovieImage)
            binding.tvMovieTitle.text = post?.movieTitle
            binding.tvMovieStudio.text = post?.studio
            binding.tvMovieRating.text = post?.criticsRating
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(MoviesRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}