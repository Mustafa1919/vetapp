package com.dw.vetapp.veterinary.user;

import com.dw.vetapp.veterinary.helper.exception.GenericException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository repository;
    // Logger logger = LoggerFactory.getLogger(UserService.class); // (Lombok @Slf4j anatasyonu bu işi yapıyor)

    @Transactional
    public UserDto getUserDto(String username) {
        User loginUser = findUserByUsername(username);
        log.debug("Test Log");
        return new UserDto.UserDtoBuilder().id(loginUser.getId())
                .role(loginUser.getRole())
                .username(loginUser.getEMail())
                .build();
    }

    @Transactional
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

    @Transactional
    public User updateUser(User user) {
        return repository.save(user);
    }
}
