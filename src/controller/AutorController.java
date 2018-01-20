	package controller;

	import java.util.ArrayList;

	import model.autor.Autor;
	import model.autor.AutorDao;
	import model.autor.AutorValidacao;


	public class AutorController {
		AutorDao genDao = new AutorDao();

		public ArrayList<String> Inserir(String nome_Autor, String nacionalidade) {
			Autor autor = new Autor();
			
			// Os métodos set abaixo criam um objeto autor contendo os dados do funcionário informado.
			// Este objeto será enviado à classe DAO, que fará a inserção de seus dados no banco de dados.
			if (!nome_Autor.equals(""))
				autor.setNome_Autor(nome_Autor);
				autor.setNacionalidade(nacionalidade);
			
			
			// Envia os dados do funcionário (informados no formulário) ao controller. 
			// O método retorna então um ArrayList contendo os erros de validação encontrados.
			ArrayList<String> erros = AutorValidacao.ValidaAutor(autor);

			if (erros.size() == 0) { // Se nenhum erro de validação for encontrado.
				if (genDao.inserirAutor(autor)) 
					erros.add("ok"); // Se o funcionário foi inserido com sucesso no banco de dados.
				else 
					erros.add("erro"); // Se houve algum problema ao tentar inserir o funcionário no banco de dados.
			}
			// Retorna o ArrayList contendo:
			// - "ok" no 1º elemento; OU
			// - "erro" no 1º elemento; OU
			// - as mensagens de erro incluídas pela classe autorValidacao.
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
		
		
		
		// Retorna um ArrayList de objetos autor, contendo os dados dos funcionários cadastrados.
		public ArrayList<Autor> Consultar() {
			ArrayList<Autor> autors = genDao.consultarAutors();
			
			
			return autors;
		}
		
		public ArrayList<String> Alterar(int idAutor, String nome_Autor, String nacionalidade) {
			Autor autor = new Autor();
			
			// Os métodos set abaixo criam um objeto autor contendo os dados do funcionário alterado.
			// Este objeto será enviado à classe DAO, que fará a alteração de seus dados no banco de dados.
			autor.setIdAutor(idAutor);
			
			if (!nome_Autor.equals(""))
				autor.setNome_Autor(nome_Autor);
			
			if (!nacionalidade.equals(""))
				autor.setNacionalidade(nacionalidade);


			
			
			
			// Envia os dados do funcionário (informados no formulário) ao controller. 
			// O método retorna então um ArrayList contendo os erros de validação encontrados.
			ArrayList<String> erros = AutorValidacao.ValidaAutor(autor);

			if (erros.size() == 0) { // Se nenhum erro de validação for encontrado.
				if (genDao.alterarAutor(autor)) 
					erros.add("ok"); // Se o funcionário foi alterado com sucesso no banco de dados.
				else 
					erros.add("erro"); // Se houve algum problema ao tentar alterar o funcionário no banco de dados.
			}
			// Retorna o ArrayList contendo:
			// - "ok" no 1º elemento; OU
			// - "erro" no 1º elemento; OU
			// - as mensagens de erro incluídas pela classe autorValidacao.
			return erros; 
		}
		
		public boolean Excluir(int idAutor) {
			if (genDao.excluirAutor(idAutor)) 
				return true; // Se o funcionário foi excluído com sucesso no banco de dados.
			else 
				return false; // Se houve algum problema ao tentar excluir o funcionário no banco de dados. 
		}
	}
