package com.fnFCinema.fnFCinema.api.v1

import com.fnFCinema.fnFCinema.MovieDetailsService
import com.fnFCinema.fnFCinema.api.request.XUserRequest
import com.fnFCinema.fnFCinema.api.v1.request.NewMovieRequest
import com.fnFCinema.fnFCinema.api.v1.resource.MovieRepresentationModel
import io.swagger.v3.oas.annotations.Parameter
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid


@RestController
@RequestMapping(path = ["/api/v1/movies"], produces = ["application/json;charset=UTF-8"])
class MoviesController(val movieDetailsService: MovieDetailsService) {

    @PostMapping("")
    fun addMovie(@Parameter(hidden = true) xUser: XUserRequest, @Valid @RequestBody newMovieRequest: NewMovieRequest): MovieRepresentationModel {

        val imdbMovie = movieDetailsService.findByImdbId(newMovieRequest.imdbId)

        // TODO: add saving to DB here

        return MovieRepresentationModel.from(newMovieRequest.imdbId, imdbMovie)
    }
}
