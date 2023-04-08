package com.example.clinicaOdontologica.security;

import com.example.clinicaOdontologica.domain.*;
import com.example.clinicaOdontologica.repository.OdontologoRepository;
import com.example.clinicaOdontologica.repository.PacienteRepository;
import com.example.clinicaOdontologica.repository.TurnosRepository;
import com.example.clinicaOdontologica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements ApplicationRunner {

    private UsuarioRepository usuarioRepository;
    private OdontologoRepository odontologoRepository;
    private PacienteRepository pacienteRepository;


    @Autowired
    public DataLoader(UsuarioRepository usuarioRepository, OdontologoRepository odontologoRepository, PacienteRepository pacienteRepository) {
        this.usuarioRepository = usuarioRepository;
        this.odontologoRepository = odontologoRepository;
        this.pacienteRepository = pacienteRepository;

    }

    @Override
    public void run(ApplicationArguments args) {
        BCryptPasswordEncoder cifrador= new BCryptPasswordEncoder();

        String passACifrar1="123456789";
        String passCifrada1=cifrador.encode(passACifrar1);
        Usuario usuarioAInsertar= new Usuario("Estefania","Escarria",
                "estefy@gmail.com",passCifrada1, UsuarioRol.ROLE_USER);
        usuarioRepository.save(usuarioAInsertar);

        String passACifrar2="abcdefghi";
        String passCifrada2=cifrador.encode(passACifrar2);
        Usuario adminAInsertar= new Usuario("Juan","Alvear",
                "juan@gmail.com",passCifrada2, UsuarioRol.ROLE_ADMIN);
        usuarioRepository.save(adminAInsertar);

        Domicilio domicilio = new Domicilio("Avenida","Sexta","Cali","Valle");
        Paciente paciente = new Paciente("Escarria","Estefania","abc123",
                LocalDate.of(2023,3,15),"estefy@mail.com",domicilio);
        pacienteRepository.save(paciente);

        Odontologo odontologo = new Odontologo("abc123","Miguel","Ruiz");
        odontologoRepository.save(odontologo);
    }
}
