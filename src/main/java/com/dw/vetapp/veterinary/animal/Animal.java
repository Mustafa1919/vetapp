package com.dw.vetapp.veterinary.animal;


import com.dw.vetapp.veterinary.animalowner.AnimalOwner;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "animal")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long animalId;

    private String type;
    private String kind;
    private String name;
    private int age;
    private String comment;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @JsonIgnore
    private AnimalOwner owner;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return age == animal.age && Objects.equals(animalId, animal.animalId) && Objects.equals(type, animal.type) && Objects.equals(kind, animal.kind) && Objects.equals(name, animal.name) && Objects.equals(comment, animal.comment) && Objects.equals(owner, animal.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(animalId, type, kind, name, age, comment);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "animalId=" + animalId +
                ", type='" + type + '\'' +
                ", kind='" + kind + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", comment='" + comment + '\'' +
                '}';
    }
}
