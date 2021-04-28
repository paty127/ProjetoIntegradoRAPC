/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Aluno;
import util.DbUtil;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alex
 */
public class AlunoDAO2 {
    private final Connection  conn;
    private PreparedStatement stmt;
    
    public AlunoDAO2() throws SQLException, IOException{
        conn = new DbUtil().getConnection();
    }
    public void inserir(Aluno aluno){
        String sql = "INSERT INTO aluno (nome,data_de_nascimento,sexo,pai,"
                + "mae,celular,telefone_pai,telefone_mae,email) values"
                + "?,?,?,?,?,?,?,?,?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setDate(2, new java.sql.Date(aluno.getDataNasc().getTime()));
            stmt.setString(3, aluno.getSexo());
            stmt.setString(4, aluno.getNomePai());
            stmt.setString(5, aluno.getNomeMae());
            stmt.setString(6, aluno.getCelular());
            stmt.setString(7, aluno.getCelularPai());
            stmt.setString(8, aluno.getCelularMae());
            stmt.setString(9, aluno.getEmail());
            
            stmt.execute();
            stmt.close();
            
        } catch (Exception erro) {
            throw new RuntimeException("\nErro ao inserir aluno"+ erro);
        }
    }   
    public void alterar(Aluno aluno){
        String sql = "UPDATE aluno set nome = ?, WHERE nomePai = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getNomePai());
            
            stmt.execute();
            stmt.close();
            
        } catch (Exception erro) {
            throw new RuntimeException("\nErro ao alterar aluno"+ erro);
        }
    }   
    public void excluir(Aluno aluno){
        try {

            String sql = "delete nome from aluno where cod_aluno = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, aluno.getCodAluno());

            stmt.execute();
            stmt.close();

        } catch (SQLException erro) {
            throw new RuntimeException("\nErro ao excluir aluno"+ erro);
        }
    }
    public List<Aluno> listarAlunos() {
        try {

            List<Aluno> lista = new ArrayList<>();

            String sql = "select cod from aluno";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Aluno aluno = new Aluno();

                aluno.setCodAluno(rs.getInt("id"));
                aluno.setNome(rs.getString("Nome"));
                aluno.setNomeMae(rs.getString("Nome da Mãe"));
                aluno.setCelular(rs.getString("Celular"));

                lista.add(aluno);
            }
            return lista;
        } catch (SQLException erro) {

            return null;
        }
    }
}
