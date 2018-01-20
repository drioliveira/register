
	package model.editora;
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;

	import model.ConfigDao;
	import model.editora.Editora;
	

	public class EditoraDao {
	    private Connection conexao; // Objeto que recebe os dados de conexão com o banco de dados.
	    private PreparedStatement comando; // Objeto usado para executar a instrução SQL.
	    private ResultSet registros; // Objeto que recebe os dados retornados pela instrução SQL.
	    
	    // Recupera os dados do objeto Editora recebido e tenta inserí-los no banco de dados.
		public boolean inserirEditora(Editora editora){
	        String instrucaoSQL;
	        ConfigDao cfgDao = new ConfigDao();

	        try {
	            if (cfgDao.ConectarBD()) { // Abre a conexão com o banco de dados.
	                conexao = cfgDao.ObterConexaoBD(); // Obtém os dados de conexão com o banco de dados.
	                instrucaoSQL = "INSERT INTO editora (Nome_Editora, IdEditora) VALUES ("
	                        + "'" + editora.getNome_Editora() + "','" 
	                        
	                        + editora.getIdEditora() + "')";
	                        
	                        
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
		
		// Recupera os Editoras cadastrados no banco de dados para que sejam carregados no JComboBox Editora.
		public ArrayList<Editora> recuperarEditorasBd(){
	        String instrucaoSQL;
	        ConfigDao cfgDao = new ConfigDao();
	        Editora c;
	        ArrayList<Editora> editoras = new ArrayList<>();

	        try {
	            if (cfgDao.ConectarBD()) { // Abre a conexão com o banco de dados.
	                conexao = cfgDao.ObterConexaoBD(); // Obtém os dados de conexão com o banco de dados.
	                instrucaoSQL = "SELECT IdEditora, Nome_Editora FROM editora";
	                comando = conexao.prepareStatement(instrucaoSQL);
	                
	                // Executa a instrução SQL e retorna os dados ao objeto ResultSet.
	                registros = comando.executeQuery(); 
	                
	                try {
	                    if (registros.next()) { // Se for retornado pelo menos um registro.
	                        registros.beforeFirst(); // Retorna o cursor para antes do 1º registro.
	                        while (registros.next()) { // Percorre os registros retornados.
	                            Integer idEditora = Integer.parseInt(registros.getString("IdEditora")); // Obtém o id armazenado no registro.
	                            String nome_Editora = registros.getString("Nome_Editora"); // Obtém a descrição armazenada no registro.

	                            // Atribui os dados do editora ao objeto Editora por meio dos métodos set e
	                            // adiciona este objeto ao ArrayList editoras.
	                            c = new Editora();
	                            c.setIdEditora(idEditora);
	                            c.setNome_Editora(nome_Editora);
	                            editoras.add(c);
	                        }
	                    }
	                } catch (SQLException erro) {
	                    erro.getMessage(); // Se houver exceção, o objeto editoras fica igual à null.
	                }
	                cfgDao.DesconectarBD(); // Fecha a conexão com o banco de dados.
	            }
	        } catch (Exception erro) {
	        	erro.getMessage(); // Se houver qualquer outra exceção, o objeto editoras fica igual à null.
	        }
	        return editoras; // Retorna o ArrayList de objetos Editora.
		}
		
		
		// Recupera os editoras cadastrados no banco de dados para que sejam carregados no JTable de editoras.
		public ArrayList<Editora> consultarEditoras(){
	        String instrucaoSQL;
	        ConfigDao cfgDao = new ConfigDao();
	        Editora f;
	        ArrayList<Editora> editoras = new ArrayList<>();

	        try {
	            if (cfgDao.ConectarBD()) { // Abre a conexão com o banco de dados.
	                conexao = cfgDao.ObterConexaoBD(); // Obtém os dados de conexão com o banco de dados.
	                instrucaoSQL = "SELECT * FROM editora";
	                comando = conexao.prepareStatement(instrucaoSQL);
	                
	                // Executa a instrução SQL e retorna os dados ao objeto ResultSet.
	                registros = comando.executeQuery(); 
	                
	                try {
	                    if (registros.next()) { // Se for retornado pelo menos um registro.
	                        registros.beforeFirst(); // Retorna o cursor para antes do 1º registro.
	                        while (registros.next()) { // Percorre os registros retornados.
	                            Integer idEditora = Integer.parseInt(registros.getString("IdEditora")); // Obtém o id do editora armazenado no registro.
	                            String nome_Editora = registros.getString("Nome_Editora"); // Obtém o titulo armazenado no registro.
      
	                            
	                            // Atribui os dados do editora ao objeto editora por meio dos métodos set e
	                            // adiciona este objeto ao ArrayList editoras.
	                            f = new Editora();
	                            f.setIdEditora(idEditora);
	                            f.setNome_Editora(nome_Editora);
	                            
	                            editoras.add(f);
	                        }
	                    }
	                } catch (SQLException erro) {
	                	System.out.println(erro.getMessage());
	                    erro.getMessage(); // Se houver exceção, o objeto editoras fica igual à null.
	                }
	                cfgDao.DesconectarBD(); // Fecha a conexão com o banco de dados.
	            }
	        } catch (Exception erro) {
	        	erro.getMessage(); // Se houver qualquer outra exceção, o objeto editoras fica igual à null.
	        	System.out.println(erro.getMessage());
	        }
	        return editoras;
		}
		
	    // Recupera os dados do objeto editora recebido e tenta alterá-los no banco de dados.
		public boolean alterarEditora(Editora editora){
	        String instrucaoSQL;
	        ConfigDao cfgDao = new ConfigDao();

	        try {
	            if (cfgDao.ConectarBD()) { // Abre a conexão com o banco de dados.
	                conexao = cfgDao.ObterConexaoBD(); // Obtém os dados de conexão com o banco de dados.      
	                
	                instrucaoSQL = "UPDATE editora SET  "
			                        + "Nome_Editora='" + editora.getNome_Editora() + "'," 
			                        + "idEditora='" + editora.getIdEditora()    
			                        + "' WHERE IdEditora='" + editora.getIdEditora() + "'"; 
	                comando = conexao.prepareStatement(instrucaoSQL);
	                comando.executeUpdate(); // Executa a instrução SQL.
	                cfgDao.DesconectarBD(); // Fecha a conexão com o banco de dados.
	                return true;
	            } else {
	                return false; // Em caso de erro na conexão com o banco de dados.
	            }
	        } catch (Exception erro) {
	        	System.out.print("UPDATE editora SET "
	                    + "Nome_Editora='" + editora.getNome_Editora() + "'," 
	                    + "idEditora='" + editora.getIdEditora()
	                    + "' WHERE IdEditora='" + editora.getIdEditora());
	            return false; // Caso ocorra qualquer tipo de exceção.
	        }
		}
		
	    // Recupera o código do editora recebido e tenta excluí-lo do banco de dados.
		public boolean excluirEditora(int idEditora){
	        String instrucaoSQL;
	        ConfigDao cfgDao = new ConfigDao();

	        try {
	            if (cfgDao.ConectarBD()) { // Abre a conexão com o banco de dados.
	                conexao = cfgDao.ObterConexaoBD(); // Obtém os dados de conexão com o banco de dados.                
	                instrucaoSQL = "DELETE FROM editora WHERE IdEditora=" + idEditora; 
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

