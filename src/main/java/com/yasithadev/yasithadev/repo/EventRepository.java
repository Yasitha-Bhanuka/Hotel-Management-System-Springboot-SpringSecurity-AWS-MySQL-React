package com.yasithadev.yasithadev.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yasithadev.yasithadev.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
} 