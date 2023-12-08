package com.citas.apicitas.controllers;

import com.citas.apicitas.entities.Cita;
import com.citas.apicitas.entities.Paciente;
import com.citas.apicitas.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping("")
    public ResponseEntity<Set<Paciente>> getPacientes() {
        Set<Paciente> pacientes = pacienteService.findAll();
        return new ResponseEntity<>(pacientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getPaciente(@PathVariable long id) {
        Paciente paciente = pacienteService.findById(id);
        return new ResponseEntity<>(paciente, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Paciente> addPaciente(@RequestBody Paciente paciente) {
        Paciente addedPaciente = pacienteService.addPaciente(paciente);
        return new ResponseEntity<>(addedPaciente, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> modifyPaciente(@PathVariable long id, @RequestBody Paciente newPaciente) {
        Paciente paciente = pacienteService.modifyPaciente(id, newPaciente);
        return new ResponseEntity<>(paciente, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable long id) {
        pacienteService.deletePaciente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
 
    @GetMapping("/{id}/citas")
    public ResponseEntity<Set<Cita>> getCitasByPacienteId(@PathVariable long id) {
        Set<Cita> citas = pacienteService.findCitasByPacienteId(id);
        return new ResponseEntity<>(citas, HttpStatus.OK);
    }
}
