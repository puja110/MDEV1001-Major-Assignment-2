package com.example.major_assignment2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.major_assignment2.adapter.MoviesListAdapter
import com.example.major_assignment2.database.MoviesDatabase
import com.example.major_assignment2.databinding.ActivityMainBinding
import com.example.major_assignment2.ui.fragment.MoviesActivity
import com.example.major_assignment2.ui.fragment.MoviesFragment
import com.example.major_assignment2.viewmodel.MainViewModel
import com.example.major_assignment2.viewmodel.MyViewModelFactory

//import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.lifecycleScope

class MainActivity : AppCompatActivity() {

    /*private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { MoviesListAdapter(this) }
    private lateinit var viewModel: MainViewModel*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, MoviesActivity::class.java)
        startActivity(intent)

        /*//instance of database
        val noteDatabase = MoviesDatabase.getDatabaseInstance(this)

        val fragment = MoviesFragment.getInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment, fragment.javaClass.simpleName)
            .commit()*/

        /*binding = ActivityMainBinding.inflate(layoutInflater)
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
        })*/
    }
}