package com.example.GeradorDeSenhasAep.controller;

import com.example.GeradorDeSenhasAep.model.Senha;
import com.example.GeradorDeSenhasAep.model.Usuario;
import com.example.GeradorDeSenhasAep.service.GerenciadorDeSenhas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/senhas")
public class SenhaController {

    @Autowired
    private GerenciadorDeSenhas gerenciadorDeSenhas;

    @PostMapping
    public void adicionarSenha(@RequestBody Senha senha) {
        // Verificar se o ID do usuário está presente
        if (senha.getUsuario() != null && senha.getUsuario().getIdUsuario() != 0) {
            // Se o ID do usuário estiver presente, buscar o usuário no banco de dados
            Usuario usuario = gerenciadorDeSenhas.buscarUsuarioPorId(senha.getUsuario().getIdUsuario());

            // Se o usuário não for encontrado, lançar um erro (pode ser uma exceção ou retornar um status 404)
            if (usuario == null) {
                throw new RuntimeException("Usuário não encontrado para o ID fornecido");
            }

            // Associar o usuário à senha
            senha.setUsuario(usuario);

            // Salvar a senha
            gerenciadorDeSenhas.adicionarSenha(senha);
        } else {
            throw new RuntimeException("O ID do usuário não foi fornecido corretamente.");
        }
    }
    @GetMapping("/{id}")
    public List<Senha> listarSenhas(@PathVariable int id) {
        return gerenciadorDeSenhas.listarSenhasDoUsuario(id);
    }
}
