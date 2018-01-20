package model.editora;

public class Editora {


//Classe de domínio da entidade Editora.
//Juntas, as classes de domínio modelam a estrutura de negócio da aplicação.
//Possuem apenas atributos e seus respectivos métodos get e set, 
//usados para recuperação e atribuição de valores aos seus dados.

//DICA: Para criar automaticamente os métodos get e set para os atributos da classe, no editor 
//de código, clique com o botão direito do mouse >> Source >> Generate Getters and Setters.

// Classe de domínio da entidade Editora.
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