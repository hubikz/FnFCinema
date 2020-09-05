package com.fnFCinema.fnFCinema.api.v1

import com.fnFCinema.fnFCinema.api.request.XUserRequest
import com.fnFCinema.fnFCinema.api.v1.request.NewMovieRequest
import com.fnFCinema.fnFCinema.api.v1.request.ShowingRequest
import com.fnFCinema.fnFCinema.api.v1.resource.MovieRepresentationModel
import com.fnFCinema.fnFCinema.api.v1.resource.ShowRepresentationModel
import com.fnFCinema.fnFCinema.service.MovieService
import io.swagger.v3.oas.annotations.Parameter
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping(path = ["/api/v1/movies"], produces = ["application/json;charset=UTF-8"])
class MoviesController(val movieService: MovieService) {

    @PostMapping("")
    fun addMovie(@Parameter(hidden = true) xUser: XUserRequest, @Valid @RequestBody newMovieRequest: NewMovieRequest): MovieRepresentationModel {
        return MovieRepresentationModel.from(movieService.create(newMovieRequest))
    }

    @PostMapping("/{movieId}/showings")
    fun addShowing(@Parameter(hidden = true) xUser: XUserRequest,
                   @PathVariable("movieId") movieId: String,
                   @Valid @RequestBody showingRequest: ShowingRequest): ShowRepresentationModel {
        return ShowRepresentationModel.from(movieService.addShowing(movieId, showingRequest))
    }

    @GetMapping("/{movieId}/showings")
    fun getShowing(@PathVariable("movieId") movieId: String,
                   @Valid @RequestBody showingRequest: ShowingRequest): ShowRepresentationModel {
        return ShowRepresentationModel.from(movieService.addShowing(movieId, showingRequest))
    }
}
