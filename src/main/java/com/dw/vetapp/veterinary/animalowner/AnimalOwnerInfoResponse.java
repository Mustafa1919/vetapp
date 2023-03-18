package com.dw.vetapp.veterinary.animalowner;

import com.dw.vetapp.veterinary.address.AddressResponse;
import com.dw.vetapp.veterinary.animal.AnimalResponse;
import com.dw.vetapp.veterinary.doctor.DoctorResponse;
import com.dw.vetapp.veterinary.phonenumber.PhoneNumberResponse;
import lombok.Builder;

import java.util.List;

@Builder
public record AnimalOwnerInfoResponse(
        Long id,
        String name,
        String lastName,
        List<AddressResponse> addresses,
        List<PhoneNumberResponse> phoneNumbers,
        List<AnimalResponse> animals,
        DoctorResponse doctor
) {

    public static AnimalOwnerInfoResponse convertAnimalToAnimalOwnerInfo(AnimalOwner owner){
        return AnimalOwnerInfoResponse.builder()
                .id(owner.getId())
                .name(owner.getName())
                .lastName(owner.getLastName())
                .addresses(owner.getAddresses().stream().map(AddressResponse::convertAddressToAddressResponse).toList())
                .phoneNumbers(owner.getPhoneNumbers().stream().map(PhoneNumberResponse::convertPhoneNumberToPhoneNumberResponse).toList())
                .animals(owner.getAnimals().stream().map(AnimalResponse::convertAnimalToAnimalResponse).toList())
                .doctor(DoctorResponse.convertDoctorToDoctorResponse(owner.getDoctor()))
                .build();
    }
}
