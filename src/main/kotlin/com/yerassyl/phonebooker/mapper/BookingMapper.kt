package com.yerassyl.phonebooker.mapper

import com.yerassyl.phonebooker.entity.BookingHistoryEntity
import com.yerassyl.phonebooker.entity.DeviceEntity
import com.yerassyl.phonebooker.enum.BookingStatus
import com.yerassyl.phonebooker.model.request.BookingRequest
import com.yerassyl.phonebooker.model.response.BookingResponse
import java.time.OffsetDateTime

fun BookingHistoryEntity.toDto() = BookingResponse(
    id = this.id,
    updated = this.updated,
    user = this.user,
    status = this.status
)

fun BookingRequest.toEntity(status: BookingStatus, device: DeviceEntity) =
    BookingHistoryEntity(user = this.user, updated = OffsetDateTime.now(), status = status, device = device)
