package com.dw.vetapp.veterinary.doctor;

import com.dw.vetapp.veterinary.address.AddressResponse;
import com.dw.vetapp.veterinary.animal.AnimalResponse;
import com.dw.vetapp.veterinary.animalowner.AnimalOwner;
import com.dw.vetapp.veterinary.animalowner.AnimalOwnerInfoResponse;
import com.dw.vetapp.veterinary.animalowner.AnimalOwnerResponse;
import com.dw.vetapp.veterinary.phonenumber.PhoneNumberResponse;
import lombok.Builder;

import java.util.List;

@Builder
public record DoctorInfoResponse(
        Long id,
        String name,
        String lastName,
        List<PhoneNumberResponse> phoneNumbers,
        List<AnimalOwnerResponse> patients

) {
    public static DoctorInfoResponse convertDoctorToDoctorInfoResponse(Doctor doctor){
        return DoctorInfoResponse.builder()
                .id(doctor.getId())
                .name(doctor.getName())
                .lastName(doctor.getLastName())
                .phoneNumbers(doctor.getPhoneNumbers().stream().map(PhoneNumberResponse::convertPhoneNumberToPhoneNumberResponse).toList())
                .patients(doctor.getAnimalOwners().stream().map(AnimalOwnerResponse::convertAnimalOwnerToAnimalOwnerResponse).toList())
                .build();
    }
}
