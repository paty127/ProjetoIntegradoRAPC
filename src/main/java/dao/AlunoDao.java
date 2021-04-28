package dao;

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

    private Connection connection;

    public AlunoDao() {
        connection = DbUtil.getConnection();
    }

    public void adicionarAluno(Aluno aluno) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into Aluno(nome,data_de_nascimento,sexo,pai,mae,celular,telefone_pai,telefone_mae,email)values(?, ?, ?, ?, ?, ?, ?, ?, ? )");

            preparedStatement.setString(1, aluno.getNome());
            preparedStatement.setDate(2, new java.sql.Date(aluno.getDataNasc().getTime()));
            preparedStatement.setString(3, aluno.getSexo());
            preparedStatement.setString(4, aluno.getNomePai());
            preparedStatement.setString(5, aluno.getNomeMae());
            preparedStatement.setString(6, aluno.getCelular());
            preparedStatement.setString(7, aluno.getCelularPai());
            preparedStatement.setString(8, aluno.getCelularMae());
            preparedStatement.setString(9, aluno.getEmail());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
/*
    public void deletarAluno(int alunoID) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from users where userid=?");

            preparedStatement.setInt(1, alunoID);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
/*
    public void updateUser(User user) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update users set firstname=?, lastname=?, dob=?, email=?" +
                            "where userid=?");
            // Parameters start with 1
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setDate(3, new java.sql.Date(user.getDob().getTime()));
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setInt(5, user.getUserid());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> listaDeUsuario = new ArrayList<User>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from users");
            while (rs.next()) {
                User user = new User();
                user.setUserid(rs.getInt("userid"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                user.setDob(rs.getDate("dob"));
                user.setEmail(rs.getString("email"));
                listaDeUsuario.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaDeUsuario;
    }

    public User getUserById(int userId) {
        User user = new User();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where userid=?");
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user.setUserid(rs.getInt("userid"));
                user.setFirstName(rs.getString("firstname"));
                user.setLastName(rs.getString("lastname"));
                user.setDob(rs.getDate("dob"));
                user.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    }

 */
}
