	package model.autor;

	import java.util.ArrayList;

	public class AutorValidacao {
		private static ArrayList<String> erros; // ArrayList para armazenar as mensagens de erro.
		
		// Valida os dados informados conforme as regras abaixo.
		public static ArrayList<String> ValidaAutor(Autor autor){
			erros = new ArrayList<>();
			
			// Validação do campo Titulo.
			if (autor.getNome_Autor() != null) {
				if (autor.getNome_Autor().length() < 1 || autor.getNome_Autor().length() > 1000)
					erros.add("O nome deve ter mais de um caracter!");
			} else {
				erros.add("O nome não foi informado!");
			}
			
			if (autor.getNacionalidade() != null) {
				if (autor.getNacionalidade().length() < 1 || autor.getNacionalidade().length() > 1000)
					erros.add("A nacionalidade deve ter mais de um caracter!");
			} else {
				erros.add("A nacionalidade não foi informada!");
			}
			
			return erros; // Retorna o ArrayList contendo as mensagens de erro.
		}
	}
