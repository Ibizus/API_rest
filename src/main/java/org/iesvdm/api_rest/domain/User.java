package org.iesvdm.api_rest.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "user",
        indexes = @Index(name = "user_name_index", columnList = "name"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    private String name;
    private String lastName1;
    private String lastName2;
    private String address;
    private String address2;
    private String postalCode;
    private String city;
    private String region;
    private String email;
    private String phoneNumber;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Wedding> weddings = new HashSet<>();
}