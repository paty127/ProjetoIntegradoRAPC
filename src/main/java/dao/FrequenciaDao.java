package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Frequencia;
import util.DbUtil;

public class FrequenciaDao {

    private final DbUtil dbUtil = new DbUtil();

    public void adicionarDesempenho(Frequencia frequencia) throws SQLException, IOException {

        String sql = "insert into frequencia(aula_assistida,presente,fk_disciplinaID,fk_cod_aluno)value(?,?,?,?)";
        
        try (Connection conn = dbUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, frequencia.getAula_assistida());
            stmt.setInt(2, frequencia.getPresente());
            stmt.setInt(3, frequencia.getCodDisciplina());
            stmt.setInt(4, frequencia.getCodAluno());



            stmt.executeUpdate();
            
            stmt.close();
            conn.close();
            

        } catch (SQLException ex) {
            System.err.println("Ocorreu um erro ao adicionar presença.");
        }
    }
}
