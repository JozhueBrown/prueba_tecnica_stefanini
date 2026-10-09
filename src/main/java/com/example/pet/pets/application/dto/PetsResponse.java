package com.example.pet.pets.application.dto;

import lombok.Data;

@Data
public class PetsResponse {
    private String transactionId;
    private String dateCreated;
    private boolean status;
    private String name;
}
