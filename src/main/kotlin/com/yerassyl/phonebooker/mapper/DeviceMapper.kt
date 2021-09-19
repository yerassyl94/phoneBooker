package com.yerassyl.phonebooker.mapper

import com.yerassyl.phonebooker.entity.DeviceEntity
import com.yerassyl.phonebooker.model.response.DeviceResponse

fun DeviceEntity.toDto(isAvailable: Boolean) =
    DeviceResponse(
        id = this.id,
        name = this.name,
        isAvailable = isAvailable,
        twoG = this.twoG,
        threeG = this.threeG,
        fourG = this.fourG,
        technology = this.technology
    )
