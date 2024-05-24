package org.iesvdm.api_rest.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToMany(mappedBy = "wedding", fetch = FetchType.EAGER)
    private Set<Event> events = new HashSet<>();

    @OneToMany(mappedBy = "wedding", fetch = FetchType.EAGER)
    private Set<Task> tasks = new HashSet<>();

    @OneToMany(mappedBy = "wedding", fetch = FetchType.EAGER)
    private Set<Menu> menus = new HashSet<>();

    @OneToMany(mappedBy = "wedding", fetch = FetchType.EAGER)
    private Set<Invitation> invitations = new HashSet<>();

    @OneToMany(mappedBy = "wedding", fetch = FetchType.EAGER)
    private Set<Gift> gifts = new HashSet<>();

    @OneToMany(mappedBy = "wedding", fetch = FetchType.EAGER)
    private Set<Photo> gallery = new HashSet<>();

    @ToString.Exclude
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
}

// Thank you very much!! You are great, now this is another entity: