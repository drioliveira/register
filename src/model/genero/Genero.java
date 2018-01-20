package model.genero;

// Classe de domínio da entidade genero.
// Juntas, as classes de domínio modelam a estrutura de negócio da aplicação.
// Possuem apenas atributos e seus respectivos métodos get e set, 
// usados para recuperação e atribuição de valores aos seus dados.

// DICA: Para criar automaticamente os métodos get e set para os atributos da classe, no editor 
// de código, clique com o botão direito do mouse >> Source >> Generate Getters and Setters.

public class Genero { // Classe de domínio da entidade Genero.
	int idGenero;
	String descricao;
	
	
	public int getIdGenero() {
		return idGenero;
	}
	public void setIdGenero(int idGenero) {
		this.idGenero = idGenero;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}