package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import model.Disciplina;
import util.DbUtil;

public class DisciplinaDao {
    
    private final DbUtil dbUtil = new DbUtil();
    
    public List<Disciplina> getAllDisciplinas() throws SQLException, IOException {
        String sql = "select * FROM disciplinas";
        List<Disciplina> listaDisciplina = new ArrayList<>();
        try (
            Connection conn = dbUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rst = stmt.executeQuery(sql)){
            while (rst.next()) {
                Disciplina disciplina = new Disciplina();
                disciplina.setDisciplinaID(rst.getInt("disciplinaID"));
                disciplina.setNome(rst.getString("nome"));
                disciplina.setCargahoraria(rst.getInt("cargahoraria"));
                listaDisciplina.add(disciplina);
            }
            conn.close();
            stmt.close();
            rst.close();
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao montar a lista de"
                    + " disciplinas.");
        }
        return listaDisciplina;
    }

    public Disciplina getDisciplinaById(int disciplinaID) throws SQLException, IOException {
        String sql = "select * FROM disciplinas WHERE disciplinaID = ?";
        Disciplina disciplina = new Disciplina();
        Connection conn = dbUtil.getConnection();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, disciplinaID);
            //Execultando o comando
            ResultSet rst = stmt.executeQuery();

            if (rst.next()) {
                disciplina.setDisciplinaID(rst.getInt("disciplinaID"));
                disciplina.setNome(rst.getString("nome"));
                disciplina.setCargahoraria(rst.getInt("cargahoraria"));
            }
            conn.close();
            stmt.close();
            rst.close();
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao tentar recuperar"
                    + " a disciplinaID" + disciplinaID );
        }
        return disciplina;
    }
    
        public List<Disciplina> recuperaListaDisciDifer(int disciplinaID) throws SQLException, IOException {
        String sql = "SELECT disciplinaID,nome FROM disciplinas WHERE disciplinaID != ?";
        
        List<Disciplina> listaDeDisciplina = new ArrayList<>();
        Connection conn = dbUtil.getConnection();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, disciplinaID);
            //Execultando o comando
            ResultSet rst = stmt.executeQuery();

            while (rst.next()) {
                Disciplina disciplina = new Disciplina();
                disciplina.setDisciplinaID(rst.getInt("disciplinaID"));
                disciplina.setNome(rst.getString("nome"));
                listaDeDisciplina.add(disciplina);
            }
            conn.close();
            stmt.close();
            rst.close();
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao recuperar"
                    + " a lista de disciplinas diferente de: " + disciplinaID );
        }
        return listaDeDisciplina;
    }
    
    public Disciplina recuperaDisci(int disciplinaID) throws SQLException, IOException {
        String sql = "SELECT disciplinaID,nome FROM disciplinas WHERE disciplinaID = ?";
        Disciplina disciplina = new Disciplina();
        Connection conn = dbUtil.getConnection();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, disciplinaID);
            //Execultando o comando
            ResultSet rst = stmt.executeQuery();

            if (rst.next()) {
                disciplina.setDisciplinaID(rst.getInt("disciplinaID"));
                disciplina.setNome(rst.getString("nome"));
            }
            conn.close();
            stmt.close();
            rst.close();
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao recuperar "
                    + "disciplina: " + disciplinaID );
        }
        return disciplina;
    }
}
