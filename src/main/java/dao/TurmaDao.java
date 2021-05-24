package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Turma;
import util.DbUtil;


public class TurmaDao {

    private final DbUtil dbUtil = new DbUtil();
    
    public List<Turma> getAllTurmas() throws SQLException, IOException {
        String sql = "select * FROM turma";
        List<Turma> listaTurma = new ArrayList<>();
        try (
            Connection conn = dbUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rst = stmt.executeQuery(sql)){
            while (rst.next()) {
                Turma turma = new Turma();
                turma.setTurmaID(rst.getInt("cod_turma"));
                turma.setSerie(rst.getString("serie"));
                
                listaTurma.add(turma);
            }
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao montar a lista de"
                    + " disciplinas.");
        }
        return listaTurma;
    }

    public Turma getDisciplinaById(int turmaID) throws SQLException, IOException {
        String sql = "select * FROM turma WHERE cod_turma = ?";
        Turma turma = new Turma();
        Connection conn = dbUtil.getConnection();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, turmaID);
            //Execultando o comando
            ResultSet rst = stmt.executeQuery();

            if (rst.next()) {
                turma.setTurmaID(rst.getInt("cod_turma"));
                turma.setSerie(rst.getString("serie"));
            }
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao tentar recuperar"
                    + " a disciplinaID" + turmaID );
        }
        return turma;
    }
}
