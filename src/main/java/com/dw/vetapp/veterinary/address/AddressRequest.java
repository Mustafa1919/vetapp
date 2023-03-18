package com.dw.vetapp.veterinary.address;

import com.dw.vetapp.veterinary.animalowner.AnimalOwner;

public record AddressRequest(
        String country,
        String county,
        String street,
        String buildingNumber,
        int doorNumber,
        long ownerId
) {

    public Address convertAddressRequestToAddress(AnimalOwner owner){
        return Address.builder()
                .buildingNumber(this.buildingNumber)
                .county(this.county)
                .doorNumber(this.doorNumber)
                .country(this.country)
                .street(this.street)
                .animalOwner(owner)
                .build();
    }
}
