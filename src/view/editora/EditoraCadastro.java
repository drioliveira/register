package view.editora;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import controller.EditoraController;

@SuppressWarnings("serial")
public class EditoraCadastro extends JDialog {
	private JLabel lbNome_Editora;
	private JTextField tfNome_Editora;

	private JButton btCadastrar;
	private Container cp; // Container para organizar os componentes na janela.
	private EditoraController fc;

	public EditoraCadastro() { // Construtor.
		// Instanciação e configuração dos componentes de interface.
		setTitle("Cadastro de editoras"); // Título da janela.
		setSize(500, 335); // Tamanho da janela em pixels.
		setLocationRelativeTo(null); // Centraliza a janela na tela.
		setModal(true); // Torna a janela "modal" (janela que não permite acesso
						// a outras janelas abertas).

		lbNome_Editora = new JLabel("Cadastro de editoras");
		lbNome_Editora.setFont(new Font("Arial", Font.BOLD, 19)); // Ajusta a
																	// fonte do
																	// JLabel.

		lbNome_Editora = new JLabel("Nome editora");

		tfNome_Editora = new JTextField();

		btCadastrar = new JButton("Cadastrar");

		cp = getContentPane(); // Instancia o container da janela.
		cp.setLayout(null); // Configura o layout do container como nulo.
		cp.setBackground(new Color(180, 205, 205)); // Configura a cor de fundo
													// do container.

		// Posicionamento dos componentes de interface na janela.
		lbNome_Editora.setBounds(100, 10, 300, 25); // x, y, largura, altura.
		lbNome_Editora.setBounds(20, 50, 100, 25);
		tfNome_Editora.setBounds(100, 50, 360, 25);

		btCadastrar.setBounds(200, 250, 100, 25);

		// Adição dos componentes de interface ao container.
		cp.add(lbNome_Editora);
		cp.add(lbNome_Editora);
		cp.add(tfNome_Editora);

		cp.add(btCadastrar);

		// Declaração do processador de evento referente ao clique no botão
		// Cadastrar.
		btCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btCadastrarAction();
			}
		});
	} // Final do construtor.

	private void btCadastrarAction() { // Método acionado pelo clique no botão
										// Cadastrar.
		fc = new EditoraController();
		// Envia os dados do editora (informados no formulário) ao controller.
		// O controller retorna então um ArrayList contendo os erros de
		// validação encontrados.
		ArrayList<String> erros = fc.Inserir(tfNome_Editora.getText());

		if (erros.get(0).equals("ok")) { // Se o primeiro elemento do ArrayList
											// for "ok".
			JOptionPane.showMessageDialog(this, "Editora cadastrada com sucesso!", "Informação",
					JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(false); // Fecha a janela.
		} else if (erros.get(0).equals("erro")) { // Se o primeiro elemento do
													// ArrayList for "erro".
			JOptionPane.showMessageDialog(this, "Erro ao cadastrar a editora!", "Erro", JOptionPane.ERROR_MESSAGE);
		} else { // Se o primeiro elemento do ArrayList não for "ok", nem
					// "erro".
			String mensagem = "Foram encontrados os seguintes erros de validação:\n";
			for (String e : erros) // Cria uma mensagem contendo todos os erros
									// armazenados no ArrayList.
				mensagem = mensagem + " * " + e + "\n";
			JOptionPane.showMessageDialog(this, mensagem, "Erro de validação", JOptionPane.ERROR_MESSAGE);
		}
	}
}
