package com.example.clinicaOdontologica.service;

import com.example.clinicaOdontologica.domain.Odontologo;
import com.example.clinicaOdontologica.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class OdontologoServiceTest {

    @Autowired
    OdontologoService odontologoService;

    @Test
    @Order(1)
    public void guardarOdontologoTest(){
        Odontologo odontologo = new Odontologo("abc123","Miguel","Ruiz");
        Odontologo odontologoGuardado = odontologoService.guardarOdontologo(odontologo);

        assertEquals(1L, odontologoGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarOdontologoTest(){
        Long idABuscar = 1L;
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(idABuscar);
        assertNotNull(odontologoBuscado.get());
    }

    @Test
    @Order(3)
    public void listarOdontologosTest(){
        Odontologo odontologo = new Odontologo("def456","Julian","Garcia");
        odontologoService.guardarOdontologo(odontologo);

        List<Odontologo> listaOdontologos = odontologoService.buscarTodos();
        assertEquals(2,listaOdontologos.size());
    }

    @Test
    @Order(4)
    public void actualizarOdontologoTest(){
        Odontologo odontologo = new Odontologo("def456","Julian","Herrera");
        odontologo.setId(2L);

        Odontologo odontologoActualizado = odontologoService.actualizarOdontologo(odontologo);
        assertEquals("Herrera", odontologoActualizado.getApellido());
    }

    @Test
    @Order(5)
    public void eliminarOdontologoTest() throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(2L);
        assertFalse(odontologoService.buscarOdontologo(2L).isPresent());
    }
}