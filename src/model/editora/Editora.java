package model.editora;

public class Editora {


//Classe de dom�nio da entidade Editora.
//Juntas, as classes de dom�nio modelam a estrutura de neg�cio da aplica��o.
//Possuem apenas atributos e seus respectivos m�todos get e set, 
//usados para recupera��o e atribui��o de valores aos seus dados.

//DICA: Para criar automaticamente os m�todos get e set para os atributos da classe, no editor 
//de c�digo, clique com o bot�o direito do mouse >> Source >> Generate Getters and Setters.

// Classe de dom�nio da entidade Editora.
	int idEditora;
	String nome_Editora;
	
	

	
	public int getIdEditora() {
		return idEditora;
	}
	public void setIdEditora(int idEditora) {
		this.idEditora = idEditora;
	}
	public String getNome_Editora() {
		return nome_Editora;
	}
	public void setNome_Editora(String nome_Editora) {
		this.nome_Editora = nome_Editora;
	}
	
}