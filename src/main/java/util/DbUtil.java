/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtil {

    private static Connection connection = null;

 
      public Connection getConnection() throws SQLException, FileNotFoundException, IOException {
        if (connection != null){
            return connection;
        }else {// 1) Abrir o arquivo de propriedades com informações de conexão
            try  (FileReader propReader = new FileReader("C:/Senac/conexao-bd.properties.txt")) {
                Properties bdProps = new Properties();
                bdProps.load(propReader);

                // 2) Declarar o driver JDBC
                try {
                    Class.forName(bdProps.getProperty("driver"));
                } catch (ClassNotFoundException ex) {
                    throw new SQLException("Driver do banco de dados não encontrado", ex);
                }

                // 3) Abrir conexão usando as propriedades configuradas no arquivo
                Connection conn = DriverManager.getConnection(bdProps.getProperty("url"), bdProps);
                return conn;
            } catch (IOException ex) {
                throw new SQLException("Arquivo conexao-bd.properties não encontrado", ex);
            }
            
        }
    }

}