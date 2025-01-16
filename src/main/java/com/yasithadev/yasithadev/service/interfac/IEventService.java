package com.yasithadev.yasithadev.service.interfac;

import org.springframework.web.multipart.MultipartFile;
import com.yasithadev.yasithadev.dto.Response;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface IEventService {
    Response addEvent(MultipartFile photo, String eventName, String artistName, 
                     LocalDateTime eventDateTime, String location, 
                     String description, BigDecimal price);
    
    Response getAllEvents();
    
    Response getEventById(Long eventId);
    
    Response updateEvent(Long eventId, MultipartFile photo, String eventName, 
                        String artistName, LocalDateTime eventDateTime, 
                        String location, String description, BigDecimal price);
    
    Response deleteEvent(Long eventId);
} 