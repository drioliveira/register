
package controller;

import java.util.ArrayList;

import model.genero.Genero;
import model.genero.GeneroDao;
import model.genero.GeneroValidacao;


public class GeneroController {
	GeneroDao genDao = new GeneroDao();

	public ArrayList<String> Inserir(String descricao) {
		Genero genero = new Genero();
		
		// Os m�todos set abaixo criam um objeto genero contendo os dados do funcion�rio informado.
		// Este objeto ser� enviado � classe DAO, que far� a inser��o de seus dados no banco de dados.
		if (!descricao.equals(""))
			genero.setDescricao(descricao);
		
				
		
		
		// Envia os dados do funcion�rio (informados no formul�rio) ao controller. 
		// O m�todo retorna ent�o um ArrayList contendo os erros de valida��o encontrados.
		ArrayList<String> erros = GeneroValidacao.ValidaGenero(genero);

		if (erros.size() == 0) { // Se nenhum erro de valida��o for encontrado.
			if (genDao.inserirGenero(genero)) 
				erros.add("ok"); // Se o funcion�rio foi inserido com sucesso no banco de dados.
			else 
				erros.add("erro"); // Se houve algum problema ao tentar inserir o funcion�rio no banco de dados.
		}
		// Retorna o ArrayList contendo:
		// - "ok" no 1� elemento; OU
		// - "erro" no 1� elemento; OU
		// - as mensagens de erro inclu�das pela classe generoValidacao.
		return erros; 
	}
	
	// Recupera os generos cadastrados no banco de dados para que sejam carregados no JComboBox genero.
	public ArrayList<Genero> recuperarGeneros(){
		ArrayList<Genero> generos = genDao.recuperarGenerosBd();		
		if (generos != null) {
			System.out.println(generos);
			return generos;
		}
		else{
			System.out.println("nada");
			return null;
		}
	}
	
	
	
	// Retorna um ArrayList de objetos genero, contendo os dados dos funcion�rios cadastrados.
	public ArrayList<Genero> Consultar() {
		ArrayList<Genero> generos = genDao.consultarGeneros();
		
		
		return generos;
	}
	
	public ArrayList<String> Alterar(int idGenero, String descricao) {
		Genero genero = new Genero();
		
		// Os m�todos set abaixo criam um objeto genero contendo os dados do funcion�rio alterado.
		// Este objeto ser� enviado � classe DAO, que far� a altera��o de seus dados no banco de dados.
		genero.setIdGenero(idGenero);
		
		if (!descricao.equals(""))
			genero.setDescricao(descricao);


		
		
		
		// Envia os dados do funcion�rio (informados no formul�rio) ao controller. 
		// O m�todo retorna ent�o um ArrayList contendo os erros de valida��o encontrados.
		ArrayList<String> erros = GeneroValidacao.ValidaGenero(genero);

		if (erros.size() == 0) { // Se nenhum erro de valida��o for encontrado.
			if (genDao.alterarGenero(genero)) 
				erros.add("ok"); // Se o funcion�rio foi alterado com sucesso no banco de dados.
			else 
				erros.add("erro"); // Se houve algum problema ao tentar alterar o funcion�rio no banco de dados.
		}
		// Retorna o ArrayList contendo:
		// - "ok" no 1� elemento; OU
		// - "erro" no 1� elemento; OU
		// - as mensagens de erro inclu�das pela classe generoValidacao.
		return erros; 
	}
	
	public boolean Excluir(int idGenero) {
		if (genDao.excluirGenero(idGenero)) 
			return true; // Se o funcion�rio foi exclu�do com sucesso no banco de dados.
		else 
			return false; // Se houve algum problema ao tentar excluir o funcion�rio no banco de dados. 
	}
}