package com.dw.vetapp.veterinary.auth;

import com.dw.vetapp.veterinary.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthApi {

    private final AuthService service;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(service.login(loginRequest));
    }

    @GetMapping("/currentUser")
    public ResponseEntity<UserDto> getCurrentUser(){
        return ResponseEntity.ok(service.getAuthenticatedUser());
    }
}
