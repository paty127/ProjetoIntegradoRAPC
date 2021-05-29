package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Aluno;
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
                turma.setQte(rst.getInt("qte"));
                listaTurma.add(turma);
            }
            conn.close();
            stmt.close();
            rst.close();
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao montar a lista de"
                    + " todas as turmas.");
        }
        return listaTurma;
    }

    public Turma SelecionarDesempenho(int turmaID) throws SQLException, IOException {
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
                turma.setQte(rst.getInt("qte"));
            }
            conn.close();
            stmt.close();
            rst.close();
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao tentar recuperar"
                    + " seleciona uma unica turma" + turmaID );
        }
        return turma;
    }
    
    public List<Aluno> listarAlunosDaTurma(int turmaID) throws SQLException, IOException {
        String sql = "select * FROM aluno WHERE fk_turma = ?";
        
        List<Aluno> listaDeAluno = new ArrayList<>();
        Connection conn = dbUtil.getConnection();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, turmaID);
            //Execultando o comando
            ResultSet rst = stmt.executeQuery();

            while (rst.next()) {
                Aluno aluno = new Aluno();
                aluno.setCodAluno(rst.getInt("cod_aluno"));
                aluno.setNome(rst.getString("nome"));
                listaDeAluno.add(aluno);
            }
            conn.close();
            stmt.close();
            rst.close();
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao tentar recuperar"
                    + " a lista de alunos" + turmaID );
        }
        return listaDeAluno;
    }
    
    public List<Turma> recuperaListaTurmaDifer(int turmaID) throws SQLException, IOException {
        String sql = "select cod_turma,serie FROM turma WHERE cod_turma != ?";
        
        List<Turma> listaDeturma = new ArrayList<>();
        Connection conn = dbUtil.getConnection();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, turmaID);
            //Execultando o comando
            ResultSet rst = stmt.executeQuery();

            while (rst.next()) {
                Turma turma = new Turma();
                turma.setTurmaID(rst.getInt("cod_turma"));
                turma.setSerie(rst.getString("serie"));
                listaDeturma.add(turma);
            }
            conn.close();
            stmt.close();
            rst.close();
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao recuperar"
                    + " a lista de turmas diferente de: " + turmaID );
        }
        return listaDeturma;
    }
    
    public Turma recuperaTurma(int turmaID) throws SQLException, IOException {
        String sql = "select cod_turma,serie FROM turma WHERE cod_turma = ?";
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
            conn.close();
            stmt.close();
            rst.close();
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao recuperar"
                    + "serie da turma: " + turmaID );
        }
        return turma;
    }
    
}
