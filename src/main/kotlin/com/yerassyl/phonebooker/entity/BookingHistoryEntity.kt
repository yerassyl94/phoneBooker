package com.yerassyl.phonebooker.entity

import com.yerassyl.phonebooker.enum.BookingStatus
import java.time.OffsetDateTime
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "booking_history")
data class BookingHistoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,
    @Column(name = "user_id")
    val user: Int,
    val updated: OffsetDateTime,
    @Enumerated(value = EnumType.STRING)
    val status: BookingStatus,
    @ManyToOne
    @JoinColumn(name = "device_id")
    val device: DeviceEntity
)
