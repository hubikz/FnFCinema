package com.fnFCinema.fnFCinema.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@ConstructorBinding
@ConfigurationProperties(prefix = "app.movie-details-service")
data class MovieDetailsServiceProperties(val domain: String, val apiKey: String)
