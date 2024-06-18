package org.iesvdm.api_rest.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "guest",
        indexes = @Index(name = "guest_name_index", columnList = "name"))
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    private String name;
    private String lastname1;
    private String lastname2;
    private String email;
    private String phone;

    @JsonIgnore
    @ToString.Exclude
    @JsonBackReference
    @OneToOne(mappedBy = "guest", cascade = CascadeType.PERSIST)
    @JoinColumn(name = "invitation_id")
    private Invitation invitation;
}