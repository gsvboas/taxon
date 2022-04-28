package br.ufscar.dc.pibd.dao.admin.conveniadas;

import br.ufscar.dc.pibd.dao.GenericDAO;
import br.ufscar.dc.pibd.domain.admin.conveniadas.ResumoConveniada;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConveniadasDAO extends GenericDAO {
    public List<ResumoConveniada> getAllResumosDeConveniada() {
        String sql = "SELECT * FROM admin_resumo_de_conveniadas_view";
        List<ResumoConveniada> resumosDeConveniadas = new ArrayList<>();

        try{
            Connection conn = this.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String cnpj = rs.getString("cnpj");
                String nome = rs.getString("nome");
                String setor = rs.getString("setor");
                ResumoConveniada conveniada = new ResumoConveniada(cnpj, nome, setor);
                resumosDeConveniadas.add(conveniada);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }

        return resumosDeConveniadas;
    }

}
