package br.ufscar.dc.pibd.domain.admin.agendamentos;

public class ResumoAgendamento {
    String cnpj;
    String nomeConveniada;
    String data;
    String hora;

    public ResumoAgendamento(String cnpj, String nomeConveniada, String data, String hora){
        this.cnpj = cnpj;
        this.nomeConveniada = nomeConveniada;
        this.data = data;
        this.hora = hora;
    }


}
