	package model.genero;
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;

	import model.ConfigDao;
	import model.genero.Genero;
	

	public class GeneroDao {
	    private Connection conexao; // Objeto que recebe os dados de conexão com o banco de dados.
	    private PreparedStatement comando; // Objeto usado para executar a instrução SQL.
	    private ResultSet registros; // Objeto que recebe os dados retornados pela instrução SQL.
	    
	    // Recupera os dados do objeto Genero recebido e tenta inserí-los no banco de dados.
		public boolean inserirGenero(Genero genero){
	        String instrucaoSQL;
	        ConfigDao cfgDao = new ConfigDao();

	        try {
	            if (cfgDao.ConectarBD()) { // Abre a conexão com o banco de dados.
	                conexao = cfgDao.ObterConexaoBD(); // Obtém os dados de conexão com o banco de dados.
	                instrucaoSQL = "INSERT INTO genero (Descricao, IdGenero) VALUES ("
	                        + "'" + genero.getDescricao() + "','" 
	                        
	                        + genero.getIdGenero() + "')";
	                        
	                        
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
		
		// Recupera os Generos cadastrados no banco de dados para que sejam carregados no JComboBox Genero.
		public ArrayList<Genero> recuperarGenerosBd(){
	        String instrucaoSQL;
	        ConfigDao cfgDao = new ConfigDao();
	        Genero c;
	        ArrayList<Genero> generos = new ArrayList<>();

	        try {
	            if (cfgDao.ConectarBD()) { // Abre a conexão com o banco de dados.
	                conexao = cfgDao.ObterConexaoBD(); // Obtém os dados de conexão com o banco de dados.
	                instrucaoSQL = "SELECT IdGenero, Descricao FROM genero";
	                comando = conexao.prepareStatement(instrucaoSQL);
	                
	                // Executa a instrução SQL e retorna os dados ao objeto ResultSet.
	                registros = comando.executeQuery(); 
	                
	                try {
	                    if (registros.next()) { // Se for retornado pelo menos um registro.
	                        registros.beforeFirst(); // Retorna o cursor para antes do 1º registro.
	                        while (registros.next()) { // Percorre os registros retornados.
	                            Integer idGenero = Integer.parseInt(registros.getString("IdGenero")); // Obtém o id armazenado no registro.
	                            String descricao = registros.getString("Descricao"); // Obtém a descrição armazenada no registro.

	                            // Atribui os dados do genero ao objeto Genero por meio dos métodos set e
	                            // adiciona este objeto ao ArrayList generos.
	                            c = new Genero();
	                            c.setIdGenero(idGenero);
	                            c.setDescricao(descricao);
	                            generos.add(c);
	                        }
	                    }
	                } catch (SQLException erro) {
	                    erro.getMessage(); // Se houver exceção, o objeto generos fica igual à null.
	                }
	                cfgDao.DesconectarBD(); // Fecha a conexão com o banco de dados.
	            }
	        } catch (Exception erro) {
	        	erro.getMessage(); // Se houver qualquer outra exceção, o objeto generos fica igual à null.
	        }
	        return generos; // Retorna o ArrayList de objetos Genero.
		}
		
		
		// Recupera os generos cadastrados no banco de dados para que sejam carregados no JTable de generos.
		public ArrayList<Genero> consultarGeneros(){
	        String instrucaoSQL;
	        ConfigDao cfgDao = new ConfigDao();
	        Genero f;
	        ArrayList<Genero> generos = new ArrayList<>();

	        try {
	            if (cfgDao.ConectarBD()) { // Abre a conexão com o banco de dados.
	                conexao = cfgDao.ObterConexaoBD(); // Obtém os dados de conexão com o banco de dados.
	                instrucaoSQL = "SELECT * FROM genero";
	                comando = conexao.prepareStatement(instrucaoSQL);
	                
	                // Executa a instrução SQL e retorna os dados ao objeto ResultSet.
	                registros = comando.executeQuery(); 
	                
	                try {
	                    if (registros.next()) { // Se for retornado pelo menos um registro.
	                        registros.beforeFirst(); // Retorna o cursor para antes do 1º registro.
	                        while (registros.next()) { // Percorre os registros retornados.
	                            Integer idGenero = Integer.parseInt(registros.getString("IdGenero")); // Obtém o id do genero armazenado no registro.
	                            String descricao = registros.getString("Descricao"); // Obtém o titulo armazenado no registro.
      
	                            
	                            // Atribui os dados do genero ao objeto genero por meio dos métodos set e
	                            // adiciona este objeto ao ArrayList generos.
	                            f = new Genero();
	                            f.setIdGenero(idGenero);
	                            f.setDescricao(descricao);
	                            
	                            generos.add(f);
	                        }
	                    }
	                } catch (SQLException erro) {
	                	System.out.println(erro.getMessage());
	                    erro.getMessage(); // Se houver exceção, o objeto generos fica igual à null.
	                }
	                cfgDao.DesconectarBD(); // Fecha a conexão com o banco de dados.
	            }
	        } catch (Exception erro) {
	        	erro.getMessage(); // Se houver qualquer outra exceção, o objeto generos fica igual à null.
	        	System.out.println(erro.getMessage());
	        }
	        return generos;
		}
		
	    // Recupera os dados do objeto genero recebido e tenta alterá-los no banco de dados.
		public boolean alterarGenero(Genero genero){
	        String instrucaoSQL;
	        ConfigDao cfgDao = new ConfigDao();

	        try {
	            if (cfgDao.ConectarBD()) { // Abre a conexão com o banco de dados.
	                conexao = cfgDao.ObterConexaoBD(); // Obtém os dados de conexão com o banco de dados.      
	                
	                instrucaoSQL = "UPDATE genero SET  "
			                        + "Descricao='" + genero.getDescricao() + "'," 
			                        + "idGenero='" + genero.getIdGenero()    
			                        + "' WHERE IdGenero='" + genero.getIdGenero() + "'"; 
	                comando = conexao.prepareStatement(instrucaoSQL);
	                comando.executeUpdate(); // Executa a instrução SQL.
	                cfgDao.DesconectarBD(); // Fecha a conexão com o banco de dados.
	                return true;
	            } else {
	                return false; // Em caso de erro na conexão com o banco de dados.
	            }
	        } catch (Exception erro) {
	        	System.out.print("UPDATE genero SET "
	                    + "Descricao='" + genero.getDescricao() + "'," 
	                    + "idGenero='" + genero.getIdGenero()
	                    + "' WHERE IdGenero='" + genero.getIdGenero());
	            return false; // Caso ocorra qualquer tipo de exceção.
	        }
		}
		
	    // Recupera o código do genero recebido e tenta excluí-lo do banco de dados.
		public boolean excluirGenero(int idGenero){
	        String instrucaoSQL;
	        ConfigDao cfgDao = new ConfigDao();

	        try {
	            if (cfgDao.ConectarBD()) { // Abre a conexão com o banco de dados.
	                conexao = cfgDao.ObterConexaoBD(); // Obtém os dados de conexão com o banco de dados.                
	                instrucaoSQL = "DELETE FROM genero WHERE IdGenero=" + idGenero; 
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
