package br.ufscar.dc.pibd.domain;

public class Veiculo {
    private String chassi;
		private String cor;
    private String placa;
    private Integer ano;
    private String modelo;
    private String marca;
    private Integer maxOcupacao;
    private String garagemCep;
    private Integer garagemNum;
    private Integer garagemNumVaga;
    private String motoristaCpf;

    public Veiculo(String chassi, String cor, String placa, Integer ano, String modelo, String marca,
				Integer maxOcupacao, String garagemCep, Integer garagemNum, Integer garagemNumVaga, String motoristaCpf) {
			this.setChassi(chassi);
			this.setCor(cor);
			this.setPlaca(placa);
			this.setAno(ano);
			this.setModelo(modelo);
			this.setMarca(marca);
			this.setMaxOcupacao(maxOcupacao);
			this.setGaragemCep(garagemCep);
			this.setGaragemNum(garagemNum);
			this.setGaragemNumVaga(garagemNumVaga);
			this.setMotoristaCpf(motoristaCpf);
		}

		public String getMotoristaCpf() {
			return motoristaCpf;
		}

		public void setMotoristaCpf(String motoristaCpf) {
			this.motoristaCpf = motoristaCpf;
		}

		public Integer getGaragemNumVaga() {
			return garagemNumVaga;
		}

		public void setGaragemNumVaga(Integer garagemNumVaga) {
			this.garagemNumVaga = garagemNumVaga;
		}

		public Integer getGaragemNum() {
			return garagemNum;
		}

		public void setGaragemNum(Integer garagemNum) {
			this.garagemNum = garagemNum;
		}

		public String getGaragemCep() {
			return garagemCep;
		}

		public void setGaragemCep(String garagemCep) {
			this.garagemCep = garagemCep;
		}

		public Integer getMaxOcupacao() {
			return maxOcupacao;
		}

		public void setMaxOcupacao(Integer maxOcupacao) {
			this.maxOcupacao = maxOcupacao;
		}

		public String getMarca() {
			return marca;
		}

		public void setMarca(String marca) {
			this.marca = marca;
		}

		public String getModelo() {
			return modelo;
		}

		public void setModelo(String modelo) {
			this.modelo = modelo;
		}

		public Integer getAno() {
			return ano;
		}

		public void setAno(Integer ano) {
			this.ano = ano;
		}

		public String getPlaca() {
			return placa;
		}

		public void setPlaca(String placa) {
			this.placa = placa;
		}

		public String getCor() {
			return cor;
		}

		public void setCor(String cor) {
			this.cor = cor;
		}

		public String getChassi() {
			return chassi;
		}

		public void setChassi(String chassi) {
			this.chassi = chassi;
		}
}
