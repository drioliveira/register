

	package view.autor;

	import java.awt.*;
	import java.awt.event.*;
	import java.util.ArrayList;

	import javax.swing.*;

	import controller.AutorController;
	import model.autor.Autor;


	@SuppressWarnings("serial")
	public class AutorAlteracao  extends JDialog {
		private JLabel  lbNome_Autor, lbAutor, lbNacionalidade;
		private JTextField tfNome_Autor;
		private JTextField tfNacionalidade;
		private JComboBox<String> cbAutor;
		private JButton btSalvar;
		private JButton btnSalvar;
		private Container cp; // Container para organizar os componentes na janela.	
		private AutorController fc;
		private int idAutor; // Id do Autor a ser alterado.
		private int linhaSelecionada; // Índice da linha selecionada no JTable.
		private ModeloTabela mtTabela; // Modelo que define a estrutura da tabela.

		// Construtor.
		public AutorAlteracao(int idAutor, String Nome_Autor, String nacionalidade, int linha, ModeloTabela mtTabela) { 
			// Instanciação e configuração dos componentes de interface.
			setTitle("Alteração de Autors"); // descrição da janela.
			setSize(500, 250); // Tamanho da janela em pixels.
			setLocationRelativeTo(null); // Centraliza a janela na tela.
			setModal(true); // Torna a janela "modal" (janela que não permite acesso a outras janelas abertas).
			
			lbNome_Autor = new JLabel("Alteração de Autores");
			lbNome_Autor.setFont(new Font("Arial", Font.BOLD, 19)); // Ajusta a fonte do JLabel.
			lbNacionalidade = new JLabel("Alteração de Autores");
			lbNacionalidade.setFont(new Font("Arial", Font.BOLD, 19)); // Ajusta a fonte do JLabel.
			
			lbNome_Autor = new JLabel("Nome_Autor");
			lbNacionalidade = new JLabel("Nacionalidade");
			//lbAutor = new JLabel("Autor");
			
			tfNome_Autor = new JTextField();
			tfNacionalidade = new JTextField();
			
	    
			btnSalvar = new JButton("Salvar");
			
			btSalvar = new JButton("Salvar");
			
			// Carrega os dados do Autor selecionado no JTable de Autors.
			tfNome_Autor.setText(Nome_Autor);
			tfNacionalidade.setText(nacionalidade);
			
					
			this.idAutor = idAutor; // Recebe o id do Autor selecionado no JTable.
			this.linhaSelecionada = linha; // Recebe o índice da linha selecionada no JTable.
			this.mtTabela = mtTabela; // Recebe o modelo que define a estrutura da tabela.

			cp = getContentPane(); // Instancia o container da janela.
			cp.setLayout(null); // Configura o layout do container como nulo.
			cp.setBackground(new Color(180, 205, 205)); // Configura a cor de fundo do container.

			// Posicionamento dos componentes de interface na janela.
			lbNome_Autor.setBounds(100, 10, 300, 25); // x, y, largura, altura.
			lbNome_Autor.setBounds(20, 50, 100, 25);
			tfNome_Autor.setBounds(100, 50, 360, 25);
			lbNacionalidade.setBounds(100, 20, 300, 25); // x, y, largura, altura.
			lbNacionalidade.setBounds(20, 100, 100, 25);
			tfNacionalidade.setBounds(100, 100, 360, 25);
			//lbAutor.setBounds(20, 90, 100, 25);
			//cbAutor.setBounds(100, 90, 100, 25);
			btnSalvar.setBounds(200, 150, 100, 25);

			// Adição dos componentes de interface ao container.
			cp.add(lbNome_Autor);
			cp.add(lbNome_Autor);
			cp.add(tfNome_Autor);
			cp.add(lbNacionalidade);
			cp.add(lbNacionalidade);
			cp.add(tfNacionalidade);
			//cp.add(lbAutor);
			//cp.add(cbAutor);
			cp.add(btnSalvar);

			// Declaração do processador de evento referente ao clique no botão Cadastrar.
			btnSalvar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btSalvarAction();
				}
			});
		} // Final do construtor.

		private void btSalvarAction() { // Método acionado pelo clique no botão Salvar.
			fc = new AutorController();
			
			// Envia os dados do Autor (informados no formulário) ao controller. 
			// O controller retorna então um ArrayList contendo os erros de validação encontrados. 
			ArrayList<String> erros = fc.Alterar(this.idAutor,
												 tfNome_Autor.getText(),
												 tfNacionalidade.getText());
			

			if (erros.get(0).equals("ok")) { // Se o primeiro elemento do ArrayList for "ok".
				JOptionPane.showMessageDialog(this, "Autor alterado com sucesso!", 
						                      "Informação", JOptionPane.INFORMATION_MESSAGE);
				// Atualiza os dados do JTable após a alteração do Autor.
				this.mtTabela.setValueAt(tfNome_Autor.getText(), this.linhaSelecionada, 1);
				this.mtTabela.setValueAt(tfNacionalidade.getText(), this.linhaSelecionada, 2);
				//this.mtTabela.setValueAt(cbAutor.getSelectedItem().toString(), this.linhaSelecionada, 0);
				this.setVisible(false); // Fecha a janela.
			} else if (erros.get(0).equals("erro")) { // Se o primeiro elemento do ArrayList for "erro".
				JOptionPane.showMessageDialog(this, "Erro ao alterar o Autor!", 
						                      "Erro", JOptionPane.ERROR_MESSAGE);
			} else { // Se o primeiro elemento do ArrayList não for "ok", nem "erro".
				String mensagem = "Foram encontrados os seguintes erros de validação:\n";
				for (String e : erros) // Cria uma mensagem contendo todos os erros armazenados no ArrayList.
					mensagem = mensagem + " * " + e + "\n";
				JOptionPane.showMessageDialog(this, mensagem, "Erro de validação", JOptionPane.ERROR_MESSAGE);
			}
		}
	}