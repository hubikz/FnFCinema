package com.fnFCinema.fnFCinema.repository

import com.fnFCinema.fnFCinema.model.MovieDocument
import org.springframework.data.mongodb.repository.MongoRepository

interface MovieRepository : MongoRepository<MovieDocument, String>
