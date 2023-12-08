package com.citas.apicitas.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class CitaId implements Serializable {
    private long idProfesional;
    private long idNumeroCedula;
    private LocalDateTime fechaHora;
}
