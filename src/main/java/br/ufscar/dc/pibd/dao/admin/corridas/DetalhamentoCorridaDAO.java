package br.ufscar.dc.pibd.dao.admin.corridas;

import br.ufscar.dc.pibd.dao.GenericDAO;
import br.ufscar.dc.pibd.domain.admin.corridas.DetalhamentoCorrida;

import java.sql.*;
import java.time.LocalDateTime;

public class DetalhamentoCorridaDAO extends GenericDAO {
    public DetalhamentoCorrida getDetalhamentoDeCorridaByCorridaId(int corridaId)
    {
        String sql = "SELECT * FROM admin_detalhamento_de_corrida_view WHERE id = ?";
        DetalhamentoCorrida detalhamentoCorrida = null;



        try{
            Connection conn = this.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, corridaId);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            String nomeMotorista = rs.getString("motorista_nome");
            String cpfMotorista = rs.getString("motorista_cpf");
            String marcaVeiculo = rs.getString("veiculo_marca");
            String modeloVeiculo = rs.getString("veiculo_modelo");
            String corVeiculo = rs.getString("veiculo_cor");
            String placaVeiculo = rs.getString("veiculo_placa");
            String localDeChegada = rs.getString("inicia_em");
            String localDePartida = rs.getString("termina_em");

            Timestamp iniciaAsTS = rs.getTimestamp("inicia_as");
            Timestamp terminaAsTS = rs.getTimestamp("termina_as");
            LocalDateTime iniciaAs = iniciaAsTS.toLocalDateTime();
            LocalDateTime terminaAs = null;
            if (terminaAsTS != null)
                terminaAs = terminaAsTS.toLocalDateTime();
            Double valorTotal = rs.getDouble("valor_total");

            detalhamentoCorrida = new DetalhamentoCorrida(
                    nomeMotorista,
                    cpfMotorista,
                    marcaVeiculo,
                    modeloVeiculo,
                    corVeiculo,
                    placaVeiculo,
                    localDeChegada,
                    localDePartida,
                    iniciaAs,
                    terminaAs,
                    valorTotal
            );

        }catch(SQLException e){
            throw new RuntimeException(e);
        }

        return detalhamentoCorrida;
    }

}
