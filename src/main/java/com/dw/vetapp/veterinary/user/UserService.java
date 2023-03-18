package com.dw.vetapp.veterinary.user;

import com.dw.vetapp.veterinary.helper.exception.GenericException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public UserDto getUserDto(String username) {
        User loginUser = findUserByUsername(username);
        return new UserDto.UserDtoBuilder().id(loginUser.getId())
                .role(loginUser.getRole())
                .username(loginUser.getEMail())
                .build();
    }

    public User getUser(int id){
        return repository.findById((long) id).orElseThrow(
                () -> GenericException.builder()
                        .status(HttpStatus.NOT_FOUND)
                        .errorMessage("Kullanıcı Bulunamadı")
                        .build()
        );
    }

    @Transactional
    public User findUserByUsername(String username) {
        return repository.findUserByeMail(username).orElseThrow();
    }
}
