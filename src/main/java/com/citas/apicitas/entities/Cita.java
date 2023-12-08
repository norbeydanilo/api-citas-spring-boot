package com.citas.apicitas.entities;

//import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "cita")
public class Cita {

    @EmbeddedId
    private CitaId id;

    @MapsId("idProfesional")
    @ManyToOne
    @JoinColumn(name = "id_profesional")
    //@JsonBackReference // evita el bucle en la referencia
    private Doctor doctor;

    @MapsId("idNumeroCedula")
    @ManyToOne
    @JoinColumn(name = "id_numero_cedula")
    //@JsonBackReference // evita el bucle en la referencia
    private Paciente paciente;
}
