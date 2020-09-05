package com.fnFCinema.fnFCinema.api.v1

import com.fnFCinema.fnFCinema.api.request.ApiGatewayHeaders
import com.fnFCinema.fnFCinema.api.v1.request.NewMovieRequest
import com.fnFCinema.fnFCinema.extensions.toJson
import com.fnFCinema.fnFCinema.repository.MovieRepository
import com.fnFCinema.fnFCinema.stubs.ImdbResponseStubs
import com.github.tomakehurst.wiremock.client.WireMock
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureWireMock(port = 0)
internal class MoviesControllerTest: ImdbResponseStubs {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var movieRepository: MovieRepository

    @BeforeEach
    @AfterEach
    fun cleanup() {
        // clear DB
        movieRepository.deleteAll()
        WireMock.reset()
    }

    @Test
    fun `Should allow add movie based on IMDB Id and get content from external API`() {
        val userId = "blazej"
        val imdbId = "tt0232500"

        stubGetImdbMovie(imdbId, WireMock.okJson(stubImdbMovieResponse()))

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/movies")
                        .header(ApiGatewayHeaders.USER_ID_HEADER_NAME, userId)
                        .contentType("application/json")
                        .content(NewMovieRequest(imdbId).toJson())
        )
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.imdbId").value(imdbId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("The Fast and the Furious"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genre").value("Action, Crime, Thriller"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.actors").value("Paul Walker, Vin Diesel, Michelle Rodriguez, Jordana Brewster"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ratings.length()").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ratings[0].source").value("Internet Movie Database"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ratings[0].value").value("6.8/10"))
                // TODO: add checking rest of ratings
    }

    fun `Should return 502 status code when IMDB API is unavailable`() {

    }

    fun `Should not allow add movie when user is not authorized`() {

    }

    fun `Should allow modify movie attributes`() {

    }

    fun `Should not allow modify movie attributes when user is not authorized`() {

    }
}