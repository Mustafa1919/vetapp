package com.dw.vetapp.veterinary.phonenumber;

import com.dw.vetapp.veterinary.user.User;

public record PhoneNumberRequest(
        String number,
        int userId
) {
    public PhoneNumber convertPhoneNumberRequestToPhoneNumber(User user){
        return PhoneNumber.builder()
                .user(user)
                .phoneNumber(this.number)
                .build();
    }
}
