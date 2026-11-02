package com.example.hotelbooking.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class HotelDto {
    private Long id;

    @NotBlank(message = "Назва готелю не може бути порожньою")
    private String name;

    @NotBlank(message = "Локація не може бути порожньою")
    private String location;
}