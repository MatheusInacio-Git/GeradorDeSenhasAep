package com.example.GeradorDeSenhasAep.controller;

import com.example.GeradorDeSenhasAep.model.Senha;
import com.example.GeradorDeSenhasAep.model.Usuario;
import com.example.GeradorDeSenhasAep.service.GerenciadorDeSenhas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/senhas")
public class SenhaController {

    @Autowired
    private GerenciadorDeSenhas gerenciadorDeSenhas;

    @PostMapping
    public void adicionarSenha(@RequestBody Senha senha) {
        gerenciadorDeSenhas.adicionarSenha(senha);
    }

    @GetMapping("/{idUsuario}")
    public List<Senha> listarSenhasDoUsuario(@PathVariable int idUsuario) {
        Usuario usuario = gerenciadorDeSenhas.buscarUsuarioPorId(idUsuario);
        if (usuario != null) {
            return gerenciadorDeSenhas.listarSenhasDoUsuario(usuario.getIdUsuario());
        } else {
            return List.of(); // Retorna uma lista vazia se o usuário não for encontrado
        }
    }
}
