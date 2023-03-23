package com.dw.vetapp.veterinary.address;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/address")
@RequiredArgsConstructor
public class AddressApi {

    private final AddressService service;

    @PostMapping
    public ResponseEntity<AddressResponse> saveAddress(@RequestBody AddressRequest createRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveAddress(createRequest));
    }

    @PutMapping
    public ResponseEntity<AddressResponse> updateAddress(@RequestBody AddressUpdateRequest createRequest){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.updateAddress(createRequest));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAddress(@RequestParam Long addressId){
        service.deleteAddress(addressId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
