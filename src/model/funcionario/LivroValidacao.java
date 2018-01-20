package model.funcionario;

import java.util.ArrayList;

public class LivroValidacao {
	private static ArrayList<String> erros; // ArrayList para armazenar as mensagens de erro.
	
	// Valida os dados informados conforme as regras abaixo.
	public static ArrayList<String> ValidaLivro(Livro livro){
		erros = new ArrayList<>();
		
		// Validação do campo Titulo.
		if (livro.getTitulo() != null) {
			if (livro.getTitulo().length() < 1 || livro.getTitulo().length() > 1000)
				erros.add("O Titulo deve ter mais de um caracter!");
		} else {
			erros.add("O Titulo não foi informado!");
		}
		
		// Validação do campo Preço.
		if (livro.getPreco() != null) {
			if (livro.getPreco() < 5 || livro.getPreco() > 10000)
				erros.add("O preço deve ser de R$ 5,00 à R$ 10.000,00!");
		} else
			erros.add("O preço não foi informado!");
		
		// Validação do campo Genero (para o caso de não existirem generos cadastrados).
		if (livro.getIdGenero() == null)
			erros.add("O Genero não foi informado!");
		
		if (livro.getIdEditora() == null)
			erros.add("O Editora não foi informado!");
		
		if (livro.getIdAutor() == null)
			erros.add("O autor não foi informado!");
		
		
		return erros; // Retorna o ArrayList contendo as mensagens de erro.
	}
}