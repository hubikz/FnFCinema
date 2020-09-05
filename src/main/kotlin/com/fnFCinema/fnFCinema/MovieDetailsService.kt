package com.fnFCinema.fnFCinema

import org.springframework.stereotype.Service

@Service
class MovieDetailsService {

    fun findByImdbId(imdbId: String): ImdbMovieResponse {
        return ImdbMovieResponse(
                "The Fast and the Furious",
                "Action, Crime, Thriller",
                "Paul Walker, Vin Diesel, Michelle Rodriguez, Jordana Brewster",
                listOf(
                        ImdbMovieResponse.ImdbMovieRatingResponse("Internet Movie Database", "6.8/10"),
                        ImdbMovieResponse.ImdbMovieRatingResponse("Rotten Tomatoes", "53%"),
                        ImdbMovieResponse.ImdbMovieRatingResponse("Metacritic", "58/100")
                )
        )
    }
}

// TODO: do all mappings
data class ImdbMovieResponse(val title: String, val genre: String, val actors: String, val ratings: List<ImdbMovieRatingResponse>) {
    data class ImdbMovieRatingResponse(val source: String, val value: String)
}
