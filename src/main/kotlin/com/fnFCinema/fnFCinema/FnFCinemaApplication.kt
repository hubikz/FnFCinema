package com.fnFCinema.fnFCinema

import com.fnFCinema.fnFCinema.config.properties.MovieDetailsServiceProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(MovieDetailsServiceProperties::class)
class FnFCinemaApplication

fun main(args: Array<String>) {
    runApplication<FnFCinemaApplication>(*args)
}
