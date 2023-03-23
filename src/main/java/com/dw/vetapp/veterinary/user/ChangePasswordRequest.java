package com.dw.vetapp.veterinary.user;

public record ChangePasswordRequest(
        int id,
        String newPassword,
        String oldPassword
) {
}
