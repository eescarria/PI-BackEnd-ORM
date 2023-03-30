package com.example.clinicaOdontologica.repository;

import com.example.clinicaOdontologica.domain.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente,Long> {
    Optional<Paciente> findByEmail(String email);
}
