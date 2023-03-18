package com.dw.vetapp.veterinary.phonenumber;


public record PhoneNumberUpdateRequest(
        int id,
        String number
) {
    public PhoneNumber convertSavedPhoneNumberToUpdatedPhoneNumber(PhoneNumber savedNumber){
        savedNumber.setPhoneNumber(number);
        return savedNumber;
    }
}
