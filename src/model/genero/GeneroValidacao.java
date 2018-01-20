package model.genero;

import java.util.ArrayList;

public class GeneroValidacao {
	private static ArrayList<String> erros; // ArrayList para armazenar as mensagens de erro.
	
	// Valida os dados informados conforme as regras abaixo.
	public static ArrayList<String> ValidaGenero(Genero genero){
		erros = new ArrayList<>();
		
		// Valida��o do campo Titulo.
		if (genero.getDescricao() != null) {
			if (genero.getDescricao().length() < 1 || genero.getDescricao().length() > 1000)
				erros.add("A descri��o deve ter mais de um caracter!");
		} else {
			erros.add("A descri��o n�o foi informada!");
		}
		
		
		
		return erros; // Retorna o ArrayList contendo as mensagens de erro.
	}
}