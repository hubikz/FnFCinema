package com.fnFCinema.fnFCinema.config

import com.fnFCinema.fnFCinema.api.argumentResolver.UserValueResolver
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class XUserConfiguration: WebMvcConfigurer {
    override fun addArgumentResolvers(argumentResolvers: MutableList<HandlerMethodArgumentResolver>) {
        argumentResolvers.add(UserValueResolver())
    }
}
