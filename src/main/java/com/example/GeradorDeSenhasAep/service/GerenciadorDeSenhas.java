package com.example.GeradorDeSenhasAep.service;

import com.example.GeradorDeSenhasAep.model.Senha;
import com.example.GeradorDeSenhasAep.model.Usuario;
import com.example.GeradorDeSenhasAep.repository.SenhaRepository;
import com.example.GeradorDeSenhasAep.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GerenciadorDeSenhas {

    @Autowired
    private SenhaRepository senhaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Método para adicionar um usuário com senha em hash
    public Usuario adicionarUsuario(Usuario usuario) {
        usuario.setSenhaHash(passwordEncoder.encode(usuario.getSenhaHash()));
        return usuarioRepository.save(usuario);
    }

    // Método para listar todos os usuários
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // Método para buscar um usuário pelo ID
    public Usuario buscarUsuarioPorId(Integer idUsuario) {
        if (idUsuario == null) {
            throw new IllegalArgumentException("O ID do usuário não pode ser nulo.");
        }

        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
        return usuario.orElse(null); // Retorna o usuário ou null se não encontrado
    }

    // Método para adicionar uma senha
    public void adicionarSenha(Senha senha) {
        senhaRepository.save(senha);
    }

    // Método para listar as senhas de um usuário específico
    public List<Senha> listarSenhasDoUsuario(int idUsuario) {
        return senhaRepository.findAll().stream()
                .filter(senha -> senha.getUsuario().getIdUsuario() == idUsuario)
                .toList();
    }
}
