package com.dw.vetapp.veterinary.animal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/animal")
@RequiredArgsConstructor
public class AnimalApi {

    private final AnimalService service;

    @GetMapping("/getAnimalsByOwnerId")
    public ResponseEntity<List<AnimalResponse>> getAnimalListByOwnerId(@RequestParam Long ownerId){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAnimalListByOwnerId(ownerId));
    }

    @GetMapping("/getAllAnimals")
    public ResponseEntity<List<AnimalResponse>> getAllAnimal(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllAnimal());
    }

    @GetMapping("/doctorAnimals")
    public ResponseEntity<List<AnimalResponse>> getDoctorAnimals(@RequestParam Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getDoctorAnimals(id));
    }

    @GetMapping("/getAnimal")
    public ResponseEntity<AnimalInfoResponse> getAnimal(@RequestParam Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAnimal(id));
    }

    @GetMapping("/getAllAnimalsByName")
    public ResponseEntity<List<AnimalResponse>> getAnimalListByName(@RequestParam String name){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAnimalListByName(name));
    }

    @PostMapping
    public ResponseEntity<AnimalResponse> saveAnimal(@RequestBody AnimalCreateRequest createRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveAnimal(createRequest));
    }

    @PutMapping
    public ResponseEntity<AnimalResponse> updateAnimal(@RequestBody AnimalUpdateRequest createRequest){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.updateAnimal(createRequest));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAnimal(@RequestParam Long animalId){
        service.deleteAnimal(animalId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
