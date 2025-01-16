package com.yasithadev.yasithadev.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yasithadev.yasithadev.dto.Response;
import com.yasithadev.yasithadev.entity.Event;
import com.yasithadev.yasithadev.exception.OurException;
import com.yasithadev.yasithadev.repo.EventRepository;
import com.yasithadev.yasithadev.service.AwsS3Service;
import com.yasithadev.yasithadev.service.interfac.IEventService;
import com.yasithadev.yasithadev.utils.Utils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService implements IEventService {
    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
    private AwsS3Service awsS3Service;

    @Override
    public Response addEvent(MultipartFile photo, String eventName, String artistName,
                           LocalDateTime eventDateTime, String location,
                           String description, BigDecimal price) {
        Response response = new Response();
        
        try {
            String imageUrl = null;
            if (photo != null && !photo.isEmpty()) {
                imageUrl = awsS3Service.saveImageToS3(photo);
            }
            
            Event event = new Event();
            event.setEventName(eventName);
            event.setArtistName(artistName);
            event.setEventDateTime(eventDateTime);
            event.setLocation(location);
            event.setDescription(description);
            event.setEventPhotoUrl(imageUrl);
            event.setPrice(price);
            
            Event savedEvent = eventRepository.save(event);
            response.setStatusCode(200);
            response.setMessage("Event added successfully");
            response.setEvent(Utils.mapEventEntityToEventDTO(savedEvent));
            
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error adding event: " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response updateEvent(Long eventId, MultipartFile photo, String eventName, 
                              String artistName, LocalDateTime eventDateTime, 
                              String location, String description, BigDecimal price) {
        Response response = new Response();
        try {
            Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new OurException("Event not found"));

            if (photo != null && !photo.isEmpty()) {
                String imageUrl = awsS3Service.saveImageToS3(photo);
                event.setEventPhotoUrl(imageUrl);
            }
            
            event.setEventName(eventName);
            event.setArtistName(artistName);
            event.setEventDateTime(eventDateTime);
            event.setLocation(location);
            event.setDescription(description);
            event.setPrice(price);
            
            Event updatedEvent = eventRepository.save(event);
            response.setStatusCode(200);
            response.setMessage("Event updated successfully");
            response.setEvent(Utils.mapEventEntityToEventDTO(updatedEvent));
            
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error updating event: " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getAllEvents() {
        Response response = new Response();
        try {
            List<Event> events = eventRepository.findAll();
            response.setStatusCode(200);
            response.setMessage("Events retrieved successfully");
            response.setEvents(events.stream()
                .map(Utils::mapEventEntityToEventDTO)
                .toList());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error retrieving events: " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response deleteEvent(Long eventId) {
        Response response = new Response();
        try {
            if (!eventRepository.existsById(eventId)) {
                throw new OurException("Event not found");
            }
            eventRepository.deleteById(eventId);
            response.setStatusCode(200);
            response.setMessage("Event deleted successfully");
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error deleting event: " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getEventById(Long eventId) {
        Response response = new Response();
        try {
            Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new OurException("Event not found"));
            response.setStatusCode(200);
            response.setMessage("Event retrieved successfully");
            response.setEvent(Utils.mapEventEntityToEventDTO(event));
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error retrieving event: " + e.getMessage());
        }
        return response;
    }

    // Implement other methods similarly
}

// Note: You can test the Event APIs using Postman by sending requests to the appropriate endpoints with the required parameters.
