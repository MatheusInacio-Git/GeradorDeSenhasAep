package com.example.GeradorDeSenhasAep.repository;

import com.example.GeradorDeSenhasAep.model.Senha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SenhaRepository extends JpaRepository<Senha, Integer> {
}
