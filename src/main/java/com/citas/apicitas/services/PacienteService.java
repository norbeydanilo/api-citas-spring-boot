package com.citas.apicitas.services;

import java.util.Set;

import com.citas.apicitas.entities.Cita;
import com.citas.apicitas.entities.Paciente;

public interface PacienteService {
    Set<Paciente> findAll();

    Paciente findById(long id);

    Paciente addPaciente(Paciente paciente);

    Paciente modifyPaciente(long id, Paciente newPaciente);

    void deletePaciente(long id);

    Set<Cita> findCitasByPacienteId(long id);
}
