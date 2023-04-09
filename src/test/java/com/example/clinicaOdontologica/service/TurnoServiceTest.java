package com.example.clinicaOdontologica.service;

import com.example.clinicaOdontologica.domain.Domicilio;
import com.example.clinicaOdontologica.domain.Odontologo;
import com.example.clinicaOdontologica.domain.Paciente;
import com.example.clinicaOdontologica.dto.TurnoDTO;
import com.example.clinicaOdontologica.exceptions.BadRequestException;
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
class TurnoServiceTest {

    @Autowired
    TurnoService turnoService;
    @Autowired
    OdontologoService odontologoService;
    @Autowired
    PacienteService pacienteService;

    @Test
    @Order(1)
    public void guardarTurnoTest() throws BadRequestException {
        Domicilio domicilio = new Domicilio("Avenida","Sexta","Cali","Valle");
        Paciente paciente = new Paciente("Luis","Alvarez","mno134",
                LocalDate.of(2023,3,20),"luis@mail.com",domicilio);
        pacienteService.guardarPaciente(paciente);

        Odontologo odontologo = new Odontologo("abc012","Felipe","Martinez");
        odontologoService.guardarOdontologo(odontologo);

        TurnoDTO turnoDTO = new TurnoDTO();
        turnoDTO.setPaciente_id(2L);
        turnoDTO.setNombre_paciente("Luis");
        turnoDTO.setOdontologo_id(2L);
        turnoDTO.setNombre_odontologo("Felipe");
        turnoDTO.setFecha(LocalDate.of(2023,4,15));
        TurnoDTO turnoGuardado = turnoService.guardarTurno(turnoDTO);

        assertEquals(1L, turnoGuardado.getId());
    }

    @Test
    @Order(2)
    public void listarTurnosTest() throws BadRequestException {
        TurnoDTO turnoDTO = new TurnoDTO();
        turnoDTO.setPaciente_id(2L);
        turnoDTO.setNombre_paciente("Estefania");
        turnoDTO.setOdontologo_id(2L);
        turnoDTO.setNombre_odontologo("Miguel");
        turnoDTO.setFecha(LocalDate.of(2023,4,25));
        turnoService.guardarTurno(turnoDTO);

        List<TurnoDTO> listaTurnos = turnoService.listarTurnos();
        assertEquals(2,listaTurnos.size());
    }

    @Test
    @Order(3)
    public void buscarTurnoTest(){
        Long idABuscar = 2L;
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurno(idABuscar);
        assertNotNull(turnoBuscado.get());
    }

    @Test
    @Order(4)
    public void actualizarTurnoTest(){
        TurnoDTO turnoAActualizar = new TurnoDTO();
        turnoAActualizar.setId(2L);
        turnoAActualizar.setPaciente_id(1L);
        turnoAActualizar.setNombre_paciente("Estefania");
        turnoAActualizar.setOdontologo_id(1L);
        turnoAActualizar.setNombre_odontologo("Miguel");
        turnoAActualizar.setFecha(LocalDate.of(2023,5,25));

        TurnoDTO turnoActualizado = turnoService.actualizarTurno(turnoAActualizar);
        assertEquals(LocalDate.of(2023,5,25), turnoActualizado.getFecha());
    }

    @Test
    @Order(5)
    public void eliminarTurnoTest() throws ResourceNotFoundException {
        turnoService.eliminarTurno(2L);
        assertFalse(turnoService.buscarTurno(2L).isPresent());
    }
}