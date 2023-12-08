package com.citas.apicitas.services;

import com.citas.apicitas.entities.Cita;
import com.citas.apicitas.entities.Paciente;
import com.citas.apicitas.exception.ResourceNotFoundException;
import com.citas.apicitas.repositories.PacienteRepository;
import com.citas.apicitas.repositories.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private CitaRepository citaRepository;

    @Override
    public Set<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

    @Override
    public Paciente findById(long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente not found with id: " + id));
        return paciente;
    }

    @Override
    public Paciente addPaciente(Paciente paciente) {
        Optional<Paciente> existingPaciente = pacienteRepository.findById(paciente.getIdNumeroCedula());
        if (existingPaciente.isPresent()) {
            throw new DataIntegrityViolationException("Data integrity violation");
        } else {
            return pacienteRepository.save(paciente);
        }
    }

    @Override
    public Paciente modifyPaciente(long id, Paciente newPaciente) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente not found with id: " + id));
        newPaciente.setIdNumeroCedula(paciente.getIdNumeroCedula());
        return pacienteRepository.save(newPaciente);
    }

    @Override
    public void deletePaciente(long id) {
        pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente not found with id: " + id));
        pacienteRepository.deleteById(id);
    }

    @Override
    public Set<Cita> findCitasByPacienteId(long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente not found with id: " + id));
        Set<Cita> citas = citaRepository.findCitasByPacienteId(id);
        paciente.setCitas(citas);
        return paciente.getCitas();
    }
    
}
