package com.example.GeradorDeSenhasAep.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idUsuario;

    private String nome;
    private String senhaHash; // Armazena a senha em hash

    @JsonManagedReference
    @OneToMany(mappedBy = "usuario")
    private List<Senha> senhas;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    public List<Senha> getSenhas() {
        return senhas;
    }

    public void setSenhas(List<Senha> senhas) {
        this.senhas = senhas;
    }

    // Getters e Setters
}