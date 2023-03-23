package com.dw.vetapp.veterinary.phonenumber;

import com.dw.vetapp.veterinary.phonenumber.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber,Long> {
}
