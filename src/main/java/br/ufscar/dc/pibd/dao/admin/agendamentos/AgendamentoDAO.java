package br.ufscar.dc.pibd.dao.admin.agendamentos;

import br.ufscar.dc.pibd.dao.GenericDAO;
import br.ufscar.dc.pibd.domain.admin.agendamentos.AgendamentoForm;
import br.ufscar.dc.pibd.domain.admin.agendamentos.ResumoAgendamento;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AgendamentoDAO extends GenericDAO {
    public void criarAgendamento(AgendamentoForm agendamentoForm)
    {
        String sql = "INSERT INTO admin_criacao_de_agendamento_view VALUES (?, ?, ?)";
        LocalDateTime iniciaAsLDT = LocalDateTime.parse(
                agendamentoForm.getData() + " " + agendamentoForm.getHora() + ":00.0000",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSS"));

        try{
            Connection conn = this.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, agendamentoForm.getCnpj());
            stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setTimestamp(3, Timestamp.valueOf(iniciaAsLDT));
            stmt.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<ResumoAgendamento> getAllResumosDeAgendamento() {
        String sql = "SELECT * FROM admin_resumo_de_agendamento_view";
        List<ResumoAgendamento> listagemAgendamentos = new ArrayList<>();

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
