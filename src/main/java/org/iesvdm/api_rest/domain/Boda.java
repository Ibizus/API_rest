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
public class Boda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    private String nombre;
    private LocalDate fecha;
    private LocalTime hora;
    private String nombre_pareja1;
    private String nombre_pareja2;
    private String direccion;
    private String direccion2;
    private String cod_postal;
    private String ciudad;
    private String region;

    @OneToMany(mappedBy = "boda", fetch = FetchType.EAGER)
    private Set<Evento> eventos = new HashSet<>();

    @OneToMany(mappedBy = "boda", fetch = FetchType.EAGER)
    private Set<Tarea> tareas = new HashSet<>();

    @OneToMany(mappedBy = "boda", fetch = FetchType.EAGER)
    private Set<Menu> menus = new HashSet<>();

    @OneToMany(mappedBy = "boda", fetch = FetchType.EAGER)
    private Set<Invitacion> invitaciones = new HashSet<>();

    @OneToMany(mappedBy = "boda", fetch = FetchType.EAGER)
    private Set<Regalo> regalos = new HashSet<>();

    @OneToMany(mappedBy = "boda", fetch = FetchType.EAGER)
    private Set<Foto> galeria = new HashSet<>();

    @ToString.Exclude
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;
}