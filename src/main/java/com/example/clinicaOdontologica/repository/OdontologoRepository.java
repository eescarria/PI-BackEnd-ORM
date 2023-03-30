package com.example.clinicaOdontologica.repository;

import com.example.clinicaOdontologica.domain.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {
}
