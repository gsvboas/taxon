package br.ufscar.dc.pibd.domain.admin;


import java.util.Date;

public class ResumoCorrida {
    private Long id;
    private String cpfMotorista;
    private String chassiVeiculo;
    private Double valorTotal;
    private Date dataInicio;

    public ResumoCorrida(Long id, String cpf, String chassi, Double valor, Date dataInicio){
        this.id = id;
        this.cpfMotorista = cpf;
        this.chassiVeiculo = chassi;
        this.valorTotal = valor;
        this.dataInicio = dataInicio;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public Long getId() {
        return id;
    }

    public String getCpfMotorista() {
        return cpfMotorista;
    }

    public String getChassiVeiculo() {
        return chassiVeiculo;
    }
}
