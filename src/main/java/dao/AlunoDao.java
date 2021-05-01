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
import util.DbUtil;

/**
 *
 * @author Carlos Pavão <carlos.henrique93@msn.com>
 */
public class AlunoDao {
    
private final DbUtil dbUtil = new DbUtil();
    String erro = "Erro na execução";
    
    public void adicionarAluno(Aluno aluno) throws SQLException, IOException {
        String sql = "INSERT INTO aluno(nome,data_de_nascimento,sexo,pai,mae,celular,telefone_pai,telefone_mae,email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? );";
            try(Connection conn = dbUtil.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql)) {
                
            stmt.setString(1, aluno.getNome());
            stmt.setDate(2, new java.sql.Date(aluno.getDataNasc().getTime()));
            stmt.setString(3, aluno.getSexo());
            stmt.setString(4, aluno.getNomePai());
            stmt.setString(5, aluno.getNomeMae());
            stmt.setString(6, aluno.getCelular());
            stmt.setString(7, aluno.getCelularPai());
            stmt.setString(8, aluno.getCelularMae());
            stmt.setString(9, aluno.getEmail());
            //Executar atualização no banco
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(erro);
        }
    }
    public void deletarAluno(int alunoID) throws SQLException, IOException {
        String sql = "delete from aluno where cod_aluno=?;";
        try(Connection conn = dbUtil.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, alunoID);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println(erro);
        }
    }
    
    public void updateUser(Aluno aluno) throws SQLException, IOException {
        String sql ="update aluno set nome=?,data_de_nascimento=?,"
                            + "sexo=?,pai=?,mae=?,celular=?,telefone_pai=?,"
                            + "telefone_mae=?,email=? where cod_aluno=?;";
        try (Connection conn = dbUtil.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql)){            
            stmt.setString(1, aluno.getNome());
            stmt.setDate(2, new java.sql.Date(aluno.getDataNasc().getTime()));
            stmt.setString(3, aluno.getSexo());
            stmt.setString(4, aluno.getNomePai());
            stmt.setString(5, aluno.getNomeMae());
            stmt.setString(6, aluno.getCelular());
            stmt.setString(7, aluno.getCelularPai());
            stmt.setString(8, aluno.getCelularMae());
            stmt.setString(9, aluno.getEmail());  
            stmt.setInt(10,aluno.getCodAluno());
            
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println(erro);
        }
    }
    
    public List<Aluno> getAllAlunos() throws SQLException, IOException {
        String sql = "SELECT * from aluno;";
        List<Aluno> listaDeAluno = new ArrayList<>();
        try (
            Connection conn = dbUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rst = stmt.executeQuery(sql)){
            while (rst.next()) {
                Aluno aluno = new Aluno();
                aluno.setCodAluno(rst.getInt("cod_aluno"));
                aluno.setNome(rst.getString("nome"));
                aluno.setSexo(rst.getString("sexo"));
                aluno.setDataNasc(rst.getDate("data_de_nascimento"));
                aluno.setNomePai(rst.getString("pai"));
                aluno.setNomeMae(rst.getString("mae"));
                aluno.setCelular(rst.getString("celular"));
                aluno.setCelularPai(rst.getString("telefone_pai"));
                aluno.setCelularMae(rst.getString("telefone_mae"));
                aluno.setEmail(rst.getString("email"));
                
                
                listaDeAluno.add(aluno);
            }
        } catch (SQLException e) {
            System.err.println(erro);
        }

        return listaDeAluno;
    }


    
    public Aluno getAlunoById(int CodAluno) throws SQLException, IOException {
        String sql ="select * from aluno where cod_aluno=?";
        Aluno aluno = new Aluno();
        Connection conn = dbUtil.getConnection();
        
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, CodAluno);
            //Execultando o comando            
            String Teste = stmt.toString();
            ResultSet rst = stmt.executeQuery();

            if (rst.next()) {
                aluno.setCodAluno(rst.getInt("cod_aluno"));
                aluno.setNome(rst.getString("nome"));
                aluno.setDataNasc(rst.getDate("data_de_nascimento"));
                aluno.setNomePai(rst.getString("pai"));
                aluno.setNomeMae(rst.getString("mae"));
                aluno.setCelular(rst.getString("celular"));
                aluno.setCelularPai(rst.getString("telefone_pai"));
                aluno.setCelularMae(rst.getString("telefone_mae"));
                aluno.setEmail(rst.getString("email"));
                aluno.setSexo(rst.getString("sexo"));
            }
        } catch (SQLException e) {
            System.err.println("Erro na execução");
        }

        return aluno;
}

}
