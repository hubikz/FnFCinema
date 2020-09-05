package com.fnFCinema.fnFCinema.api.v1.resource

import com.fnFCinema.fnFCinema.service.ImdbMovieResponse

data class MovieRepresentationModel(val imdbId: String,
                                    val title: String,
                                    val genre: String,
                                    val actors: String,
                                    val ratings: List<MovieRepresentationRatingModel>) {

    companion object {
        fun from(imdbId: String, imdbMovieResponse: ImdbMovieResponse): MovieRepresentationModel {
            return with(imdbMovieResponse) {
                MovieRepresentationModel(imdbId, title, genre, actors, ratings.map { MovieRepresentationRatingModel.from(it) })
            }
        }
    }

    data class MovieRepresentationRatingModel(val source: String, val value: String) {
        companion object {
            fun from(rating: ImdbMovieResponse.ImdbMovieRatingResponse): MovieRepresentationRatingModel {
                return MovieRepresentationRatingModel(rating.source, rating.value)
            }
        }
    }
}

