package com.dw.vetapp.veterinary.user;

import com.dw.vetapp.veterinary.phonenumber.PhoneNumber;
import com.dw.vetapp.veterinary.helper.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

// @MappedSuperclass Yöntem 1 bunda tablo üretilmiyor buna özel
@Entity(name = "user_table")
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(unique = true)
    private String eMail;

    private String password;
    private String name;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PhoneNumber> phoneNumbers;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", eMail='" + eMail + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(eMail, user.eMail) && Objects.equals(password, user.password) && Objects.equals(name, user.name) && Objects.equals(lastName, user.lastName) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eMail, password, name, lastName, role);
    }
}
