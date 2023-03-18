package com.dw.vetapp.veterinary.phonenumber;


public record PhoneNumberResponse (
        int numberId,
        String phoneNumber
){
    public static PhoneNumberResponse convertPhoneNumberToPhoneNumberResponse(PhoneNumber number){
        return new PhoneNumberResponse(number.getNumberId().intValue(), number.getPhoneNumber());
    }
}
