package com.fnFCinema.fnFCinema.model

import com.fnFCinema.fnFCinema.service.ImdbMovieResponse
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "movies")
data class MovieDocument(@Id val imdbId: String,
                         val title: String,
                         val genre: String,
                         val actors: String,
                         val ratings: List<MovieRatingDocument>,
                         val createdAt: Date,
                         val updatedAt: Date) {

    data class MovieRatingDocument(val source: String, val value: String) {
        companion object {
            fun from(rating: ImdbMovieResponse.ImdbMovieRatingResponse): MovieRatingDocument {
                return MovieRatingDocument(rating.source, rating.value)
            }
        }
    }

    companion object {
        fun create(imdbMovieResponse: ImdbMovieResponse, currentDate: Date): MovieDocument {
            return with(imdbMovieResponse) {
                MovieDocument(imdbId, title, genre, actors, ratings.map { MovieRatingDocument.from(it) }, currentDate, currentDate)
            }
        }
    }
}
