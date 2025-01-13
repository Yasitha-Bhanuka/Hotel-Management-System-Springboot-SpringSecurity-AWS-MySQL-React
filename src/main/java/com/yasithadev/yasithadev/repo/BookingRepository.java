package com.yasithadev.yasithadev.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yasithadev.yasithadev.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    Optional<Booking> findByBookingConfirmationCode(String confirmationCode);
}
