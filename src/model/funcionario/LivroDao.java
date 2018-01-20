package model.funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ConfigDao;
import model.genero.Genero;
import model.editora.Editora;
import model.autor.Autor;

public class LivroDao {
    private Connection conexao; // Objeto que recebe os dados de conex�o com o banco de dados.
    private PreparedStatement comando; // Objeto usado para executar a instru��o SQL.
    private ResultSet registros; // Objeto que recebe os dados retornados pela instru��o SQL.
    
    // Recupera os dados do objeto Livro recebido e tenta inser�-los no banco de dados.
	public boolean inserirLivro(Livro livro){
        String instrucaoSQL;
        ConfigDao cfgDao = new ConfigDao();

        try {
            if (cfgDao.ConectarBD()) { // Abre a conex�o com o banco de dados.
                conexao = cfgDao.ObterConexaoBD(); // Obt�m os dados de conex�o com o banco de dados.
                instrucaoSQL = "INSERT INTO livro (Titulo, Preco, IdGenero, IdEditora, IdAutor) VALUES ("
		                        + "'" + livro.getTitulo() + "','" 
		                        + livro.getPreco() + "','"
		                        + livro.getIdGenero() + "','"
		                        + livro.getIdEditora() + "','"
		                        + livro.getIdAutor() + "')";  
                comando = conexao.prepareStatement(instrucaoSQL);
                comando.executeUpdate(); // Executa a instru��o SQL.
                cfgDao.DesconectarBD(); // Fecha a conex�o com o banco de dados.
                return true;
            } else {
                return false; // Em caso de erro na conex�o com o banco de dados.
            }
        } catch (Exception erro) {
        	System.out.println(erro.getMessage());
            return false; // Caso ocorra qualquer tipo de exce��o.
        }
	}
	
	// Recupera os Generos cadastrados no banco de dados para que sejam carregados no JComboBox Genero.
	public ArrayList<Genero> recuperarGenerosBd(){
        String instrucaoSQL;
        ConfigDao cfgDao = new ConfigDao();
        Genero c;
        ArrayList<Genero> generos = new ArrayList<>();

        try {
            if (cfgDao.ConectarBD()) { // Abre a conex�o com o banco de dados.
                conexao = cfgDao.ObterConexaoBD(); // Obt�m os dados de conex�o com o banco de dados.
                instrucaoSQL = "SELECT IdGenero, Descricao FROM genero";
                comando = conexao.prepareStatement(instrucaoSQL);
                
                // Executa a instru��o SQL e retorna os dados ao objeto ResultSet.
                registros = comando.executeQuery(); 
                
                try {
                    if (registros.next()) { // Se for retornado pelo menos um registro.
                        registros.beforeFirst(); // Retorna o cursor para antes do 1� registro.
                        while (registros.next()) { // Percorre os registros retornados.
                            Integer idGenero = Integer.parseInt(registros.getString("IdGenero")); // Obt�m o id armazenado no registro.
                            String descricao = registros.getString("Descricao"); // Obt�m a descri��o armazenada no registro.

                            // Atribui os dados do genero ao objeto Genero por meio dos m�todos set e
                            // adiciona este objeto ao ArrayList generos.
                            c = new Genero();
                            c.setIdGenero(idGenero);
                            c.setDescricao(descricao);
                            generos.add(c);
                        }
                    }
                } catch (SQLException erro) {
                    erro.getMessage(); // Se houver exce��o, o objeto generos fica igual � null.
                }
                cfgDao.DesconectarBD(); // Fecha a conex�o com o banco de dados.
            }
        } catch (Exception erro) {
        	erro.getMessage(); // Se houver qualquer outra exce��o, o objeto generos fica igual � null.
        }
        return generos; // Retorna o ArrayList de objetos Genero.
	}
	
	
	
	
	public ArrayList<Editora> recuperarEditorasBd(){
        String instrucaoSQL;
        ConfigDao cfgDao = new ConfigDao();
        Editora e;
        ArrayList<Editora> editoras = new ArrayList<>();

        try {
            if (cfgDao.ConectarBD()) { 
                conexao = cfgDao.ObterConexaoBD(); 
                instrucaoSQL = "SELECT IdEditora, Nome_Editora FROM editora";
                comando = conexao.prepareStatement(instrucaoSQL);
                
                
                registros = comando.executeQuery(); 
                
                try {
                    if (registros.next()) { 
                        registros.beforeFirst(); 
                        while (registros.next()) { 
                            Integer idEditora = Integer.parseInt(registros.getString("IdEditora")); 
                            String nome_Editora = registros.getString("Nome_Editora");
                            
                            e = new Editora();
                            e.setIdEditora(idEditora);
                            e.setNome_Editora(nome_Editora);
                            editoras.add(e);
                        }
                    }
                } catch (SQLException erro) {
                    erro.getMessage();
                }
                cfgDao.DesconectarBD();
            }
        } catch (Exception erro) {
        	erro.getMessage(); 
        }
        return editoras; 
	}
	
	
	public ArrayList<Autor> recuperarAutoresBd(){
        String instrucaoSQL;
        ConfigDao cfgDao = new ConfigDao();
        Autor a;
        ArrayList<Autor> autores = new ArrayList<>();

        try {
            if (cfgDao.ConectarBD()) { 
                conexao = cfgDao.ObterConexaoBD(); 
                instrucaoSQL = "SELECT IdAutor, Nome_Autor FROM autor";
                comando = conexao.prepareStatement(instrucaoSQL);
                
                
                registros = comando.executeQuery(); 
                
                try {
                    if (registros.next()) { 
                        registros.beforeFirst(); 
                        while (registros.next()) { 
                            Integer idAutor = Integer.parseInt(registros.getString("IdAutor")); 
                            String nome_Autor = registros.getString("Nome_Autor");
                            
                            a = new Autor();
                            a.setIdAutor(idAutor);
                            a.setNome_Autor(nome_Autor);
                            autores.add(a);
                        }
                    }
                } catch (SQLException erro) {
                    erro.getMessage();
                }
                cfgDao.DesconectarBD();
            }
        } catch (Exception erro) {
        	erro.getMessage(); 
        }
        return autores; 
	}
	
	
	// Recupera os livros cadastrados no banco de dados para que sejam carregados no JTable de livros.
	public ArrayList<Livro> consultarLivros(){
        String instrucaoSQL;
        ConfigDao cfgDao = new ConfigDao();
        Livro f;
        ArrayList<Livro> livros = new ArrayList<>();

        try {
            if (cfgDao.ConectarBD()) { // Abre a conex�o com o banco de dados.
                conexao = cfgDao.ObterConexaoBD(); // Obt�m os dados de conex�o com o banco de dados.
                instrucaoSQL = "SELECT * FROM livro";
                comando = conexao.prepareStatement(instrucaoSQL);
                
                // Executa a instru��o SQL e retorna os dados ao objeto ResultSet.
                registros = comando.executeQuery(); 
                
                try {
                    if (registros.next()) { // Se for retornado pelo menos um registro.
                        registros.beforeFirst(); // Retorna o cursor para antes do 1� registro.
                        while (registros.next()) { // Percorre os registros retornados.
                            Integer idLivro = Integer.parseInt(registros.getString("IdLivro")); // Obt�m o id do livro armazenado no registro.
                            String titulo = registros.getString("titulo"); // Obt�m o titulo armazenado no registro.
                            Double preco = Double.parseDouble(registros.getString("Preco")); // Obt�m o sal�rio armazenado no registro.
                            Integer idGenero = Integer.parseInt(registros.getString("IdGenero")); // Obt�m o id do genero armazenado no registro.
                            Integer idAutor = Integer.parseInt(registros.getString("IdAutor"));
                            Integer idEditora = Integer.parseInt(registros.getString("IdEditora"));
                            
                            // Atribui os dados do livro ao objeto Livro por meio dos m�todos set e
                            // adiciona este objeto ao ArrayList livros.
                            f = new Livro();
                            f.setIdLivro(idLivro);
                            f.setTitulo(titulo);
                            f.setPreco(preco);
                            f.setIdGenero(idGenero);
                            f.setIdAutor(idAutor);
                            f.setIdEditora(idEditora);
                            
                            livros.add(f);
                        }
                    }
                } catch (SQLException erro) {
                    erro.getMessage(); // Se houver exce��o, o objeto livros fica igual � null.
                }
                cfgDao.DesconectarBD(); // Fecha a conex�o com o banco de dados.
            }
        } catch (Exception erro) {
        	erro.getMessage(); // Se houver qualquer outra exce��o, o objeto livros fica igual � null.
        }
        return livros;
	}
	
    // Recupera os dados do objeto Livro recebido e tenta alter�-los no banco de dados.
	public boolean alterarLivro(Livro livro){
        String instrucaoSQL;
        ConfigDao cfgDao = new ConfigDao();

        try {
            if (cfgDao.ConectarBD()) { // Abre a conex�o com o banco de dados.
                conexao = cfgDao.ObterConexaoBD(); // Obt�m os dados de conex�o com o banco de dados.      
                System.out.println(livro.getIdEditora() + " " + livro.getIdAutor());
                instrucaoSQL = "UPDATE livro SET  "
		                        + "Titulo='" + livro.getTitulo() + "'," 
		                        + "Preco='" + livro.getPreco() + "'," 
		                        + "idGenero='" + livro.getIdGenero()
		                        + "', idAutor='" + livro.getIdAutor()
		                        + "', idEditora='" + livro.getIdEditora()
		                        + "' WHERE IdLivro='" + livro.getIdLivro() + "'"; 
                comando = conexao.prepareStatement(instrucaoSQL);
                comando.executeUpdate(); // Executa a instru��o SQL.
                cfgDao.DesconectarBD(); // Fecha a conex�o com o banco de dados.
                return true;
            } else {
                return false; // Em caso de erro na conex�o com o banco de dados.
            }
        } catch (Exception erro) {
        	System.out.print("UPDATE livro SET "
                    + "Titulo='" + livro.getTitulo() + "'," 
                    + "Preco='" + livro.getPreco() + "'," 
                    + "idGenero='" + livro.getIdGenero()
                    + "' idEditora='" + livro.getIdEditora()
                    + "' idAutor=" + livro.getIdAutor()
                    + "' WHERE IdLivro='" + livro.getIdLivro());
            return false; // Caso ocorra qualquer tipo de exce��o.
        }
	}
	
    // Recupera o c�digo do livro recebido e tenta exclu�-lo do banco de dados.
	public boolean excluirLivro(int idLivro){
        String instrucaoSQL;
        ConfigDao cfgDao = new ConfigDao();

        try {
            if (cfgDao.ConectarBD()) { // Abre a conex�o com o banco de dados.
                conexao = cfgDao.ObterConexaoBD(); // Obt�m os dados de conex�o com o banco de dados.                
                instrucaoSQL = "DELETE FROM livro WHERE IdLivro=" + idLivro; 
                comando = conexao.prepareStatement(instrucaoSQL);
                comando.executeUpdate(); // Executa a instru��o SQL.
                cfgDao.DesconectarBD(); // Fecha a conex�o com o banco de dados.
                return true;
            } else {
                return false; // Em caso de erro na conex�o com o banco de dados.
            }
        } catch (Exception erro) {
            return false; // Caso ocorra qualquer tipo de exce��o.
        }
	}
}