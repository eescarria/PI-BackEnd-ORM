package com.example.clinicaOdontologica.controller;
import com.example.clinicaOdontologica.domain.Odontologo;
import com.example.clinicaOdontologica.service.OdontologoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private static final Logger logger = Logger.getLogger(OdontologoController.class);

    private OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @GetMapping("id/{id}")
    public ResponseEntity<Odontologo> buscarOdontologo(@PathVariable Long id){
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(id);
        if(odontologoBuscado.isPresent()){
            logger.debug("Se ha encontrado el odontólogo con id: " + id);
            return ResponseEntity.ok(odontologoBuscado.get());
        }else{
            logger.debug("No se ha encontrado el odontólogo con id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo){
        logger.debug("Se ha creado el odontólogo: " + odontologo.getNombre() + " " + odontologo.getApellido());
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }

    @PutMapping
    public ResponseEntity<Odontologo> actualizarOdontologo(@RequestBody Odontologo odontologo){
        ResponseEntity<Odontologo> respuesta;

        if(odontologoService.buscarOdontologo(odontologo.getId()).isPresent()){
            respuesta = ResponseEntity.ok(odontologoService.actualizarOdontologo(odontologo));
            logger.debug("Se ha actualizado el odontólogo con id: " + odontologo.getId());
        }else {
            respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            logger.debug("No se ha encontrado el odontólogo con id: " + odontologo.getId());
        }
        return respuesta;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id){
        if(odontologoService.buscarOdontologo(id).isPresent()){
            odontologoService.eliminarOdontologo(id);
            logger.debug("Odontólogo eliminado - ID: " + id);
            return ResponseEntity.ok("Odontólogo eliminado - ID: " + id);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Odontólogo no encontrado");
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> listarOdontologos(){
        logger.debug("Se genera la lista de odontólogos");
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }
}
