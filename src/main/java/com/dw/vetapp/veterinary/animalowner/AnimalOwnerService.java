package com.dw.vetapp.veterinary.animalowner;

import com.dw.vetapp.veterinary.animal.Animal;
import com.dw.vetapp.veterinary.animal.AnimalResponse;
import com.dw.vetapp.veterinary.doctor.Doctor;
import com.dw.vetapp.veterinary.doctor.DoctorService;
import com.dw.vetapp.veterinary.helper.exception.GenericException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimalOwnerService {

    private final AnimalOwnerRepository repository;
    private final PasswordEncoder passwordEncoder;
    public final DoctorService doctorService;


    public List<AnimalOwnerResponse> getAnimalOwners(){
        return repository.findAll()
                .stream()
                .map(AnimalOwnerResponse::convertAnimalOwnerToAnimalOwnerResponse)
                .toList();
    }

    @Transactional
    public AnimalOwnerInfoResponse getAnimalOwnerResponse(Long id){
        AnimalOwner owner = repository.findById(id).orElseThrow(
                () -> GenericException.builder()
                        .status(HttpStatus.NOT_FOUND)
                        .errorMessage("Hayvan Sahibi Bulunamadı")
                        .build());
        return AnimalOwnerInfoResponse.convertAnimalToAnimalOwnerInfo(owner);
    }

    public AnimalOwner getAnimalOwner(Long id){
        return repository.findById(id).orElseThrow(
                () -> GenericException.builder()
                        .status(HttpStatus.NOT_FOUND)
                        .errorMessage("Hayvan Sahibi Bulunamadı")
                        .build());
    }

    public List<AnimalOwnerResponse> getOwnersByDoctorId(Long id){
        return repository.findAnimalOwnerByDoctor(id)
                .stream()
                .map(AnimalOwnerResponse::convertAnimalOwnerToAnimalOwnerResponse)
                .toList();
    }

    @Transactional
    public AnimalOwnerResponse create(AnimalOwnerCreateRequest createRequest){
        try {
            Doctor doctor = doctorService.getDoctor(createRequest.doctorId());
            AnimalOwner owner = createRequest.convertAnimalOwnerCreateToAnimalOwner(doctor);
            owner.setPassword(passwordEncoder.encode(createRequest.password()));
            AnimalOwner savedOwner = repository.save(owner);
            if (doctor.getAnimalOwners() != null)
                doctor.getAnimalOwners().add(savedOwner);
            else doctor.setAnimalOwners(new ArrayList<>(List.of(savedOwner)));
            doctorService.update(doctor);
            return AnimalOwnerResponse.convertAnimalOwnerToAnimalOwnerResponse(savedOwner);
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Transactional
    public AnimalOwnerResponse update(AnimalOwnerUpdateRequest updateRequest){
        AnimalOwner savedAnimalOwner = repository.findById((long) updateRequest.id()).orElseThrow(
                () -> GenericException.builder()
                        .status(HttpStatus.NOT_FOUND)
                        .errorMessage("Güncellenecek Hayvan Sahibi Bulunamadı")
                        .build()
        );
        return AnimalOwnerResponse.convertAnimalOwnerToAnimalOwnerResponse
                (repository.save(updateRequest.convertSavedAnimalOwnerToUpdateAnimalOwner(savedAnimalOwner)));

    }

    public AnimalOwner update(AnimalOwner updateRequest){
        return repository.save(updateRequest);
    }

    public void deleteOwner(Long id){
        repository.deleteById(id);
    }

}
