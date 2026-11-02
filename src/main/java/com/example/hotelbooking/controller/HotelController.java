package com.example.hotelbooking.controller;

import com.example.hotelbooking.service.HotelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    // Новий ендпоінт для перевірки Лабораторної №5
    @PostMapping("/async-process/{id}")
    public ResponseEntity<String> triggerAsyncProcess(@PathVariable Long id, @RequestParam String email) {
        String result = hotelService.processAsyncHotelOperations(id, email);
        return ResponseEntity.ok(result);
    }
}