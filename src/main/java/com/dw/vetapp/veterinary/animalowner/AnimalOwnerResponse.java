package com.dw.vetapp.veterinary.animalowner;

import com.dw.vetapp.veterinary.helper.Role;
import lombok.Builder;

@Builder
public record AnimalOwnerResponse(
        int id,
        String eMail,
        String name,
        String lastName
) {
    public static AnimalOwnerResponse convertAnimalOwnerToAnimalOwnerResponse(AnimalOwner owner){
        return AnimalOwnerResponse.builder()
                .lastName(owner.getLastName())
                .name(owner.getName())
                .eMail(owner.getEMail())
                .id(owner.getId().intValue())
                .build();
    }
}
