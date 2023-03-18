package com.dw.vetapp.veterinary.doctor;

public record DoctorResponse(int id, String email, String name, String lastName) {
    public static DoctorResponse convertDoctorToDoctorResponse(Doctor doctor) {
        return new DoctorResponse(doctor.getId().intValue(),
                doctor.getEMail(),
                doctor.getName(),
                doctor.getLastName());
    }
}
