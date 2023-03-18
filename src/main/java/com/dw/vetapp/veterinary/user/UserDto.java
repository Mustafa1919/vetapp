package com.dw.vetapp.veterinary.user;

import com.dw.vetapp.veterinary.helper.Role;
import lombok.Builder;

@Builder
public record UserDto (
        String username,
        Role role,
        Long id
){

}
