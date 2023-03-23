package com.dw.vetapp.veterinary.phonenumber;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pNumber")
@RequiredArgsConstructor
public class PhoneNumberApi {

    private final PhoneNumberService service;

    @PostMapping
    public ResponseEntity<PhoneNumberResponse> savePhoneNumber(@RequestBody PhoneNumberRequest createRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.savePhoneNumber(createRequest));
    }

    @PutMapping
    public ResponseEntity<PhoneNumberResponse> updatePhoneNumber(@RequestBody PhoneNumberUpdateRequest createRequest){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.updatePhoneNumber(createRequest));
    }

    @DeleteMapping
    public ResponseEntity<?> deletePhoneNumber(@RequestParam Long phoneNumberId){
        service.deletePhoneNumber(phoneNumberId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
