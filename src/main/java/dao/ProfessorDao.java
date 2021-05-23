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
   
    public void adicionarProfessor(Professor professor) throws SQLException, IOException {
        String sql = "call novo_professor(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            try(Connection conn = dbUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
                
            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getSexo());
            stmt.setDate(3, java.sql.Date.valueOf(professor.getDataNasc()));
            stmt.setString(4, professor.getRg());
            stmt.setString(5, professor.getCpf());
            stmt.setString(6, professor.getCelular());
            stmt.setString(7, professor.getEmail());
            stmt.setString(8, professor.getDisciplina1());
            stmt.setString(9, professor.getDisciplina2());
            stmt.setString(10, professor.getSenha());
            stmt.setString(11, professor.getSenha_repetida());
            stmt.setString(12, professor.getPerfil());
            stmt.setString(13, professor.getRua());
            stmt.setInt(14, professor.getNumero());
            stmt.setString(15, professor.getComplemento());
            stmt.setString(16, professor.getBairro());
            stmt.setString(17, professor.getCep());
            //Executar atualização no banco
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro durante insert dos dados");
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
            System.err.println("Ocorreu um erro durante exclusão dos dados");
        }
    }
    
    public void updateProfessor(Professor professor) throws SQLException, IOException {
        String sql = "UPDATE professor INNER JOIN endereco ON "
                + "professor.fk_endereco = id_endereco SET professor.nome = ?,"
                + "professor.sexo = ?,professor.data_nascimento = ?,professor.rg = ?,"
                + "professor.cpf = ?,professor.celular = ?,professor.email = ?,"
                + "professor.disciplina1 = ?,professor.disciplina2 = ?,"
                + "professor.senha = ?, professor.senha_repetida = ?,"                
                + "professor.perfil = ?,endereco.rua = ?,endereco.numero = ?,"
                + "endereco.complemento = ?,endereco.bairro = ?,"
                + "endereco.cep = ? WHERE cod_professor = ?";
        try (Connection conn = dbUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){ 
            
            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getSexo());
            stmt.setDate(3, java.sql.Date.valueOf(professor.getDataNasc()));
            stmt.setString(4, professor.getRg());
            stmt.setString(5, professor.getCpf());
            stmt.setString(6, professor.getCelular());
            stmt.setString(7, professor.getEmail());
            stmt.setString(8, professor.getDisciplina1());
            stmt.setString(9, professor.getDisciplina2());
            stmt.setString(10, professor.getSenha());
            stmt.setString(11, professor.getSenha_repetida());
            stmt.setString(12, professor.getPerfil());
            stmt.setString(13, professor.getRua());
            stmt.setInt(14, professor.getNumero());
            stmt.setString(15, professor.getComplemento());
            stmt.setString(16, professor.getBairro());
            stmt.setString(17, professor.getCep());
            stmt.setInt(18, professor.getCodProfessor());
            
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro na edição dos dados");
        }
    }
    
    public List<Professor> getAllProfessor() throws SQLException, IOException {
        String sql = "select * FROM professor INNER JOIN endereco on"
                + " professor.fk_endereco = endereco.id_endereco";
        List<Professor> listaDeProfessor = new ArrayList<>();
        try (
            Connection conn = dbUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rst = stmt.executeQuery(sql)){
            while (rst.next()) {
                Professor professor = new Professor();
                professor.setCodProfessor(rst.getInt("cod_professor"));
                professor.setNome(rst.getString("nome"));
                professor.setDataNasc(rst.getDate("data_nascimento").toLocalDate());
                professor.setSexo(rst.getString("sexo"));
                professor.setRg(rst.getString("rg"));
                professor.setCpf(rst.getString("cpf"));
                professor.setCelular(rst.getString("celular"));
                professor.setEmail(rst.getString("email"));
                professor.setDisciplina1(rst.getString("disciplina1"));
                professor.setDisciplina2(rst.getString("disciplina2"));
                professor.setSenha(rst.getString("senha"));
                professor.setSenha_repetida(rst.getString("senha_repetida"));
                professor.setPerfil(rst.getString("perfil"));
                professor.setRua(rst.getString("rua"));
                professor.setNumero(Integer.parseInt(rst.getString("numero")));
                professor.setComplemento(rst.getString("complemento"));
                professor.setBairro(rst.getString("bairro"));
                professor.setCep(rst.getString("cep"));
                 listaDeProfessor.add(professor);
            }
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao montar a lista de"
                    + " professores.");
        }
        return listaDeProfessor;
    }


    
    public Professor getProfessorById(int codProfessor) throws SQLException, IOException {
        String sql = "select * FROM professor INNER JOIN endereco ON"
                + " professor.fk_endereco = endereco.id_endereco WHERE cod_professor = ?";
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
                professor.setSexo(rst.getString("sexo"));
                professor.setDataNasc(rst.getDate("data_nascimento").toLocalDate());
                professor.setRg(rst.getString("rg"));
                professor.setCpf(rst.getString("cpf"));
                professor.setCelular(rst.getString("celular"));
                professor.setEmail(rst.getString("email"));
                professor.setDisciplina1(rst.getString("disciplina1"));
                professor.setDisciplina2(rst.getString("disciplina2"));
                professor.setSenha(rst.getString("senha"));
                professor.setSenha_repetida(rst.getString("senha_repetida"));
                professor.setPerfil(rst.getString("perfil"));
                professor.setRua(rst.getString("rua"));
                professor.setNumero(Integer.parseInt(rst.getString("numero")));
                professor.setComplemento(rst.getString("complemento"));
                professor.setBairro(rst.getString("bairro"));
                professor.setCep(rst.getString("cep"));
            }
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao recuperar o professor: "
                    + "" + codProfessor);
        }
        return professor;
}

   
}
