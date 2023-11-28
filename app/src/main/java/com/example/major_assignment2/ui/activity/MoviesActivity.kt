package com.example.major_assignment2.ui.activity

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.major_assignment2.R
import com.example.major_assignment2.database.MoviesDatabase
import com.example.major_assignment2.database.MoviesEntity
import com.example.major_assignment2.databinding.ActivityMoviesBinding
import com.example.major_assignment2.ui.adapter.MoviesListAdapter
import com.example.major_assignment2.ui.interfaces.RecyclerClickListener
import com.example.major_assignment2.viewmodel.MainViewModel
import com.example.major_assignment2.viewmodel.MyViewModelFactory
import kotlinx.coroutines.launch

class MoviesActivity : AppCompatActivity() {

    // defining activity binding, movie adapter and view model
    private lateinit var binding: ActivityMoviesBinding
    private val adapter by lazy { MoviesListAdapter(this) }
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // getting instance of database class
        val moviesDatabase = MoviesDatabase.getDatabaseInstance(this)
        val myViewModelFactory = MyViewModelFactory(moviesDatabase!!)

        // initializing the viewmodel
        viewModel = ViewModelProvider(this, myViewModelFactory)[MainViewModel::class.java]

        // this button navigates to the add new movie screen
        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this, AddMovieActivity::class.java)
            startActivity(intent)
        }

        // submitting data to the adapter which the maps it to recyclerview
        viewModel.movies.observe(this, Observer { result ->
            adapter.submitList(result)

            adapter.setItemListener(object : RecyclerClickListener {
                override fun onItemDeleteClick(position: Int) {
                    val moviesList = adapter.currentList.toMutableList()
                    val movieId = moviesList[position].id
                    val movieTitleText = moviesList[position].movieTitle
                    val movieStudioText = moviesList[position].studio
                    val movieThumbnail = moviesList[position].thumbnail
                    val movieRatingText = moviesList[position].criticsRating

                    // sending data from the view to the database for the delete movie operation
                    val deleteMovie = MoviesEntity(movieId, movieTitleText, movieStudioText, movieThumbnail, movieRatingText)

                    // show dialog message to user before movie delete
                    showDialog(position, moviesList, deleteMovie)
                }

                // Tap the note to go to detail page.
                override fun onItemClick(position: Int) {
                    // navigating to the edit movie screen
                    val intent = Intent(this@MoviesActivity, EditMovieActivity::class.java)
                    val moviesList = adapter.currentList.toMutableList()
                    intent.putExtra("id", moviesList[position].id)
                    intent.putExtra("title", moviesList[position].movieTitle)
                    intent.putExtra("studio", moviesList[position].studio)
                    intent.putExtra("rating", moviesList[position].criticsRating)
                    intent.putExtra("thumbnail", moviesList[position].thumbnail)
                    editMoviesResultLauncher.launch(intent)
                }

                // Tap the note to edit.
                override fun onEditClick(position: Int) {
                    // navigating to the edit movie screen
                    val intent = Intent(this@MoviesActivity, EditMovieActivity::class.java)
                    val moviesList = adapter.currentList.toMutableList()
                    intent.putExtra("id", moviesList[position].id)
                    intent.putExtra("title", moviesList[position].movieTitle)
                    intent.putExtra("studio", moviesList[position].studio)
                    intent.putExtra("rating", moviesList[position].criticsRating)
                    intent.putExtra("thumbnail", moviesList[position].thumbnail)
                    editMoviesResultLauncher.launch(intent)
                }
            })

            binding.recyclerView.adapter = adapter
        })
    }

    val editMoviesResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // Get the edited movie from the AddMovieActivity
                val id = result.data?.getIntExtra("id", 1)
                val title = result.data?.getStringExtra("movie_title")
                val studio = result.data?.getStringExtra("movie_studio")
                val rating = result.data?.getStringExtra("movie_rating")
                val thumbnail = result.data?.getStringExtra("movie_thumbnail")

                // update the movie from the database
                val editMovie = MoviesEntity(id!!, title, studio, thumbnail, rating)
                lifecycleScope.launch {
                    viewModel.updateMovie(editMovie)
                }
            }
        }

    private fun showDialog(position: Int, moviesList: MutableList<MoviesEntity>,deleteMovie: MoviesEntity) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_dialog_layout)

        val yesBtn = dialog.findViewById(R.id.btnOk) as Button
        yesBtn.setOnClickListener {
            // sending movie item to delete from the database
            lifecycleScope.launch {
                viewModel.deleteMovie(deleteMovie)
            }

            // removing deleted movie item from the movie list
            moviesList.removeAt(position)
            adapter.submitList(moviesList)

            dialog.dismiss()
        }

        val noBtn = dialog.findViewById(R.id.btnCancel) as Button
        noBtn.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}