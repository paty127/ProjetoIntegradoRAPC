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
        public List<Desempenho> getAllDesempenho() throws SQLException, IOException {
        String sql = "select * FROM desempenho";
        List<Desempenho> listaDesempenho = new ArrayList<>();
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
                double media = (desempenho.nota1 + desempenho.nota2 + desempenho.nota3 + desempenho.nota4)/4;
                desempenho.setMedia(media);
                if(media >= 6){
                    desempenho.setStatus(rst.getString("Aprovado"));
                }else{desempenho.setStatus(rst.getString("Reprovado"));}
                listaDesempenho.add(desempenho);
            }
            conn.close();
            stmt.close();
            rst.close();
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao montar a lista de todos os desempenhos");
        }
        return listaDesempenho;
    }
    
    public List<Desempenho> desempenhoPorTurma(int turmaID) throws SQLException, IOException {
        String sql = "SELECT cod_aluno,aluno.nome,disciplinas.nome disciplina,"
                + "nota1,nota2,nota3,nota4 FROM desempenho INNER JOIN"
                + " aluno ON desempenho.fk_cod_aluno = aluno.cod_aluno "
                + "INNER JOIN disciplinas ON desempenho.fk_disciplinaID ="
                + " disciplinas.disciplinaID WHERE fk_turma = ?";
        
        List<Desempenho> listaDesempenho = new ArrayList<>();
        Connection conn = dbUtil.getConnection();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, turmaID);
            //Execultando o comando
            ResultSet rst = stmt.executeQuery();

            while (rst.next()) {
                Desempenho desempenho = new Desempenho();
                desempenho.setCod_aluno(rst.getInt("cod_aluno"));
                desempenho.setNome(rst.getString("nome"));
                desempenho.setDisciplina(rst.getString("disciplina"));
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
                    + " a lista de desempenho por turma: " + turmaID );
        }
        return listaDesempenho;
    }
    public List<Desempenho> desempenhoPorTurmaDisciplina(int turmaID, int disciplinaID) throws SQLException, IOException {
        String sql = "SELECT cod_aluno,nome,nota1,nota2,nota3,nota4 "
                + "FROM desempenho INNER JOIN aluno ON desempenho.fk_cod_aluno"
                + " = aluno.cod_aluno WHERE fk_turma = ? AND fk_disciplinaID = ?";
        
        List<Desempenho> listaDesempenho = new ArrayList<>();
        Connection conn = dbUtil.getConnection();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, turmaID);
            stmt.setInt(2, disciplinaID);
            //Execultando o comando
            ResultSet rst = stmt.executeQuery();

            while (rst.next()) {
                Desempenho desempenho = new Desempenho();
                desempenho.setCod_aluno(rst.getInt("cod_aluno"));
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
                    + " a lista de desempenho por turma e disciplina: " + turmaID );
        }
        return listaDesempenho;
    }
       
    public List<Desempenho> DesempenhoDisciplinaTurma(int DisciplinaID,int alunoID) throws SQLException, IOException {
        String sql = "SELECT cod_aluno,nome,nota1,nota2,nota3,nota4 "
                + "FROM desempenho INNER JOIN aluno ON desempenho.fk_cod_aluno"
                + " = aluno.cod_aluno WHERE fk_turma = ? AND fk_disciplinaID = ?";
        
        List<Desempenho> listaDeDesempenho = new ArrayList<>();
        Connection conn = dbUtil.getConnection();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, DisciplinaID);
            stmt.setInt(2, alunoID);
            //Execultando o comando
            ResultSet rst = stmt.executeQuery();

            if (rst.next()) {
                Desempenho desempenho = new Desempenho();
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