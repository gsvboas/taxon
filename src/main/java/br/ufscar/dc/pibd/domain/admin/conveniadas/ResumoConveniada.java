package br.ufscar.dc.pibd.domain.admin.conveniadas;

public class ResumoConveniada {
    String cnpj;
    String nome;
    String setor;

    public ResumoConveniada(String cnpj, String nome, String setor)
    {
        this.cnpj = cnpj;
        this.nome = nome;
        this.setor = setor;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getNome() {
        return nome;
    }

    public String getSetor() {
        return setor;
    }
}
