	package view.editora;

	import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.*;

import controller.EditoraController;

	@SuppressWarnings("serial")
	public class EditoraConsulta extends JDialog {
		private JLabel lbNome_Editora;
		private JTable tbEditora; // Tabela que exibirá os editoras cadastrados no banco de dados.
		private ModeloTabela mtTabela; // Modelo que definirá a estrutura da tabela.
		private JScrollPane spTabela; // Barra de rolagem que será vinculada à tabela.
		private JButton btAlterar;
		private JButton btExcluir;
		private Container cp; // Container para organizar os componentes na janela.	
		private EditoraController fc;

		public EditoraConsulta() { // Construtor.
			// Instanciação e configuração dos componentes de interface.
			setTitle("Consulta de editoras"); // nome_Editora da janela.
			setSize(700, 320); // Tamanho da janela em pixels.
			setLocationRelativeTo(null); // Centraliza a janela na tela.
			setModal(true); // Torna a janela "modal" (janela que não permite acesso a outras janelas abertas).
			
			lbNome_Editora = new JLabel("Consulta de editoras");
			lbNome_Editora.setFont(new Font("Arial", Font.BOLD, 19)); // Ajusta a fonte do JLabel.
			
			// Chama o construtor da classe ModeloTabela que define a estrutura e carrega os dados no JTable.
			mtTabela = new ModeloTabela(); 	
			tbEditora = new JTable(mtTabela); // Inclui o modelo da tabela no JTable.
			spTabela = new JScrollPane(tbEditora); // Vincula a barra de rolagem ao JTable.
			
			tbEditora.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Desabilita o dimensionamento automático das colunas.
			// Configura a largura de cada coluna do JTable (em pixels).
			tbEditora.getColumnModel().getColumn(0).setPreferredWidth(50);
			tbEditora.getColumnModel().getColumn(1).setPreferredWidth(150);
		
			
			
			// Configura a fonte do cabeçalho do JTable.
			tbEditora.getTableHeader().setFont(new Font(null, Font.BOLD, 12));
			
			// Centraliza o conteúdo da coluna referente ao Id do editora (índice 0).
			DefaultTableCellRenderer dtcrCentro = new DefaultTableCellRenderer();
			dtcrCentro.setHorizontalAlignment(SwingConstants.CENTER);
			tbEditora.getColumnModel().getColumn(0).setCellRenderer(dtcrCentro);
			
			// Alinha à direira o conteúdo da coluna referente ao Preço do editora (índice 3).
			DefaultTableCellRenderer dtcrDireita = new DefaultTableCellRenderer();
			dtcrDireita.setHorizontalAlignment(SwingConstants.RIGHT);
			tbEditora.getColumnModel().getColumn(1).setCellRenderer(dtcrDireita);
			
			tbEditora.getTableHeader().setReorderingAllowed(false); // Desabilita a reordenação das colunas do JTable.
			// Habilita o modo de seleção simples, onde é possível selecionar apenas uma linha de cada vez no JTable.
			tbEditora.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
			
			btAlterar = new JButton("Alterar");
			btExcluir = new JButton("Excluir");

			cp = getContentPane(); // Instancia o container da janela.
			cp.setLayout(null); // Configura o layout do container como nulo.
			cp.setBackground(new Color(180, 205, 205)); // Configura a cor de fundo do container.

			// Posicionamento dos componentes de interface na janela.
			lbNome_Editora.setBounds(215, 10, 300, 25); // x, y, largura, altura.
			spTabela.setBounds(20, 40, 645, 182);
			btAlterar.setBounds(215, 240, 100, 25);
			btExcluir.setBounds(355, 240, 100, 25);

			// Adição dos componentes de interface ao container.
			cp.add(lbNome_Editora);
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
			if (tbEditora.getSelectedRow() != -1){ // Se uma linha está selecionada no JTable.
				final int linhaSelecionada = tbEditora.getSelectedRow(); // Recupera o índice da linha selecionada no JTable.
				
				// Recupera os dados de cada coluna da linha selecionada no JTable.
				final int idEditora = Integer.parseInt(tbEditora.getModel().getValueAt(linhaSelecionada, 0).toString()); // idEditora
				final String nome_Editora = tbEditora.getModel().getValueAt(linhaSelecionada, 1).toString(); // titulo
				final String editora = tbEditora.getModel().getValueAt(linhaSelecionada, 1).toString(); // Editora
				
				
				SwingUtilities.invokeLater(new Runnable(){ // Chama o formulário de alteração de editora.
					@Override
					public void run(){ new EditoraAlteracao(idEditora, nome_Editora, editora, linhaSelecionada, mtTabela).setVisible(true); }});
			} else { // Se nenhuma linha está selecionada no JTable.
				JOptionPane.showMessageDialog(this, "Selecione um editora!", "Mensagem", JOptionPane.WARNING_MESSAGE);
			}
		}
		
		private void btExcluirAction() { // Método acionado pelo clique no botão Excluir.
			if (tbEditora.getSelectedRow() != -1){ // Se uma linha está selecionada no JTable.
				int linha = tbEditora.getSelectedRow(); // Recupera o índice da linha selecionada no JTable.
				
				// Recupera o id do editora presente na linha selecionada no JTable.
				int idEditora = Integer.parseInt(tbEditora.getModel().getValueAt(linha, 0).toString()); // idEditora
				
				// Mensagem para confirmação da exclusão do editora.
				int resposta = JOptionPane.showConfirmDialog(this, "Confirma a exclusão?", "Confirmação", 
						 									 JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (resposta == 0) { // Sim
					fc = new EditoraController();
					// Envia os dados do editora (alterados no formulário) ao controller. 
					// O controller retorna então true ou false indicando se houve ou não sucesso na exclusão. 
					if (fc.Excluir(idEditora)) {
						JOptionPane.showMessageDialog(this, "Editora excluída com sucesso!", 
			                                          "Informação", JOptionPane.INFORMATION_MESSAGE);
						mtTabela.removerEditora(linha);
					} else
						JOptionPane.showMessageDialog(this, "Erro ao excluir a editora!", 
								                      "Erro", JOptionPane.ERROR_MESSAGE);
				} else if (resposta == 1) // Não
					JOptionPane.showMessageDialog(this, "Operação cancelada!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
			} else { // Se nenhuma linha está selecionada no JTable.
				JOptionPane.showMessageDialog(this, "Selecione uma editora!", "Mensagem", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
