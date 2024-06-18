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
@Table(name = "menu",
        indexes = @Index(name = "menu_name_index", columnList = "name"))
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    private String name;
    private String starter;
    private String maincourse;
    private String dessert;

    @JsonBackReference
    @JsonIgnore
    @ToString.Exclude
    @ManyToOne(cascade =  CascadeType.PERSIST)
    @JoinColumn(name = "wedding_id")
    private Wedding wedding;
}