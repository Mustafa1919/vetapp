package com.dw.vetapp.veterinary.animalowner;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patient")
@RequiredArgsConstructor
@RateLimiter(name = "basic")
@Tag(name = "Hayvan Sahibi API", description = "Hayvan Sahibi İşlemlerinin Yapıldığı Api")
public class AnimalOwnerApi {

    private final AnimalOwnerService service;

    @GetMapping("/getPatient")
    public ResponseEntity<AnimalOwnerInfoResponse> getPatient(@RequestParam Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAnimalOwnerResponse(id));
    }

    @GetMapping("/patients")
    public ResponseEntity<List<AnimalOwnerResponse>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAnimalOwners());
    }

    @GetMapping("/patientsByDoctor")
    public ResponseEntity<List<AnimalOwnerResponse>> getPatientsByDoctorId(@RequestParam Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getOwnersByDoctorId(id));
    }

    @PreAuthorize("permitAll()")
    @PostMapping
    public ResponseEntity<AnimalOwnerResponse> savePatient(@RequestBody AnimalOwnerCreateRequest createRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(createRequest));
    }

    @PutMapping
    public ResponseEntity<AnimalOwnerResponse> updatePatient(@RequestBody AnimalOwnerUpdateRequest updateRequest){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.update(updateRequest));
    }

    @DeleteMapping
    public ResponseEntity<?> deletePatient(@RequestParam Long id){
        service.deleteOwner(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }


}
