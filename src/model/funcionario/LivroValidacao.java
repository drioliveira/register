package model.funcionario;

import java.util.ArrayList;

public class LivroValidacao {
	private static ArrayList<String> erros; // ArrayList para armazenar as mensagens de erro.
	
	// Valida os dados informados conforme as regras abaixo.
	public static ArrayList<String> ValidaLivro(Livro livro){
		erros = new ArrayList<>();
		
		// Valida��o do campo Titulo.
		if (livro.getTitulo() != null) {
			if (livro.getTitulo().length() < 1 || livro.getTitulo().length() > 1000)
				erros.add("O Titulo deve ter mais de um caracter!");
		} else {
			erros.add("O Titulo n�o foi informado!");
		}
		
		// Valida��o do campo Pre�o.
		if (livro.getPreco() != null) {
			if (livro.getPreco() < 5 || livro.getPreco() > 10000)
				erros.add("O pre�o deve ser de R$ 5,00 � R$ 10.000,00!");
		} else
			erros.add("O pre�o n�o foi informado!");
		
		// Valida��o do campo Genero (para o caso de n�o existirem generos cadastrados).
		if (livro.getIdGenero() == null)
			erros.add("O Genero n�o foi informado!");
		
		if (livro.getIdEditora() == null)
			erros.add("O Editora n�o foi informado!");
		
		if (livro.getIdAutor() == null)
			erros.add("O autor n�o foi informado!");
		
		
		return erros; // Retorna o ArrayList contendo as mensagens de erro.
	}
}