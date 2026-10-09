package com.example.pet.pets.domain.incoming;

import com.example.pet.pets.application.dto.PetDto;
import com.example.pet.pets.application.dto.PetsRequest;
import com.example.pet.pets.application.dto.PetsResponse;

public interface PetsService {
    PetDto getPetById(Long id) throws Exception;

    PetsResponse save(PetsRequest request) throws  Exception;
}
