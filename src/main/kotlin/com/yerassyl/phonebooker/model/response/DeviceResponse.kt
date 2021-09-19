package com.yerassyl.phonebooker.model.response

import java.util.UUID

data class DeviceResponse(
    val id: UUID?,
    val name: String,
    val isAvailable: Boolean,
    val twoG: String?,
    val threeG: String?,
    val fourG: String?,
    val technology: String
)
