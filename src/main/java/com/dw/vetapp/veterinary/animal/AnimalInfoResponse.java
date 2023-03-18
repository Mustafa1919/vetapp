package com.dw.vetapp.veterinary.animal;

import lombok.Builder;

@Builder
public record AnimalInfoResponse(
        String name,
        String type,
        String kind,
        int age,
        String comment
) {
    public static AnimalInfoResponse convertAnimalToAnimalInfoResponse(Animal animal){
        return AnimalInfoResponse.builder()
                .name(animal.getName())
                .age(animal.getAge())
                .kind(animal.getKind())
                .type(animal.getType())
                .comment(animal.getComment())
                .build();
    }
}
