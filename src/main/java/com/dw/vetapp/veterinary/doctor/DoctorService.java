package com.dw.vetapp.veterinary.doctor;

import com.dw.vetapp.veterinary.animal.Animal;
import com.dw.vetapp.veterinary.animal.AnimalResponse;
import com.dw.vetapp.veterinary.animal.AnimalService;
import com.dw.vetapp.veterinary.animalowner.*;
import com.dw.vetapp.veterinary.helper.exception.GenericException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository repository;
    private final PasswordEncoder passwordEncoder;

    public List<DoctorResponse> getDoctors(){
        return repository.findAll()
                .stream()
                .map(DoctorResponse::convertDoctorToDoctorResponse)
                .toList();
    }

    public DoctorInfoResponse getDoctorInfo(Long id){
        return DoctorInfoResponse.convertDoctorToDoctorInfoResponse(getDoctor(id.intValue()));
    }

    public Doctor getDoctor(int id){
        return repository.findById((long) id).orElseThrow(
                () -> GenericException.builder()
                        .status(HttpStatus.NOT_FOUND)
                        .errorMessage("Doctor Bulunamadı")
                        .build());
    }

    public DoctorResponse create(DoctorCreateRequest createRequest){
        Doctor doctor = createRequest.convertDoctorCreateRequestToDoctor();
        doctor.setPassword(passwordEncoder.encode(createRequest.password()));
        return DoctorResponse.convertDoctorToDoctorResponse(repository.save(doctor));
    }

    public DoctorResponse update(DoctorUpdateRequest updateRequest){
        Doctor savedDoctor = repository.findById((long) updateRequest.id()).orElseThrow(
                () -> GenericException.builder()
                        .status(HttpStatus.NOT_FOUND)
                        .errorMessage("Güncellenecek Doctor Bulunamadı")
                        .build()
        );
        return DoctorResponse.convertDoctorToDoctorResponse
                (repository.save(updateRequest.convertSavedDoctorToUpdateDoctor(savedDoctor)));
    }

    public Doctor update(Doctor updateRequest){
        return repository.save(updateRequest);
    }

    public void deleteDoctor(Long id){
        repository.deleteById(id);
    }
}
