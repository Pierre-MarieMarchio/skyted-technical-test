package com.pierre_marie_marchio.skyted_technical_test.infrastructure.movieexplorer.repositories

import com.pierre_marie_marchio.skyted_technical_test.domain.movieexplorer.entities.Movie
import com.pierre_marie_marchio.skyted_technical_test.domain.movieexplorer.interfaces.MovieRepository
import com.pierre_marie_marchio.skyted_technical_test.infrastructure.common.bases.RepositoryBase
import com.pierre_marie_marchio.skyted_technical_test.infrastructure.common.interfaces.ApiClient
import com.pierre_marie_marchio.skyted_technical_test.infrastructure.movieexplorer.services.parseMovieModel
import com.pierre_marie_marchio.skyted_technical_test.infrastructure.movieexplorer.services.parseMovieModels
import com.pierre_marie_marchio.skyted_technical_test.infrastructure.movieexplorer.services.toEntity

class MovieRepositoryImpl<DaoT>(
    client: ApiClient? = null,
    dao: DaoT? = null
) : RepositoryBase<DaoT>(client = client, dao = dao), MovieRepository {

    override suspend fun getMovieById(id: String): Movie =
        this.fetchFromDaoOrApi(fetchFromDao = { null }, fetchFromApi = {
            val json = this.client!!.get("https://www.omdbapi.com/?i=$id")
            val model = parseMovieModel(json)
            model.toEntity()
        })

    override suspend fun searchMovies(query: String): List<Movie> =
        fetchFromDaoOrApi(
            fetchFromDao = { null },
            fetchFromApi = {
                val json = client!!.get("https://www.omdbapi.com/?s=$query")
                val models = parseMovieModels(json)
                models.map { it.toEntity() }
            }
        )
}