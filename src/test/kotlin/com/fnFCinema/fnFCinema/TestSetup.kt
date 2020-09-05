package com.fnFCinema.fnFCinema

import com.fnFCinema.fnFCinema.api.request.ApiGatewayHeaders
import com.fnFCinema.fnFCinema.api.v1.request.NewMovieRequest
import com.fnFCinema.fnFCinema.extensions.toJson
import com.fnFCinema.fnFCinema.stubs.ImdbResponseStubs
import com.github.tomakehurst.wiremock.client.WireMock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

abstract class TestSetup : ImdbResponseStubs {

    @Autowired
    lateinit var mockMvc: MockMvc

    fun createMovie(imdbId: String, userId: String) {
        stubGetImdbMovie(imdbId, WireMock.okJson(stubImdbMovieResponse()))

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/movies")
                        .header(ApiGatewayHeaders.USER_ID_HEADER_NAME, userId)
                        .contentType("application/json")
                        .content(NewMovieRequest(imdbId).toJson())
        )
    }
}
