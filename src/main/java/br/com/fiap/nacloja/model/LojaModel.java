package br.com.fiap.nacloja.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TB_LOJA")
public class LojaModel {

    @Id
    @Column(name = "ID_CATEGORIA")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOJA_SEQ")
    @SequenceGenerator(name = "LOJA_SEQ", sequenceName = "LOJA_SEQ", allocationSize = 1)
    private Long idLoja;

    @NotNull
    @Size(min = 1)
    private String nome;

    @NotNull
    @Size(min = 10)
    private String url;

    public LojaModel(Long idLoja, String nome, String url) {
        this.idLoja = idLoja;
        this.nome = nome;
        this.url = url;
    }

    public LojaModel(){

    }

    public Long getIdLoja() {
        return idLoja;
    }

    public void setIdLoja(Long idLoja) {
        this.idLoja = idLoja;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
