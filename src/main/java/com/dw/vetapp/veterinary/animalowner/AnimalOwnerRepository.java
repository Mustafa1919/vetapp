package com.dw.vetapp.veterinary.animalowner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalOwnerRepository extends JpaRepository<AnimalOwner,Long> {
    List<AnimalOwner> findAnimalOwnerByDoctor(Long doctorId);
}
