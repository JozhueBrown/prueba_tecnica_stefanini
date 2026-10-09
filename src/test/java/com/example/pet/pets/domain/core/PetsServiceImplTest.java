package com.example.pet.pets.domain.core;

import com.example.pet.pets.application.dto.PetDto;
import com.example.pet.pets.application.dto.PetsRequest;
import com.example.pet.pets.application.dto.PetsResponse;
import com.example.pet.pets.domain.outgoing.PetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetsServiceImplTest {

    @Mock
    private PetRepository petRepository;

    @InjectMocks
    private PetsServiceImpl petsService;

    @Test
    void getPetById_returnsPetDto() throws Exception {
        Long id = 42L;
        PetDto expected = new PetDto();
        expected.setId(id);
        expected.setName("Rex");
        expected.setStatus("available");

        when(petRepository.getPetById(id)).thenReturn(expected);

        PetDto result = petsService.getPetById(id);

        assertNotNull(result);
        assertEquals(expected, result);
        verify(petRepository).getPetById(id);
    }

    @Test
    void save_returnsMappedResponse() throws Exception {
        PetsRequest request = new PetsRequest();
        request.setName("Luna");

        PetDto savedPet = new PetDto();
        savedPet.setId(7L);
        savedPet.setName("Luna");
        savedPet.setStatus("available");

        when(petRepository.save(request)).thenReturn(savedPet);

        PetsResponse response = petsService.save(request);

        assertNotNull(response);
        assertTrue(response.isStatus());
        assertEquals("Luna", response.getName());
        assertNotNull(response.getTransactionId());
        assertNotNull(response.getDateCreated());
        verify(petRepository).save(request);
    }

    @Test
    void save_throwsExceptionWhenRepositoryFails() throws Exception {
        PetsRequest request = new PetsRequest();
        request.setName("Luna");

        when(petRepository.save(request)).thenThrow(new RuntimeException("db error"));

        Exception exception = assertThrows(Exception.class, () -> petsService.save(request));

        assertTrue(exception.getMessage().contains("db error"));
        verify(petRepository).save(request);
    }
}
