package com.citas.apicitas.repositories;

import java.util.Set;
import org.springframework.data.repository.CrudRepository;
import com.citas.apicitas.entities.Paciente;

public interface PacienteRepository extends CrudRepository<Paciente, Long> {
    Set<Paciente> findAll();
}
