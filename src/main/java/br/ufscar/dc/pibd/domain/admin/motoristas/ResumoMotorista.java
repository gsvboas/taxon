package br.ufscar.dc.pibd.domain.admin.motoristas;

public class ResumoMotorista {
    String nome;
    String cpf;
    int idade;
    String cnh;

    public ResumoMotorista(String nome, String cpf, int idade, String cnh){
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.cnh = cnh;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getCnh() {
        return cnh;
    }

    public String getCpf() {
        return cpf;
    }
}
