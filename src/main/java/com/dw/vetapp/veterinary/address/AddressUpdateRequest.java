package com.dw.vetapp.veterinary.address;

public record AddressUpdateRequest(
        int id,
        String country,
        String county,
        String street,
        String buildingNumber,
        int doorNumber
) {
    public Address convertSavedAddressToUpdateAddress(Address savedAddress){
        savedAddress.setCountry(this.country);
        savedAddress.setStreet(this.street);
        savedAddress.setCounty(this.county);
        savedAddress.setDoorNumber(this.doorNumber);
        savedAddress.setBuildingNumber(this.buildingNumber);
        return savedAddress;
    }
}
