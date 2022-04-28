package br.ufscar.dc.pibd.dao.admin.agendamentos;

import br.ufscar.dc.pibd.dao.GenericDAO;
import br.ufscar.dc.pibd.domain.admin.agendamentos.ResumoAgendamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoDAO extends GenericDAO {
    public void criarAgendamento(String cnpj, String data, String hora)
    {
        String sql = "INSERT INTO agendamento (cnpj, efetuado_as) VALUES (?, ?)";
        try{
            Connection conn = this.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cnpj);
            stmt.setString(2, LocalDateTime.now().toString());
            ResultSet rs = stmt.executeQuery();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<ResumoAgendamento> getAllResumosDeAgendamento() {
        String sql = "SELECT * FROM admin_resumo_de_agendamento_view";
        List<ResumoAgendamento> listagemAgendamentos = new ArrayList<ResumoAgendamento>();

        try{
            Connection conn = this.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                String cnpj = rs.getString("cnpj");
                String nomeConveniada = rs.getString("nome_conveniada");
                int corridaId = rs.getInt("corrida_id");
                ResumoAgendamento resumoAgendamento = new ResumoAgendamento(cnpj, nomeConveniada, corridaId);
                listagemAgendamentos.add(resumoAgendamento);
            }

        }catch(SQLException e){
            throw new RuntimeException(e);
        }

        return listagemAgendamentos;
    }
}
