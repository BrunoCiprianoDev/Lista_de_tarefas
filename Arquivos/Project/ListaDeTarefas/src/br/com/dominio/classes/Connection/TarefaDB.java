package br.com.dominio.classes.Connection;

import br.com.dominio.classes.objects.Tarefa;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TarefaDB {
    public static boolean TarefaDBTestKey(Key key){
        Connection connection = ConnectionFactory.getConexao(key);
        try{
            Statement statement = connection.createStatement();
            ConnectionFactory.fecharConexao(connection, statement);
            return true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Usuario ou senha invalidos!","Error de autenticação!", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return false;
    }

    public static void save(Tarefa tarefa, Key key){
        String sql = "INSERT INTO tarefas (data, titulo, checklist) VALUES ('"+tarefa.getData()+"', '"+tarefa.getTitulo()+"', '0')";
        Connection connection = ConnectionFactory.getConexao(key);
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            ConnectionFactory.fecharConexao(connection, statement);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void update(Tarefa tarefaAntiga, Tarefa tarefaNova, Key key){
        if(tarefaAntiga == null || tarefaNova== null) {
            System.out.println("Não existe ID");
            return;
        }
        String sql2 = "UPDATE tarefas SET data = '"+tarefaNova.getData()+"' WHERE titulo = '"+tarefaAntiga.getTitulo()+"'";
        String sql1 = "UPDATE tarefas SET titulo = '"+tarefaNova.getTitulo()+"' WHERE titulo = '"+tarefaAntiga.getTitulo()+"'";
        Connection connection = ConnectionFactory.getConexao(key);
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql2);
            statement.executeUpdate(sql1);
            ConnectionFactory.fecharConexao(connection, statement);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void updateStatus(Tarefa tarefa, boolean value, Key key){
        if(tarefa == null || tarefa.getTitulo()== null) {
            System.out.println("Não existe ID");
            return;
        }
        String sql;
        if(value) {
            sql = "UPDATE tarefas SET checklist = '1' WHERE titulo = '"+tarefa.getTitulo()+"'";
        }else{
            sql = "UPDATE tarefas SET checklist = '0' WHERE titulo = '"+tarefa.getTitulo()+"'";
        }
        Connection connection = ConnectionFactory.getConexao(key);
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            ConnectionFactory.fecharConexao(connection, statement);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void delete(Tarefa tarefa, Key key){
        if(tarefa == null || tarefa.getTitulo()== null) {
            System.out.println("Não existe ID");
            return;
        }
        String sql ="DELETE FROM tarefas WHERE titulo='"+tarefa.getTitulo()+"'";
        Connection connection = ConnectionFactory.getConexao(key);
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            ConnectionFactory.fecharConexao(connection, statement);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static List<Tarefa> selectAll(Key key){
        String sql = "SELECT data, titulo, checklist FROM tarefas";
        Connection connection = ConnectionFactory.getConexao(key);
        ArrayList<Tarefa> listaTarefas = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                boolean check;
                if(resultSet.getInt("checklist")==1){
                    check = true;
                }else{
                    check = false;
                }
                listaTarefas.add(new Tarefa(resultSet.getString("data"), resultSet.getString("titulo"), check));
            }
            ConnectionFactory.fecharConexao(connection, statement, resultSet);
            return listaTarefas;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}