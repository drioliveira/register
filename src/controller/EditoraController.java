package controller;

import java.util.ArrayList;

import model.editora.Editora;
import model.editora.EditoraDao;
import model.editora.EditoraValidacao;


public class EditoraController {
	EditoraDao genDao = new EditoraDao();

	public ArrayList<String> Inserir(String nome_Editora) {
		Editora editora = new Editora();
		
		// Os métodos set abaixo criam um objeto editora contendo os dados do funcionário informado.
		// Este objeto será enviado à classe DAO, que fará a inserção de seus dados no banco de dados.
		if (!nome_Editora.equals(""))
			editora.setNome_Editora(nome_Editora);
		
				
		
		
		// Envia os dados do funcionário (informados no formulário) ao controller. 
		// O método retorna então um ArrayList contendo os erros de validação encontrados.
		ArrayList<String> erros = EditoraValidacao.ValidaEditora(editora);

		if (erros.size() == 0) { // Se nenhum erro de validação for encontrado.
			if (genDao.inserirEditora(editora)) 
				erros.add("ok"); // Se o funcionário foi inserido com sucesso no banco de dados.
			else 
				erros.add("erro"); // Se houve algum problema ao tentar inserir o funcionário no banco de dados.
		}
		// Retorna o ArrayList contendo:
		// - "ok" no 1º elemento; OU
		// - "erro" no 1º elemento; OU
		// - as mensagens de erro incluídas pela classe editoraValidacao.
		return erros; 
	}
	
	// Recupera os editoras cadastrados no banco de dados para que sejam carregados no JComboBox editora.
	public ArrayList<Editora> recuperarEditoras(){
		ArrayList<Editora> editoras = genDao.recuperarEditorasBd();		
		if (editoras != null) {
			System.out.println(editoras);
			return editoras;
		}
		else{
			System.out.println("nada");
			return null;
		}
	}
	
	
	
	// Retorna um ArrayList de objetos editora, contendo os dados dos funcionários cadastrados.
	public ArrayList<Editora> Consultar() {
		ArrayList<Editora> editoras = genDao.consultarEditoras();
		
		
		return editoras;
	}
	
	public ArrayList<String> Alterar(int idEditora, String nome_Editora) {
		Editora editora = new Editora();
		
		// Os métodos set abaixo criam um objeto editora contendo os dados do funcionário alterado.
		// Este objeto será enviado à classe DAO, que fará a alteração de seus dados no banco de dados.
		editora.setIdEditora(idEditora);
		
		if (!nome_Editora.equals(""))
			editora.setNome_Editora(nome_Editora);


		
		
		
		// Envia os dados do funcionário (informados no formulário) ao controller. 
		// O método retorna então um ArrayList contendo os erros de validação encontrados.
		ArrayList<String> erros = EditoraValidacao.ValidaEditora(editora);

		if (erros.size() == 0) { // Se nenhum erro de validação for encontrado.
			if (genDao.alterarEditora(editora)) 
				erros.add("ok"); // Se o funcionário foi alterado com sucesso no banco de dados.
			else 
				erros.add("erro"); // Se houve algum problema ao tentar alterar o funcionário no banco de dados.
		}
		// Retorna o ArrayList contendo:
		// - "ok" no 1º elemento; OU
		// - "erro" no 1º elemento; OU
		// - as mensagens de erro incluídas pela classe editoraValidacao.
		return erros; 
	}
	
	public boolean Excluir(int idEditora) {
		if (genDao.excluirEditora(idEditora)) 
			return true; // Se o funcionário foi excluído com sucesso no banco de dados.
		else 
			return false; // Se houve algum problema ao tentar excluir o funcionário no banco de dados. 
	}
}