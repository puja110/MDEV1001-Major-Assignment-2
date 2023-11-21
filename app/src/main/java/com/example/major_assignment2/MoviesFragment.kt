package com.example.major_assignment2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MoviesFragment : Fragment(), MoviesInterface {

    private val movieTitle: String? = null
    var moviesModel = ArrayList<MoviesModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_movies, container, false)
        val recycler = root.findViewById<RecyclerView>(R.id.dashboardRecyclerView)
        populateRecyclerView(recycler)
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
        moviesModel.add(MoviesModel(1, "https://fastly.picsum.photos/id/20/3670/2462.jpg?hmac=CmQ0ln-k5ZqkdtLvVO23LjVAEabZQx2wOaT4pyeG10I","The Godfather", "studio", "7.0"))
        moviesModel.add(MoviesModel(2, "https://fastly.picsum.photos/id/19/2500/1667.jpg?hmac=7epGozH4QjToGaBf_xb2HbFTXoV5o8n_cYzB7I4lt6g","Angry Bird", "studio", "6.5"))
        moviesModel.add(MoviesModel(3, "https://fastly.picsum.photos/id/16/2500/1667.jpg?hmac=uAkZwYc5phCRNFTrV_prJ_0rP0EdwJaZ4ctje2bY7aE","Inception", "studio", "9.0"))
        moviesModel.add(MoviesModel(4, "https://fastly.picsum.photos/id/0/5000/3333.jpg?hmac=_j6ghY5fCfSD6tvtcV74zXivkJSPIfR9B8w34XeQmvU","The Forest", "studio", "5.0"))
        moviesModel.add(MoviesModel(5, "https://fastly.picsum.photos/id/28/4928/3264.jpg?hmac=GnYF-RnBUg44PFfU5pcw_Qs0ReOyStdnZ8MtQWJqTfA","Nowhere", "studio", "5.7"))
        moviesModel.add(MoviesModel(6, "https://fastly.picsum.photos/id/29/4000/2670.jpg?hmac=rCbRAl24FzrSzwlR5tL-Aqzyu5tX_PA95VJtnUXegGU","Mirror", "studio", "8.2"))
        moviesModel.add(MoviesModel(7, "https://fastly.picsum.photos/id/27/3264/1836.jpg?hmac=p3BVIgKKQpHhfGRRCbsi2MCAzw8mWBCayBsKxxtWO8g","Mirror 2", "studio", "8.2"))

        val adapter = MoviesAdapter(
            requireActivity(),
            moviesModel,
            this
        )
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
    override fun onItemClick(position: Int) {

    }
}