package br.ufscar.dc.pibd.dao.admin;

import br.ufscar.dc.pibd.dao.GenericDAO;
import br.ufscar.dc.pibd.domain.admin.ResumoCorrida;

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
                Double valorTotal = rs.getDouble("valor_total");
                Date dataInicio = rs.getDate("data_inicio");
                ResumoCorrida resumoCorrida = new ResumoCorrida(
                        id,
                        cpfMotorista,
                        chassiVeiculo,
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
