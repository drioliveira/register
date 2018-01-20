	package model.autor;
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;

	import model.ConfigDao;
	import model.autor.Autor;
	

	public class AutorDao {
	    private Connection conexao; // Objeto que recebe os dados de conexão com o banco de dados.
	    private PreparedStatement comando; // Objeto usado para executar a instrução SQL.
	    private ResultSet registros; // Objeto que recebe os dados retornados pela instrução SQL.
	    
	    // Recupera os dados do objeto Autor recebido e tenta inserí-los no banco de dados.
		public boolean inserirAutor(Autor autor){
	        String instrucaoSQL;
	        ConfigDao cfgDao = new ConfigDao();

	        try {
	            if (cfgDao.ConectarBD()) { // Abre a conexão com o banco de dados.
	                conexao = cfgDao.ObterConexaoBD(); // Obtém os dados de conexão com o banco de dados.
	                instrucaoSQL = "INSERT INTO autor (Nome_Autor, Nacionalidade) VALUES ("
	                        + "'" + autor.getNome_Autor() + "',"
	                        + "'" + autor.getNacionalidade() + "')";
	                
	                System.out.println(instrucaoSQL);
	                        
	                        
            comando = conexao.prepareStatement(instrucaoSQL);
	                comando.executeUpdate(); // Executa a instrução SQL.
	                cfgDao.DesconectarBD(); // Fecha a conexão com o banco de dados.
	                return true;
	            } else {
	                return false; // Em caso de erro na conexão com o banco de dados.
	            }
	        } catch (Exception erro) {
	        	System.out.println(erro.getMessage());
	            return false; // Caso ocorra qualquer tipo de exceção.
	        }
		}
		
		// Recupera os Autors cadastrados no banco de dados para que sejam carregados no JComboBox Autor.
		public ArrayList<Autor> recuperarAutorsBd(){
	        String instrucaoSQL;
	        ConfigDao cfgDao = new ConfigDao();
	        Autor c;
	        ArrayList<Autor> autors = new ArrayList<>();

	        try {
	            if (cfgDao.ConectarBD()) { // Abre a conexão com o banco de dados.
	                conexao = cfgDao.ObterConexaoBD(); // Obtém os dados de conexão com o banco de dados.
	                instrucaoSQL = "SELECT IdAutor, Nome_Autor, Nacionalidade FROM autor";
	                comando = conexao.prepareStatement(instrucaoSQL);
	                
	                // Executa a instrução SQL e retorna os dados ao objeto ResultSet.
	                registros = comando.executeQuery(); 
	                
	                try {
	                    if (registros.next()) { // Se for retornado pelo menos um registro.
	                        registros.beforeFirst(); // Retorna o cursor para antes do 1º registro.
	                        while (registros.next()) { // Percorre os registros retornados.
	                            Integer idAutor = Integer.parseInt(registros.getString("IdAutor")); // Obtém o id armazenado no registro.
	                            String nome_Autor = registros.getString("Nome_Autor"); // Obtém a descrição armazenada no registro.
	                            String nacionalidade = registros.getString("Nacionalidade");
	                            
	                            // Atribui os dados do autor ao objeto Autor por meio dos métodos set e
	                            // adiciona este objeto ao ArrayList autors.
	                            c = new Autor();
	                            c.setIdAutor(idAutor);
	                            c.setNome_Autor(nome_Autor);
	                            c.setNacionalidade(nacionalidade);
	                            autors.add(c);
	                        }
	                    }
	                } catch (SQLException erro) {
	                    erro.getMessage(); // Se houver exceção, o objeto autors fica igual à null.
	                }
	                cfgDao.DesconectarBD(); // Fecha a conexão com o banco de dados.
	            }
	        } catch (Exception erro) {
	        	erro.getMessage(); // Se houver qualquer outra exceção, o objeto autors fica igual à null.
	        }
	        return autors; // Retorna o ArrayList de objetos Autor.
		}
		
		
		// Recupera os autors cadastrados no banco de dados para que sejam carregados no JTable de autors.
		public ArrayList<Autor> consultarAutors(){
	        String instrucaoSQL;
	        ConfigDao cfgDao = new ConfigDao();
	        Autor f;
	        ArrayList<Autor> autors = new ArrayList<>();

	        try {
	            if (cfgDao.ConectarBD()) { // Abre a conexão com o banco de dados.
	                conexao = cfgDao.ObterConexaoBD(); // Obtém os dados de conexão com o banco de dados.
	                instrucaoSQL = "SELECT * FROM autor";
	                comando = conexao.prepareStatement(instrucaoSQL);
	                
	                // Executa a instrução SQL e retorna os dados ao objeto ResultSet.
	                registros = comando.executeQuery(); 
	                
	                try {
	                    if (registros.next()) { // Se for retornado pelo menos um registro.
	                        registros.beforeFirst(); // Retorna o cursor para antes do 1º registro.
	                        while (registros.next()) { // Percorre os registros retornados.
	                            Integer idAutor = Integer.parseInt(registros.getString("IdAutor")); // Obtém o id do autor armazenado no registro.
	                            String nome_Autor = registros.getString("Nome_Autor"); // Obtém o titulo armazenado no registro.
	                            String nacionalidade = registros.getString("Nacionalidade");
	                            
	                            // Atribui os dados do autor ao objeto autor por meio dos métodos set e
	                            // adiciona este objeto ao ArrayList autors.
	                            f = new Autor();
	                            f.setIdAutor(idAutor);
	                            f.setNome_Autor(nome_Autor);
	                            f.setNacionalidade(nacionalidade);
	                            
	                            autors.add(f);
	                        }
	                    }
	                } catch (SQLException erro) {
	                	System.out.println(erro.getMessage());
	                    erro.getMessage(); // Se houver exceção, o objeto autors fica igual à null.
	                }
	                cfgDao.DesconectarBD(); // Fecha a conexão com o banco de dados.
	            }
	        } catch (Exception erro) {
	        	erro.getMessage(); // Se houver qualquer outra exceção, o objeto autors fica igual à null.
	        	System.out.println(erro.getMessage());
	        }
	        return autors;
		}
		
	    // Recupera os dados do objeto autor recebido e tenta alterá-los no banco de dados.
		public boolean alterarAutor(Autor autor){
	        String instrucaoSQL;
	        ConfigDao cfgDao = new ConfigDao();

	        try {
	            if (cfgDao.ConectarBD()) { // Abre a conexão com o banco de dados.
	                conexao = cfgDao.ObterConexaoBD(); // Obtém os dados de conexão com o banco de dados.      
	                
	                instrucaoSQL = "UPDATE autor SET  "
			                        + "Nome_Autor='" + autor.getNome_Autor() + "'," 
			                        + "nacionalidade='" + autor.getNacionalidade()
			                        + "' WHERE IdAutor=" + autor.getIdAutor() + ""; 
	                
	                comando = conexao.prepareStatement(instrucaoSQL);
	                comando.executeUpdate(); // Executa a instrução SQL.
	                cfgDao.DesconectarBD(); // Fecha a conexão com o banco de dados.
	                return true;
	            } else {
	                return false; // Em caso de erro na conexão com o banco de dados.
	            }
	        } catch (Exception erro) {
	        	System.out.print("UPDATE autor SET "
	                    + "Nome_Autor='" + autor.getNome_Autor() + "'," 
	                    + "idAutor='" + autor.getIdAutor()
	                    + "nacionalidade='" + autor.getNacionalidade()
	                    + "' WHERE IdAutor='" + autor.getIdAutor());
	            return false; // Caso ocorra qualquer tipo de exceção.
	        }
		}
		
	    // Recupera o código do autor recebido e tenta excluí-lo do banco de dados.
		public boolean excluirAutor(int idAutor){
	        String instrucaoSQL;
	        ConfigDao cfgDao = new ConfigDao();

	        try {
	            if (cfgDao.ConectarBD()) { // Abre a conexão com o banco de dados.
	                conexao = cfgDao.ObterConexaoBD(); // Obtém os dados de conexão com o banco de dados.                
	                instrucaoSQL = "DELETE FROM autor WHERE IdAutor=" + idAutor; 
	                comando = conexao.prepareStatement(instrucaoSQL);
	                comando.executeUpdate(); // Executa a instrução SQL.
	                cfgDao.DesconectarBD(); // Fecha a conexão com o banco de dados.
	                return true;
	            } else {
	                return false; // Em caso de erro na conexão com o banco de dados.
	            }
	        } catch (Exception erro) {
	            return false; // Caso ocorra qualquer tipo de exceção.
	        }
		}
	}


