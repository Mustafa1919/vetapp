package com.dw.vetapp.veterinary.address;

import lombok.Builder;

@Builder
public record AddressResponse(
        int id,
        String country,
        String county,
        String street,
        String buildingNumber,
        int doorNumber

) {
    public static AddressResponse convertAddressToAddressResponse(Address address){
        return AddressResponse.builder()
                .buildingNumber(address.getBuildingNumber())
                .country(address.getCountry())
                .county(address.getCounty())
                .doorNumber(address.getDoorNumber())
                .id(address.getAddressId().intValue())
                .street(address.getStreet())
                .build();
    }
}
