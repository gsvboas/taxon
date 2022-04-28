package br.ufscar.dc.pibd.domain.admin.corridas;

import java.time.LocalDateTime;

public class DetalhamentoCorrida {
    private String cpfMotorista;
    private String nomeMotorista;
    private String marcaVeiculo;
    private String modeloVeiculo;
    private String corVeiculo;
    private String placaVeiculo;
    private String localDePartida;
    private String localDeChegada;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private Double valorTotal;

    public DetalhamentoCorrida(
            final String nomeMotorista,
            final String cpfMotorista,
            final String marcaVeiculo,
            String modeloVeiculo,
            String corVeiculo,
            String placaVeiculo,
            String localDeChegada,
            String localDePartida,
            LocalDateTime dataInicio,
            LocalDateTime dataFim,
            Double valorTotal
            ){
        this.cpfMotorista = cpfMotorista;
        this.nomeMotorista = nomeMotorista;
        this.marcaVeiculo = marcaVeiculo;
        this.modeloVeiculo = modeloVeiculo;
        this.corVeiculo = corVeiculo;
        this.placaVeiculo = placaVeiculo;
        this.valorTotal = valorTotal;
        this.localDeChegada = localDeChegada;
        this.localDePartida = localDePartida;
        this.dataFim = dataFim;
        this.dataInicio = dataInicio;
    }

    public String getCpfMotorista() {
        return cpfMotorista;
    }

    public String getNomeMotorista() {
        return nomeMotorista;
    }

    public String getMarcaVeiculo() {
        return marcaVeiculo;
    }

    public String getModeloVeiculo() {
        return modeloVeiculo;
    }

    public String getCorVeiculo() {
        return corVeiculo;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public String getLocalDeChegada() {
        return localDeChegada;
    }

    public String getLocalDePartida() {
        return localDePartida;
    }

}

