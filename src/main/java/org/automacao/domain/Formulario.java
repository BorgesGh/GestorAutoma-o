package org.automacao.domain;

public class Formulario {
    private String tipo;
    private String descricao;
    private boolean resposta;

    public Formulario(String tipo, String descricao, boolean resposta) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.resposta = resposta;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setResposta(boolean resposta) {
        this.resposta = resposta;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isResposta() {
        return resposta;
    }
}
