package br.ufscar.dc.pibd.domain;

public class Client {
    private String cnpj;
    private String nome;
    private String email;
    private String password;
    private String role;
    private String setor;


    public Client(String cnpj) {
        this.setCpf(cnpj);
    }

    public Client(String cpf, String name, String email, String password, String role, String setor) {
        this.setCpf(cpf);
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.setRole(role);
        this.setSetor(setor);
    }

    public void setCpf(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCpf() {
        return this.cnpj;
    }

    public void setName(String nome) {
        this.nome = nome;
    }

    public String getName() {
        return this.nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getSetor() {
        return this.setor;
    }
}