package com.example.major_assignment2.ui.activity

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.major_assignment2.R
import com.example.major_assignment2.adapter.MoviesListAdapter
import com.example.major_assignment2.database.MoviesDatabase
import com.example.major_assignment2.database.MoviesEntity
import com.example.major_assignment2.databinding.ActivityMoviesBinding
import com.example.major_assignment2.ui.interfaces.RecyclerClickListener
import com.example.major_assignment2.viewmodel.MainViewModel
import com.example.major_assignment2.viewmodel.MyViewModelFactory
import kotlinx.coroutines.launch
import java.util.Date
import java.util.concurrent.Flow


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

        viewModel = ViewModelProvider(this, myViewModelFactory)[MainViewModel::class.java]

        binding.floatingActionButton.setOnClickListener {
            /*val random = kotlin.math.abs((0..999999999999).random())
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
            }*/

            val intent = Intent(this, AddMovieActivity::class.java)
            startActivity(intent)
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
                    val deleteMovie = MoviesEntity(movieId, movieTitleText, movieStudioText, movieThumbnail, movieRatingText)
                    // adapter.notifyDataSetChanged()
                    showDialog(position, moviesList, deleteMovie)
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
                    val intent = Intent(this@MoviesActivity, EditMovieActivity::class.java)
                    val moviesList = adapter.currentList.toMutableList()
                    intent.putExtra("id", moviesList[position].id)
                    editNoteResultLauncher.launch(intent)
                }
            })

            binding.recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        })
    }

    val editNoteResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // Get the edited movie from the AddMovieActivity
                val id = result.data?.getIntExtra("id", 1)
                val title = result.data?.getStringExtra("movie_title")
                val studio = result.data?.getStringExtra("movie_studio")
                val rating = result.data?.getStringExtra("movie_rating")
                // Update the movie in the list
                val editMovie = MoviesEntity(id!!, title, studio, "https://hips.hearstapps.com/hmg-prod/images/the-matrix-1574173308.jpg?crop=1.00xw:0.941xh;0,0.0146xh&resize=2048:*", rating)
                lifecycleScope.launch {
                    viewModel.updateMovie(editMovie)
                }
                adapter.notifyDataSetChanged()
            }
        }

    private fun showDialog(position: Int, moviesList: MutableList<MoviesEntity>,deleteMovie: MoviesEntity) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_dialog_layout)

        val yesBtn = dialog.findViewById(R.id.btnOk) as Button
        yesBtn.setOnClickListener {
            moviesList.removeAt(position)
            adapter.submitList(moviesList)
            lifecycleScope.launch {
                viewModel.deleteMovie(deleteMovie)
            }
            dialog.dismiss()
        }

        val noBtn = dialog.findViewById(R.id.btnCancel) as Button
        noBtn.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}