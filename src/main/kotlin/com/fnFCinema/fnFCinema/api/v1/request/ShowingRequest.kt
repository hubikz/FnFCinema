package com.fnFCinema.fnFCinema.api.v1.request

import org.springframework.format.annotation.DateTimeFormat
import java.math.BigDecimal
import java.util.*

class ShowingRequest(val price: BigDecimal,
                     @field:DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) val startShowAt: Date)