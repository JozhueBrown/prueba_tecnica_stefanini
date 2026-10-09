package com.example.pet.pets.domain.core;

import com.example.pet.pets.application.dto.PetDto;
import com.example.pet.pets.application.dto.PetsRequest;
import com.example.pet.pets.application.dto.PetsResponse;
import com.example.pet.pets.domain.outgoing.PetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
public class PetsServiceImpl implements com.example.pet.pets.domain.incoming.PetsService {

    private final PetRepository petRepository;


    public PetsServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public PetDto getPetById(final Long id) throws Exception {

        PetDto pet = petRepository.getPetById(id);
        log.info(pet.toString());
        return pet;

    }

    @Override
    public PetsResponse save(final PetsRequest request) throws  Exception{

        PetDto petsResponse = petRepository.save(request);
        log.info(petsResponse.toString());

        UUID  transactionId = UUID.randomUUID();

        PetsResponse response = new PetsResponse();
        response.setTransactionId(transactionId.toString());
        response.setDateCreated(LocalDateTime.now().toString());
        response.setStatus(true);
        response.setName(petsResponse.getName());

        return response;

    }
}
