package com.fnFCinema.fnFCinema.api.v1.resource

import com.fasterxml.jackson.annotation.JsonFormat
import com.fnFCinema.fnFCinema.model.ShowingDocument
import org.springframework.format.annotation.DateTimeFormat
import java.math.BigDecimal
import java.util.*

data class ShowRepresentationModel(val id: String,
                                   val imdbId: String,
                                   val price: BigDecimal,
                                   @field:JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX") val startShowAt: Date) {

    companion object {
        fun from(showingDocument: ShowingDocument): ShowRepresentationModel {
            return with (showingDocument) {
                ShowRepresentationModel(id, movieId, price, showAt)
            }
        }
    }
}
