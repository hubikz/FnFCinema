package com.fnFCinema.fnFCinema.repository

import com.fnFCinema.fnFCinema.model.ShowingDocument
import org.springframework.data.mongodb.repository.MongoRepository

interface ShowingRepository : MongoRepository<ShowingDocument, String>
