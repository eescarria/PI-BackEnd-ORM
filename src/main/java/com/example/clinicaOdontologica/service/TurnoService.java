package com.example.clinicaOdontologica.service;

import com.example.clinicaOdontologica.domain.Turno;
import com.example.clinicaOdontologica.repository.TurnosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

    private TurnosRepository turnosRepository;

    @Autowired
    public TurnoService(TurnosRepository turnosRepository) {
        this.turnosRepository = turnosRepository;
    }

    public Turno guardarTurno(Turno turno){
        return turnosRepository.save(turno);
    }

    public List<Turno> listarTurnos(){
        return turnosRepository.findAll();
    }

    public void eliminarTurno(Long id){
        turnosRepository.deleteById(id);
    }

    public Optional<Turno> buscarTurno(Long id){
        return turnosRepository.findById(id);
    }

    public Turno actualizarTurno(Turno turno){
        return turnosRepository.save(turno);
    }
}
