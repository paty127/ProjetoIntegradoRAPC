package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Adm;

import util.DbUtil;


public class AdmDao {

    private final DbUtil dbUtil = new DbUtil();
    //String erro = "Erro na execução";

    public void adicionarAdm(Adm adm) throws SQLException, IOException {
        String sql = "call novo_adm(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection conn = dbUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, adm.getNome());
            stmt.setString(2, adm.getSexo());
            stmt.setDate(3, java.sql.Date.valueOf(adm.getDataNasc()));
            stmt.setString(4, adm.getRg());
            stmt.setString(5, adm.getCpf());
            stmt.setString(6, adm.getCelular());
            stmt.setString(7, adm.getEmail());
            stmt.setString(8, adm.getSenha());
            stmt.setString(9, adm.getSenha_repetida());
            stmt.setString(10, adm.getPerfil());
            stmt.setString(11, adm.getRua());
            stmt.setInt(12, adm.getNumero());
            stmt.setString(13, adm.getComplemento());
            stmt.setString(14, adm.getBairro());
            stmt.setString(15, adm.getCep());
            //Executar atualização no banco
            stmt.executeUpdate();
            conn.close();
            stmt.close();

        } catch (SQLException ex) {
            System.err.println("Erro durante insert dos dados");
        }
    }

    public void deletarAdm(int admID) throws SQLException, IOException {
        String sql = "DELETE adm,endereco FROM adm INNER JOIN endereco"
                + " ON adm.fk_endereco = endereco.id_endereco"
                + " WHERE cod_adm = ?";
        try (Connection conn = dbUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, admID);
            stmt.executeUpdate();
            conn.close();
            stmt.close();

        } catch (SQLException e) {
            System.err.println("Ocorreu um erro durante exclusão dos dados");
        }
    }

    public void updateAdm(Adm adm) throws SQLException, IOException {
        String sql = "UPDATE adm INNER JOIN endereco ON "
                + "adm.fk_endereco = id_endereco SET adm.nome = ?,"
                + "adm.sexo = ?,adm.data_nascimento = ?,adm.rg = ?,"
                + "adm.cpf = ?,adm.celular = ?,adm.email = ?,"
                + "adm.senha = ?, adm.senha_repetida = ?,"
                + "adm.perfil = ?,endereco.rua = ?,endereco.numero = ?,"
                + "endereco.complemento = ?,endereco.bairro = ?,"
                + "endereco.cep = ? WHERE cod_adm = ?";
        try (Connection conn = dbUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, adm.getNome());
            stmt.setString(2, adm.getSexo());
            stmt.setDate(3, java.sql.Date.valueOf(adm.getDataNasc()));
            stmt.setString(4, adm.getRg());
            stmt.setString(5, adm.getCpf());
            stmt.setString(6, adm.getCelular());
            stmt.setString(7, adm.getEmail());
            stmt.setString(8, adm.getSenha());
            stmt.setString(9, adm.getSenha_repetida());
            stmt.setString(10, adm.getPerfil());
            stmt.setString(11, adm.getRua());
            stmt.setInt(12, adm.getNumero());
            stmt.setString(13, adm.getComplemento());
            stmt.setString(14, adm.getBairro());
            stmt.setString(15, adm.getCep());
            stmt.setInt(16, adm.getCodAdm());

            stmt.executeUpdate();

            conn.close();
            stmt.close();

        } catch (SQLException e) {
            System.err.println("Ocorreu um erro na edição dos dados");
        }
    }

    public List<Adm> getAllAdm() throws SQLException, IOException {
        String sql = "select * FROM adm INNER JOIN endereco on"
                + " adm.fk_endereco = endereco.id_endereco";
        List<Adm> listaDeAdm = new ArrayList<>();
        try (
                Connection conn = dbUtil.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rst = stmt.executeQuery(sql)) {
            while (rst.next()) {
                Adm adm = new Adm();
                adm.setCodAdm(rst.getInt("cod_adm"));
                adm.setNome(rst.getString("nome"));
                adm.setDataNasc(rst.getDate("data_nascimento").toLocalDate());
                adm.setSexo(rst.getString("sexo"));
                adm.setRg(rst.getString("rg"));
                adm.setCpf(rst.getString("cpf"));
                adm.setCelular(rst.getString("celular"));
                adm.setEmail(rst.getString("email"));
                adm.setSenha(rst.getString("senha"));
                adm.setSenha_repetida(rst.getString("senha_repetida"));
                adm.setPerfil(rst.getString("perfil"));
                adm.setRua(rst.getString("rua"));
                adm.setNumero(Integer.parseInt(rst.getString("numero")));
                adm.setComplemento(rst.getString("complemento"));
                adm.setBairro(rst.getString("bairro"));
                adm.setCep(rst.getString("cep"));
                listaDeAdm.add(adm);
            }

            conn.close();
            stmt.close();
            rst.close();
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao recuperar a"
                    + " lista de todos ADM's.");
        }
        return listaDeAdm;
    }

    public Adm getAdmById(int codAdm) throws SQLException, IOException {
        String sql = "select * FROM adm INNER JOIN endereco ON"
                + " adm.fk_endereco = endereco.id_endereco WHERE cod_adm = ?";
        Adm adm = new Adm();
        Connection conn = dbUtil.getConnection();

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, codAdm);
            //Execultando o comando            

            ResultSet rst = stmt.executeQuery();

            if (rst.next()) {
                adm.setCodAdm(rst.getInt("cod_adm"));
                adm.setNome(rst.getString("nome"));
                adm.setSexo(rst.getString("sexo"));
                adm.setDataNasc(rst.getDate("data_nascimento").toLocalDate());
                adm.setRg(rst.getString("rg"));
                adm.setCpf(rst.getString("cpf"));
                adm.setCelular(rst.getString("celular"));
                adm.setEmail(rst.getString("email"));
                adm.setSenha(rst.getString("senha"));
                adm.setSenha_repetida(rst.getString("senha_repetida"));
                adm.setPerfil(rst.getString("perfil"));
                adm.setRua(rst.getString("rua"));
                adm.setNumero(Integer.parseInt(rst.getString("numero")));
                adm.setComplemento(rst.getString("complemento"));
                adm.setBairro(rst.getString("bairro"));
                adm.setCep(rst.getString("cep"));
            }
            conn.close();
            stmt.close();
            rst.close();
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao tentar recuperar"
                    + " os dados do Adm.");
        }
        return adm;
    }
}
