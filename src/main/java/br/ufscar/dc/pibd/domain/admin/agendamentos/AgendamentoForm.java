package br.ufscar.dc.pibd.domain.admin.agendamentos;


public class AgendamentoForm {
    String cnpj;
    String data;
    String hora;


    public AgendamentoForm(String cnpj, String data, String hora){
        this.cnpj = cnpj;
        this.data = data;
        this.hora = hora;
    }

    public String getHora() {
        return hora;
    }

    public String getData() {
        return data;
    }

    public String getCnpj() {
        return cnpj;
    }
}
