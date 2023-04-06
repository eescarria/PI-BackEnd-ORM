package com.example.clinicaOdontologica.security;

import com.example.clinicaOdontologica.domain.Usuario;
import com.example.clinicaOdontologica.domain.UsuarioRol;
import com.example.clinicaOdontologica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public DataLoader(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        BCryptPasswordEncoder cifrador= new BCryptPasswordEncoder();
        String passACifrar="123456789";
        String passCifrada=cifrador.encode(passACifrar);
        Usuario usuarioAInsertar= new Usuario("Estefania","Escarria",
                "estefy@gmail.com",passCifrada, UsuarioRol.ROLE_USER);
        usuarioRepository.save(usuarioAInsertar);
    }
}
