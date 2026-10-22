package com.example.pet.pets.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PetsRequest {
    private Long id;
    private String status;

    @NotNull(message = "El campo nombre es requerido")
    @NotBlank(message = "El campo nombre es requerido")
    private String name;
}
