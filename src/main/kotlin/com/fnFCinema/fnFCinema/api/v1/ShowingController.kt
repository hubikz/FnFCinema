package com.fnFCinema.fnFCinema.api.v1

import com.fnFCinema.fnFCinema.api.v1.request.ShowingsFilter
import com.fnFCinema.fnFCinema.api.v1.resource.ShowRepresentationModel
import com.fnFCinema.fnFCinema.model.ShowingDocument
import com.fnFCinema.fnFCinema.repository.ShowingRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/api/v1/showings"], produces = ["application/json;charset=UTF-8"])
class ShowingController(val showingRepository: ShowingRepository) {

    @GetMapping("")
    fun getShowings(filters: ShowingsFilter,
                    page: Pageable): Page<ShowingDocument> {
        // FIXME: add mapping here and enrich with data from MovieDocument
        return showingRepository.findByCriteria(filters.toQueryCriteria(), page)
    }
}
