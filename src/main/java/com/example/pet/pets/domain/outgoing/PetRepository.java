package com.example.pet.pets.domain.outgoing;

import com.example.pet.pets.application.dto.PetDto;
import com.example.pet.pets.application.dto.PetsRequest;

public interface PetRepository {
    PetDto getPetById(Long id) throws Exception;

    PetDto save(PetsRequest request) throws Exception;
}
