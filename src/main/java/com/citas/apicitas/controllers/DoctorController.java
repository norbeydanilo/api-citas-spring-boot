package com.citas.apicitas.controllers;

import com.citas.apicitas.entities.Cita;
import com.citas.apicitas.entities.Doctor;
import com.citas.apicitas.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping("/doctores")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("")
    public ResponseEntity<Set<Doctor>> getDoctors() {
        Set<Doctor> doctors = doctorService.findAll();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctor(@PathVariable long id) {
        Doctor doctor = doctorService.findById(id);
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
        Doctor addedDoctor = doctorService.addDoctor(doctor);
        return new ResponseEntity<>(addedDoctor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> modifyDoctor(@PathVariable long id, @RequestBody Doctor newDoctor) {
        Doctor doctor = doctorService.modifyDoctor(id, newDoctor);
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable long id) {
        doctorService.deleteDoctor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/especialidad/{especialidad}")
    public ResponseEntity<Set<Doctor>> getDoctorsByEspecialidad(@PathVariable Doctor.Especialidad especialidad) {
        Set<Doctor> doctors = doctorService.findAllByEspecialidad(especialidad);
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @GetMapping("/{id}/citas")
    public ResponseEntity<Set<Cita>> getCitasByDoctorId(@PathVariable long id) {
        Set<Cita> citas = doctorService.findCitasByDoctorId(id);
        return new ResponseEntity<>(citas, HttpStatus.OK);
    }
}
