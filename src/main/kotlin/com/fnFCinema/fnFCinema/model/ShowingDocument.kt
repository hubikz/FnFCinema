package com.fnFCinema.fnFCinema.model

import com.fnFCinema.fnFCinema.api.v1.request.ShowingRequest
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.index.CompoundIndexes
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.util.*

@Document(collection = "showings")
@CompoundIndexes(
        value = [
            CompoundIndex(def = "{'movieId': 1, 'showAt': 1 }", name = "movieId_showAt_index")
        ]
)
data class ShowingDocument(@Id val id: String,
                           @Indexed val movieId: String,
                           val price: BigDecimal,
                           val showAt: Date) {

    companion object {
        fun create(movieId: String, showingRequest: ShowingRequest): ShowingDocument {
            return ShowingDocument(UUID.randomUUID().toString(), movieId, showingRequest.price, showingRequest.startShowAt)
        }
    }
}
