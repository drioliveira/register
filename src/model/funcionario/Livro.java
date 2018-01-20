package model.funcionario;

// Classe de domínio da entidade Livro.
// Juntas, as classes de domínio modelam a estrutura de negócio da aplicação.
// Possuem apenas atributos e seus respectivos métodos get e set, 
// usados para recuperação e atribuição de valores aos seus dados.

// DICA: Para criar automaticamente os métodos get e set para os atributos da classe, no editor 
// de código, clique com o botão direito do mouse >> Source >> Generate Getters and Setters.

public class Livro {
	int idLivro;
	String titulo;
	Double preco;
	Integer idGenero;
	Integer idEditora;
	Integer idAutor;
	
	public Integer getIdAutor() {
		return idAutor;
	}
	public void setIdAutor(Integer idAutor) {
		this.idAutor = idAutor;
	}
	public Integer getIdEditora() {
		return idEditora;
	}
	public void setIdEditora(Integer idEditora) {
		this.idEditora = idEditora;
	}
	public int getIdLivro() {
		return idLivro;
	}
	public void setIdLivro(int idLivro) {
		this.idLivro = idLivro;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	public Integer getIdGenero() {
		return idGenero;
	}
	public void setIdGenero(Integer idGenero) {
		this.idGenero = idGenero;
	}
}