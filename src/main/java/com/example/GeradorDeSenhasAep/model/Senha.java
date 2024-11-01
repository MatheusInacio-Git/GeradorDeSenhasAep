package com.example.GeradorDeSenhasAep.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Senha {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idSenha;

    private String hashSenha;
    private String descricao;
    private String nome;
    private int idUsuario;

    @JsonBackReference
    @ManyToOne
    private Usuario usuario;

    public int getIdSenha() {
        return idSenha;
    }

    public void setIdSenha(int idSenha) {
        this.idSenha = idSenha;
    }

    public String getHashSenha() {
        return hashSenha;
    }

    public void setHashSenha(String hashSenha) {
        this.hashSenha = hashSenha;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    // Getters e Setters
}