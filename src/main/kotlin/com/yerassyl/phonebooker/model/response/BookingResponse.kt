package com.yerassyl.phonebooker.model.response

import com.yerassyl.phonebooker.enum.BookingStatus
import java.time.OffsetDateTime
import java.util.UUID

data class BookingResponse(
    val id: UUID?,
    val updated: OffsetDateTime,
    val user: Int,
    val status: BookingStatus
)
