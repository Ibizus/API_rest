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
@Table(name = "gift",
        indexes = @Index(name = "gift_name_index", columnList = "name"))
public class Gift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    private String name;
    private Boolean selected;

    @JsonBackReference
    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    private Wedding wedding;
}