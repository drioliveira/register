package view.funcionario;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

import controller.LivroController;

@SuppressWarnings("serial")
public class LivroConsulta extends JDialog {
	private JLabel lbTitulo;
	private JTable tbLivro; // Tabela que exibir� os livros cadastrados no banco de dados.
	private ModeloTabela mtTabela; // Modelo que definir� a estrutura da tabela.
	private JScrollPane spTabela; // Barra de rolagem que ser� vinculada � tabela.
	private JButton btAlterar;
	private JButton btExcluir;
	private Container cp; // Container para organizar os componentes na janela.	
	private LivroController fc;

	public LivroConsulta() { // Construtor.
		// Instancia��o e configura��o dos componentes de interface.
		setTitle("Consulta de livros"); // T�tulo da janela.
		setSize(700, 320); // Tamanho da janela em pixels.
		setLocationRelativeTo(null); // Centraliza a janela na tela.
		setModal(true); // Torna a janela "modal" (janela que n�o permite acesso a outras janelas abertas).
		
		lbTitulo = new JLabel("Consulta de livros");
		lbTitulo.setFont(new Font("Arial", Font.BOLD, 19)); // Ajusta a fonte do JLabel.
		
		// Chama o construtor da classe ModeloTabela que define a estrutura e carrega os dados no JTable.
		mtTabela = new ModeloTabela(); 	
		tbLivro = new JTable(mtTabela); // Inclui o modelo da tabela no JTable.
		spTabela = new JScrollPane(tbLivro); // Vincula a barra de rolagem ao JTable.
		
		tbLivro.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Desabilita o dimensionamento autom�tico das colunas.
		// Configura a largura de cada coluna do JTable (em pixels).
		tbLivro.getColumnModel().getColumn(0).setPreferredWidth(50);
		tbLivro.getColumnModel().getColumn(1).setPreferredWidth(150);
		tbLivro.getColumnModel().getColumn(2).setPreferredWidth(80);
		tbLivro.getColumnModel().getColumn(3).setPreferredWidth(100);
		tbLivro.getColumnModel().getColumn(4).setPreferredWidth(100);
		tbLivro.getColumnModel().getColumn(5).setPreferredWidth(150);
		
		// Configura a fonte do cabe�alho do JTable.
		tbLivro.getTableHeader().setFont(new Font(null, Font.BOLD, 12));
		
		// Centraliza o conte�do da coluna referente ao Id do livro (�ndice 0).
		DefaultTableCellRenderer dtcrCentro = new DefaultTableCellRenderer();
		dtcrCentro.setHorizontalAlignment(SwingConstants.CENTER);
		tbLivro.getColumnModel().getColumn(0).setCellRenderer(dtcrCentro);
		
		// Alinha � direira o conte�do da coluna referente ao Pre�o do livro (�ndice 3).
		DefaultTableCellRenderer dtcrDireita = new DefaultTableCellRenderer();
		dtcrDireita.setHorizontalAlignment(SwingConstants.RIGHT);
		tbLivro.getColumnModel().getColumn(3).setCellRenderer(dtcrDireita);
		
		tbLivro.getTableHeader().setReorderingAllowed(false); // Desabilita a reordena��o das colunas do JTable.
		// Habilita o modo de sele��o simples, onde � poss�vel selecionar apenas uma linha de cada vez no JTable.
		tbLivro.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
		
		btAlterar = new JButton("Alterar");
		btExcluir = new JButton("Excluir");

		cp = getContentPane(); // Instancia o container da janela.
		cp.setLayout(null); // Configura o layout do container como nulo.
		cp.setBackground(new Color(180, 205, 205)); // Configura a cor de fundo do container.

		// Posicionamento dos componentes de interface na janela.
		lbTitulo.setBounds(215, 10, 300, 25); // x, y, largura, altura.
		spTabela.setBounds(20, 40, 645, 182);
		btAlterar.setBounds(215, 240, 100, 25);
		btExcluir.setBounds(355, 240, 100, 25);

		// Adi��o dos componentes de interface ao container.
		cp.add(lbTitulo);
		cp.add(spTabela); 
		cp.add(btAlterar);
		cp.add(btExcluir);
		
		// Declara��o do processador de evento referente ao clique no bot�o Alterar.
		btAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btAlterarAction();
			}
		});
		
		// Declara��o do processador de evento referente ao clique no bot�o Excluir.
		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btExcluirAction();
			}
		});
	} // Final do construtor.
	
	private void btAlterarAction() { // M�todo acionado pelo clique no bot�o Alterar.		
		if (tbLivro.getSelectedRow() != -1){ // Se uma linha est� selecionada no JTable.
			final int linhaSelecionada = tbLivro.getSelectedRow(); // Recupera o �ndice da linha selecionada no JTable.
			
			// Recupera os dados de cada coluna da linha selecionada no JTable.
			final int idLivro = Integer.parseInt(tbLivro.getModel().getValueAt(linhaSelecionada, 0).toString()); // idLivro
			final String titulo = tbLivro.getModel().getValueAt(linhaSelecionada, 1).toString(); // titulo
			final String preco = tbLivro.getModel().getValueAt(linhaSelecionada, 2).toString(); // Pre�o
			final String genero = tbLivro.getModel().getValueAt(linhaSelecionada, 3).toString(); // Genero
			final String editora = tbLivro.getModel().getValueAt(linhaSelecionada, 4).toString(); // editora
			final String autor = tbLivro.getModel().getValueAt(linhaSelecionada, 5).toString(); // autor
			
			SwingUtilities.invokeLater(new Runnable(){ // Chama o formul�rio de altera��o de livro.
				@Override
				public void run(){ new LivroAlteracao(idLivro, titulo, preco, genero, editora, autor,
						linhaSelecionada, mtTabela).setVisible(true); }});
		} else { // Se nenhuma linha est� selecionada no JTable.
			JOptionPane.showMessageDialog(this, "Selecione um livro!", "Mensagem", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void btExcluirAction() { // M�todo acionado pelo clique no bot�o Excluir.
		if (tbLivro.getSelectedRow() != -1){ // Se uma linha est� selecionada no JTable.
			int linha = tbLivro.getSelectedRow(); // Recupera o �ndice da linha selecionada no JTable.
			
			// Recupera o id do livro presente na linha selecionada no JTable.
			int idLivro = Integer.parseInt(tbLivro.getModel().getValueAt(linha, 0).toString()); // idLivro
			
			// Mensagem para confirma��o da exclus�o do livro.
			int resposta = JOptionPane.showConfirmDialog(this, "Confirma a exclus�o?", "Confirma��o", 
					 									 JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (resposta == 0) { // Sim
				fc = new LivroController();
				// Envia os dados do livro (alterados no formul�rio) ao controller. 
				// O controller retorna ent�o true ou false indicando se houve ou n�o sucesso na exclus�o. 
				if (fc.Excluir(idLivro)) {
					JOptionPane.showMessageDialog(this, "livro exclu�do com sucesso!", 
		                                          "Informa��o", JOptionPane.INFORMATION_MESSAGE);
					mtTabela.removerLivro(linha);
				} else
					JOptionPane.showMessageDialog(this, "Erro ao excluir o livro!", 
							                      "Erro", JOptionPane.ERROR_MESSAGE);
			} else if (resposta == 1) // N�o
				JOptionPane.showMessageDialog(this, "Opera��o cancelada!", "Confirma��o", JOptionPane.INFORMATION_MESSAGE);
		} else { // Se nenhuma linha est� selecionada no JTable.
			JOptionPane.showMessageDialog(this, "Selecione um livro!", "Mensagem", JOptionPane.WARNING_MESSAGE);
		}
	}
}