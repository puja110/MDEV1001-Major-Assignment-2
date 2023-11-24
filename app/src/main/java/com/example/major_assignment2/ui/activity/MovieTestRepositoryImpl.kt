package com.example.major_assignment2.ui.activity

import com.example.major_assignment2.model.Movies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieTestRepositoryImpl constructor(
    private val localRepository: MovieTestRepository.Local,
) : MovieTestRepository {

    companion object {
        @Volatile
        private var instance: MovieTestRepository? = null

        @Synchronized
        fun getInstance(): MovieTestRepository {
            if (instance != null) {
                return instance!!
            }

            val local = MovieTestLocalImpl.getInstance()
            return MovieTestRepositoryImpl(local).also { instance = it }
        }
    }

    override suspend fun getCategories(): List<Movies> {
        val items = mutableListOf<Movies>()
        items.add(
            Movies(
                id = 1,
                imageUrl = "https://fastly.picsum.photos/id/20/3670/2462.jpg?hmac=CmQ0ln-k5ZqkdtLvVO23LjVAEabZQx2wOaT4pyeG10I",
                title = "One",
                studio = "",
                rating = "7.0"
            )
        )
        items.add(
            Movies(
                id = 2,
                imageUrl = "https://fastly.picsum.photos/id/19/2500/1667.jpg?hmac=7epGozH4QjToGaBf_xb2HbFTXoV5o8n_cYzB7I4lt6g",
                title = "One",
                studio = "",
                rating = "7.5"
            )
        )
        items.add(
            Movies(
                id = 3,
                imageUrl = "https://fastly.picsum.photos/id/16/2500/1667.jpg?hmac=uAkZwYc5phCRNFTrV_prJ_0rP0EdwJaZ4ctje2bY7aE",
                title = "One",
                studio = "",
                rating = "8.5"
            )
        )
        items.add(
            Movies(
                id = 4,
                imageUrl = "https://fastly.picsum.photos/id/0/5000/3333.jpg?hmac=_j6ghY5fCfSD6tvtcV74zXivkJSPIfR9B8w34XeQmvU",
                title = "One",
                studio = "",
                rating = "8.5"
            )
        )

        return items
    }

}