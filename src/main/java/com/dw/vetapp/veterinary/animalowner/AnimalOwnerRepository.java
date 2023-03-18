package com.dw.vetapp.veterinary.animalowner;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalOwnerRepository extends JpaRepository<AnimalOwner,Long> {
    List<AnimalOwner> findAnimalOwnerByDoctor(Long doctorId);
}
