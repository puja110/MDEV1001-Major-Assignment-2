package com.example.major_assignment2.adapter

import android.content.Context
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
        fun bind(post: MoviesEntity?) {
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
        val v = MyHolder(MoviesRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))

//        val v = LayoutInflater.from(parent.context).inflate(R.layout.movies_row, parent, false)
//         val noteHolder = MyHolder(v)

         val movieDelete = v.itemView.findViewById<TextView>(R.id.btnDelete)
        movieDelete.setOnClickListener {
             listener.onItemRemoveClick(v.adapterPosition)
         }

        val movieEdit = v.itemView.findViewById<TextView>(R.id.btnUpdate)
        movieEdit.setOnClickListener {
            listener.onEditClick(v.adapterPosition)
        }

         val note = v.itemView.findViewById<CardView>(R.id.movieCard)
         note.setOnClickListener {
             listener.onItemClick(v.adapterPosition)
         }
        return v
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}