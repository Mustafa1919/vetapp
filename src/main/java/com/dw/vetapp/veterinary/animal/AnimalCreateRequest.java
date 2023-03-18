package com.dw.vetapp.veterinary.animal;

import com.dw.vetapp.veterinary.animalowner.AnimalOwner;

public record AnimalCreateRequest(
        String type,
        String kind,
        String name,
        int age,
        String comment,
        int ownerId
) {
    public Animal convertAnimalCreateRequestToAnimal(AnimalOwner owner){
        return Animal.builder()
                .owner(owner)
                .age(this.age)
                .comment(this.comment)
                .kind(this.kind)
                .name(this.name)
                .type(this.type)
                .build();
    }
}
