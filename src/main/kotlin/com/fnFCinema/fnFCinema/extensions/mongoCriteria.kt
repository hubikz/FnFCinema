package com.fnFCinema.fnFCinema.extensions

import org.springframework.data.mongodb.core.query.Criteria

fun Criteria.rangeCriteria(name: String, from: Any?, to: Any?) {
    if (from!=null || to!=null) {
        this.and(name).apply {
            from?.let { gte(it) }
            to?.let { lte(it) }
        }
    }
}
