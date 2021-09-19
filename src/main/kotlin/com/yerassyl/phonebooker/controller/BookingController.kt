package com.yerassyl.phonebooker.controller

import com.yerassyl.phonebooker.model.request.BookingRequest
import com.yerassyl.phonebooker.model.response.BookingResponse
import com.yerassyl.phonebooker.service.BookingService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/booking")
class BookingController(val bookingService: BookingService) {
    @PostMapping("/book")
    fun book(@RequestBody body: BookingRequest): ResponseEntity<Void> {
        bookingService.bookDevice(body)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PostMapping("/return")
    fun returnDevice(@RequestBody body: BookingRequest): ResponseEntity<Void> {
        bookingService.returnDevice(body)
        return ResponseEntity.status(HttpStatus.ACCEPTED).build()
    }

    @GetMapping("/isAvailable")
    fun isDeviceAvailable(@RequestParam id: UUID): ResponseEntity<Boolean> =
        ResponseEntity.ok(bookingService.isAvailable(id))

    @GetMapping("/getLast")
    fun getLastDeviceRecord(@RequestParam id: UUID): ResponseEntity<BookingResponse> =
        ResponseEntity.ok(bookingService.getLastBookingInfo(id))
}
