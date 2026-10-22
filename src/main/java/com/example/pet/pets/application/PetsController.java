package com.example.pet.pets.application;

import com.example.pet.pets.application.dto.PetsRequest;
import com.example.pet.pets.domain.incoming.PetsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/pet")
public class PetsController {

    private final PetsService petsService;

    public PetsController(PetsService petsService) {
        this.petsService = petsService;
    }

    @PostMapping()
    public ResponseEntity<?> save(@Valid @RequestBody final PetsRequest request) throws Exception {
        return new ResponseEntity<>(petsService.save(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable final Long id) throws Exception {
        return new ResponseEntity<>(this.petsService.getPetById(id), HttpStatus.OK);
    }
}
