package com.yerassyl.phonebooker.entity

import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "devices")
data class DeviceEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID,
    val name: String,
    val technology: String,
    @Column(name = "two_g")
    val twoG: String?,
    @Column(name = "three_g")
    val threeG: String?,
    @Column(name = "four_g")
    val fourG: String?
)
