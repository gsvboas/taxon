package br.ufscar.dc.pibd.domain.admin.agendamentos;

public class ResumoAgendamento {
    String cnpj;
    String nomeConveniada;
    int corridaId;

    public ResumoAgendamento(String cnpj, String nomeConveniada, int corridaId){
        this.cnpj = cnpj;
        this.nomeConveniada = nomeConveniada;
        this.corridaId = corridaId;
    }

    public String getCnpj() {
        return cnpj;
    }

    public int getCorridaId() {
        return corridaId;
    }

    public String getNomeConveniada() {
        return nomeConveniada;
    }
}
