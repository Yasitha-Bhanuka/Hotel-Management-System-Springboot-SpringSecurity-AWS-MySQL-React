package com.yasithadev.yasithadev.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Event name is required")
    private String eventName;

    @NotBlank(message = "Artist name is required")
    private String artistName;

    @NotNull(message = "Event date and time is required")
    private LocalDateTime eventDateTime;

    @NotBlank(message = "Event location is required")
    private String location;

    private String description;
    
    private String eventPhotoUrl;
    
    @NotNull(message = "Price is required")
    private BigDecimal price;
} 