package com.example.major_assignment2.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.major_assignment2.adapter.MoviesListAdapter
import com.example.major_assignment2.database.MoviesEntity
import com.example.major_assignment2.database.MoviesDatabase
import com.example.major_assignment2.databinding.ActivityMoviesBinding
import com.example.major_assignment2.ui.interfaces.RecyclerClickListener
import com.example.major_assignment2.viewmodel.MainViewModel
import com.example.major_assignment2.viewmodel.MyViewModelFactory
import kotlinx.coroutines.launch
import kotlin.random.Random

class MoviesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoviesBinding
    private val adapter by lazy { MoviesListAdapter(this) }
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)

        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //instance of database
        val moviesDatabase = MoviesDatabase.getDatabaseInstance(this)
        val myViewModelFactory = MyViewModelFactory(moviesDatabase!!)

        viewModel = ViewModelProvider(this, myViewModelFactory).get(MainViewModel::class.java)

        binding.floatingActionButton.setOnClickListener {
            val random = kotlin.math.abs((0..999999999999).random())
            Log.d("random number: ", random.toString())
            val newMovie = MoviesEntity(
                100, //Add randomly generated 3 digit Integer here
                "The Forest 2",
                "Gibili 2",
                "",
                "7.7"
            )
            lifecycleScope.launch {
                viewModel.addMovie(newMovie)
            }
        }

        //submitting data to the adapter which the maps it to recyclerview
        viewModel.movies.observe(this, Observer { result ->
            adapter.submitList(result)

            adapter.setItemListener(object : RecyclerClickListener {

                // Tap the 'X' to delete the note.
                override fun onItemRemoveClick(position: Int) {
                    val moviesList = adapter.currentList.toMutableList()
                    val movieId = moviesList[position].id
                    val movieTitleText = moviesList[position].movieTitle
                    val movieStudioText = moviesList[position].studio
                    val movieThumbnail = moviesList[position].thumbnail
                    val movieRatingText = moviesList[position].criticsRating
                    val removeNote = MoviesEntity(movieId, movieTitleText, movieStudioText, movieThumbnail, movieRatingText)
                    moviesList.removeAt(position)
                    adapter.submitList(moviesList)
                    // adapter.notifyDataSetChanged()
                    lifecycleScope.launch {
                        viewModel.deleteMovie(removeNote)
                    }
                }

                // Tap the note to go to detail page.
                override fun onItemClick(position: Int) {
                    Log.d("onItemClick: ", position.toString())
                    /*val intent = Intent(this@NotesActivity, AddNoteActivity::class.java)
                    val notesList = adapter.currentList.toMutableList()
                    intent.putExtra("note_date_added", notesList[position].dateAdded)
                    intent.putExtra("note_text", notesList[position].noteText)
                    editNoteResultLauncher.launch(intent)*/
                }

                // Tap the note to edit.
                override fun onEditClick(position: Int) {
                    val moviesList = adapter.currentList.toMutableList()
                    val movieId = moviesList[position].id
                    val movieTitleText = "Hush"
                    val movieStudioText = "Gibili"
                    val movieThumbnail = moviesList[position].thumbnail
                    val movieRatingText = "9.0"
                    val removeNote = MoviesEntity(movieId, movieTitleText, movieStudioText, movieThumbnail, movieRatingText)
                    lifecycleScope.launch {
                        viewModel.updateMovie(removeNote)
                    }
                }
            })

            binding.recyclerView.adapter = adapter
        })
    }
}