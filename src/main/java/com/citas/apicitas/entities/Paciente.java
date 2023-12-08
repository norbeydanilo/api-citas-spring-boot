package com.citas.apicitas.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "paciente")
public class Paciente {

    @Id
    @Column(name = "id_numero_cedula")
    private long idNumeroCedula;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private String telefono;

    @Column(name = "fecha_nacimiento")
    private LocalDateTime fechaNacimiento;

    @OneToMany(mappedBy = "paciente")
    @JsonManagedReference // evita el bucle en la referencia
    @JsonIgnore // ignora que aparezca en la respuesta del objeto
    private Set<Cita> citas = new HashSet<>();
}
