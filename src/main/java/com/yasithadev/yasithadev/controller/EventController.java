package com.yasithadev.yasithadev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.yasithadev.yasithadev.dto.Response;
import com.yasithadev.yasithadev.service.interfac.IEventService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private IEventService eventService;

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> addEvent(
            @RequestParam(value = "photo", required = false) MultipartFile photo,
            @RequestParam("eventName") String eventName,
            @RequestParam("artistName") String artistName,
            @RequestParam("eventDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime eventDateTime,
            @RequestParam("location") String location,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam("price") BigDecimal price
    ) {
        Response response = eventService.addEvent(photo, eventName, artistName, 
                                                eventDateTime, location, description, price);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<Response> getAllEvents() {
        Response response = eventService.getAllEvents();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/event-by-id/{eventId}")
    public ResponseEntity<Response> getEventById(@PathVariable Long eventId) {
        Response response = eventService.getEventById(eventId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PutMapping("/update/{eventId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> updateEvent(
            @PathVariable Long eventId,
            @RequestParam(value = "photo", required = false) MultipartFile photo,
            @RequestParam(value = "eventName", required = false) String eventName,
            @RequestParam(value = "artistName", required = false) String artistName,
            @RequestParam(value = "eventDateTime", required = false) 
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime eventDateTime,
            @RequestParam(value = "location", required = false) String location,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "price", required = false) BigDecimal price
    ) {
        Response response = eventService.updateEvent(eventId, photo, eventName, 
                                                   artistName, eventDateTime, location, 
                                                   description, price);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/delete/{eventId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> deleteEvent(@PathVariable Long eventId) {
        Response response = eventService.deleteEvent(eventId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
} 