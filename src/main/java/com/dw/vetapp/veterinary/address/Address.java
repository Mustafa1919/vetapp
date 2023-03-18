package com.dw.vetapp.veterinary.address;

import com.dw.vetapp.veterinary.animalowner.AnimalOwner;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    private String country;
    private String county;
    private String street;
    private String buildingNumber;
    private Integer doorNumber;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @JsonIgnore
    private AnimalOwner animalOwner;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(addressId, address.addressId) && Objects.equals(country, address.country) && Objects.equals(county, address.county) && Objects.equals(street, address.street) && Objects.equals(buildingNumber, address.buildingNumber) && Objects.equals(doorNumber, address.doorNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId, country, county, street, buildingNumber, doorNumber);
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", country='" + country + '\'' +
                ", county='" + county + '\'' +
                ", street='" + street + '\'' +
                ", buildingNumber='" + buildingNumber + '\'' +
                ", doorNumber=" + doorNumber +
                ", animalOwner=" + animalOwner +
                '}';
    }
}
