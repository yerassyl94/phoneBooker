package com.yerassyl.phonebooker.service

import com.yerassyl.phonebooker.entity.BookingHistoryEntity
import com.yerassyl.phonebooker.entity.DeviceEntity
import com.yerassyl.phonebooker.enum.BookingStatus
import com.yerassyl.phonebooker.repository.BookingRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.time.OffsetDateTime
import java.util.UUID

@ExtendWith(MockKExtension::class)
class BookingServiceTest {

    @MockK
    private lateinit var bookingRepository: BookingRepository

    @MockK
    private lateinit var deviceService: DeviceService

    @InjectMockKs
    private lateinit var bookService: BookingService

    @Test
    fun `device must be available for booking if no history found`() {
        val uuid = UUID.randomUUID()
        every { bookService.getLastBook(uuid) } returns null
        assertEquals(true, bookService.isAvailable(uuid))
    }

    @Test
    fun `device should be available for booking if last record shows returned`() {
        val uuid = UUID.randomUUID()
        every { bookService.getLastBook(uuid) } returns
            BookingHistoryEntity(
                id = UUID.randomUUID(),
                user = 1,
                updated = OffsetDateTime.now(),
                status = BookingStatus.RETURNED,
                device = DeviceEntity(
                    id = uuid,
                    name = "Test",
                    twoG = "2g",
                    threeG = "3g",
                    fourG = "4g",
                    technology = "tech"
                )
            )
        assertEquals(true, bookService.isAvailable(uuid))
    }

    @Test
    fun `device is not available for booking if status booked`() {
        val uuid = UUID.randomUUID()
        every { bookService.getLastBook(uuid) } returns
            BookingHistoryEntity(
                id = UUID.randomUUID(),
                user = 1,
                updated = OffsetDateTime.now(),
                status = BookingStatus.BOOKED,
                device = DeviceEntity(
                    id = uuid,
                    name = "Test",
                    twoG = "2g",
                    threeG = "3g",
                    fourG = "4g",
                    technology = "tech"
                )
            )
        assertEquals(false, bookService.isAvailable(uuid))
    }
}
