package br.ufscar.dc.pibd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.pibd.domain.Veiculo;

public class VeiculoDAO extends GenericDAO {
    public List<Veiculo> getAllVeiculosByMotorista(String cpfMotorista) {

        List<Veiculo> veiculos = new ArrayList<Veiculo>();
        String sql = "SELECT * FROM recupera_veiculos(?)";

        try {
            // Conectando no banco e realizando consulta

            Connection conn = this.getConnection();
            PreparedStatement statementCorridas = conn.prepareStatement(sql);
            statementCorridas.setString(1, cpfMotorista);

            ResultSet resultSet = statementCorridas.executeQuery();

            while (resultSet.next()) {

                String chassi = resultSet.getString("chassi");
                String cor = resultSet.getString("cor");
                String placa = resultSet.getString("placa");
                Integer ano = resultSet.getInt("ano");
                String modelo = resultSet.getString("modelo");
                String marca = resultSet.getString("marca");
                Integer maxOcupacao = resultSet.getInt("max_ocupacao");
                String garagemCep = resultSet.getString("garagem_cep");
                Integer garagemNum = resultSet.getInt("garagem_num");
                Integer garagemNumVaga = resultSet.getInt("garagem_num_vaga");
                String motoristaCpf = resultSet.getString("motorista_cpf");
                Veiculo veiculo = new Veiculo(chassi, cor, placa, ano, modelo, marca, maxOcupacao, garagemCep, garagemNum, garagemNumVaga, motoristaCpf);
                
                veiculos.add(veiculo);
            }

            resultSet.close();
            statementCorridas.close();
            conn.close();
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }

        return veiculos;
    }
}
