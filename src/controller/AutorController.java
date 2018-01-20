	package controller;

	import java.util.ArrayList;

	import model.autor.Autor;
	import model.autor.AutorDao;
	import model.autor.AutorValidacao;


	public class AutorController {
		AutorDao genDao = new AutorDao();

		public ArrayList<String> Inserir(String nome_Autor, String nacionalidade) {
			Autor autor = new Autor();
			
			// Os m�todos set abaixo criam um objeto autor contendo os dados do funcion�rio informado.
			// Este objeto ser� enviado � classe DAO, que far� a inser��o de seus dados no banco de dados.
			if (!nome_Autor.equals(""))
				autor.setNome_Autor(nome_Autor);
				autor.setNacionalidade(nacionalidade);
			
			
			// Envia os dados do funcion�rio (informados no formul�rio) ao controller. 
			// O m�todo retorna ent�o um ArrayList contendo os erros de valida��o encontrados.
			ArrayList<String> erros = AutorValidacao.ValidaAutor(autor);

			if (erros.size() == 0) { // Se nenhum erro de valida��o for encontrado.
				if (genDao.inserirAutor(autor)) 
					erros.add("ok"); // Se o funcion�rio foi inserido com sucesso no banco de dados.
				else 
					erros.add("erro"); // Se houve algum problema ao tentar inserir o funcion�rio no banco de dados.
			}
			// Retorna o ArrayList contendo:
			// - "ok" no 1� elemento; OU
			// - "erro" no 1� elemento; OU
			// - as mensagens de erro inclu�das pela classe autorValidacao.
			return erros; 
		}
		
		// Recupera os autors cadastrados no banco de dados para que sejam carregados no JComboBox autor.
		public ArrayList<Autor> recuperarAutors(){
			ArrayList<Autor> autors = genDao.recuperarAutorsBd();		
			if (autors != null) {
				System.out.println(autors);
				return autors;
			}
			else{
				System.out.println("nada");
				return null;
			}
		}
		
		
		
		// Retorna um ArrayList de objetos autor, contendo os dados dos funcion�rios cadastrados.
		public ArrayList<Autor> Consultar() {
			ArrayList<Autor> autors = genDao.consultarAutors();
			
			
			return autors;
		}
		
		public ArrayList<String> Alterar(int idAutor, String nome_Autor, String nacionalidade) {
			Autor autor = new Autor();
			
			// Os m�todos set abaixo criam um objeto autor contendo os dados do funcion�rio alterado.
			// Este objeto ser� enviado � classe DAO, que far� a altera��o de seus dados no banco de dados.
			autor.setIdAutor(idAutor);
			
			if (!nome_Autor.equals(""))
				autor.setNome_Autor(nome_Autor);
			
			if (!nacionalidade.equals(""))
				autor.setNacionalidade(nacionalidade);


			
			
			
			// Envia os dados do funcion�rio (informados no formul�rio) ao controller. 
			// O m�todo retorna ent�o um ArrayList contendo os erros de valida��o encontrados.
			ArrayList<String> erros = AutorValidacao.ValidaAutor(autor);

			if (erros.size() == 0) { // Se nenhum erro de valida��o for encontrado.
				if (genDao.alterarAutor(autor)) 
					erros.add("ok"); // Se o funcion�rio foi alterado com sucesso no banco de dados.
				else 
					erros.add("erro"); // Se houve algum problema ao tentar alterar o funcion�rio no banco de dados.
			}
			// Retorna o ArrayList contendo:
			// - "ok" no 1� elemento; OU
			// - "erro" no 1� elemento; OU
			// - as mensagens de erro inclu�das pela classe autorValidacao.
			return erros; 
		}
		
		public boolean Excluir(int idAutor) {
			if (genDao.excluirAutor(idAutor)) 
				return true; // Se o funcion�rio foi exclu�do com sucesso no banco de dados.
			else 
				return false; // Se houve algum problema ao tentar excluir o funcion�rio no banco de dados. 
		}
	}
