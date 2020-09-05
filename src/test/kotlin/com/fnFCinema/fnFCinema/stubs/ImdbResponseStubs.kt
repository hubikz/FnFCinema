package com.fnFCinema.fnFCinema.stubs

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.matching.StringValuePattern
import com.github.tomakehurst.wiremock.stubbing.StubMapping

interface ImdbResponseStubs {

    fun stubGetImdbMovie(imdbId: String, expectedResponse: ResponseDefinitionBuilder): StubMapping {
        return WireMock.stubFor(
                with(WireMock.get("/?apikey=f4k3k3Y&i=$imdbId")) {
                    willReturn(expectedResponse)
                }
        )
    }

    fun stubImdbMovieResponse(): String {
        return """
            {  
               "Title":"The Fast and the Furious",
               "Year":"2001",
               "Rated":"PG-13",
               "Released":"22 Jun 2001",
               "Runtime":"106 min",
               "Genre":"Action, Crime, Thriller",
               "Director":"Rob Cohen",
               "Writer":"Ken Li (magazine article \"Racer X\"), Gary Scott Thompson (screen story), Gary Scott Thompson (screenplay), Erik Bergquist (screenplay), David Ayer (screenplay)",
               "Actors":"Paul Walker, Vin Diesel, Michelle Rodriguez, Jordana Brewster",
               "Plot":"Los Angeles police officer Brian O'Conner must decide where his loyalty really lies when he becomes enamored with the street racing world he has been sent undercover to destroy.",
               "Language":"English, Spanish",
               "Country":"USA, Germany",
               "Awards":"11 wins & 12 nominations.",
               "Poster":"https://m.media-amazon.com/images/M/MV5BNzlkNzVjMDMtOTdhZC00MGE1LTkxODctMzFmMjkwZmMxZjFhXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg",
               "Ratings":[  
                  {  
                     "Source":"Internet Movie Database",
                     "Value":"6.8/10"
                  },
                  {  
                     "Source":"Rotten Tomatoes",
                     "Value":"53%"
                  },
                  {  
                     "Source":"Metacritic",
                     "Value":"58/100"
                  }
               ],
               "Metascore":"58",
               "imdbRating":"6.8",
               "imdbVotes":"322,264",
               "imdbID":"tt0232500",
               "Type":"movie",
               "DVD":"01 Jan 2002",
               "BoxOffice":"\\$142,542,950",
               "Production":"Universal Pictures",
               "Website":"http://www.thefastandthefurious.com",
               "Response":"True"
            }
        """.trimIndent()
    }
}