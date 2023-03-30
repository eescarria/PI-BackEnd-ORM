package com.example.clinicaOdontologica.repository;

import com.example.clinicaOdontologica.domain.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnosRepository extends JpaRepository<Turno,Long> {
}
