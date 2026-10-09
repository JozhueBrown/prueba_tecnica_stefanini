package com.example.pet.pets.infraestructure;

import com.example.pet.pets.application.dto.PetDto;
import com.example.pet.pets.application.dto.PetsRequest;
import com.example.pet.pets.domain.incoming.error.PetException;
import com.example.pet.pets.domain.outgoing.PetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;


@Repository
@Slf4j
public class PetSwaggerRepository implements PetRepository {

    private final RestTemplate restTemplate;

    public PetSwaggerRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public PetDto getPetById(final Long id) throws Exception {

        String resourceUrl = "https://petstore.swagger.io/v2/pet/" + id;
        try {

            var response = restTemplate.getForEntity(resourceUrl, PetDto.class);
            PetDto pet = response.getBody();

            log.info("Mascota encontrada: {} - Status: {}", pet.getName(), pet.getStatus());

            PetDto petDto = new PetDto();
            petDto.setId(pet.getId());
            petDto.setName(pet.getName());
            petDto.setStatus(pet.getStatus());

            return petDto;

        } catch (RestClientResponseException e) {
            throw new PetException(e.getStatusCode().value(), e);

        } catch (Exception e) {
            throw new PetException(HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }

    @Override
    public PetDto save(final PetsRequest request) throws  Exception{

        String resourceUrl = "https://petstore.swagger.io/v2/pet";

        try {
            var responsePet = restTemplate.postForObject(resourceUrl, request, PetDto.class);
            PetDto pet = responsePet;

            return pet;
        } catch (RestClientResponseException e) {
            throw new PetException(e.getStatusCode().value(), e);

        } catch (Exception e) {
            throw new PetException(HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
        }
    }
}
