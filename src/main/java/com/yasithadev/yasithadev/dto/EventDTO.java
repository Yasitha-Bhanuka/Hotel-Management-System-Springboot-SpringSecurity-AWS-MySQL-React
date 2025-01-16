package com.yasithadev.yasithadev.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventDTO {
    private Long id;
    private String eventName;
    private String artistName;
    private LocalDateTime eventDateTime;
    private String location;
    private String description;
    private String eventPhotoUrl;
    private BigDecimal price;
} 