package com.example.clinicaOdontologica.service;

import com.example.clinicaOdontologica.domain.Domicilio;
import com.example.clinicaOdontologica.domain.Paciente;
import com.example.clinicaOdontologica.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    public void guardarPacienteTest(){
        Domicilio domicilio = new Domicilio("Avenida","Cuarta","Medellin","Antioquia");
        Paciente paciente = new Paciente("Alvear","Luisa","zxy642",
                LocalDate.of(2023,3,10),"luisa@mail.com",domicilio);

        Paciente pacienteGuardado = pacienteService.guardarPaciente(paciente);
        assertEquals(2L,pacienteGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarPacienteTest(){
        Long idABuscar = 2L;
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(idABuscar);
        assertNotNull(pacienteBuscado.get());
    }

    @Test
    @Order(3)
    public void listarPacientesTest(){
        Domicilio domicilio = new Domicilio("Avenida","Quinta","Cali","Valle");
        Paciente paciente = new Paciente("Alvear","Juan","abc1234",
                LocalDate.of(2023,3,17),"juan@mail.com",domicilio);
        pacienteService.guardarPaciente(paciente);

        List<Paciente> listaPacientes = pacienteService.buscarTodos();
        assertEquals(3,listaPacientes.size());
    }

    @Test
    @Order(4)
    public void actualizarPacienteTest(){
        Domicilio domicilio = new Domicilio("Avenida","Quinta","Cali","Valle");
        Paciente pacienteAActualizar = new Paciente("Alvear","Juan","1234",
                LocalDate.of(2023,3,17),"juan@mail.com",domicilio);
        pacienteAActualizar.setId(3L);

        Paciente pacienteActualizado = pacienteService.actualizarPaciente(pacienteAActualizar);
        assertEquals("1234", pacienteActualizado.getDocumento());
    }
    @Test
    @Order(5)
    public void buscarPacienteXEmail(){
        String emailABuscar = "luisa@mail.com";
        Optional<Paciente> pacienteBuscado = pacienteService.buscarXEmail(emailABuscar);
        assertNotNull(pacienteBuscado.get());
    }

    @Test
    @Order(6)
    public void eliminarPacienteTest() throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(2L);
        assertFalse(pacienteService.buscarPaciente(2L).isPresent());
    }


}