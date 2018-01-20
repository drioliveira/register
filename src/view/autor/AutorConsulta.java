
	package view.autor;


	import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.*;

import controller.AutorController;

	@SuppressWarnings("serial")
	public class AutorConsulta extends JDialog {
		private JLabel lbNome_Autor;
		private JLabel lbNacionalidade;
		private JTable tbAutor; // Tabela que exibirá os autors cadastrados no banco de dados.
		private ModeloTabela mtTabela; // Modelo que definirá a estrutura da tabela.
		private JScrollPane spTabela; // Barra de rolagem que será vinculada à tabela.
		private JButton btAlterar;
		private JButton btExcluir;
		private Container cp; // Container para organizar os componentes na janela.	
		private AutorController fc;

		public AutorConsulta() { // Construtor.
			// Instanciação e configuração dos componentes de interface.
			setTitle("Consulta de autores"); // Nome_Autor da janela.
			setSize(700, 320); // Tamanho da janela em pixels.
			setLocationRelativeTo(null); // Centraliza a janela na tela.
			setModal(true); // Torna a janela "modal" (janela que não permite acesso a outras janelas abertas).
			
			lbNome_Autor = new JLabel("Consulta de autores");
			lbNome_Autor.setFont(new Font("Arial", Font.BOLD, 19)); // Ajusta a fonte do JLabel.
			lbNacionalidade = new JLabel("Consulta de autores");
			lbNacionalidade.setFont(new Font("Arial", Font.BOLD, 19)); // Ajusta a fonte do JLabel.
			
			// Chama o construtor da classe ModeloTabela que define a estrutura e carrega os dados no JTable.
			mtTabela = new ModeloTabela(); 	
			tbAutor = new JTable(mtTabela); // Inclui o modelo da tabela no JTable.
			spTabela = new JScrollPane(tbAutor); // Vincula a barra de rolagem ao JTable.
			
			tbAutor.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Desabilita o dimensionamento automático das colunas.
			// Configura a largura de cada coluna do JTable (em pixels).
			tbAutor.getColumnModel().getColumn(0).setPreferredWidth(50);
			tbAutor.getColumnModel().getColumn(1).setPreferredWidth(150);
			tbAutor.getColumnModel().getColumn(2).setPreferredWidth(150);
		
			
			
			// Configura a fonte do cabeçalho do JTable.
			tbAutor.getTableHeader().setFont(new Font(null, Font.BOLD, 12));
			
			// Centraliza o conteúdo da coluna referente ao Id do Autor (índice 0).
			DefaultTableCellRenderer dtcrCentro = new DefaultTableCellRenderer();
			dtcrCentro.setHorizontalAlignment(SwingConstants.CENTER);
			tbAutor.getColumnModel().getColumn(0).setCellRenderer(dtcrCentro);
			
			// Alinha à direira o conteúdo da coluna referente ao Preço do Autor (índice 3).
			DefaultTableCellRenderer dtcrDireita = new DefaultTableCellRenderer();
			dtcrDireita.setHorizontalAlignment(SwingConstants.RIGHT);
			tbAutor.getColumnModel().getColumn(1).setCellRenderer(dtcrDireita);
			
			tbAutor.getTableHeader().setReorderingAllowed(false); // Desabilita a reordenação das colunas do JTable.
			// Habilita o modo de seleção simples, onde é possível selecionar apenas uma linha de cada vez no JTable.
			tbAutor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
			
			btAlterar = new JButton("Alterar");
			btExcluir = new JButton("Excluir");

			cp = getContentPane(); // Instancia o container da janela.
			cp.setLayout(null); // Configura o layout do container como nulo.
			cp.setBackground(new Color(180, 205, 205)); // Configura a cor de fundo do container.

			// Posicionamento dos componentes de interface na janela.
			lbNome_Autor.setBounds(215, 10, 300, 25); // x, y, largura, altura.
			lbNacionalidade.setBounds(215, 10, 300, 25); // x, y, largura, altura.
			spTabela.setBounds(20, 40, 645, 182);
			btAlterar.setBounds(215, 240, 100, 25);
			btExcluir.setBounds(355, 240, 100, 25);

			// Adição dos componentes de interface ao container.
			cp.add(lbNome_Autor);
			cp.add(lbNacionalidade);
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
			if (tbAutor.getSelectedRow() != -1){ // Se uma linha está selecionada no JTable.
				final int linhaSelecionada = tbAutor.getSelectedRow(); // Recupera o índice da linha selecionada no JTable.
				
				// Recupera os dados de cada coluna da linha selecionada no JTable.
				final int idAutor = Integer.parseInt(tbAutor.getModel().getValueAt(linhaSelecionada, 0).toString()); // idAutor
				final String nome_Autor = tbAutor.getModel().getValueAt(linhaSelecionada, 1).toString(); // titulo
				final String nacionalidade = tbAutor.getModel().getValueAt(linhaSelecionada, 2).toString(); // titulo
				
				
				SwingUtilities.invokeLater(new Runnable(){ // Chama o formulário de alteração de Autor.
					@Override
					public void run(){ new AutorAlteracao(idAutor, nome_Autor, nacionalidade, linhaSelecionada, mtTabela).setVisible(true); }});
			} else { // Se nenhuma linha está selecionada no JTable.
				JOptionPane.showMessageDialog(this, "Selecione um autor!", "Mensagem", JOptionPane.WARNING_MESSAGE);
			}
		}
		
		private void btExcluirAction() { // Método acionado pelo clique no botão Excluir.
			if (tbAutor.getSelectedRow() != -1){ // Se uma linha está selecionada no JTable.
				int linha = tbAutor.getSelectedRow(); // Recupera o índice da linha selecionada no JTable.
				
				// Recupera o id do Autor presente na linha selecionada no JTable.
				int idAutor = Integer.parseInt(tbAutor.getModel().getValueAt(linha, 0).toString()); // idAutor
				
				// Mensagem para confirmação da exclusão do Autor.
				int resposta = JOptionPane.showConfirmDialog(this, "Confirma a exclusão?", "Confirmação", 
						 									 JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (resposta == 0) { // Sim
					fc = new AutorController();
					// Envia os dados do Autor (alterados no formulário) ao controller. 
					// O controller retorna então true ou false indicando se houve ou não sucesso na exclusão. 
					if (fc.Excluir(idAutor)) {
						JOptionPane.showMessageDialog(this, "Autor excluído com sucesso!", 
			                                          "Informação", JOptionPane.INFORMATION_MESSAGE);
						mtTabela.removerAutor(linha);
					} else
						JOptionPane.showMessageDialog(this, "Erro ao excluir o autor!", 
								                      "Erro", JOptionPane.ERROR_MESSAGE);
				} else if (resposta == 1) // Não
					JOptionPane.showMessageDialog(this, "Operação cancelada!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
			} else { // Se nenhuma linha está selecionada no JTable.
				JOptionPane.showMessageDialog(this, "Selecione um autor!", "Mensagem", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
