package com.dw.vetapp.veterinary.animal;

import com.dw.vetapp.veterinary.animalowner.AnimalOwner;
import com.dw.vetapp.veterinary.animalowner.AnimalOwnerService;
import com.dw.vetapp.veterinary.doctor.Doctor;
import com.dw.vetapp.veterinary.doctor.DoctorService;
import com.dw.vetapp.veterinary.helper.exception.GenericException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AnimalService {
    private final AnimalRepository repository;
    private final AnimalOwnerService ownerService;
    private final DoctorService doctorService;

    public List<AnimalResponse> getAnimalListByOwnerId(Long ownerId){
        return repository.getAnimalByOwner(ownerId)
                .orElseThrow(() -> GenericException.builder()
                        .status(HttpStatus.NOT_FOUND)
                        .errorMessage("Hayvan Listesi Bulunamadı")
                        .build())
                .stream()
                .map(AnimalResponse::convertAnimalToAnimalResponse)
                .collect(Collectors.toList());
    }

    public List<AnimalResponse> getAllAnimal(){
        return repository.findAll().stream().map(AnimalResponse::convertAnimalToAnimalResponse).collect(Collectors.toList());
    }

    public AnimalInfoResponse getAnimal(Long id){
        return AnimalInfoResponse.convertAnimalToAnimalInfoResponse(
                repository.findById(id).orElseThrow(() -> GenericException.builder()
                        .status(HttpStatus.NOT_FOUND)
                        .errorMessage("Hayvan Bulunamadı")
                        .build()));
    }

    public List<AnimalResponse> getDoctorAnimals(Long id){
        Doctor doctor = doctorService.getDoctor(id.intValue());
        List<AnimalResponse> animalList = new ArrayList<>();
        for (AnimalOwner owner : doctor.getAnimalOwners()){
            animalList = Stream.
                    concat(animalList.stream(),getAnimalListByOwnerId(owner.getId()).stream())
                    .toList();
        }
        return animalList;
    }

    public List<AnimalResponse> getAnimalListByName(String name){
        return repository.getAnimalByName(name)
                .orElseThrow(() -> GenericException.builder()
                        .status(HttpStatus.NOT_FOUND)
                        .errorMessage("Hayvan Listesi Bulunamadı")
                        .build())
                .stream()
                .map(AnimalResponse::convertAnimalToAnimalResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public AnimalResponse saveAnimal(AnimalCreateRequest createRequest){
        AnimalOwner owner = ownerService.getAnimalOwner((long) createRequest.ownerId());
        Animal savedAnimal = repository.save(createRequest.convertAnimalCreateRequestToAnimal(owner));
        if (owner.getAnimals() != null)
            owner.getAnimals().add(savedAnimal);
        else owner.setAnimals(new ArrayList<>(List.of(savedAnimal)));
        ownerService.update(owner);
        return AnimalResponse.convertAnimalToAnimalResponse(savedAnimal);
    }

    public AnimalResponse updateAnimal(AnimalUpdateRequest updateRequest){
        Animal savedAnimal = repository.findById((long) updateRequest.id()).orElseThrow(
                () -> GenericException.builder()
                .status(HttpStatus.NOT_FOUND)
                .errorMessage("Güncellenecek Hayvan Bulunamadı")
                .build()
        );
        return AnimalResponse.convertAnimalToAnimalResponse(repository.save(updateRequest.convertSavedAnimalToUpdateAnimal(savedAnimal)));
    }

    public void deleteAnimal(Long id){
        repository.deleteById(id);
    }

}
