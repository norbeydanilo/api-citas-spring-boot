package com.citas.apicitas.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.citas.apicitas.entities.Cita;
import com.citas.apicitas.entities.CitaId;
import java.util.Set;

public interface CitaRepository extends CrudRepository<Cita, CitaId> {
    Set<Cita> findAll();

    Optional<Cita> findById(CitaId id);

    @Query("SELECT c FROM cita c WHERE c.paciente.idNumeroCedula = :idNumeroCedula")
    Set<Cita> findCitasByPacienteId(@Param("idNumeroCedula") Long idNumeroCedula);

    @Query("SELECT c FROM cita c WHERE c.doctor.idProfesional = :idProfesional")
    Set<Cita> findCitasByDoctorId(@Param("idProfesional") Long idProfesional);
}
