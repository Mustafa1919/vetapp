package com.dw.vetapp.veterinary.animal;

public record AnimalResponse (
        Long id,
        String name
){


    public static AnimalResponse convertAnimalToAnimalResponse(Animal animal){
        return new AnimalResponse(animal.getAnimalId(),animal.getName());
    }
}
