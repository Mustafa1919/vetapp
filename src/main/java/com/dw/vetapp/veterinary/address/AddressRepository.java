package com.dw.vetapp.veterinary.address;

import com.dw.vetapp.veterinary.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface AddressRepository extends JpaRepository<Address,Long> {
}
