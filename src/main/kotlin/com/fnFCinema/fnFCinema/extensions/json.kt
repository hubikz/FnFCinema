package com.fnFCinema.fnFCinema.extensions

import com.fasterxml.jackson.databind.ObjectMapper

val objectMapper = ObjectMapper()

fun Any.toJson(): String = objectMapper.writeValueAsString(this)
