package br.ufscar.dc.pibd.dao.admin.motoristas;

import br.ufscar.dc.pibd.dao.GenericDAO;
import br.ufscar.dc.pibd.domain.admin.motoristas.ResumoMotorista;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MotoristasDAO extends GenericDAO {
    public List<ResumoMotorista> getAllResumosDeMotorista() {
        String sql = "SELECT * FROM ";
        List<ResumoMotorista> resumosDeMotoristas = new ArrayList<>();

        try{
            Connection conn = this.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                int idade = rs.getInt("idade");
                String cnh = rs.getString("cnh");

                ResumoMotorista resumoMotorista = new ResumoMotorista(nome, cpf, idade, cnh);
                resumosDeMotoristas.add(resumoMotorista);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }

        return resumosDeMotoristas;
    }
}
