package model;

import java.sql.*;

public class ConfigDao {
    Connection conexao; // Objeto que receber� os dados para conex�o com o banco de dados.

    public boolean ConectarBD() { // Abre a conex�o com o banco de dados.
        // String de conex�o (Dados: servidor, porta de conex�o, banco de dados, usu�rio e senha).
        String strCon = "jdbc:mysql://localhost:3336/dpbd1?user=root&password=aluno";

        try {
            Class.forName("com.mysql.jdbc.Driver"); // Driver de acesso ao MySQL.
            conexao = DriverManager.getConnection(strCon); // Atribui os dados de conex�o ao objeto "conexao".
            return true;
        } catch (Exception erro) {
        	System.out.println(erro.getMessage());
            return false;
        }
    }

    public boolean DesconectarBD() { // Fecha a conex�o com o banco de dados.
        try {
            conexao.close();
            return true;
        } catch (SQLException erro) {
            return false;
        }
    }

    public Connection ObterConexaoBD() { // Retorna os dados para conex�o com o banco de dados.
        return conexao;
    }
    
}