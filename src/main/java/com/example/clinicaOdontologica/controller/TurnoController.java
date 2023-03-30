package com.example.clinicaOdontologica.controller;

import com.example.clinicaOdontologica.domain.Turno;
import com.example.clinicaOdontologica.service.OdontologoService;
import com.example.clinicaOdontologica.service.PacienteService;
import com.example.clinicaOdontologica.service.TurnoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private static final Logger logger = Logger.getLogger(TurnoController.class);

    private TurnoService turnoService;
    private OdontologoService odontologoService;
    private PacienteService pacienteService;


    @Autowired
    public TurnoController(TurnoService turnoService, OdontologoService odontologoService, PacienteService pacienteService) {
        this.turnoService = turnoService;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }


    @PostMapping
    public ResponseEntity<Turno> registrarTurno(@RequestBody Turno turno){
      ResponseEntity<Turno> respuesta;
      if(pacienteService.buscarPaciente(turno.getPaciente().getId()).isPresent() &&
              odontologoService.buscarOdontologo(turno.getOdontologo().getId()).isPresent()){
          respuesta = ResponseEntity.ok(turnoService.guardarTurno(turno));
      }else {
          respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
      }
      return respuesta;
    }

    @GetMapping
    public ResponseEntity<List<Turno>> listarTurnos(){
        ResponseEntity<List<Turno>> respuesta;
        if(!turnoService.listarTurnos().isEmpty()){
            respuesta = ResponseEntity.ok(turnoService.listarTurnos());
        }else {
            respuesta = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return respuesta;
    }

    @GetMapping("id/{id}")
    public ResponseEntity<Turno> buscarTurno(@PathVariable Long id){
        Optional<Turno> turnoBuscado = turnoService.buscarTurno(id);
        if(turnoBuscado.isPresent()){
            logger.debug("Se ha encontrado el turno con id: " + id);
            return ResponseEntity.ok(turnoBuscado.get());
        }else{
            logger.debug("No se ha encontrado el turno con id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id){
        if(turnoService.buscarTurno(id).isPresent()){
            turnoService.eliminarTurno(id);
            logger.debug("Turno eliminado - ID: " + id);
            return ResponseEntity.ok("Turno eliminado - ID: " +id);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turno no encontrado");
        }
    }

    @PutMapping
    public ResponseEntity<Turno> actualizarTurno(@RequestBody Turno turno){
        ResponseEntity<Turno> respuesta;

        if(turnoService.buscarTurno(turno.getId()).isPresent()){
            respuesta = ResponseEntity.ok(turnoService.actualizarTurno(turno));
            logger.debug("Se ha actualizado el turno con id: " + turno.getId());
        }else {
            respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            logger.debug("No se ha encontrado el turno con id: " + turno.getId());
        }
        return respuesta;
    }

}
