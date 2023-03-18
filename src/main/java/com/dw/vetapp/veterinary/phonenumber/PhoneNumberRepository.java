package com.dw.vetapp.veterinary.phonenumber;

import com.dw.vetapp.veterinary.phonenumber.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber,Long> {
}
