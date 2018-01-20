	package view.editora;


	import java.util.ArrayList;
	import javax.swing.table.AbstractTableModel;

	import controller.EditoraController;
	import model.editora.Editora;
	
	

	@SuppressWarnings("serial")
	public class ModeloTabela extends AbstractTableModel {
		// Array de nome_Editoras a serem exibidos no cabe�alho do JTable.
		private String[] colunas = { "C�digo", "Nome Editora"};
		private ArrayList<Editora> dados; // Lista que conter� os dados a serem exibidos no corpo do JTable.
		private EditoraController fc;

		public ModeloTabela() { // Construtor.
			fc = new EditoraController();

			// Obt�m um ArrayList de objetos Editora, contendo os dados dos editoras cadastrados.
			dados = fc.Consultar();
		}

		// M�todo da classe abstrata AbstractTableModel (implementa��o obrigat�ria).
		@Override
		public int getRowCount() { // Retorna a quantidade de linhas do modelo da tabela.
			return dados.size();
		}

		// M�todo da classe abstrata AbstractTableModel (implementa��o obrigat�ria).
		@Override
		public int getColumnCount() { // Retorna a quantidade de colunas do modelo da tabela.
			return colunas.length;
		}

		// M�todo da classe abstrata AbstractTableModel (implementa��o obrigat�ria).
		@Override
	    public Object getValueAt(int linha, int coluna) { // Obt�m os dados do ArrayList de editoras e os retorna para popular o JTable.
	        Editora f = dados.get(linha); // Recupera o objeto Editora presente na posi��o "linha" do ArrayList.
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

	    // M�todo da classe abstrata AbstractTableModel (implementa��o opcional).
		@Override
		public String getColumnName(int coluna) { // Retorna o Nome_Editora da coluna recebida como argumento.
			return colunas[coluna];
		}

		// M�todo da classe abstrata AbstractTableModel (implementa��o opcional).
		@Override
		public Class<?> getColumnClass(int coluna) { // Retorna a classe da coluna recebida como argumento.
			return getValueAt(0, coluna).getClass();
		}
		
		// M�todo da classe abstrata AbstractTableModel (implementa��o opcional).
		@Override 
		public boolean isCellEditable(int linha, int coluna) { // Bloqueia para edi��o a c�lula recebida como argumento. 
			return false;
		}
		
		// M�todo da classe abstrata AbstractTableModel (implementa��o opcional).
		@Override
		// Este m�todo ser� usado para atualizar o JTable ap�s a altera��o de Editoras. 
		public void setValueAt(Object valor, int linha, int coluna) { 
			// Ao realizar esta atribui��o, o objeto Editora existente no ArrayList "dados"
			// e o objeto "Editora" declarado aqui ficam apontando para o mesmo endere�o de mem�ria,
			// Logo, ao atribuir valores ao objeto "Editora" por meio dos m�todos set, estes valores
			// tamb�m s�o atribu�dos ao objeto Editora do ArrayList "dados".
			Editora editora = dados.get(linha); 

			switch (coluna) { 
			case 1: // Coluna Nome_Editora 
				editora.setNome_Editora(valor.toString()); 
				break;
			
			}
			// Informa todos os processadores de eventos que um valor do ArrayList "dados" foi alterado
			// e que o valor da c�lula correspondente no JTable deve ser atualizado.
			fireTableCellUpdated(linha, coluna); 
		}
		
		// Remove o Editora exclu�do do ArrayList de editoras e atualiza o JTable.
	    public void removerEditora(int linha) {
	        dados.remove(linha);
	        int linhaInicial = linha;
	        int linhaFinal = linha;
			// Informa todos os processadores de eventos que um objeto do ArrayList "dados" foi exclu�do
			// e que a linha correspondente no JTable deve ser removida.
	        // As linhas inicial e final s�o as mesmas, porque somente um editora 
	        // poder� ser selecionado para exclus�o de cada vez.
	        fireTableRowsDeleted(linhaInicial, linhaFinal);
	    }
	}
