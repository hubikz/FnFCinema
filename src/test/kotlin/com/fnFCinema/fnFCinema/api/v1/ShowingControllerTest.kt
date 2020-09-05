package com.fnFCinema.fnFCinema.api.v1

import com.fnFCinema.fnFCinema.TestSetup
import com.fnFCinema.fnFCinema.api.request.ApiGatewayHeaders
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureWireMock(port = 0)
internal class ShowingControllerTest : TestSetup() {

    @Test
    fun `Should find shows by filter`() {
        val imdbId = "tt0232500"
        val userId = "blazej"
        createMovie(imdbId, userId)

        createShow(imdbId, userId, "50.0", "2020-09-05T23:00:00.000Z")
        createShow(imdbId, userId, "30.0", "2020-09-05T24:00:00.000Z")

        val andReturn = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/showings")
                        .param("movieId", "tt0232500")
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.length()").value(2))
    }

    @Test
    fun `Should not find shows by not existed movieId`() {
        val imdbId = "tt0232500"
        val userId = "blazej"
        createMovie(imdbId, userId)

        createShow(imdbId, userId, "50.0", "2020-09-05T23:00:00.000Z")
        createShow(imdbId, userId, "30.0", "2020-09-05T24:00:00.000Z")

        val andReturn = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/showings")
                        .param("movieId", "not3xistedId")
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.length()").value(0))
    }
}