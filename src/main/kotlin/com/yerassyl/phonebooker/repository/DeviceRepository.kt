package com.yerassyl.phonebooker.repository

import com.yerassyl.phonebooker.entity.DeviceEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional
import java.util.UUID

interface DeviceRepository : JpaRepository<DeviceEntity, UUID> {
    fun findByName(name: String): Optional<DeviceEntity>
}
