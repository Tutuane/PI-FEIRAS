package com.example.feirasapp;

import androidx.annotation.NonNull;

public class Produto {
    private String id=null;
    private String nome;
    private String valor;
    private String quilos;

    public Produto() {
        // Construtor vazio necessário para o Firebase Database
    }
    public Produto(String nome, String valor, String quilos) {
        this.nome = nome;
        this.valor = valor;
        this.quilos = quilos;
    }
    public Produto(String id, String nome, String valor, String quilos) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.quilos = quilos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getQuilos() {
        return quilos;
    }

    public void setQuilos(String quilos) {
        this.quilos = quilos;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("id=%s = %s", id, nome);
    }
}
