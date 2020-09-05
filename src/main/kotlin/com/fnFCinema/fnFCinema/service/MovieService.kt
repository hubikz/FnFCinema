package com.fnFCinema.fnFCinema.service

import com.fnFCinema.fnFCinema.api.v1.request.NewMovieRequest
import com.fnFCinema.fnFCinema.model.MovieDocument
import com.fnFCinema.fnFCinema.repository.MovieRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.Clock
import java.util.*

@Service
class MovieService(val movieDetailsService: MovieDetailsService,
                   val clock: Clock,
                   val repository: MovieRepository
        ) {

    fun create(request: NewMovieRequest): MovieDocument {
        return movieDetailsService.findByImdbId(request.imdbId).let {
            MovieDocument.create(it, Date.from(clock.instant()))
        }.let {
            repository.save(it)
            // TODO: send event about creation to event bus
        }
    }
}

