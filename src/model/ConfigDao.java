package model;

import java.sql.*;

public class ConfigDao {
    Connection conexao; // Objeto que receberá os dados para conexão com o banco de dados.

    public boolean ConectarBD() { // Abre a conexão com o banco de dados.
        // String de conexão (Dados: servidor, porta de conexão, banco de dados, usuário e senha).
        String strCon = "jdbc:mysql://localhost:3336/dpbd1?user=root&password=aluno";

        try {
            Class.forName("com.mysql.jdbc.Driver"); // Driver de acesso ao MySQL.
            conexao = DriverManager.getConnection(strCon); // Atribui os dados de conexão ao objeto "conexao".
            return true;
        } catch (Exception erro) {
        	System.out.println(erro.getMessage());
            return false;
        }
    }

    public boolean DesconectarBD() { // Fecha a conexão com o banco de dados.
        try {
            conexao.close();
            return true;
        } catch (SQLException erro) {
            return false;
        }
    }

    public Connection ObterConexaoBD() { // Retorna os dados para conexão com o banco de dados.
        return conexao;
    }
    
}