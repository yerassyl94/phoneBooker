package com.yerassyl.phonebooker.controller

import com.yerassyl.phonebooker.model.response.DeviceResponse
import com.yerassyl.phonebooker.service.DeviceService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/device")
class DeviceController(private val deviceService: DeviceService) {

    @GetMapping("/all")
    fun getAllDevices(): ResponseEntity<List<DeviceResponse>> = ResponseEntity.ok(deviceService.getAll())

    @GetMapping
    fun getDevice(@RequestParam id: UUID): ResponseEntity<DeviceResponse> =
        ResponseEntity.ok(deviceService.getDevice(id))
}
