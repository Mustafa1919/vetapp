package com.dw.vetapp.veterinary.phonenumber;

import com.dw.vetapp.veterinary.helper.exception.GenericException;
import com.dw.vetapp.veterinary.user.User;
import com.dw.vetapp.veterinary.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PhoneNumberService {

    private final PhoneNumberRepository repository;
    private final UserService userService;

    public PhoneNumberResponse savePhoneNumber(PhoneNumberRequest request){
        User user = userService.getUser(request.userId());
        PhoneNumber savedPhoneNumber = repository.save
                (request.convertPhoneNumberRequestToPhoneNumber(user));
        checkUserPhoneNumber(user, savedPhoneNumber);
        return PhoneNumberResponse.convertPhoneNumberToPhoneNumberResponse(savedPhoneNumber);
    }

    public PhoneNumberResponse updatePhoneNumber(PhoneNumberUpdateRequest request){
        PhoneNumber savedPhoneNumber = getPhoneNumber((long) request.id());
        return PhoneNumberResponse.convertPhoneNumberToPhoneNumberResponse(
                repository.save(request.convertSavedPhoneNumberToUpdatedPhoneNumber(savedPhoneNumber)));
    }

    public void deletePhoneNumber(Long id){
        repository.deleteById(id);
    }

    private PhoneNumber getPhoneNumber(Long id){
        return repository.findById(id).orElseThrow(
                () -> GenericException.builder()
                        .status(HttpStatus.NOT_FOUND)
                        .errorMessage("Numara Bulunamadı")
                        .build()
        );
    }

    private void checkUserPhoneNumber(User user, PhoneNumber savedPhoneNumber){
        if (user.getPhoneNumbers() != null)
            user.getPhoneNumbers().add(savedPhoneNumber);
        else user.setPhoneNumbers(new ArrayList<>(List.of(savedPhoneNumber)));
    }
}
