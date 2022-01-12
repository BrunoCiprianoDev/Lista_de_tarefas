package br.com.dominio.classes.Connection;

import javax.swing.*;
import java.sql.*;

public class ConnectionFactory {

    public static Connection getConexao(Key key){
        String url = "jdbc:mysql://localhost:3306/lista_tarefas";
        try{
            return DriverManager.getConnection(url,key.getUser(), key.getPassword());
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Usuario ou senha invalidos!","Falha de autenticação!", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return null;
    }
    public static void fecharConexao(Connection connection){
        try{
            if(connection!=null){
                connection.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void fecharConexao(Connection connection, Statement statement){
        fecharConexao(connection);
        try{
            if(statement != null){
                statement.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void fecharConexao(Connection connection, Statement statement, ResultSet resultSet){
        fecharConexao(connection, statement);
        try{
            if(resultSet != null){
                resultSet.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
