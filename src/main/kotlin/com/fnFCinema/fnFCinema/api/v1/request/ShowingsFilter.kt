package com.fnFCinema.fnFCinema.api.v1.request

import com.fnFCinema.fnFCinema.extensions.rangeCriteria
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.format.annotation.DateTimeFormat
import java.util.*

class ShowingsFilter(val movieId: String? = null,
                     @field:DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) val startShowAtFrom: Date? = null,
                     @field:DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) val startShowAtTo: Date? = null) {

    fun toQueryCriteria(): Criteria {
        val criteria = Criteria()
        movieId?.let { criteria.and("movieId").`is`(it) }
        criteria.rangeCriteria("createdDate", startShowAtFrom, startShowAtTo)
        return criteria;
    }
}
