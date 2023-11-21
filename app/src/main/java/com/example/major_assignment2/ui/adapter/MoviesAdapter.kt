package com.example.major_assignment2.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.major_assignment2.R
import com.example.major_assignment2.model.Movies
import com.example.major_assignment2.ui.activity.AddMovieActivity
import com.example.major_assignment2.ui.activity.EditMovieActivity
import com.example.major_assignment2.ui.interfaces.MoviesInterface
import org.w3c.dom.Text

class MoviesAdapter(
    private var context: Context,
    private var movieModels: ArrayList<Movies>,
    private val recyclerViewInterface: MoviesInterface
) : RecyclerView.Adapter<MoviesAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // This is where you inflate the layout (giving a look to your rows)
        val inflater = LayoutInflater.from(context)

        val view: View = inflater.inflate(R.layout.movies_row_item, parent, false)
        return MyViewHolder(view, recyclerViewInterface)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // Assigning values to the views we created in the dashboard_recycler_view_row layout file
        // Based on the position of the recycler view
        Glide.with(context)
            .load(movieModels[position].imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.movieImage)
        holder.movieTitle.text = movieModels[position].title
        holder.movieStudio.text = movieModels[position].studio
        holder.movieRating.text = movieModels[position].rating

        holder.updateButton.setOnClickListener {
            val intent = Intent(context, EditMovieActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        // The recycler view wants to know the number of items you want to be displayed
        return movieModels.size
    }

    class MyViewHolder(itemView: View, recyclerViewInterface: MoviesInterface?) :
        RecyclerView.ViewHolder(itemView) {
        // Grabbing the views from the dashboard_recycler_view_row layout file
        // Kind of like an onCreate method
        var movieImage: ImageView
        var movieTitle: TextView
        var movieStudio: TextView
        var movieRating: TextView
        var updateButton: TextView
        var deleteButton: TextView

        init {
            movieImage = itemView.findViewById(R.id.ivMovieImage)
            movieTitle = itemView.findViewById(R.id.tvMovieTitle)
            movieStudio = itemView.findViewById(R.id.tvMovieStudio)
            movieRating = itemView.findViewById(R.id.tvMovieRating)
            updateButton = itemView.findViewById(R.id.btnUpdate)
            deleteButton = itemView.findViewById(R.id.btnDelete)
            itemView.setOnClickListener {
                if (recyclerViewInterface != null) {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        recyclerViewInterface.onItemClick(position)
                    }
                }
            }
        }
    }
}