package com.fnFCinema.fnFCinema.config

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Clock

@Configuration
class ClockConfiguration() {

    @Bean
    @ConditionalOnMissingBean(Clock::class)
    fun clock(): Clock {
        return Clock.systemUTC()
    }
}
