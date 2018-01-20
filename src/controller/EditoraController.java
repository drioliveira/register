package controller;

import java.util.ArrayList;

import model.editora.Editora;
import model.editora.EditoraDao;
import model.editora.EditoraValidacao;


public class EditoraController {
	EditoraDao genDao = new EditoraDao();

	public ArrayList<String> Inserir(String nome_Editora) {
		Editora editora = new Editora();
		
		// Os m�todos set abaixo criam um objeto editora contendo os dados do funcion�rio informado.
		// Este objeto ser� enviado � classe DAO, que far� a inser��o de seus dados no banco de dados.
		if (!nome_Editora.equals(""))
			editora.setNome_Editora(nome_Editora);
		
				
		
		
		// Envia os dados do funcion�rio (informados no formul�rio) ao controller. 
		// O m�todo retorna ent�o um ArrayList contendo os erros de valida��o encontrados.
		ArrayList<String> erros = EditoraValidacao.ValidaEditora(editora);

		if (erros.size() == 0) { // Se nenhum erro de valida��o for encontrado.
			if (genDao.inserirEditora(editora)) 
				erros.add("ok"); // Se o funcion�rio foi inserido com sucesso no banco de dados.
			else 
				erros.add("erro"); // Se houve algum problema ao tentar inserir o funcion�rio no banco de dados.
		}
		// Retorna o ArrayList contendo:
		// - "ok" no 1� elemento; OU
		// - "erro" no 1� elemento; OU
		// - as mensagens de erro inclu�das pela classe editoraValidacao.
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
	
	
	
	// Retorna um ArrayList de objetos editora, contendo os dados dos funcion�rios cadastrados.
	public ArrayList<Editora> Consultar() {
		ArrayList<Editora> editoras = genDao.consultarEditoras();
		
		
		return editoras;
	}
	
	public ArrayList<String> Alterar(int idEditora, String nome_Editora) {
		Editora editora = new Editora();
		
		// Os m�todos set abaixo criam um objeto editora contendo os dados do funcion�rio alterado.
		// Este objeto ser� enviado � classe DAO, que far� a altera��o de seus dados no banco de dados.
		editora.setIdEditora(idEditora);
		
		if (!nome_Editora.equals(""))
			editora.setNome_Editora(nome_Editora);


		
		
		
		// Envia os dados do funcion�rio (informados no formul�rio) ao controller. 
		// O m�todo retorna ent�o um ArrayList contendo os erros de valida��o encontrados.
		ArrayList<String> erros = EditoraValidacao.ValidaEditora(editora);

		if (erros.size() == 0) { // Se nenhum erro de valida��o for encontrado.
			if (genDao.alterarEditora(editora)) 
				erros.add("ok"); // Se o funcion�rio foi alterado com sucesso no banco de dados.
			else 
				erros.add("erro"); // Se houve algum problema ao tentar alterar o funcion�rio no banco de dados.
		}
		// Retorna o ArrayList contendo:
		// - "ok" no 1� elemento; OU
		// - "erro" no 1� elemento; OU
		// - as mensagens de erro inclu�das pela classe editoraValidacao.
		return erros; 
	}
	
	public boolean Excluir(int idEditora) {
		if (genDao.excluirEditora(idEditora)) 
			return true; // Se o funcion�rio foi exclu�do com sucesso no banco de dados.
		else 
			return false; // Se houve algum problema ao tentar excluir o funcion�rio no banco de dados. 
	}
}