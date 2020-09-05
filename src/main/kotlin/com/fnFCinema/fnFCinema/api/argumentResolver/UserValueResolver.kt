package com.fnFCinema.fnFCinema.api.argumentResolver

import com.fnFCinema.fnFCinema.api.request.ApiGatewayHeaders
import com.fnFCinema.fnFCinema.api.request.XUserRequest
import org.springframework.core.MethodParameter
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

class UserValueResolver : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.parameterType == XUserRequest::class.java
    }

    @Throws(Exception::class)
    override fun resolveArgument(parameter: MethodParameter, mavContainer: ModelAndViewContainer?, webRequest: NativeWebRequest, binderFactory: WebDataBinderFactory?): Any? {
        val userIdString = webRequest.getHeader(ApiGatewayHeaders.USER_ID_HEADER_NAME)
                ?: throw InvalidXUserException("User Not Authorized")

        return XUserRequest(
                userIdString
        )
    }
}

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "User Not Authorized")
class InvalidXUserException(s: String) : IllegalArgumentException(s)