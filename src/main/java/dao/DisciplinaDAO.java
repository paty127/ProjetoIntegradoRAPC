package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Disciplina;
import util.DbUtil;

public class DisciplinaDAO {
    
    private final DbUtil dbUtil = new DbUtil();
    String erro = "Erro na execução";
    
    public List<Disciplina> getAllDisciplina() throws SQLException, IOException {
        String sql = "select * FROM aluno INNER JOIN endereco on aluno.fk_endereco = endereco.id_endereco";
        List<Disciplina> listaDeDisciplina = new ArrayList<>();
        try (
                Connection conn = dbUtil.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rst = stmt.executeQuery(sql)) {
            while (rst.next()) {
                Disciplina disciplina = new Disciplina();
                disciplina.setDiciplinaID(rst.getInt("disciplinaID"));
                disciplina.setNome(rst.getString("nome"));
                disciplina.setCargaHorario(rst.getInt("cargahoraria"));
                
                listaDeDisciplina.add(disciplina);
            }
        } catch (SQLException e) {
            System.err.println(erro);
        }

        return listaDeDisciplina;
    }
    
}
