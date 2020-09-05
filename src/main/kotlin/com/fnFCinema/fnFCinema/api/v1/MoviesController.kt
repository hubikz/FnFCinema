package com.fnFCinema.fnFCinema.api.v1

import com.fnFCinema.fnFCinema.service.MovieDetailsService
import com.fnFCinema.fnFCinema.api.request.XUserRequest
import com.fnFCinema.fnFCinema.api.v1.request.NewMovieRequest
import com.fnFCinema.fnFCinema.api.v1.resource.MovieRepresentationModel
import com.fnFCinema.fnFCinema.service.MovieService
import io.swagger.v3.oas.annotations.Parameter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid


@RestController
@RequestMapping(path = ["/api/v1/movies"], produces = ["application/json;charset=UTF-8"])
class MoviesController(val movieService: MovieService) {

    @PostMapping("")
    fun addMovie(@Parameter(hidden = true) xUser: XUserRequest, @Valid @RequestBody newMovieRequest: NewMovieRequest): MovieRepresentationModel {
        return MovieRepresentationModel.from(movieService.create(newMovieRequest))
    }
}
