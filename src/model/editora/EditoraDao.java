
	package model.editora;
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;

	import model.ConfigDao;
	import model.editora.Editora;
	

	public class EditoraDao {
	    private Connection conexao; // Objeto que recebe os dados de conex�o com o banco de dados.
	    private PreparedStatement comando; // Objeto usado para executar a instru��o SQL.
	    private ResultSet registros; // Objeto que recebe os dados retornados pela instru��o SQL.
	    
	    // Recupera os dados do objeto Editora recebido e tenta inser�-los no banco de dados.
		public boolean inserirEditora(Editora editora){
	        String instrucaoSQL;
	        ConfigDao cfgDao = new ConfigDao();

	        try {
	            if (cfgDao.ConectarBD()) { // Abre a conex�o com o banco de dados.
	                conexao = cfgDao.ObterConexaoBD(); // Obt�m os dados de conex�o com o banco de dados.
	                instrucaoSQL = "INSERT INTO editora (Nome_Editora, IdEditora) VALUES ("
	                        + "'" + editora.getNome_Editora() + "','" 
	                        
	                        + editora.getIdEditora() + "')";
	                        
	                        
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
		
		// Recupera os Editoras cadastrados no banco de dados para que sejam carregados no JComboBox Editora.
		public ArrayList<Editora> recuperarEditorasBd(){
	        String instrucaoSQL;
	        ConfigDao cfgDao = new ConfigDao();
	        Editora c;
	        ArrayList<Editora> editoras = new ArrayList<>();

	        try {
	            if (cfgDao.ConectarBD()) { // Abre a conex�o com o banco de dados.
	                conexao = cfgDao.ObterConexaoBD(); // Obt�m os dados de conex�o com o banco de dados.
	                instrucaoSQL = "SELECT IdEditora, Nome_Editora FROM editora";
	                comando = conexao.prepareStatement(instrucaoSQL);
	                
	                // Executa a instru��o SQL e retorna os dados ao objeto ResultSet.
	                registros = comando.executeQuery(); 
	                
	                try {
	                    if (registros.next()) { // Se for retornado pelo menos um registro.
	                        registros.beforeFirst(); // Retorna o cursor para antes do 1� registro.
	                        while (registros.next()) { // Percorre os registros retornados.
	                            Integer idEditora = Integer.parseInt(registros.getString("IdEditora")); // Obt�m o id armazenado no registro.
	                            String nome_Editora = registros.getString("Nome_Editora"); // Obt�m a descri��o armazenada no registro.

	                            // Atribui os dados do editora ao objeto Editora por meio dos m�todos set e
	                            // adiciona este objeto ao ArrayList editoras.
	                            c = new Editora();
	                            c.setIdEditora(idEditora);
	                            c.setNome_Editora(nome_Editora);
	                            editoras.add(c);
	                        }
	                    }
	                } catch (SQLException erro) {
	                    erro.getMessage(); // Se houver exce��o, o objeto editoras fica igual � null.
	                }
	                cfgDao.DesconectarBD(); // Fecha a conex�o com o banco de dados.
	            }
	        } catch (Exception erro) {
	        	erro.getMessage(); // Se houver qualquer outra exce��o, o objeto editoras fica igual � null.
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
	            if (cfgDao.ConectarBD()) { // Abre a conex�o com o banco de dados.
	                conexao = cfgDao.ObterConexaoBD(); // Obt�m os dados de conex�o com o banco de dados.
	                instrucaoSQL = "SELECT * FROM editora";
	                comando = conexao.prepareStatement(instrucaoSQL);
	                
	                // Executa a instru��o SQL e retorna os dados ao objeto ResultSet.
	                registros = comando.executeQuery(); 
	                
	                try {
	                    if (registros.next()) { // Se for retornado pelo menos um registro.
	                        registros.beforeFirst(); // Retorna o cursor para antes do 1� registro.
	                        while (registros.next()) { // Percorre os registros retornados.
	                            Integer idEditora = Integer.parseInt(registros.getString("IdEditora")); // Obt�m o id do editora armazenado no registro.
	                            String nome_Editora = registros.getString("Nome_Editora"); // Obt�m o titulo armazenado no registro.
      
	                            
	                            // Atribui os dados do editora ao objeto editora por meio dos m�todos set e
	                            // adiciona este objeto ao ArrayList editoras.
	                            f = new Editora();
	                            f.setIdEditora(idEditora);
	                            f.setNome_Editora(nome_Editora);
	                            
	                            editoras.add(f);
	                        }
	                    }
	                } catch (SQLException erro) {
	                	System.out.println(erro.getMessage());
	                    erro.getMessage(); // Se houver exce��o, o objeto editoras fica igual � null.
	                }
	                cfgDao.DesconectarBD(); // Fecha a conex�o com o banco de dados.
	            }
	        } catch (Exception erro) {
	        	erro.getMessage(); // Se houver qualquer outra exce��o, o objeto editoras fica igual � null.
	        	System.out.println(erro.getMessage());
	        }
	        return editoras;
		}
		
	    // Recupera os dados do objeto editora recebido e tenta alter�-los no banco de dados.
		public boolean alterarEditora(Editora editora){
	        String instrucaoSQL;
	        ConfigDao cfgDao = new ConfigDao();

	        try {
	            if (cfgDao.ConectarBD()) { // Abre a conex�o com o banco de dados.
	                conexao = cfgDao.ObterConexaoBD(); // Obt�m os dados de conex�o com o banco de dados.      
	                
	                instrucaoSQL = "UPDATE editora SET  "
			                        + "Nome_Editora='" + editora.getNome_Editora() + "'," 
			                        + "idEditora='" + editora.getIdEditora()    
			                        + "' WHERE IdEditora='" + editora.getIdEditora() + "'"; 
	                comando = conexao.prepareStatement(instrucaoSQL);
	                comando.executeUpdate(); // Executa a instru��o SQL.
	                cfgDao.DesconectarBD(); // Fecha a conex�o com o banco de dados.
	                return true;
	            } else {
	                return false; // Em caso de erro na conex�o com o banco de dados.
	            }
	        } catch (Exception erro) {
	        	System.out.print("UPDATE editora SET "
	                    + "Nome_Editora='" + editora.getNome_Editora() + "'," 
	                    + "idEditora='" + editora.getIdEditora()
	                    + "' WHERE IdEditora='" + editora.getIdEditora());
	            return false; // Caso ocorra qualquer tipo de exce��o.
	        }
		}
		
	    // Recupera o c�digo do editora recebido e tenta exclu�-lo do banco de dados.
		public boolean excluirEditora(int idEditora){
	        String instrucaoSQL;
	        ConfigDao cfgDao = new ConfigDao();

	        try {
	            if (cfgDao.ConectarBD()) { // Abre a conex�o com o banco de dados.
	                conexao = cfgDao.ObterConexaoBD(); // Obt�m os dados de conex�o com o banco de dados.                
	                instrucaoSQL = "DELETE FROM editora WHERE IdEditora=" + idEditora; 
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

