package com.yerassyl.phonebooker.repository

import com.yerassyl.phonebooker.entity.BookingHistoryEntity
import com.yerassyl.phonebooker.entity.DeviceEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import java.util.UUID

interface BookingRepository : JpaRepository<BookingHistoryEntity, UUID> {
    fun findFirstByDeviceOrderByUpdatedDesc(device: DeviceEntity): Optional<BookingHistoryEntity>
}
