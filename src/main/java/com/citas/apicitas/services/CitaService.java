package com.citas.apicitas.services;

import com.citas.apicitas.entities.Cita;
import com.citas.apicitas.entities.CitaId;
import java.util.Set;

public interface CitaService {
    Set<Cita> findAll();

    Cita findById(CitaId id);

    Cita addCita(Cita cita, CitaId id);

    Cita modifyCita(CitaId id, CitaId newCita);

    void deleteCita(CitaId id);
}
