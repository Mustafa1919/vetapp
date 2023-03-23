package com.dw.vetapp.veterinary.doctor;

public record DoctorUpdateRequest(
        String eMail,
        String name,
        String lastName,
        int id
) {
    public Doctor convertSavedDoctorToUpdateDoctor(Doctor savedDoctor){
        savedDoctor.setEMail(this.eMail);
        savedDoctor.setName(this.name);
        savedDoctor.setLastName(this.lastName);
        return savedDoctor;
    }
}
