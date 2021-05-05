package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Professor;
import util.DbUtil;

/**
 *
 * @author Carlos Pavão <carlos.henrique93@msn.com>
 */
public class ProfessorDao {
    
private final DbUtil dbUtil = new DbUtil();
    String erro = "Erro na execução";
    
    public void adicionarProfessor(Professor professor) throws SQLException, IOException {
        String sql = "call novo_professor(?,?,?,?,?,?,?,?,?,?,?,?)";
            try(Connection conn = dbUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
                
            stmt.setString(1, professor.getNome());
            stmt.setDate(2, new java.sql.Date(professor.getDataNasc().getTime()));
            stmt.setString(3, professor.getSexo());
            stmt.setString(4, professor.getCelular());
            stmt.setString(5, professor.getTelefone());
            stmt.setString(6, professor.getCpf());
            stmt.setString(7, professor.getRg());
            stmt.setString(8, professor.getEmail());
            stmt.setString(9, professor.getRua());
            stmt.setInt(10, professor.getNumero());
            stmt.setString(11, professor.getBairro());
            stmt.setString(12, professor.getCep());
            //Executar atualização no banco
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(erro);
        }
    }
    public void deletarProfessor(int professorID) throws SQLException, IOException {
        String sql = "DELETE professor,endereco FROM professor INNER JOIN endereco"
                + " ON professor.fk_endereco = endereco.id_endereco"
                + " WHERE cod_professor = ?";
        try(Connection conn = dbUtil.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, professorID);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println(erro);
        }
    }
    
    public void updateProfessor(Professor professor) throws SQLException, IOException {
        String sql = "UPDATE professor INNER JOIN endereco ON "
                + "professor.fk_endereco = id_endereco SET professor.nome = ?,"
                + "professor.data_de_nascimento = ?,professor.sexo = ?,professor.celular = ?,"
                + "professor.telefone = ?,professor.cpf = ?,professor.rg = ?,"
                + "professor.email = ?,endereco.rua = ?,"
                + "endereco.numero = ?,endereco.bairro = ?,endereco.cep = ? WHERE cod_professor = ?";
        try (Connection conn = dbUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){ 
            
             stmt.setString(1, professor.getNome());
            stmt.setDate(2, new java.sql.Date(professor.getDataNasc().getTime()));
            stmt.setString(3, professor.getSexo());
            stmt.setString(4, professor.getCelular());
            stmt.setString(5, professor.getTelefone());
            stmt.setString(6, professor.getCpf());
            stmt.setString(7, professor.getRg());
            stmt.setString(8, professor.getEmail());
            stmt.setString(9, professor.getRua());
            stmt.setInt(10, professor.getNumero());
            stmt.setString(11, professor.getBairro());
            stmt.setString(12, professor.getCep());
            stmt.setInt(13, professor.getCodProfessor());
            
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println(erro);
        }
    }
    
    public List<Professor> getAllProfessor() throws SQLException, IOException {
        String sql = "select * FROM professor INNER JOIN endereco on professor.fk_endereco = endereco.id_endereco";
        List<Professor> listaDeProfessor = new ArrayList<>();
        try (
            Connection conn = dbUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rst = stmt.executeQuery(sql)){
            while (rst.next()) {
                Professor professor = new Professor();
                professor.setCodProfessor(rst.getInt("cod_professor"));
                professor.setNome(rst.getString("nome"));
                professor.setDataNasc(rst.getDate("data_de_nascimento"));
                professor.setSexo(rst.getString("sexo"));
                professor.setCelular(rst.getString("celular"));
                professor.setTelefone(rst.getString("telefone"));
                professor.setCpf(rst.getString("cpf"));
                professor.setRg(rst.getString("rg"));
                professor.setEmail(rst.getString("email"));
                professor.setRua(rst.getString("rua"));
                professor.setNumero(Integer.parseInt(rst.getString("numero")));
                professor.setBairro(rst.getString("bairro"));
                professor.setCep(rst.getString("cep"));
                
                
                listaDeProfessor.add(professor);
            }
        } catch (SQLException e) {
            System.err.println(erro);
        }

        return listaDeProfessor;
    }


    
    public Professor getProfessorById(int codProfessor) throws SQLException, IOException {
        String sql = "select * FROM professor INNER JOIN endereco ON professor.fk_endereco = endereco.id_endereco WHERE cod_professor = ?";
        Professor professor = new Professor();
        Connection conn = dbUtil.getConnection();
        
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, codProfessor);
            //Execultando o comando            
            
            ResultSet rst = stmt.executeQuery();

            if (rst.next()) {
                professor.setCodProfessor(rst.getInt("cod_professor"));
                professor.setNome(rst.getString("nome"));
                professor.setDataNasc(rst.getDate("data_de_nascimento"));
                professor.setSexo(rst.getString("sexo"));
                professor.setCelular(rst.getString("celular"));
                professor.setTelefone(rst.getString("telefone"));
                professor.setCpf(rst.getString("cpf"));
                professor.setRg(rst.getString("rg"));
                professor.setEmail(rst.getString("email"));
                professor.setRua(rst.getString("rua"));
                professor.setNumero(Integer.parseInt(rst.getString("numero")));
                professor.setBairro(rst.getString("bairro"));
                professor.setCep(rst.getString("cep"));
            }
        } catch (SQLException e) {
            System.err.println("Erro na execução");
        }
        return professor;
}

}
