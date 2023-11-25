package com.example.major_assignment2.ui.fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.major_assignment2.adapter.MoviesListAdapter
import com.example.major_assignment2.database.MoviesDatabase
import com.example.major_assignment2.databinding.ActivityMoviesBinding
import com.example.major_assignment2.viewmodel.MainViewModel
import com.example.major_assignment2.viewmodel.MyViewModelFactory

class MoviesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoviesBinding
    private val adapter by lazy { MoviesListAdapter(this) }
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*setContentView(R.layout.activity_main)

        //instance of database
        val noteDatabase = MoviesDatabase.getDatabaseInstance(this)

        val fragment = MoviesFragment.getInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment, fragment.javaClass.simpleName)
            .commit()*/

        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //instance of database
        val noteDatabase = MoviesDatabase.getDatabaseInstance(this)
        val myViewModelFactory = MyViewModelFactory(noteDatabase!!)

        viewModel = ViewModelProvider(this, myViewModelFactory).get(MainViewModel::class.java)

        //submitting data to the adapter which the maps it to recyclerview
        viewModel.movies.observe(this, Observer { result ->
            adapter.submitList(result)
            binding.recyclerView.adapter = adapter
            Log.d("dataaaaaaaaaa", result.toString())
            Log.d("asxaas", result.toString())
        })
    }
}