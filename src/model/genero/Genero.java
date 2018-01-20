package model.genero;

// Classe de dom�nio da entidade genero.
// Juntas, as classes de dom�nio modelam a estrutura de neg�cio da aplica��o.
// Possuem apenas atributos e seus respectivos m�todos get e set, 
// usados para recupera��o e atribui��o de valores aos seus dados.

// DICA: Para criar automaticamente os m�todos get e set para os atributos da classe, no editor 
// de c�digo, clique com o bot�o direito do mouse >> Source >> Generate Getters and Setters.

public class Genero { // Classe de dom�nio da entidade Genero.
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