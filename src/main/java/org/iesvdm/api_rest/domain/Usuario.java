package org.iesvdm.api_rest.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    private String nombre;
    private String apellido1;
    private String apellido2;
    private String direccion;
    private String direccion2;
    private String cod_postal;
    private String ciudad;
    private String region;
    private String email;
    private String telefono;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    private Set<Boda> bodas = new HashSet<>();
}
