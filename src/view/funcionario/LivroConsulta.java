package view.funcionario;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

import controller.LivroController;

@SuppressWarnings("serial")
public class LivroConsulta extends JDialog {
	private JLabel lbTitulo;
	private JTable tbLivro; // Tabela que exibirá os livros cadastrados no banco de dados.
	private ModeloTabela mtTabela; // Modelo que definirá a estrutura da tabela.
	private JScrollPane spTabela; // Barra de rolagem que será vinculada à tabela.
	private JButton btAlterar;
	private JButton btExcluir;
	private Container cp; // Container para organizar os componentes na janela.	
	private LivroController fc;

	public LivroConsulta() { // Construtor.
		// Instanciação e configuração dos componentes de interface.
		setTitle("Consulta de livros"); // Título da janela.
		setSize(700, 320); // Tamanho da janela em pixels.
		setLocationRelativeTo(null); // Centraliza a janela na tela.
		setModal(true); // Torna a janela "modal" (janela que não permite acesso a outras janelas abertas).
		
		lbTitulo = new JLabel("Consulta de livros");
		lbTitulo.setFont(new Font("Arial", Font.BOLD, 19)); // Ajusta a fonte do JLabel.
		
		// Chama o construtor da classe ModeloTabela que define a estrutura e carrega os dados no JTable.
		mtTabela = new ModeloTabela(); 	
		tbLivro = new JTable(mtTabela); // Inclui o modelo da tabela no JTable.
		spTabela = new JScrollPane(tbLivro); // Vincula a barra de rolagem ao JTable.
		
		tbLivro.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Desabilita o dimensionamento automático das colunas.
		// Configura a largura de cada coluna do JTable (em pixels).
		tbLivro.getColumnModel().getColumn(0).setPreferredWidth(50);
		tbLivro.getColumnModel().getColumn(1).setPreferredWidth(150);
		tbLivro.getColumnModel().getColumn(2).setPreferredWidth(80);
		tbLivro.getColumnModel().getColumn(3).setPreferredWidth(100);
		tbLivro.getColumnModel().getColumn(4).setPreferredWidth(100);
		tbLivro.getColumnModel().getColumn(5).setPreferredWidth(150);
		
		// Configura a fonte do cabeçalho do JTable.
		tbLivro.getTableHeader().setFont(new Font(null, Font.BOLD, 12));
		
		// Centraliza o conteúdo da coluna referente ao Id do livro (índice 0).
		DefaultTableCellRenderer dtcrCentro = new DefaultTableCellRenderer();
		dtcrCentro.setHorizontalAlignment(SwingConstants.CENTER);
		tbLivro.getColumnModel().getColumn(0).setCellRenderer(dtcrCentro);
		
		// Alinha à direira o conteúdo da coluna referente ao Preço do livro (índice 3).
		DefaultTableCellRenderer dtcrDireita = new DefaultTableCellRenderer();
		dtcrDireita.setHorizontalAlignment(SwingConstants.RIGHT);
		tbLivro.getColumnModel().getColumn(3).setCellRenderer(dtcrDireita);
		
		tbLivro.getTableHeader().setReorderingAllowed(false); // Desabilita a reordenação das colunas do JTable.
		// Habilita o modo de seleção simples, onde é possível selecionar apenas uma linha de cada vez no JTable.
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

		// Adição dos componentes de interface ao container.
		cp.add(lbTitulo);
		cp.add(spTabela); 
		cp.add(btAlterar);
		cp.add(btExcluir);
		
		// Declaração do processador de evento referente ao clique no botão Alterar.
		btAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btAlterarAction();
			}
		});
		
		// Declaração do processador de evento referente ao clique no botão Excluir.
		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btExcluirAction();
			}
		});
	} // Final do construtor.
	
	private void btAlterarAction() { // Método acionado pelo clique no botão Alterar.		
		if (tbLivro.getSelectedRow() != -1){ // Se uma linha está selecionada no JTable.
			final int linhaSelecionada = tbLivro.getSelectedRow(); // Recupera o índice da linha selecionada no JTable.
			
			// Recupera os dados de cada coluna da linha selecionada no JTable.
			final int idLivro = Integer.parseInt(tbLivro.getModel().getValueAt(linhaSelecionada, 0).toString()); // idLivro
			final String titulo = tbLivro.getModel().getValueAt(linhaSelecionada, 1).toString(); // titulo
			final String preco = tbLivro.getModel().getValueAt(linhaSelecionada, 2).toString(); // Preço
			final String genero = tbLivro.getModel().getValueAt(linhaSelecionada, 3).toString(); // Genero
			final String editora = tbLivro.getModel().getValueAt(linhaSelecionada, 4).toString(); // editora
			final String autor = tbLivro.getModel().getValueAt(linhaSelecionada, 5).toString(); // autor
			
			SwingUtilities.invokeLater(new Runnable(){ // Chama o formulário de alteração de livro.
				@Override
				public void run(){ new LivroAlteracao(idLivro, titulo, preco, genero, editora, autor,
						linhaSelecionada, mtTabela).setVisible(true); }});
		} else { // Se nenhuma linha está selecionada no JTable.
			JOptionPane.showMessageDialog(this, "Selecione um livro!", "Mensagem", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void btExcluirAction() { // Método acionado pelo clique no botão Excluir.
		if (tbLivro.getSelectedRow() != -1){ // Se uma linha está selecionada no JTable.
			int linha = tbLivro.getSelectedRow(); // Recupera o índice da linha selecionada no JTable.
			
			// Recupera o id do livro presente na linha selecionada no JTable.
			int idLivro = Integer.parseInt(tbLivro.getModel().getValueAt(linha, 0).toString()); // idLivro
			
			// Mensagem para confirmação da exclusão do livro.
			int resposta = JOptionPane.showConfirmDialog(this, "Confirma a exclusão?", "Confirmação", 
					 									 JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (resposta == 0) { // Sim
				fc = new LivroController();
				// Envia os dados do livro (alterados no formulário) ao controller. 
				// O controller retorna então true ou false indicando se houve ou não sucesso na exclusão. 
				if (fc.Excluir(idLivro)) {
					JOptionPane.showMessageDialog(this, "livro excluído com sucesso!", 
		                                          "Informação", JOptionPane.INFORMATION_MESSAGE);
					mtTabela.removerLivro(linha);
				} else
					JOptionPane.showMessageDialog(this, "Erro ao excluir o livro!", 
							                      "Erro", JOptionPane.ERROR_MESSAGE);
			} else if (resposta == 1) // Não
				JOptionPane.showMessageDialog(this, "Operação cancelada!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
		} else { // Se nenhuma linha está selecionada no JTable.
			JOptionPane.showMessageDialog(this, "Selecione um livro!", "Mensagem", JOptionPane.WARNING_MESSAGE);
		}
	}
}