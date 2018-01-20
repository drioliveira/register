
	package view.autor;


	import java.util.ArrayList;
	import javax.swing.table.AbstractTableModel;

	import controller.AutorController;
	import model.autor.Autor;
	
	

	@SuppressWarnings("serial")
	public class ModeloTabela extends AbstractTableModel {
		// Array de Nome_Autors a serem exibidos no cabeçalho do JTable.
		private String[] colunas = { "Código", "Nome Autor", "Nacionalidade"};
		private ArrayList<Autor> dados; // Lista que conterá os dados a serem exibidos no corpo do JTable.
		private AutorController fc;

		public ModeloTabela() { // Construtor.
			fc = new AutorController();

			// Obtém um ArrayList de objetos Autor, contendo os dados dos Autors cadastrados.
			dados = fc.Consultar();
		}

		// Método da classe abstrata AbstractTableModel (implementação obrigatória).
		@Override
		public int getRowCount() { // Retorna a quantidade de linhas do modelo da tabela.
			return dados.size();
		}

		// Método da classe abstrata AbstractTableModel (implementação obrigatória).
		@Override
		public int getColumnCount() { // Retorna a quantidade de colunas do modelo da tabela.
			return colunas.length;
		}

		// Método da classe abstrata AbstractTableModel (implementação obrigatória).
		@Override
	    public Object getValueAt(int linha, int coluna) { // Obtém os dados do ArrayList de Autors e os retorna para popular o JTable.
	        Autor f = dados.get(linha); // Recupera o objeto Autor presente na posição "linha" do ArrayList.
	        Object valor = null;
			
	        switch(coluna){
	            case 0: // Coluna idAutor
	            	valor = f.getIdAutor(); 
	            	break;
	            case 1: // Coluna Nome_Autor 
	            	valor = f.getNome_Autor(); 
	            	break;
	            case 2: // Coluna Nome_Autor 
	            	valor = f.getNacionalidade(); 
	            	break;
	           
	            
	        }
	        return valor;
	    }

	    // Método da classe abstrata AbstractTableModel (implementação opcional).
		@Override
		public String getColumnName(int coluna) { // Retorna o Nome_Autor da coluna recebida como argumento.
			return colunas[coluna];
		}

		// Método da classe abstrata AbstractTableModel (implementação opcional).
		@Override
		public Class<?> getColumnClass(int coluna) { // Retorna a classe da coluna recebida como argumento.
			return getValueAt(0, coluna).getClass();
		}
		
		// Método da classe abstrata AbstractTableModel (implementação opcional).
		@Override 
		public boolean isCellEditable(int linha, int coluna) { // Bloqueia para edição a célula recebida como argumento. 
			return false;
		}
		
		// Método da classe abstrata AbstractTableModel (implementação opcional).
		@Override
		// Este método será usado para atualizar o JTable após a alteração de Autors. 
		public void setValueAt(Object valor, int linha, int coluna) { 
			// Ao realizar esta atribuição, o objeto Autor existente no ArrayList "dados"
			// e o objeto "Autor" declarado aqui ficam apontando para o mesmo endereço de memória,
			// Logo, ao atribuir valores ao objeto "Autor" por meio dos métodos set, estes valores
			// também são atribuídos ao objeto Autor do ArrayList "dados".
			Autor autor = dados.get(linha); 

			switch (coluna) { 
			case 1: // Coluna Nome_Autor 
				autor.setNome_Autor(valor.toString()); 
				break;
			case 2: // Coluna Nome_Autor 
				autor.setNacionalidade(valor.toString()); 
				break;
			
			}
			// Informa todos os processadores de eventos que um valor do ArrayList "dados" foi alterado
			// e que o valor da célula correspondente no JTable deve ser atualizado.
			fireTableCellUpdated(linha, coluna); 
		}
		
		// Remove o Autor excluído do ArrayList de Autors e atualiza o JTable.
	    public void removerAutor(int linha) {
	        dados.remove(linha);
	        int linhaInicial = linha;
	        int linhaFinal = linha;
			// Informa todos os processadores de eventos que um objeto do ArrayList "dados" foi excluído
			// e que a linha correspondente no JTable deve ser removida.
	        // As linhas inicial e final são as mesmas, porque somente um Autor 
	        // poderá ser selecionado para exclusão de cada vez.
	        fireTableRowsDeleted(linhaInicial, linhaFinal);
	    }
	}
