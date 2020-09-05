package com.fnFCinema.fnFCinema.api.v1.resource

import com.fnFCinema.fnFCinema.ImdbMovieResponse

data class MovieRepresentationModel(val imdbId: String,
                                    val title: String,
                                    val genre: String,
                                    val actors: String,
                                    val ratings: List<ImdbMovieResponse.ImdbMovieRatingResponse>) {

    companion object {
        fun from(imdbId: String, imdbMovieResponse: ImdbMovieResponse): MovieRepresentationModel {
            return with(imdbMovieResponse) {
                MovieRepresentationModel(imdbId, title, genre, actors, ratings)
            }
        }
    }
}
