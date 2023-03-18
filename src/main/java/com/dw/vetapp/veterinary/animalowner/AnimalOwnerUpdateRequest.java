package com.dw.vetapp.veterinary.animalowner;

public record AnimalOwnerUpdateRequest(
        String eMail,
        String password,
        String name,
        String lastName,
        int id
) {
    public AnimalOwner convertSavedAnimalOwnerToUpdateAnimalOwner(AnimalOwner savedAnimalOwner){
        savedAnimalOwner.setEMail(this.eMail);
        savedAnimalOwner.setPassword(this.password);
        savedAnimalOwner.setName(this.name);
        savedAnimalOwner.setLastName(this.lastName);
        return savedAnimalOwner;
    }
}
