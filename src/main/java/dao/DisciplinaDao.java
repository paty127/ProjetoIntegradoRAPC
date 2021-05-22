package dao;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Disciplina;
import util.DbUtil;

public class DisciplinaDao {
    
    private final DbUtil dbUtil = new DbUtil();
    String erro = "Erro na execução";
    
    public List<Disciplina> getAllDisciplina() throws SQLException, IOException {
        String sql = "select * FROM disciplina";
        List<Disciplina> disciplinas = new ArrayList<>();
        try (
                Connection conn = dbUtil.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rst = stmt.executeQuery(sql)) {
            while (rst.next()) {
                Disciplina disciplina = new Disciplina();
                disciplina.setDiciplinaID(rst.getInt("disciplinaID"));
                disciplina.setNome(rst.getString("nome"));
                disciplina.setCargaHorario(rst.getInt("cargahoraria"));
                
                disciplinas.add(disciplina);
            }
        } catch (SQLException e) {
            System.err.println(erro);
        }

        return disciplinas;
    }

    public Disciplina getDisciplinaById(int disciplinaID) throws SQLException, IOException {
        String sql = "select * FROM disciplina WHERE disciplinaID = ?";
        Disciplina disciplina = new Disciplina();
        Connection conn = dbUtil.getConnection();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, disciplinaID);
            //Execultando o comando
            ResultSet rst = stmt.executeQuery();

            if (rst.next()) {
                disciplina.setDiciplinaID(rst.getInt("disciplinaID"));
                disciplina.setNome(rst.getString("nome"));
                disciplina.setCargaHorario(rst.getInt("cargahoraria"));
            }
        } catch (SQLException e) {
            System.err.println("Erro na execução");
        }
        return disciplina;
    }

    
}
