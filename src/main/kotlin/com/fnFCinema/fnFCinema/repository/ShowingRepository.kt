package com.fnFCinema.fnFCinema.repository

import com.fnFCinema.fnFCinema.model.ShowingDocument
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.repository.MongoRepository

interface ShowingRepository : MongoRepository<ShowingDocument, String>, ShowingSearchRepository

interface ShowingSearchRepository {
    fun findByCriteria(criteria: Criteria, pageRequest: Pageable): Page<ShowingDocument>
    fun countByCriteria(criteria: Criteria): Long
}

class ShowingSearchRepositoryImpl(val mongoTemplate: MongoTemplate) : ShowingSearchRepository {

    override fun findByCriteria(criteria: Criteria, pageRequest: Pageable): Page<ShowingDocument> {
        val total = countByCriteria(criteria)
        return PageImpl(mongoTemplate.find(Query(criteria).with(pageRequest), ShowingDocument::class.java), pageRequest, total)
    }

    override fun countByCriteria(criteria: Criteria): Long {
        val query = Query(criteria)
        return mongoTemplate.count(query, ShowingDocument::class.java)
    }
}
