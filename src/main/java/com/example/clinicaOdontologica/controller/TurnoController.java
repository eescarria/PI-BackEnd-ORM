package com.example.clinicaOdontologica.controller;

import com.example.clinicaOdontologica.domain.Odontologo;
import com.example.clinicaOdontologica.domain.Paciente;
import com.example.clinicaOdontologica.dto.TurnoDTO;
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
    public ResponseEntity<TurnoDTO> registrarTurno(@RequestBody TurnoDTO turno){
      ResponseEntity<TurnoDTO> respuesta;
      Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(turno.getPaciente_id());
      Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(turno.getOdontologo_id());

      if(pacienteBuscado.isPresent() && odontologoBuscado.isPresent()){
          respuesta = ResponseEntity.ok(turnoService.guardarTurno(turno));
          logger.debug("Se ha generado el turno para la fecha: "+ turno.getFecha());
      }else {
          respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
          logger.debug("No se logró generar el turno para la fecha: "+ turno.getFecha());
      }
      return respuesta;
    }

    @GetMapping
    public ResponseEntity<List<TurnoDTO>> listarTurnos(){
        ResponseEntity<List<TurnoDTO>> respuesta;
        if(!turnoService.listarTurnos().isEmpty()){
            respuesta = ResponseEntity.ok(turnoService.listarTurnos());
            logger.debug("Se genera la lista de turnos");
        }else {
            respuesta = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            logger.debug("No se logró generar la lista de turnos");
        }

        return respuesta;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id){
        if(turnoService.buscarTurno(id).isPresent()){
            turnoService.eliminarTurno(id);
            logger.debug("Turno eliminado - ID: " + id);
            return ResponseEntity.ok("Turno eliminado - ID: " +id);
        }else {
            logger.debug("Turno no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Turno no encontrado");
        }
    }

    @GetMapping("id/{id}")
    public ResponseEntity<TurnoDTO> buscarTurno(@PathVariable Long id){
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurno(id);
        if(turnoBuscado.isPresent()){
            logger.debug("Se ha encontrado el turno con id: " + id);
            return ResponseEntity.ok(turnoBuscado.get());
        }else{
            logger.debug("No se ha encontrado el turno con id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping
    public ResponseEntity<TurnoDTO> actualizarTurno(@RequestBody TurnoDTO turnoDTO){
        ResponseEntity<TurnoDTO> respuesta;

        if(turnoService.buscarTurno(turnoDTO.getId()).isPresent()){
            respuesta = ResponseEntity.ok(turnoService.actualizarTurno(turnoDTO));
            logger.debug("Se ha actualizado el turno con id: " + turnoDTO.getId());
        }else {
            respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            logger.debug("No se ha encontrado el turno con id: " + turnoDTO.getId());
        }
        return respuesta;
    }

}
