package controller;

import java.util.ArrayList;

import model.editora.Editora;
import model.genero.Genero;
import model.funcionario.Livro;
import model.funcionario.LivroDao;
import model.funcionario.LivroValidacao;
import model.autor.Autor;

public class LivroController {
	LivroDao funcDao = new LivroDao();

	public ArrayList<String> Inserir(String titulo, String preco, String genero, String editora, String autor) {
		Livro livro = new Livro();
		
		// Os m�todos set abaixo criam um objeto livro contendo os dados do funcion�rio informado.
		// Este objeto ser� enviado � classe DAO, que far� a inser��o de seus dados no banco de dados.
		if (!titulo.equals(""))
			livro.setTitulo(titulo);


		if (!preco.equals(""))
			livro.setPreco(Double.parseDouble(preco));
		
				
		if (!genero.equals("")){ 
			// Extrai o id do genero selecionado no JComboBox Genero.
			// Por exemplo: Extrai o "3" da string "3-Analista de Sistemas".
			int id = Integer.parseInt(genero.substring(0, (genero.indexOf("-"))));
			livro.setIdGenero(id);
		}
		
		if (!editora.equals("")){ 
			int id = Integer.parseInt(editora.substring(0, (editora.indexOf("-"))));
			livro.setIdEditora(id);
		}
		
		if (!autor.equals("")){ 
			int id = Integer.parseInt(autor.substring(0, (autor.indexOf("-"))));
			livro.setIdAutor(id);
		}
		
		// Envia os dados do funcion�rio (informados no formul�rio) ao controller. 
		// O m�todo retorna ent�o um ArrayList contendo os erros de valida��o encontrados.
		ArrayList<String> erros = LivroValidacao.ValidaLivro(livro);

		if (erros.size() == 0) { // Se nenhum erro de valida��o for encontrado.
			if (funcDao.inserirLivro(livro)) 
				erros.add("ok"); // Se o funcion�rio foi inserido com sucesso no banco de dados.
			else 
				erros.add("erro"); // Se houve algum problema ao tentar inserir o funcion�rio no banco de dados.
		}
		// Retorna o ArrayList contendo:
		// - "ok" no 1� elemento; OU
		// - "erro" no 1� elemento; OU
		// - as mensagens de erro inclu�das pela classe livroValidacao.
		return erros; 
	}
	
	// Recupera os generos cadastrados no banco de dados para que sejam carregados no JComboBox genero.
	public ArrayList<Genero> recuperarGeneros(){
		ArrayList<Genero> generos = funcDao.recuperarGenerosBd();		
		if (generos != null)
			return generos;
		else
			return null;
	}
	
	public ArrayList<Editora> recuperarEditora(){
		ArrayList<Editora> editoras = funcDao.recuperarEditorasBd();		
		if (editoras != null)
			return editoras;
		else
			return null;
	}
	
	public ArrayList<Autor> recuperarAutor(){
		ArrayList<Autor> autores = funcDao.recuperarAutoresBd();		
		if (autores != null)
			return autores;
		else
			return null;
	}
	
	
	// Retorna um ArrayList de objetos livro, contendo os dados dos funcion�rios cadastrados.
	public ArrayList<Livro> Consultar() {
		ArrayList<Livro> livros = funcDao.consultarLivros();
		return livros;
	}
	
	public ArrayList<String> Alterar(int idLivro, String titulo, String preco, String genero, String editora, String autor) {
		Livro livro = new Livro();
		
		// Os m�todos set abaixo criam um objeto livro contendo os dados do funcion�rio alterado.
		// Este objeto ser� enviado � classe DAO, que far� a altera��o de seus dados no banco de dados.
		livro.setIdLivro(idLivro);
		
		if (!titulo.equals(""))
			livro.setTitulo(titulo);


		if (!preco.equals(""))
			livro.setPreco(Double.parseDouble(preco.replace(".", "").replace(",", ".")));
		
	
		
		if (!genero.equals("")){ 
			// Extrai o id do genero selecionado no JComboBox Genero.
			// Por exemplo: Extrai o "3" da string "3-Analista de Sistemas".
			int id = Integer.parseInt(genero.substring(0, genero.indexOf("-")));
			livro.setIdGenero(id);
		}
		
		if (!editora.equals("")){ 
			int id = Integer.parseInt(editora.substring(0, editora.indexOf("-")));
			livro.setIdEditora(id);
		}
		
		if (!autor.equals("")){ 
			int id = Integer.parseInt(autor.substring(0, autor.indexOf("-")));
			livro.setIdAutor(id);
		}
		
		// Envia os dados do funcion�rio (informados no formul�rio) ao controller. 
		// O m�todo retorna ent�o um ArrayList contendo os erros de valida��o encontrados.
		ArrayList<String> erros = LivroValidacao.ValidaLivro(livro);

		if (erros.size() == 0) { // Se nenhum erro de valida��o for encontrado.
			if (funcDao.alterarLivro(livro)) 
				erros.add("ok"); // Se o funcion�rio foi alterado com sucesso no banco de dados.
			else 
				erros.add("erro"); // Se houve algum problema ao tentar alterar o funcion�rio no banco de dados.
		}
		// Retorna o ArrayList contendo:
		// - "ok" no 1� elemento; OU
		// - "erro" no 1� elemento; OU
		// - as mensagens de erro inclu�das pela classe livroValidacao.
		return erros; 
	}
	
	public boolean Excluir(int idLivro) {
		if (funcDao.excluirLivro(idLivro)) 
			return true; // Se o funcion�rio foi exclu�do com sucesso no banco de dados.
		else 
			return false; // Se houve algum problema ao tentar excluir o funcion�rio no banco de dados. 
	}
}