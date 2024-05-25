package org.iesvdm.api_rest.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    private String name;
    private String lastName1;
    private String lastName2;
    private String email;
    private String phone;

    @JsonIgnore
    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "invitation_id")
    private Invitation invitation;
}