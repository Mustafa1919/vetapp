package com.dw.vetapp.veterinary.animalowner;

import com.dw.vetapp.veterinary.doctor.Doctor;
import com.dw.vetapp.veterinary.helper.Role;
import com.dw.vetapp.veterinary.phonenumber.PhoneNumber;
import com.dw.vetapp.veterinary.phonenumber.PhoneNumberRequest;
import lombok.Builder;

@Builder
public record AnimalOwnerCreateRequest(
        String eMail,
        String password,
        String name,
        String lastName,
        int doctorId
) {
    public AnimalOwner convertAnimalOwnerCreateToAnimalOwner(Doctor doctor){
        return AnimalOwner.builder()
                .doctor(doctor)
                .lastName(this.lastName)
                .name(this.name)
                .password(this.password)
                .role(Role.OWNER)
                .eMail(this.eMail)
                .build();
    }
}
