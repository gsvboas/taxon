package br.ufscar.dc.pibd.dao.admin;

import br.ufscar.dc.pibd.dao.GenericDAO;
import br.ufscar.dc.pibd.domain.admin.corridas.ResumoCorrida;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResumoCorridaDAO extends GenericDAO {
    public List<ResumoCorrida> getAllResumosDeCorrida()
    {
        List<ResumoCorrida> resumosDeCorrida = new ArrayList();
        String sql = "SELECT * FROM admin_resumo_de_corridas_view";
        try{
            Connection conn = this.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Long id = rs.getLong("id");
                String cpfMotorista = rs.getString("motorista_cpf");
                String chassiVeiculo = rs.getString("veiculo_chassi");
                String nomeConveniada = rs.getString("conveniada_nome");
                Double valorTotal = rs.getDouble("valor_total");
                Date dataInicio = rs.getDate("data_inicio");
                ResumoCorrida resumoCorrida = new ResumoCorrida(
                        id,
                        cpfMotorista,
                        chassiVeiculo,
                        nomeConveniada,
                        valorTotal,
                        dataInicio
                );
                resumosDeCorrida.add(resumoCorrida);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }

        return resumosDeCorrida;

    }

    public List<ResumoCorrida> getAllResumosDeCorridaPorConveniada(String conveniada) {
        List<ResumoCorrida> resumosDeCorrida = new ArrayList();
        String sql = "SELECT * FROM admin_resumo_de_corridas_view WHERE conveniada_nome = ?";
        try{
            Connection conn = this.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, conveniada);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Long id = rs.getLong("id");
                String cpfMotorista = rs.getString("motorista_cpf");
                String chassiVeiculo = rs.getString("veiculo_chassi");
                String nomeConveniada = rs.getString("conveniada_nome");
                Double valorTotal = rs.getDouble("valor_total");
                Date dataInicio = rs.getDate("data_inicio");
                ResumoCorrida resumoCorrida = new ResumoCorrida(
                        id,
                        cpfMotorista,
                        chassiVeiculo,
                        nomeConveniada,
                        valorTotal,
                        dataInicio
                );
                resumosDeCorrida.add(resumoCorrida);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }

        return resumosDeCorrida;

    }
}
