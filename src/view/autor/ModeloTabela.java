
	package view.autor;


	import java.util.ArrayList;
	import javax.swing.table.AbstractTableModel;

	import controller.AutorController;
	import model.autor.Autor;
	
	

	@SuppressWarnings("serial")
	public class ModeloTabela extends AbstractTableModel {
		// Array de Nome_Autors a serem exibidos no cabe�alho do JTable.
		private String[] colunas = { "C�digo", "Nome Autor", "Nacionalidade"};
		private ArrayList<Autor> dados; // Lista que conter� os dados a serem exibidos no corpo do JTable.
		private AutorController fc;

		public ModeloTabela() { // Construtor.
			fc = new AutorController();

			// Obt�m um ArrayList de objetos Autor, contendo os dados dos Autors cadastrados.
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
	    public Object getValueAt(int linha, int coluna) { // Obt�m os dados do ArrayList de Autors e os retorna para popular o JTable.
	        Autor f = dados.get(linha); // Recupera o objeto Autor presente na posi��o "linha" do ArrayList.
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

	    // M�todo da classe abstrata AbstractTableModel (implementa��o opcional).
		@Override
		public String getColumnName(int coluna) { // Retorna o Nome_Autor da coluna recebida como argumento.
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
		// Este m�todo ser� usado para atualizar o JTable ap�s a altera��o de Autors. 
		public void setValueAt(Object valor, int linha, int coluna) { 
			// Ao realizar esta atribui��o, o objeto Autor existente no ArrayList "dados"
			// e o objeto "Autor" declarado aqui ficam apontando para o mesmo endere�o de mem�ria,
			// Logo, ao atribuir valores ao objeto "Autor" por meio dos m�todos set, estes valores
			// tamb�m s�o atribu�dos ao objeto Autor do ArrayList "dados".
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
			// e que o valor da c�lula correspondente no JTable deve ser atualizado.
			fireTableCellUpdated(linha, coluna); 
		}
		
		// Remove o Autor exclu�do do ArrayList de Autors e atualiza o JTable.
	    public void removerAutor(int linha) {
	        dados.remove(linha);
	        int linhaInicial = linha;
	        int linhaFinal = linha;
			// Informa todos os processadores de eventos que um objeto do ArrayList "dados" foi exclu�do
			// e que a linha correspondente no JTable deve ser removida.
	        // As linhas inicial e final s�o as mesmas, porque somente um Autor 
	        // poder� ser selecionado para exclus�o de cada vez.
	        fireTableRowsDeleted(linhaInicial, linhaFinal);
	    }
	}
