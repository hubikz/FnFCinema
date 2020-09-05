package com.fnFCinema.fnFCinema.service

import com.fasterxml.jackson.annotation.JsonProperty
import com.fnFCinema.fnFCinema.config.properties.MovieDetailsServiceProperties
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.client.RestTemplate

@Service
class MovieDetailsService(val restTemplate: RestTemplate, val properties: MovieDetailsServiceProperties) {

    fun findByImdbId(imdbId: String): ImdbMovieResponse {
        // TODO: refactor this to be be able work with responses different than success
        val responseObject = object : ParameterizedTypeReference<ImdbMovieResponse>() {}
        return restTemplate.exchange(
                "${properties.domain}/?apikey=${properties.apiKey}&i=$imdbId",
                HttpMethod.GET,
                HttpEntity(null, null),
                responseObject
        ).body ?: throw UnableToGetMovieDetailsException()
    }
}

// TODO: do all mappings
data class ImdbMovieResponse(@JsonProperty("imdbID") val imdbId: String,
                             @JsonProperty("Title") val title: String,
                             @JsonProperty("Genre") val genre: String,
                             @JsonProperty("Actors") val actors: String,
                             @JsonProperty("Ratings") val ratings: List<ImdbMovieRatingResponse>) {
    data class ImdbMovieRatingResponse(@JsonProperty("Source") val source: String, @JsonProperty("Value") val value: String)
}

@ResponseStatus(value = HttpStatus.BAD_GATEWAY, reason = "Unable to get movie details from IMDB")
class UnableToGetMovieDetailsException: RuntimeException()