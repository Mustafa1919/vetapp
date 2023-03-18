package com.dw.vetapp.veterinary.animal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal,Long> {

    Optional<List<Animal>> getAnimalByOwner(Long ownerId);
    Optional<List<Animal>> getAnimalByName(String name);

}
