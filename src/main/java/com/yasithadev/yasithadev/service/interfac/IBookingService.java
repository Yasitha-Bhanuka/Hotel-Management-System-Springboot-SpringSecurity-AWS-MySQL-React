package com.yasithadev.yasithadev.service.interfac;

import com.yasithadev.yasithadev.dto.Response;
import com.yasithadev.yasithadev.entity.Booking;

public interface IBookingService {
    Response saveBooking(Long roomId, Long userId, Booking bookingRequest);

    Response findBookingByConfirmationCode(String confirmationCode);

    Response getAllBookings();

    Response cancelBooking(Long bookingId);
}
