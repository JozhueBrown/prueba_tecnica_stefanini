package com.example.pet.pets.application;

import com.example.pet.pets.application.dto.PetDto;
import com.example.pet.pets.application.dto.PetsRequest;
import com.example.pet.pets.application.dto.PetsResponse;
import com.example.pet.pets.domain.incoming.PetsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class PetsControllerTest {

    private PetsService petsService;
    private PetsController controller;

    @BeforeEach
    void setUp() {
        petsService = Mockito.mock(PetsService.class);
        controller = new PetsController(petsService);
    }

    @Test
    void save_returnsCreated() throws Exception {
        PetsRequest request = new PetsRequest();
        request.setName("Luna");

        PetsResponse resp = new PetsResponse();
        resp.setName("Luna");
        resp.setStatus(true);
        resp.setTransactionId("tx-1");
        resp.setDateCreated("2026-06-01T00:00:00");

        Mockito.when(petsService.save(Mockito.any())).thenReturn(resp);

        ResponseEntity<?> result = controller.save(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertSame(resp, result.getBody());
    }

    @Test
    void findById_returnsOk() throws Exception {
        Long id = 42L;
        PetDto dto = new PetDto();
        dto.setId(id);
        dto.setName("Rex");
        dto.setStatus("available");

        Mockito.when(petsService.getPetById(id)).thenReturn(dto);

        ResponseEntity<?> result = controller.findById(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertSame(dto, result.getBody());
    }
}

