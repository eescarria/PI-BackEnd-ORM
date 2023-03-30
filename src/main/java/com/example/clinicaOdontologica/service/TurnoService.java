package com.example.clinicaOdontologica.service;

import com.example.clinicaOdontologica.domain.Odontologo;
import com.example.clinicaOdontologica.domain.Paciente;
import com.example.clinicaOdontologica.domain.Turno;
import com.example.clinicaOdontologica.dto.TurnoDTO;
import com.example.clinicaOdontologica.repository.TurnosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

    private TurnosRepository turnosRepository;

    @Autowired
    public TurnoService(TurnosRepository turnosRepository) {
        this.turnosRepository = turnosRepository;
    }

    public TurnoDTO guardarTurno(TurnoDTO turnoDTO){
        Turno turno = convertirTurnoDTOATurno(turnoDTO);
        return convertirTurnoATurnoDTO(turnosRepository.save(turno));
    }

    public List<TurnoDTO> listarTurnos(){
        List<Turno> listaTurnos = turnosRepository.findAll();
        List<TurnoDTO> listaTurnosDTO = new ArrayList<>();
        for (Turno turno : listaTurnos) {
            listaTurnosDTO.add(convertirTurnoATurnoDTO(turno));
        }

        return listaTurnosDTO;
    }

    public void eliminarTurno(Long id){
        turnosRepository.deleteById(id);
    }

    public Optional<TurnoDTO> buscarTurno(Long id){
        Optional<Turno> turnoBuscado = turnosRepository.findById(id);
        if(turnoBuscado.isPresent()){
            return Optional.of(convertirTurnoATurnoDTO(turnoBuscado.get()));
        }else {
            return Optional.empty();
        }

    }

    public TurnoDTO actualizarTurno(TurnoDTO turnoDTO){
        Turno turno = convertirTurnoDTOATurno(turnoDTO);
        return convertirTurnoATurnoDTO(turnosRepository.save(turno));
    }

    private Turno convertirTurnoDTOATurno(TurnoDTO turnoDTO){
        Turno turno = new Turno();
        Paciente paciente = new Paciente();
        Odontologo odontologo = new Odontologo();

        turno.setId(turnoDTO.getId());
        turno.setFecha(turnoDTO.getFecha());
        paciente.setId(turnoDTO.getPaciente_id());
        paciente.setNombre(turnoDTO.getNombre_paciente());
        odontologo.setId(turnoDTO.getOdontologo_id());
        odontologo.setNombre(turnoDTO.getNombre_odontologo());

        turno.setOdontologo(odontologo);
        turno.setPaciente(paciente);
        return turno;
    }

    private TurnoDTO convertirTurnoATurnoDTO(Turno turno){
        TurnoDTO turnoDTO = new TurnoDTO();

        turnoDTO.setId(turno.getId());
        turnoDTO.setFecha(turno.getFecha());
        turnoDTO.setOdontologo_id(turno.getOdontologo().getId());
        turnoDTO.setNombre_odontologo(turno.getOdontologo().getNombre());
        turnoDTO.setPaciente_id(turno.getPaciente().getId());
        turnoDTO.setNombre_paciente(turno.getPaciente().getNombre());

        return turnoDTO;
    }
}
