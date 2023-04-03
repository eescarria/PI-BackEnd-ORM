package com.example.clinicaOdontologica.service;
import com.example.clinicaOdontologica.domain.Paciente;
import com.example.clinicaOdontologica.exceptions.ResourceNotFoundException;
import com.example.clinicaOdontologica.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    private PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }


    public Paciente guardarPaciente(Paciente paciente){
        return pacienteRepository.save(paciente);
    }

    public Optional<Paciente> buscarPaciente(Long id){
        return pacienteRepository.findById(id);
    }

    public void eliminarPaciente(Long id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado = pacienteRepository.findById(id);
        if(pacienteBuscado.isPresent()){
            pacienteRepository.deleteById(id);
        }else {
            throw new ResourceNotFoundException("Error. Paciente con id: " + id + " no encontrado");
        }

    }

    public Paciente actualizarPaciente(Paciente paciente){
        return pacienteRepository.save(paciente);
    }

    public List<Paciente> buscarTodos(){
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> buscarXEmail(String email){
        return pacienteRepository.findByEmail(email);
    }


}
