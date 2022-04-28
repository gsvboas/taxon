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
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cpfMotorista);

            ResultSet resultSet = statement.executeQuery();

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
            statement.close();
            conn.close();
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }

        return veiculos;
    }

    public Veiculo getVeiculoByMotoristaChassi(String cpfMotorista, String chassi) {

        Veiculo veiculo = null;
        String sql = "SELECT * FROM recupera_veiculos(?) WHERE chassi = ?";

        try {
            // Conectando no banco e realizando consulta

            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cpfMotorista);
            statement.setString(2, chassi);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
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
                veiculo = new Veiculo(chassi, cor, placa, ano, modelo, marca, maxOcupacao, garagemCep, garagemNum, garagemNumVaga, motoristaCpf);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }

        return veiculo;
    }

    public void add(Veiculo veiculo) {

        String sql = "insert into veiculo_view (chassi, cor, placa, ano, modelo, marca, max_ocupacao, garagem_cep, garagem_num, garagem_num_vaga, motorista_cpf) values (?,?,?,?,?,?,?,?,?,?,?)";

        try {
            // Conectando no banco e realizando consulta
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, veiculo.getChassi());
            statement.setString(2, veiculo.getCor());
            statement.setString(3, veiculo.getPlaca());
            statement.setInt(4, veiculo.getAno());
            statement.setString(5, veiculo.getModelo());
            statement.setString(6, veiculo.getMarca());
            statement.setInt(7, veiculo.getMaxOcupacao());
            statement.setString(8, veiculo.getGaragemCep());
            statement.setInt(9, veiculo.getGaragemNum());
            statement.setInt(10, veiculo.getGaragemNumVaga());
            statement.setString(11, veiculo.getMotoristaCpf());
            System.out.println(statement);
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(String chassi) {

        String sql = "delete from veiculo_view where chassi = ?";

        try {
            // Conectando no banco e realizando consulta
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, chassi);
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
