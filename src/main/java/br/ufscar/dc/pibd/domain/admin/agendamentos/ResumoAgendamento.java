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


}
