package com.dw.vetapp.veterinary.auth;

import com.dw.vetapp.veterinary.helper.exception.GenericException;
import com.dw.vetapp.veterinary.user.UserDto;
import com.dw.vetapp.veterinary.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final TokenService tokenService;

    public LoginResponse login(LoginRequest loginRequest) {
        try {
            Authentication auth = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            UserDto userDto = userService.getUserDto(loginRequest.getUsername());
            return LoginResponse
                    .builder()
                    .accessToken(tokenService.generateToken(auth))
                    .id(userDto.id().intValue())
                    .email(userDto.username())
                    .build();
        } catch (final BadCredentialsException badCredentialsException) {
            throw new GenericException(HttpStatus.BAD_REQUEST, "Invalid Username or Password");
        }
    }

    public UserDto getAuthenticatedUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.getUserDto(username);
    }
}
