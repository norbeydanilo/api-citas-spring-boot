package com.citas.apicitas.services;

import com.citas.apicitas.entities.Cita;
import com.citas.apicitas.entities.Doctor;
import com.citas.apicitas.exception.ResourceNotFoundException;
import com.citas.apicitas.repositories.DoctorRepository;
import com.citas.apicitas.repositories.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private CitaRepository citaRepository;

    @Override
    public Set<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public Set<Doctor> findAllByEspecialidad(Doctor.Especialidad especialidad) {
        return doctorRepository.findAllByEspecialidad(especialidad);
    }

    @Override
    public Doctor findById(long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
        return doctor;
    }

    @Override
    public Doctor addDoctor(Doctor doctor) {
        Optional<Doctor> existingDoctor = doctorRepository.findById(doctor.getIdProfesional());
        if (existingDoctor.isPresent()) {
            throw new DataIntegrityViolationException("Data integrity violation");
        } else {
            return doctorRepository.save(doctor);
        }
    }

    @Override
    public Doctor modifyDoctor(long id, Doctor newDoctor) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
        newDoctor.setIdProfesional(doctor.getIdProfesional());
        return doctorRepository.save(newDoctor);
    }

    @Override
    public void deleteDoctor(long id) {
        doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
        doctorRepository.deleteById(id);
    }

    @Override
    public Set<Cita> findCitasByDoctorId(long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
        Set<Cita> citas = citaRepository.findCitasByDoctorId(id);
        doctor.setCitas(citas);
        return doctor.getCitas();
    }
}
