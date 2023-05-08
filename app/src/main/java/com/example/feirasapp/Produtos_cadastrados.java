package com.example.feirasapp;

public class Produtos_cadastrados {

    private String key, produto, nome;

    public Produtos_cadastrados(String key, String produto, String nome) {
        this.key = key;
        this.produto = produto;
        this.nome = nome;
    }

    //importante ler dados do banco de dados em tempo real do firebase
    public Produtos_cadastrados() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
