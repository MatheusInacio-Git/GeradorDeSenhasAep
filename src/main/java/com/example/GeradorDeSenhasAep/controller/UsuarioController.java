package com.example.GeradorDeSenhasAep.controller;

import com.example.GeradorDeSenhasAep.model.Usuario;
import com.example.GeradorDeSenhasAep.service.GerenciadorDeSenhas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private GerenciadorDeSenhas gerenciadorDeSenhas;

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return gerenciadorDeSenhas.listarUsuarios();
    }

    @PostMapping
    public Usuario adicionarUsuario(@RequestBody Usuario usuario) {
        return gerenciadorDeSenhas.adicionarUsuario(usuario);
    }

    @GetMapping("/{id}")
    public Usuario buscarUsuarioPorId(@PathVariable int id) {
        return gerenciadorDeSenhas.buscarUsuarioPorId(id);
    }
}
