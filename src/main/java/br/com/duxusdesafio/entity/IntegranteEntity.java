package br.com.duxusdesafio.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "integrante")
public class IntegranteEntity {

    /**
     * Identificador do integrante
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Nome do integrante
     */
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    /**
     * Franquia do integrante
     */
    @NotBlank(message = "Franquia é obrigatória")
    private String franquia;
    /**
     * Função do integrante
     */
    @NotBlank(message = "Função é obrigatória")
    private String funcao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFranquia() {
        return franquia;
    }

    public void setFranquia(String franquia) {
        this.franquia = franquia;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
}
