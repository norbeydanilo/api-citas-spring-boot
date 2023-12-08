package com.citas.apicitas.controllers;

import com.citas.apicitas.entities.Cita;
import com.citas.apicitas.entities.CitaId;
import com.citas.apicitas.entities.Doctor;
import com.citas.apicitas.entities.Paciente;
import com.citas.apicitas.services.CitaService;
import com.citas.apicitas.services.DoctorService;
import com.citas.apicitas.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private DoctorService doctorService;

    @GetMapping("")
    public ResponseEntity<Set<Cita>> getCitas() {
        Set<Cita> citas = citaService.findAll();
        return new ResponseEntity<>(citas, HttpStatus.OK);
    }

    @GetMapping("/one")
    public ResponseEntity<Cita> getCita(@RequestParam long idProfesional, @RequestParam long idNumeroCedula,
            @RequestParam LocalDateTime fechaHora) {
        CitaId id = new CitaId(idProfesional, idNumeroCedula, fechaHora);
        Cita cita = citaService.findById(id);
        return new ResponseEntity<>(cita, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<CitaId> addCita(@RequestBody CitaId citaId) {
        Doctor doctor = doctorService.findById(citaId.getIdProfesional());
        Paciente paciente = pacienteService.findById(citaId.getIdNumeroCedula());

        Cita cita = new Cita();
        cita.setId(citaId);
        cita.setDoctor(doctor);
        cita.setPaciente(paciente);

        Cita addedCita = citaService.addCita(cita, citaId);
        return new ResponseEntity<>(addedCita.getId(), HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<Cita> modifyCita(@RequestBody CitaId newCita, @RequestParam long idProfesional, @RequestParam long idNumeroCedula,
            @RequestParam LocalDateTime fechaHora) {
        CitaId oldCitaId = new CitaId(idProfesional, idNumeroCedula, fechaHora);
        Cita cita = citaService.modifyCita(oldCitaId, newCita);
        return new ResponseEntity<>(cita, HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteCita(@RequestBody CitaId id) {
        citaService.deleteCita(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
