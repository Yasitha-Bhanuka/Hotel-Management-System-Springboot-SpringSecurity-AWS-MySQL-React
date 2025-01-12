package com.yasithadev.yasithadev.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yasithadev.yasithadev.entity.Room;
import com.yasithadev.yasithadev.entity.User;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingDTO {
    private Long id;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private int numOfAdults;
    
    private int numOfChildren;
    
    private int totalNumOfGuest;
    
    private String bookingConfimationCode;

    private User user;

    private Room room;
}

