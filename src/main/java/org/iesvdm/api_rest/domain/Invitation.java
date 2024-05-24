package org.iesvdm.api_rest.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    private String name;
    private String email;
    private Boolean accepted;
    private String allergies;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    private Wedding wedding;

    @OneToOne(mappedBy = "invitation")
    private Guest guest;
}