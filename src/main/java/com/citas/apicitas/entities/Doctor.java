package com.citas.apicitas.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "doctor")
public class Doctor {

    @Id
    @Column(name = "id_profesional")
    private long idProfesional;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private String correo;

    @Enumerated(EnumType.STRING)
    @Column
    private Especialidad especialidad;

    @OneToMany(mappedBy = "doctor")
    @JsonManagedReference // evita el bucle en la referencia
    @JsonIgnore // ignora que aparezca en la respuesta del objeto
    private Set<Cita> citas;

    public enum Especialidad {
        medicina_interna,
        medicina_general
    }
}
