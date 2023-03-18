package com.dw.vetapp.veterinary.animal;

public record AnimalUpdateRequest(
        int id,
        String type,
        String kind,
        String name,
        int age,
        String comment
) {

    public Animal convertSavedAnimalToUpdateAnimal(Animal savedAnimal){
        savedAnimal.setAge(this.age);
        savedAnimal.setName(this.name);
        savedAnimal.setKind(this.kind);
        savedAnimal.setComment(this.comment);
        savedAnimal.setType(this.type);
        return savedAnimal;
    }
}
