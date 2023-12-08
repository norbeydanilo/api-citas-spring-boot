package com.citas.apicitas.services;

import java.util.Set;

import com.citas.apicitas.entities.Cita;
import com.citas.apicitas.entities.Doctor;

public interface DoctorService {
    Set<Doctor> findAll();

    Set<Doctor> findAllByEspecialidad(Doctor.Especialidad especialidad);

    Doctor findById(long id);

    Doctor addDoctor(Doctor doctor);

    Doctor modifyDoctor(long id, Doctor newDoctor);

    void deleteDoctor(long id);

    Set<Cita> findCitasByDoctorId(long id);
}
