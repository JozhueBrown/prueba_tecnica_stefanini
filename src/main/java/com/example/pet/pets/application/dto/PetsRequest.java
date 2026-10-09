package com.example.pet.pets.application.dto;

import lombok.Data;

@Data
public class PetsRequest {
    private Long id;
    private String status;
    private String name;
}
