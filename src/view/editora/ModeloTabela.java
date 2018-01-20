	package view.editora;


	import java.util.ArrayList;
	import javax.swing.table.AbstractTableModel;

	import controller.EditoraController;
	import model.editora.Editora;
	
	

	@SuppressWarnings("serial")
	public class ModeloTabela extends AbstractTableModel {
		// Array de nome_Editoras a serem exibidos no cabeçalho do JTable.
		private String[] colunas = { "Código", "Nome Editora"};
		private ArrayList<Editora> dados; // Lista que conterá os dados a serem exibidos no corpo do JTable.
		private EditoraController fc;

		public ModeloTabela() { // Construtor.
			fc = new EditoraController();

			// Obtém um ArrayList de objetos Editora, contendo os dados dos editoras cadastrados.
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
	    public Object getValueAt(int linha, int coluna) { // Obtém os dados do ArrayList de editoras e os retorna para popular o JTable.
	        Editora f = dados.get(linha); // Recupera o objeto Editora presente na posição "linha" do ArrayList.
	        Object valor = null;
			
	        switch(coluna){
	            case 0: // Coluna idEditora
	            	valor = f.getIdEditora(); 
	            	break;
	            case 1: // Coluna Nome_Editora 
	            	valor = f.getNome_Editora(); 
	            	break;
	           
	            
	        }
	        return valor;
	    }

	    // Método da classe abstrata AbstractTableModel (implementação opcional).
		@Override
		public String getColumnName(int coluna) { // Retorna o Nome_Editora da coluna recebida como argumento.
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
		// Este método será usado para atualizar o JTable após a alteração de Editoras. 
		public void setValueAt(Object valor, int linha, int coluna) { 
			// Ao realizar esta atribuição, o objeto Editora existente no ArrayList "dados"
			// e o objeto "Editora" declarado aqui ficam apontando para o mesmo endereço de memória,
			// Logo, ao atribuir valores ao objeto "Editora" por meio dos métodos set, estes valores
			// também são atribuídos ao objeto Editora do ArrayList "dados".
			Editora editora = dados.get(linha); 

			switch (coluna) { 
			case 1: // Coluna Nome_Editora 
				editora.setNome_Editora(valor.toString()); 
				break;
			
			}
			// Informa todos os processadores de eventos que um valor do ArrayList "dados" foi alterado
			// e que o valor da célula correspondente no JTable deve ser atualizado.
			fireTableCellUpdated(linha, coluna); 
		}
		
		// Remove o Editora excluído do ArrayList de editoras e atualiza o JTable.
	    public void removerEditora(int linha) {
	        dados.remove(linha);
	        int linhaInicial = linha;
	        int linhaFinal = linha;
			// Informa todos os processadores de eventos que um objeto do ArrayList "dados" foi excluído
			// e que a linha correspondente no JTable deve ser removida.
	        // As linhas inicial e final são as mesmas, porque somente um editora 
	        // poderá ser selecionado para exclusão de cada vez.
	        fireTableRowsDeleted(linhaInicial, linhaFinal);
	    }
	}
