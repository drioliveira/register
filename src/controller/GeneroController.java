
package controller;

import java.util.ArrayList;

import model.genero.Genero;
import model.genero.GeneroDao;
import model.genero.GeneroValidacao;


public class GeneroController {
	GeneroDao genDao = new GeneroDao();

	public ArrayList<String> Inserir(String descricao) {
		Genero genero = new Genero();
		
		// Os métodos set abaixo criam um objeto genero contendo os dados do funcionário informado.
		// Este objeto será enviado à classe DAO, que fará a inserção de seus dados no banco de dados.
		if (!descricao.equals(""))
			genero.setDescricao(descricao);
		
				
		
		
		// Envia os dados do funcionário (informados no formulário) ao controller. 
		// O método retorna então um ArrayList contendo os erros de validação encontrados.
		ArrayList<String> erros = GeneroValidacao.ValidaGenero(genero);

		if (erros.size() == 0) { // Se nenhum erro de validação for encontrado.
			if (genDao.inserirGenero(genero)) 
				erros.add("ok"); // Se o funcionário foi inserido com sucesso no banco de dados.
			else 
				erros.add("erro"); // Se houve algum problema ao tentar inserir o funcionário no banco de dados.
		}
		// Retorna o ArrayList contendo:
		// - "ok" no 1º elemento; OU
		// - "erro" no 1º elemento; OU
		// - as mensagens de erro incluídas pela classe generoValidacao.
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
	
	
	
	// Retorna um ArrayList de objetos genero, contendo os dados dos funcionários cadastrados.
	public ArrayList<Genero> Consultar() {
		ArrayList<Genero> generos = genDao.consultarGeneros();
		
		
		return generos;
	}
	
	public ArrayList<String> Alterar(int idGenero, String descricao) {
		Genero genero = new Genero();
		
		// Os métodos set abaixo criam um objeto genero contendo os dados do funcionário alterado.
		// Este objeto será enviado à classe DAO, que fará a alteração de seus dados no banco de dados.
		genero.setIdGenero(idGenero);
		
		if (!descricao.equals(""))
			genero.setDescricao(descricao);


		
		
		
		// Envia os dados do funcionário (informados no formulário) ao controller. 
		// O método retorna então um ArrayList contendo os erros de validação encontrados.
		ArrayList<String> erros = GeneroValidacao.ValidaGenero(genero);

		if (erros.size() == 0) { // Se nenhum erro de validação for encontrado.
			if (genDao.alterarGenero(genero)) 
				erros.add("ok"); // Se o funcionário foi alterado com sucesso no banco de dados.
			else 
				erros.add("erro"); // Se houve algum problema ao tentar alterar o funcionário no banco de dados.
		}
		// Retorna o ArrayList contendo:
		// - "ok" no 1º elemento; OU
		// - "erro" no 1º elemento; OU
		// - as mensagens de erro incluídas pela classe generoValidacao.
		return erros; 
	}
	
	public boolean Excluir(int idGenero) {
		if (genDao.excluirGenero(idGenero)) 
			return true; // Se o funcionário foi excluído com sucesso no banco de dados.
		else 
			return false; // Se houve algum problema ao tentar excluir o funcionário no banco de dados. 
	}
}