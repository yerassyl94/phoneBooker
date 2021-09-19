package com.yerassyl.phonebooker.service

import com.yerassyl.phonebooker.entity.BookingHistoryEntity
import com.yerassyl.phonebooker.enum.BookingStatus
import com.yerassyl.phonebooker.exception.BadRequestException
import com.yerassyl.phonebooker.exception.NotFoundException
import com.yerassyl.phonebooker.mapper.toDto
import com.yerassyl.phonebooker.mapper.toEntity
import com.yerassyl.phonebooker.model.request.BookingRequest
import com.yerassyl.phonebooker.model.response.BookingResponse
import com.yerassyl.phonebooker.repository.BookingRepository
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class BookingService(private val bookingRepository: BookingRepository, @Lazy private val deviceService: DeviceService) {
    fun getLastBook(deviceId: UUID): BookingHistoryEntity? {
        val device = deviceService.get(deviceId)
        return bookingRepository.findFirstByDeviceOrderByUpdatedDesc(device).orElse(null)
    }

    fun isAvailable(deviceId: UUID): Boolean {
        val bookingInfo = getLastBook(deviceId)
        return bookingInfo == null || bookingInfo.status == BookingStatus.RETURNED
    }

    fun getLastBookingInfo(deviceId: UUID): BookingResponse {
        val bookingInfo = getLastBook(deviceId) ?: throw NotFoundException("Device booking history not found")

        return bookingInfo.toDto()
    }

    fun bookDevice(bookingRequest: BookingRequest) {
        if (!isAvailable(bookingRequest.id)) {
            throw BadRequestException("Device is not available")
        }

        bookingRepository.save(bookingRequest.toEntity(BookingStatus.BOOKED, deviceService.get(bookingRequest.id)))
    }

    fun returnDevice(bookingRequest: BookingRequest) {
        val lastRecord = getLastBook(bookingRequest.id) ?: throw NotFoundException("Device booking history not found")

        if (lastRecord.status == BookingStatus.RETURNED) {
            throw BadRequestException("Device is already returned")
        }

        bookingRepository.save(bookingRequest.toEntity(BookingStatus.RETURNED, lastRecord.device))
    }
}
