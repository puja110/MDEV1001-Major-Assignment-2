package com.example.major_assignment2.ui.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.major_assignment2.R
import com.example.major_assignment2.database.MoviesEntity
import com.example.major_assignment2.databinding.MoviesRowBinding
import com.example.major_assignment2.ui.interfaces.RecyclerClickListener

class MoviesListAdapter(
    private var context: Context
) : ListAdapter<MoviesEntity, MoviesListAdapter.MyHolder>(DiffutilCallBack) {

    private lateinit var listener: RecyclerClickListener
    fun setItemListener(listener: RecyclerClickListener) {
        this.listener = listener
    }

    object DiffutilCallBack: DiffUtil.ItemCallback<MoviesEntity>(){
        override fun areItemsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MoviesEntity, newItem: MoviesEntity): Boolean {
            return oldItem.id == newItem.id
        }

    }
    inner class MyHolder(private val binding: MoviesRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MoviesEntity?) {
            // displaying movie image using Glide
            if (data?.thumbnail?.contains("https") == true || data?.thumbnail?.contains("http") == true
            ) {
                Glide.with(context)
                    .load(data.thumbnail)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.ivMovieImage)
            } else {
                Glide.with(context)
                    .load(Uri.parse(data?.thumbnail))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.ivMovieImage)
            }
            binding.tvMovieTitle.text = data?.movieTitle
            binding.tvMovieStudio.text = data?.studio
            binding.tvMovieRating.text = data?.criticsRating
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val v = MyHolder(MoviesRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))

        val movieDelete = v.itemView.findViewById<TextView>(R.id.btnDelete)
        val movieEdit = v.itemView.findViewById<TextView>(R.id.btnUpdate)
        val movieCard = v.itemView.findViewById<CardView>(R.id.movieCard)

        // sending movie item position to operate deletion upon delete button click
        movieDelete.setOnClickListener {
             listener.onItemDeleteClick(v.adapterPosition)
         }

        // sending movie item position to operate update upon edit button click
        movieEdit.setOnClickListener {
            listener.onEditClick(v.adapterPosition)
        }

        // sending movie item position upon movie item click
        movieCard.setOnClickListener {
             listener.onItemClick(v.adapterPosition)
         }
        return v
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}