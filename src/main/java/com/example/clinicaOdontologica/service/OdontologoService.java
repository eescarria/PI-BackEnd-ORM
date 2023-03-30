package com.example.clinicaOdontologica.service;

import com.example.clinicaOdontologica.domain.Odontologo;
import com.example.clinicaOdontologica.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {

    private OdontologoRepository odontologoRepository;

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    public Odontologo guardarOdontologo(Odontologo odontologo){
        return odontologoRepository.save(odontologo);
    }

    public List<Odontologo> buscarTodos(){
        return odontologoRepository.findAll();
    }

    public Optional<Odontologo> buscarOdontologo(Long id){
        return odontologoRepository.findById(id);
    }

    public void eliminarOdontologo(Long id){
        odontologoRepository.deleteById(id);
    }

    public Odontologo actualizarOdontologo(Odontologo odontologo){
        return odontologoRepository.save(odontologo);
    }
}
