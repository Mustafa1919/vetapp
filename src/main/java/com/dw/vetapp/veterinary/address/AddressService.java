package com.dw.vetapp.veterinary.address;

import com.dw.vetapp.veterinary.animalowner.AnimalOwner;
import com.dw.vetapp.veterinary.animalowner.AnimalOwnerService;
import com.dw.vetapp.veterinary.helper.exception.GenericException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository repository;
    private final AnimalOwnerService ownerService;

    public AddressResponse saveAddress(AddressRequest request){
        AnimalOwner owner = ownerService.getAnimalOwner(request.ownerId());
        Address savedAddress = repository.save(request.convertAddressRequestToAddress(owner));
        checkOwnerAddress(owner, savedAddress);
        ownerService.update(owner);
        return AddressResponse.convertAddressToAddressResponse(savedAddress);
    }

    public AddressResponse updateAddress(AddressUpdateRequest request){
        Address savedAddress = getAddress((long) request.id());
        return AddressResponse.convertAddressToAddressResponse(
                repository.save(request.convertSavedAddressToUpdateAddress(savedAddress)));
    }

    public void deleteAddress(Long id){
        repository.deleteById(id);
    }

    private Address getAddress(Long id){
        return repository.findById(id).orElseThrow(
                () -> GenericException.builder()
                        .status(HttpStatus.NOT_FOUND)
                        .errorMessage("Adres Bulunamadı")
                        .build()
        );
    }
    private void checkOwnerAddress(AnimalOwner owner, Address savedAddress){
        if (owner.getAddresses() != null)
            owner.getAddresses().add(savedAddress);
        else owner.setAddresses(new ArrayList<>(List.of(savedAddress)));
    }
}
