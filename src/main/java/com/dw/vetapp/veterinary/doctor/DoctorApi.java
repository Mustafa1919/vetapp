package com.dw.vetapp.veterinary.doctor;

import com.dw.vetapp.veterinary.animal.AnimalCreateRequest;
import com.dw.vetapp.veterinary.animal.AnimalResponse;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctor")
@RequiredArgsConstructor
@RateLimiter(name = "basic")
@Tag(name = "Doktor API", description = "Doktor İşlemlerinin Yapıldığı Api")
public class DoctorApi {

    private final DoctorService service;

    @GetMapping("/doctors")
    public ResponseEntity<List<DoctorResponse>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getDoctors());
    }

    @GetMapping("/getDoctor")
    public ResponseEntity<DoctorInfoResponse> getDoctor(@RequestParam Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getDoctorInfo(id));
    }

    @PreAuthorize("permitAll()")
    @PostMapping
    public ResponseEntity<DoctorResponse> saveDoctor(@RequestBody DoctorCreateRequest createRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(createRequest));
    }

    @PutMapping
    public ResponseEntity<DoctorResponse> updateDoctor(@RequestBody DoctorUpdateRequest createRequest){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.update(createRequest));
    }

    @DeleteMapping
    public ResponseEntity<DoctorResponse> saveDoctor(@RequestParam Long id){
        service.deleteDoctor(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
