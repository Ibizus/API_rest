package org.iesvdm.api_rest.domain;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "wedding",
        indexes = @Index(name = "wedding_name_index", columnList = "name"))
public class Wedding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    private String name;
    private LocalDate date;
    private LocalTime time;
    private String partner1Name;
    private String partner2Name;
    private String address;
    private String address2;
    private String postalCode;
    private String city;
    private String region;

    @JsonManagedReference
    @OneToMany(mappedBy = "wedding", fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    private Set<Event> events = new HashSet<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "wedding", fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    private Set<Task> tasks = new HashSet<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "wedding", fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    private Set<Menu> menus = new HashSet<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "wedding", fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    private Set<Invitation> invitations = new HashSet<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "wedding", fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    private Set<Gift> gifts = new HashSet<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "wedding", fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    private Set<Photo> gallery = new HashSet<>();

    @JsonBackReference
    @ToString.Exclude
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
