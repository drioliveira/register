
	package model.editora;

	import java.util.ArrayList;

	public class EditoraValidacao {
		private static ArrayList<String> erros; // ArrayList para armazenar as mensagens de erro.
		
		// Valida os dados informados conforme as regras abaixo.
		public static ArrayList<String> ValidaEditora(Editora editora){
			erros = new ArrayList<>();
			
			// Validação do campo Titulo.
			if (editora.getNome_Editora() != null) {
				if (editora.getNome_Editora().length() < 1 || editora.getNome_Editora().length() > 1000)
					erros.add("A descrição deve ter mais de um caracter!");
			} else {
				erros.add("A descrição não foi informada!");
			}
			
			
			
			return erros; // Retorna o ArrayList contendo as mensagens de erro.
		}
	}
