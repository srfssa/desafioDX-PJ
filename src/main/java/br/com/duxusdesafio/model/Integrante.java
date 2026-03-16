package br.com.duxusdesafio.model;

public class Integrante {

    private Long id;
    private String nome;
    private String franquia;
    private String funcao;

    public Integrante() {}

    public Integrante(String nome, String franquia, String funcao) {
        this.nome = nome;
        this.franquia = franquia;
        this.funcao = funcao;
    }

    public Integrante(Long id, String nome, String franquia, String funcao) {
        this.id = id;
        this.nome = nome;
        this.franquia = franquia;
        this.funcao = funcao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setFranquia(String franquia) {
        this.franquia = franquia;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getFranquia() {
        return franquia;
    }

    public String getFuncao() {
        return funcao;
    }
}
