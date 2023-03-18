package com.dw.vetapp.veterinary.animalowner;

import com.dw.vetapp.veterinary.animal.Animal;
import com.dw.vetapp.veterinary.doctor.Doctor;
import com.dw.vetapp.veterinary.address.Address;
import com.dw.vetapp.veterinary.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AnimalOwner extends User {

    @OneToMany(mappedBy = "animalOwner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Address> addresses;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Animal> animals;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "doctorId")
    @JsonIgnore
    private Doctor doctor;


}
