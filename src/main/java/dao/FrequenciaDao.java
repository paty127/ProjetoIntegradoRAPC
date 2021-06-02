package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Frequencia;
import util.DbUtil;

public class FrequenciaDao {

    private final DbUtil dbUtil = new DbUtil();

    public void adicionarDesempenho(Frequencia frequencia) throws SQLException, IOException {

        String sql = "insert into frequencia(aula_assistida,presente,fk_disciplinaID,fk_cod_aluno)value(?,?,?,?)";
        
        try (Connection conn = dbUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, frequencia.getAula_assistida());
            stmt.setInt(2, frequencia.getAusente());
            stmt.setInt(3, frequencia.getCodDisciplina());
            stmt.setInt(4, frequencia.getCodAluno());



            stmt.executeUpdate();
            
            stmt.close();
            conn.close();
            

        } catch (SQLException ex) {
            System.err.println("Ocorreu um erro ao adicionar presença.");
        }
    }
    
    public List<Frequencia> getAllFrequencia() throws SQLException, IOException {
        String sql = "select * FROM frequencia";
        List<Frequencia> listaFreq = new ArrayList<>();
        try (
            Connection conn = dbUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rst = stmt.executeQuery(sql)){
            while (rst.next()) {
                Frequencia frequencia = new Frequencia();
                frequencia.setAula_assistida(rst.getInt("aula_assistida"));
                frequencia.setAusente(rst.getInt("ausencia"));
                frequencia.setCodDisciplina(rst.getInt("fk_disciplinaID"));
                frequencia.setCodAluno(rst.getInt("fk_cod_aluno"));
                listaFreq.add(frequencia);
            }
            conn.close();
            stmt.close();
            rst.close();
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao montar a lista de todos os desempenhos");
        }
        return listaFreq;
    }
}
