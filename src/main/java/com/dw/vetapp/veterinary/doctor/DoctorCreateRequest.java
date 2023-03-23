package com.dw.vetapp.veterinary.doctor;

import com.dw.vetapp.veterinary.helper.Role;

public record DoctorCreateRequest(
        String eMail,
        String password,
        String name,
        String lastName
) {
    public Doctor convertDoctorCreateRequestToDoctor(){
        return Doctor.builder()
                .eMail(this.eMail)
                .password(this.password)
                .name(this.name)
                .lastName(this.lastName)
                .role(Role.DOCTOR)
                .build();
    }
}
