package com.dw.vetapp.veterinary.doctor;

import com.dw.vetapp.veterinary.animalowner.AnimalOwner;
import com.dw.vetapp.veterinary.user.User;
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
public class Doctor extends User {

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<AnimalOwner> animalOwners;

}
