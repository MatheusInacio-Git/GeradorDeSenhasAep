package com.example.GeradorDeSenhasAep.repository;

import com.example.GeradorDeSenhasAep.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
