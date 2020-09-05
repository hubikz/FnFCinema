package com.fnFCinema.fnFCinema

import com.fnFCinema.fnFCinema.api.request.ApiGatewayHeaders
import com.fnFCinema.fnFCinema.api.v1.request.NewMovieRequest
import com.fnFCinema.fnFCinema.extensions.toJson
import com.fnFCinema.fnFCinema.repository.MovieRepository
import com.fnFCinema.fnFCinema.repository.ShowingRepository
import com.fnFCinema.fnFCinema.stubs.ImdbResponseStubs
import com.github.tomakehurst.wiremock.client.WireMock
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

abstract class TestSetup : ImdbResponseStubs {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var movieRepository: MovieRepository

    @Autowired
    lateinit var showingRepository: ShowingRepository

    @BeforeEach
    @AfterEach
    fun cleanup() {
        // clear DB
        movieRepository.deleteAll()
        showingRepository.deleteAll()
        WireMock.reset()
    }

    fun createMovie(imdbId: String, userId: String) {
        stubGetImdbMovie(imdbId, WireMock.okJson(stubImdbMovieResponse()))

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/movies")
                        .header(ApiGatewayHeaders.USER_ID_HEADER_NAME, userId)
                        .contentType("application/json")
                        .content(NewMovieRequest(imdbId).toJson())
        )
    }

    fun createShow(imdbId: String, userId: String, price: String, startShowAt: String) {
        val payload: String = """{"price": "$price", "startShowAt": "$startShowAt"}"""
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/movies/$imdbId/showings")
                        .header(ApiGatewayHeaders.USER_ID_HEADER_NAME, userId)
                        .contentType("application/json")
                        .content(payload))
                .andExpect(MockMvcResultMatchers.status().isOk)
    }
}
