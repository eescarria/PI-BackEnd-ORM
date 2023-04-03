package com.example.clinicaOdontologica.controller;

import com.example.clinicaOdontologica.domain.Paciente;
import com.example.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.example.clinicaOdontologica.service.PacienteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private static final Logger logger = Logger.getLogger(PacienteController.class);
    private PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;

    }

    @PostMapping
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente){
        logger.debug("Se ha creado el paciente: " + paciente.getNombre() + " " + paciente.getApellido());
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));

    }

    @PutMapping
    public ResponseEntity<Paciente> actualizarPaciente(@RequestBody Paciente paciente){
        ResponseEntity<Paciente> respuesta;

        if(pacienteService.buscarPaciente(paciente.getId()).isPresent()){
            respuesta= ResponseEntity.ok(pacienteService.actualizarPaciente(paciente));
            logger.debug("Se ha actualizado el paciente con id: " + paciente.getId());
        }else {
            respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            logger.debug("No se ha encontrado el paciente con id: " + paciente.getId());
        }
        return respuesta;

    }

    @GetMapping("id/{id}")
    public ResponseEntity<Paciente> buscarPaciente(@PathVariable Long id){
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(id);
        if(pacienteBuscado.isPresent()){
            logger.debug("Se ha encontrado el paciente con id: " + id);
            return ResponseEntity.ok(pacienteBuscado.get());
        }else{
            logger.debug("No se ha encontrado el paciente con id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(id);
        logger.debug("Paciente eliminado - ID: " + id);
        return ResponseEntity.ok("Paciente eliminado - ID: " +id);

    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listarPacientes(){
        logger.debug("Se genera la lista de pacientes");
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

    @GetMapping("email/{email}")
    public ResponseEntity<Paciente> buscarPacienteXEmail(@PathVariable String email){
        Optional<Paciente> pacienteBuscado = pacienteService.buscarXEmail(email);
        if(pacienteBuscado.isPresent()){
            logger.debug("Se ha encontrado el paciente con email: " + email);
            return ResponseEntity.ok(pacienteBuscado.get());
        }else{
            logger.debug("No se ha encontrado el paciente con email: " + email);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
}
