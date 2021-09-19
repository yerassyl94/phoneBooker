package com.yerassyl.phonebooker.service

import com.yerassyl.phonebooker.entity.DeviceEntity
import com.yerassyl.phonebooker.exception.NotFoundException
import com.yerassyl.phonebooker.mapper.toDto
import com.yerassyl.phonebooker.model.response.DeviceResponse
import com.yerassyl.phonebooker.repository.DeviceRepository
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class DeviceService(private val deviceRepository: DeviceRepository, @Lazy private val bookingService: BookingService) {

    fun getAll(): List<DeviceResponse> = deviceRepository.findAll().map { it.toDto(bookingService.isAvailable(it.id)) }

    fun get(id: UUID): DeviceEntity = deviceRepository.findById(id)
        .orElseThrow { NotFoundException("Device with id:$id not found") }

    fun get(name: String): DeviceEntity = deviceRepository.findByName(name)
        .orElseThrow { NotFoundException("Device with name:$name not found") }

    fun getDevice(id: UUID): DeviceResponse = get(id).toDto(bookingService.isAvailable(id))
}
