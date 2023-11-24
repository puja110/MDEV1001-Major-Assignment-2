package com.example.major_assignment2.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.major_assignment2.R
import com.example.major_assignment2.model.Movies
import com.example.major_assignment2.ui.activity.AddMovieActivity
import com.example.major_assignment2.ui.adapter.MoviesAdapter
import com.example.major_assignment2.ui.interfaces.MoviesInterface
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MoviesFragment : Fragment(), MoviesInterface {

    private var movies = ArrayList<Movies>()
    private lateinit var moviesViewModel: MoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_movies, container, false)
        val recycler = root.findViewById<RecyclerView>(R.id.rvMovie)
        val fbAddMovie = root.findViewById<FloatingActionButton>(R.id.fbAddMovie)

        populateRecyclerView(recycler)
        fbAddMovie.setOnClickListener {
            val intent = Intent(context, AddMovieActivity::class.java)
            startActivity(intent)
        }

      /*  moviesViewModel = ViewModelProviders.of(this)[MoviesViewModel::class.java]

        moviesViewModel.getAllMovies().observe(context, Observer {
            Log.d("Movies observed", "$it")
            Log.d("Movies observed 222", "")

            //adapter.submitList(it)
        })*/

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun getInstance(): Fragment {
            return MoviesFragment()
        }
    }

    private fun populateRecyclerView(recyclerView: RecyclerView) {
        movies.add(Movies(1, "https://fastly.picsum.photos/id/20/3670/2462.jpg?hmac=CmQ0ln-k5ZqkdtLvVO23LjVAEabZQx2wOaT4pyeG10I","The Godfather", "studio", "7.0"))
        movies.add(Movies(2, "https://fastly.picsum.photos/id/19/2500/1667.jpg?hmac=7epGozH4QjToGaBf_xb2HbFTXoV5o8n_cYzB7I4lt6g","Angry Bird", "studio", "6.5"))
        movies.add(Movies(3, "https://fastly.picsum.photos/id/16/2500/1667.jpg?hmac=uAkZwYc5phCRNFTrV_prJ_0rP0EdwJaZ4ctje2bY7aE","Inception", "studio", "9.0"))
        movies.add(Movies(4, "https://fastly.picsum.photos/id/0/5000/3333.jpg?hmac=_j6ghY5fCfSD6tvtcV74zXivkJSPIfR9B8w34XeQmvU","The Forest", "studio", "5.0"))
        movies.add(Movies(5, "https://fastly.picsum.photos/id/28/4928/3264.jpg?hmac=GnYF-RnBUg44PFfU5pcw_Qs0ReOyStdnZ8MtQWJqTfA","Nowhere", "studio", "5.7"))
        movies.add(Movies(6, "https://fastly.picsum.photos/id/29/4000/2670.jpg?hmac=rCbRAl24FzrSzwlR5tL-Aqzyu5tX_PA95VJtnUXegGU","Mirror", "studio", "8.2"))
        movies.add(Movies(7, "https://fastly.picsum.photos/id/27/3264/1836.jpg?hmac=p3BVIgKKQpHhfGRRCbsi2MCAzw8mWBCayBsKxxtWO8g","Mirror 2", "studio", "8.2"))

        val adapter = MoviesAdapter(
            requireActivity(),
            movies,
            this
        )
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
    override fun onItemClick(position: Int) {

    }
}