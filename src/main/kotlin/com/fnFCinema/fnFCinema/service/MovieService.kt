package com.fnFCinema.fnFCinema.service

import com.fnFCinema.fnFCinema.api.v1.request.NewMovieRequest
import com.fnFCinema.fnFCinema.api.v1.request.ShowingRequest
import com.fnFCinema.fnFCinema.extensions.toJson
import com.fnFCinema.fnFCinema.model.MovieDocument
import com.fnFCinema.fnFCinema.model.ShowingDocument
import com.fnFCinema.fnFCinema.repository.MovieRepository
import com.fnFCinema.fnFCinema.repository.ShowingRepository
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.ResponseStatus
import java.time.Clock
import java.util.*

@Service
class MovieService(val movieDetailsService: MovieDetailsService,
                   val clock: Clock,
                   val movieRepository: MovieRepository,
                   val showingRepository: ShowingRepository
        ) {

    private val logger = LoggerFactory.getLogger(MovieService::class.java)

    fun create(request: NewMovieRequest): MovieDocument {
        return movieDetailsService.findByImdbId(request.imdbId).let {
            MovieDocument.create(it, Date.from(clock.instant()))
        }.let {
            movieRepository.save(it).also {
                // TODO: send event about creation to event bus
                logger.info("Movie added {}", it.toJson())
            }

        }
    }

    fun addShowing(imdbId: String, request: ShowingRequest): ShowingDocument {
        // TODO: add validation here not allowing add showing in same time
        // IMDB API have movie runtime information - we can use it to this validation
        return movieRepository.findByIdOrNull(imdbId)?.let {
            showingRepository.save(ShowingDocument.create(imdbId, request)).also {
                // TODO send event about creation to event bus
                logger.info("Showing added {}", it.toJson())
            }
        } ?: throw MovieNotFoundException()
    }
}

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Movie not found")
class MovieNotFoundException : RuntimeException()

