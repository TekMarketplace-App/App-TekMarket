package em.pg.rpv.projeto.dominio;

import java.util.ArrayList;

import em.pg.rpv.usuario.dominio.PessoaFisica;


public class Projeto {
    private String nome, descricao, plataforma, aplicacao;
    private PessoaFisica criador;
    private long id;

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }

    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getPlataforma() { return plataforma; }

    public void setPlataforma(String plataforma) { this.plataforma = plataforma; }

    public String getAplicacao() { return aplicacao; }

    public void setAplicacao(String aplicacao) { this.aplicacao = aplicacao; }

    public PessoaFisica getCriador() {
        return criador;
    }

    public void setCriador(PessoaFisica criador) {
        this.criador = criador;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
