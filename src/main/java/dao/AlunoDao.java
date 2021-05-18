/*
 * Desenvolvedores: 
 * ALEXSANDRO DA SILVA RAMOS
 * CARLOS HENRIQUE PAVAO INACIO
 * PATRICIA
 * FERREIRA DE SOUSA RENAN FERREIRA NOVAES
 * MATHEUS MARCHENA
*/

package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import model.Aluno;
import util.DbUtil;

public class AlunoDao {

    private final DbUtil dbUtil = new DbUtil();
    String erro = "Erro na execução";

    public void adicionarAluno(Aluno aluno) throws SQLException, IOException {
        String sql = "call novo_aluno(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection conn = dbUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setDate(3, java.sql.Date.valueOf(aluno.getDataNasc()));
            stmt.setString(4, aluno.getSexo());
            stmt.setString(5, aluno.getCelular());
            stmt.setString(6, aluno.getEmail());
            stmt.setString(7, aluno.getNomePai());
            stmt.setString(8, aluno.getCelularPai());
            stmt.setString(9, aluno.getNomeMae());
            stmt.setString(10, aluno.getCelularMae());
            stmt.setString(11, aluno.getRua());
            stmt.setInt(12, aluno.getNumero());
            stmt.setString(13, aluno.getComplemento());
            stmt.setString(14, aluno.getBairro());
            stmt.setString(15, aluno.getCep());
            //Executar atualização no banco
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(erro);
        }
    }

    public void deletarAluno(int alunoID) throws SQLException, IOException {
        String sql = "DELETE aluno,endereco FROM aluno INNER JOIN endereco"
                + " ON aluno.fk_endereco = endereco.id_endereco"
                + " WHERE cod_aluno = ?";
        try (Connection conn = dbUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, alunoID);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println(erro);
        }
    }

    public void updateAluno(Aluno aluno) throws SQLException, IOException {
        String sql = "UPDATE aluno INNER JOIN endereco ON "
                + "aluno.fk_endereco = id_endereco SET aluno.nome = ?,"
                + "aluno.cpf =?, aluno.data_de_nascimento = ?,aluno.sexo = ?,"
                + "aluno.celular = ?,aluno.email = ?,aluno.pai = ?,"
                + "aluno.telefone_pai = ?, aluno.mae = ?,aluno.telefone_mae = ?,"
                + "endereco.rua = ?,endereco.numero = ?, endereco.complemento = ?,"
                + "endereco.bairro = ?, endereco.cep = ? WHERE cod_aluno = ?";
        try (Connection conn = dbUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setDate(3, java.sql.Date.valueOf(aluno.getDataNasc()));
            stmt.setString(4, aluno.getSexo());
            stmt.setString(5, aluno.getCelular());
            stmt.setString(6, aluno.getEmail());
            stmt.setString(7, aluno.getNomePai());
            stmt.setString(8, aluno.getCelularPai());
            stmt.setString(9, aluno.getNomeMae());
            stmt.setString(10, aluno.getCelularMae());
            stmt.setString(11, aluno.getRua());
            stmt.setInt(12, aluno.getNumero());
            stmt.setString(13, aluno.getComplemento());
            stmt.setString(14, aluno.getBairro());
            stmt.setString(15, aluno.getCep());
            stmt.setInt(16, aluno.getCodAluno());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println(erro);
        }
    }

    public List<Aluno> getAllAlunos() throws SQLException, IOException {
        String sql = "select * FROM aluno INNER JOIN endereco on aluno.fk_endereco = endereco.id_endereco";
        List<Aluno> listaDeAluno = new ArrayList<>();
        try (
                Connection conn = dbUtil.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rst = stmt.executeQuery(sql)) {
            while (rst.next()) {
                Aluno aluno = new Aluno();
                aluno.setCodAluno(rst.getInt("cod_aluno"));
                aluno.setNome(rst.getString("nome"));
                aluno.setCpf(rst.getString("cpf"));
                aluno.setSexo(rst.getString("sexo"));
                aluno.setDataNasc(rst.getDate("data_de_nascimento").toLocalDate());
                aluno.setNomePai(rst.getString("pai"));
                aluno.setNomeMae(rst.getString("mae"));
                aluno.setCelular(rst.getString("celular"));
                aluno.setCelularPai(rst.getString("telefone_pai"));
                aluno.setCelularMae(rst.getString("telefone_mae"));
                aluno.setEmail(rst.getString("email"));
                aluno.setRua(rst.getString("rua"));
                aluno.setNumero(Integer.parseInt(rst.getString("numero")));
                aluno.setComplemento(rst.getString("complemento"));
                aluno.setBairro(rst.getString("bairro"));
                aluno.setCep(rst.getString("cep"));

                listaDeAluno.add(aluno);
            }
        } catch (SQLException e) {
            System.err.println(erro);
        }

        return listaDeAluno;
    }

    public Aluno getAlunoById(int codAluno) throws SQLException, IOException {
        String sql = "select * FROM aluno INNER JOIN endereco ON aluno.fk_endereco = endereco.id_endereco WHERE cod_aluno = ?";
        Aluno aluno = new Aluno();
        Connection conn = dbUtil.getConnection();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, codAluno);
            //Execultando o comando            
            ResultSet rst = stmt.executeQuery();

            if (rst.next()) {
                aluno.setCodAluno(rst.getInt("cod_aluno"));
                aluno.setNome(rst.getString("nome"));
                aluno.setCpf(rst.getString("cpf"));
                aluno.setDataNasc(rst.getDate("data_de_nascimento").toLocalDate());
                aluno.setNomePai(rst.getString("pai"));
                aluno.setNomeMae(rst.getString("mae"));
                aluno.setCelular(rst.getString("celular"));
                aluno.setCelularPai(rst.getString("telefone_pai"));
                aluno.setCelularMae(rst.getString("telefone_mae"));
                aluno.setEmail(rst.getString("email"));
                aluno.setSexo(rst.getString("sexo"));
                aluno.setRua(rst.getString("rua"));
                aluno.setNumero(Integer.parseInt(rst.getString("numero")));
                aluno.setComplemento(rst.getString("complemento"));
                aluno.setBairro(rst.getString("bairro"));
                aluno.setCep(rst.getString("cep"));
            }
        } catch (SQLException e) {
            System.err.println("Erro na execução");
        }
        return aluno;
    }
    
    public long calcularIdade(LocalDate data) {
        long idade = -1;
        if (data != null) {
            idade = ChronoUnit.YEARS.between(data,LocalDate.now());
        }
        return idade;
    }
}
