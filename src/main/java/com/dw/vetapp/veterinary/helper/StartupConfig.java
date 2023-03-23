package com.dw.vetapp.veterinary.helper;

import com.dw.vetapp.veterinary.animal.Animal;
import com.dw.vetapp.veterinary.animal.AnimalService;
import com.dw.vetapp.veterinary.animalowner.AnimalOwner;
import com.dw.vetapp.veterinary.animalowner.AnimalOwnerService;
import com.dw.vetapp.veterinary.doctor.Doctor;
import com.dw.vetapp.veterinary.doctor.DoctorRepository;
import com.dw.vetapp.veterinary.doctor.DoctorService;
import com.dw.vetapp.veterinary.address.Address;
import com.dw.vetapp.veterinary.phonenumber.PhoneNumber;
import com.dw.vetapp.veterinary.address.AddressRepository;
import com.dw.vetapp.veterinary.phonenumber.PhoneNumberRepository;
import com.dw.vetapp.veterinary.user.User;
import com.dw.vetapp.veterinary.user.UserRepository;
import com.dw.vetapp.veterinary.user.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StartupConfig implements CommandLineRunner {

    private final AnimalService animalService;
    private final DoctorRepository doctorService;
    private final AnimalOwnerService animalOwnerService;
    private final PhoneNumberRepository phoneNumberRepository;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void run(String... args) throws Exception {



/*
        Doctor doctor = Doctor.builder()
                .eMail("mail")
                .lastName("")
                .role(Role.DOCTOR)
                .password("1111")
                .name("")
                .build();

        Doctor d1 = doctorService.create(doctor);

        AnimalOwner owner = AnimalOwner.builder()
                .eMail("ema")
                .password("1234")
                .name("test")
                .lastName("")
                .role(Role.OWNER)
                .doctor(d1)
                .build();
        AnimalOwner o1 = animalOwnerService.create(owner);

        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setPhoneNumber("1111111");
        phoneNumber.setUser(d1);
        PhoneNumber p1 = phoneNumberRepository.save(phoneNumber);

        PhoneNumber phoneNumber2 = new PhoneNumber();
        phoneNumber2.setPhoneNumber("1111111");
        phoneNumber2.setUser(o1);
        PhoneNumber p2 = phoneNumberRepository.save(phoneNumber2);

        Address address = new Address();
        address.setCounty("");
        address.setCountry("");
        address.setStreet("");
        address.setBuildingNumber("");
        address.setDoorNumber(1);
        address.setAnimalOwner(o1);
        Address a1 = addressRepository.save(address);

        Animal animal = Animal.builder()
                .kind("test")
                .type("test")
                .name("test")
                .age(1)
                .comment("")
                .owner(o1)
                .build();
        Animal m1 = animalService.saveAnimal(animal);

        o1.setAddresses(new ArrayList<>(Arrays.asList(a1)));
        o1.setPhoneNumbers(new ArrayList<>(Arrays.asList(p2)));
        o1.setAnimals(new ArrayList<>(Arrays.asList(m1)));
        AnimalOwner o2 = animalOwnerService.update(o1);

        List<PhoneNumber> pss2 = new ArrayList<>();
        pss2.add(p1);
        d1.setPhoneNumbers(new ArrayList<>(Arrays.asList(p1)));
        d1.setAnimalOwners(new ArrayList<>(Arrays.asList(o2)));
        Doctor d2 = doctorService.update(d1);



        AnimalOwner test = animalOwnerService.getAnimalOwner(o2.getId());
        System.out.println(test.toString());
 */
    }

}
