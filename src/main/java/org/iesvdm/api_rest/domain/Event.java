package org.iesvdm.api_rest.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "event",
        indexes = @Index(name = "event_description_index", columnList = "description"))
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    private String location;
    private String description;
    private LocalTime time;

    @JsonBackReference
    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    private Wedding wedding;
}