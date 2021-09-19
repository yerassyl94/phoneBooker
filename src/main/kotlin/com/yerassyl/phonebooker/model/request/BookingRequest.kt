package com.yerassyl.phonebooker.model.request

import java.util.UUID

data class BookingRequest(
    val id: UUID,
    val user: Int
)
