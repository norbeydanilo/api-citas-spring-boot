package com.citas.apicitas.services;

import com.citas.apicitas.entities.Cita;
import com.citas.apicitas.entities.CitaId;
import com.citas.apicitas.entities.Doctor;
import com.citas.apicitas.entities.Paciente;
import com.citas.apicitas.exception.ResourceNotFoundException;
import com.citas.apicitas.exception.ResourceAlreadyExistsException;
import com.citas.apicitas.repositories.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class CitaServiceImpl implements CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private DoctorService doctorService;

    @Override
    public Set<Cita> findAll() {
        return citaRepository.findAll();
    }

    @Override
    public Cita findById(CitaId id) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cita not found with id: " + id));
        return cita;
    }

    @Override
    public Cita addCita(Cita cita, CitaId citaId) {
        if (citaRepository.existsById(citaId)) {
            throw new ResourceAlreadyExistsException("Cita already exists with id: " + citaId);
        }
        return citaRepository.save(cita);
    }

    @Override
    public Cita modifyCita(CitaId oldCitaId, CitaId newCita) {
        Cita cita = citaRepository.findById(oldCitaId)
                .orElseThrow(() -> new ResourceNotFoundException("Cita not found with id: " + oldCitaId));

        // Actualizar los campos de la cita
        Doctor doctor = doctorService.findById(newCita.getIdProfesional());
        Paciente paciente = pacienteService.findById(newCita.getIdNumeroCedula());

        // Nueva cita para guardar
        Cita nuevaCita = new Cita();

        nuevaCita.setDoctor(doctor);
        nuevaCita.setPaciente(paciente);
        nuevaCita.setId(newCita); // Asignación de la clave primaria

        // Eliminar cita antigua
        citaRepository.delete(cita);

        return citaRepository.save(nuevaCita);

    }

    @Override
    public void deleteCita(CitaId id) {
        citaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cita not found with id: " + id));
        citaRepository.deleteById(id);
    }
}
