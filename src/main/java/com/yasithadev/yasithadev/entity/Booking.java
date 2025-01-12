package com.yasithadev.yasithadev.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Check in date is required")
    private LocalDate checkInDate;

    @Future(message = "Check out date must be in future")
    private LocalDate checkOutDate;

    @Min(value = 1, message = "Number of adults must be at less that 1")
    private int numOfAdults;
    
    @Min(value = 0, message = "Number of children must be at less that 0")
    private int numOfChildren;
    
    private int totalNumOfGuest;
    
    private String bookingConfirmationCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    public void calculateTotalNumberOfGuest(){
        this.totalNumOfGuest = this.numOfAdults + this.numOfChildren;
    }

    public void setNumOfAdults(int numOfAdults){
        this.numOfAdults= numOfAdults;
        calculateTotalNumberOfGuest();
    }

    public void setNumOfChildren(int numOfChildren){
        this.numOfChildren = numOfChildren;
        calculateTotalNumberOfGuest();
    }

    @Override
    public String toString() {
        return "Booking {"+
        "id=" + id +
        ", checkInDate=" + checkInDate + 
        ", checkOutDate=" + checkOutDate + 
        ", numOfAdults=" + numOfAdults + 
        ", numOfChildren=" + numOfChildren + 
        ", totalNumOfGuest=" + totalNumOfGuest + 
        ", bookingConfirmationCode=" + bookingConfirmationCode + '\'' +
        "}";
    }

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookingConfirmationCode() {
        return bookingConfirmationCode;
    }

    public void setBookingConfirmationCode(String bookingConfirmationCode) {
        this.bookingConfirmationCode = bookingConfirmationCode;
    }

}
