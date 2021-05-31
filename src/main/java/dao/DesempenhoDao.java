package dao;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Desempenho;
import util.DbUtil;

public class DesempenhoDao {
    private final DbUtil dbUtil = new DbUtil();
    String erro = "Erro na execução";

    public void adicionarDesempenho(Desempenho desempenho) throws SQLException, IOException {
        String sql = "insert into desempenho(nota1,nota2,nota3,nota4,fk_disciplinaID,fk_cod_aluno)value(?,?,?,?,?,?)";
        try (Connection conn = dbUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, desempenho.getNota1());
            stmt.setDouble(2, desempenho.getNota2());
            stmt.setDouble(3, desempenho.getNota3());
            stmt.setDouble(4, desempenho.getNota4());
            stmt.setInt(5, desempenho.getCodDisciplina());
            stmt.setInt(6, desempenho.getCodAluno());
            //Executar atualização no banco
            stmt.executeUpdate();
            conn.close();
            stmt.close();
        } catch (SQLException ex) {
            System.err.println("Ocorreu um erro ao adicionar o desempenho.");
        }
    }
    public void updateDesempenho(Desempenho desempenho,int disciplinaID) throws SQLException, IOException {
        String sql = "UPDATE desempenho SET nota1 = ?,nota2 = ?,nota3 = ?,"
                + "nota4 = ? WHERE fk_disciplinaID = ?";
        try (Connection conn = dbUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, desempenho.getNota1());
            stmt.setDouble(2, desempenho.getNota2());
            stmt.setDouble(3, desempenho.getNota3());
            stmt.setDouble(4, desempenho.getNota4());
            stmt.setInt(5, disciplinaID);
            //stmt.setInt(6, cod_aluno);
            //Executar atualização no banco
            stmt.executeUpdate();
            conn.close();
            stmt.close();
        } catch (SQLException ex) {
            System.err.println("Ocorreu um erro ao realizar o update da desempenho.");
        }
    }
    
    public List<Desempenho> getAllTurmas() throws SQLException, IOException {
        String sql = "select * FROM desempenho";
        List<Desempenho> listaTurma = new ArrayList<>();
        try (
            Connection conn = dbUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rst = stmt.executeQuery(sql)){
            while (rst.next()) {
                Desempenho desempenho = new Desempenho();
                desempenho.setNota1(rst.getDouble("nota1"));
                desempenho.setNota2(rst.getDouble("nota2"));
                desempenho.setNota3(rst.getDouble("nota3"));
                desempenho.setNota4(rst.getDouble("nota4"));
                desempenho.setCodDisciplina(rst.getInt("fk_disciplinaID"));
                desempenho.setCodDisciplina(rst.getInt("fk_cod_aluno"));
                listaTurma.add(desempenho);
            }
            conn.close();
            stmt.close();
            rst.close();
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao montar a lista de todos os desempenhos");
        }
        return listaTurma;
    }
    public List<Desempenho> getAllDesempenho() throws SQLException, IOException {
        String sql = "select * FROM desempenho";
        List<Desempenho> listaTurma = new ArrayList<>();
        try (
            Connection conn = dbUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rst = stmt.executeQuery(sql)){
            while (rst.next()) {
                Desempenho desempenho = new Desempenho();
                desempenho.setNota1(rst.getDouble("nota1"));
                desempenho.setNota2(rst.getDouble("nota2"));
                desempenho.setNota3(rst.getDouble("nota3"));
                desempenho.setNota4(rst.getDouble("nota4"));
                desempenho.setCodDisciplina(rst.getInt("fk_disciplinaID"));
                desempenho.setCodDisciplina(rst.getInt("fk_cod_aluno"));
                listaTurma.add(desempenho);
            }
            conn.close();
            stmt.close();
            rst.close();
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao montar a lista de todos os desempenhos");
        }
        return listaTurma;
    }
    
    public List<Desempenho> desempenhoPorDisciplina(int DisciplinaID) throws SQLException, IOException {
        String sql = "SELECT cod_aluno,nome,nota1,nota2,nota3,nota4 "
                + "FROM desempenho INNER JOIN aluno "
                + "ON desempenho.fk_cod_aluno = aluno.cod_aluno "
                + "WHERE fk_disciplinaID = ?";
        
        Desempenho desempenho = new Desempenho();
        List<Desempenho> listaDesempenho = new ArrayList<>();
        Connection conn = dbUtil.getConnection();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, DisciplinaID);
            //Execultando o comando
            ResultSet rst = stmt.executeQuery();

            while (rst.next()) {
                desempenho.setCodAluno(rst.getInt("cod_aluno"));
                desempenho.setNome(rst.getString("nome"));
                desempenho.setNota1(rst.getDouble("nota1"));
                desempenho.setNota2(rst.getDouble("nota2"));
                desempenho.setNota3(rst.getDouble("nota3"));
                desempenho.setNota4(rst.getDouble("nota4"));

                listaDesempenho.add(desempenho);
            }
            conn.close();
            stmt.close();
            rst.close();
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao tentar montar"
                    + " a lista de desempenho " + DisciplinaID );
        }
        return listaDesempenho;
    }
    
    public List<Desempenho> DesempenhoDisciplinaTurma(int DisciplinaID,int alunoID) throws SQLException, IOException {
        String sql = "select * FROM desempenho WHERE fk_disciplinaID = ? and fk_cod_aluno = ?;";
        Desempenho desempenho = new Desempenho();
        List<Desempenho> listaDeDesempenho = new ArrayList<>();
        Connection conn = dbUtil.getConnection();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, DisciplinaID);
            stmt.setInt(2, alunoID);
            //Execultando o comando
            ResultSet rst = stmt.executeQuery();

            if (rst.next()) {
                desempenho.setNota1(rst.getDouble("nota1"));
                desempenho.setNota2(rst.getDouble("nota2"));
                desempenho.setNota3(rst.getDouble("nota3"));
                desempenho.setNota4(rst.getDouble("nota4"));
                desempenho.setCodDisciplina(rst.getInt("fk_disciplinaID"));
                desempenho.setCodDisciplina(rst.getInt("fk_cod_aluno"));

                listaDeDesempenho.add(desempenho);
            }
            conn.close();
            stmt.close();
            rst.close();
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao tentar recuperar"
                    + " a lista de desempenho " + DisciplinaID );
        }
        return listaDeDesempenho;
    }
}