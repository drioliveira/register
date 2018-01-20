package view.editora;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import controller.EditoraController;
import model.editora.Editora;


@SuppressWarnings("serial")
public class EditoraAlteracao extends JDialog {
	private JLabel  lbNome_Editora, lbEditora;
	private JTextField tfNome_Editora;
	private JComboBox<String> cbEditora;
	private JButton btSalvar;
	private Container cp; // Container para organizar os componentes na janela.	
	private EditoraController fc;
	private int idEditora; // Id do Editora a ser alterado.
	private int linhaSelecionada; // Índice da linha selecionada no JTable.
	private ModeloTabela mtTabela; // Modelo que define a estrutura da tabela.

	// Construtor.
	public EditoraAlteracao(int idEditora, String Nome_Editora, String editora, int linha, ModeloTabela mtTabela) { 
		// Instanciação e configuração dos componentes de interface.
		setTitle("Alteração de Editoras"); // descrição da janela.
		setSize(500, 200); // Tamanho da janela em pixels.
		setLocationRelativeTo(null); // Centraliza a janela na tela.
		setModal(true); // Torna a janela "modal" (janela que não permite acesso a outras janelas abertas).
	
		
		lbNome_Editora = new JLabel("Alteração de Editoras");
		lbNome_Editora.setFont(new Font("Arial", Font.BOLD, 19)); // Ajusta a fonte do JLabel.
		
		lbNome_Editora = new JLabel("Nome Editora");
		//lbEditora = new JLabel("Editora");
		
		tfNome_Editora = new JTextField();
		
		
        /*cbEditora = new JComboBox<>();
		fc = new EditoraController();
		// Retorna um ArrayList de objetos Editora, contendo o Id e a Descrição dos editoras cadastrados.
		ArrayList<Editora> editoras = fc.recuperarEditoras();	
		if (editoras != null) { // Se existir pelo menos um editora cadastrado.
			for (Editora c : editoras) // Recupera o Id e a Descrição de cada objeto Editora.
				cbEditora.addItem(c.getIdEditora() + "-" + c.getNome_Editora());
		} else { // Se não existir nenhum editora cadastrado.
			JOptionPane.showMessageDialog(this, "Não existem editoras cadastrados!", "Erro", JOptionPane.ERROR_MESSAGE);
		}*/
		
		
		
		btSalvar = new JButton("Salvar");
		
		// Carrega os dados do Editora selecionado no JTable de Editoras.
		tfNome_Editora.setText(Nome_Editora);
		
				
		this.idEditora = idEditora; // Recebe o id do Editora selecionado no JTable.
		this.linhaSelecionada = linha; // Recebe o índice da linha selecionada no JTable.
		this.mtTabela = mtTabela; // Recebe o modelo que define a estrutura da tabela.

		cp = getContentPane(); // Instancia o container da janela.
		cp.setLayout(null); // Configura o layout do container como nulo.
		cp.setBackground(new Color(180, 205, 205)); // Configura a cor de fundo do container.

		// Posicionamento dos componentes de interface na janela.
		lbNome_Editora.setBounds(100, 10, 300, 25); // x, y, largura, altura.
		lbNome_Editora.setBounds(20, 50, 100, 25);
		tfNome_Editora.setBounds(100, 50, 360, 25);
		//lbEditora.setBounds(20, 90, 100, 25);
		//cbEditora.setBounds(100, 90, 100, 25);
		btSalvar.setBounds(200, 100, 100, 25);

		// Adição dos componentes de interface ao container.
		cp.add(lbNome_Editora);
		cp.add(lbNome_Editora);
		cp.add(tfNome_Editora);
		//cp.add(lbEditora);
		//cp.add(cbEditora);
		cp.add(btSalvar);

		// Declaração do processador de evento referente ao clique no botão Cadastrar.
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btSalvarAction();
			}
		});
	} // Final do construtor.

	private void btSalvarAction() { // Método acionado pelo clique no botão Salvar.
		fc = new EditoraController();
		
		// Envia os dados do Editora (informados no formulário) ao controller. 
		// O controller retorna então um ArrayList contendo os erros de validação encontrados. 
		ArrayList<String> erros = fc.Alterar(this.idEditora,
											 tfNome_Editora.getText());
		

		if (erros.get(0).equals("ok")) { // Se o primeiro elemento do ArrayList for "ok".
			JOptionPane.showMessageDialog(this, "Editora alterado com sucesso!", 
					                      "Informação", JOptionPane.INFORMATION_MESSAGE);
			// Atualiza os dados do JTable após a alteração do Editora.
			this.mtTabela.setValueAt(tfNome_Editora.getText(), this.linhaSelecionada, 1);
			//this.mtTabela.setValueAt(cbEditora.getSelectedItem().toString(), this.linhaSelecionada, 0);
			this.setVisible(false); // Fecha a janela.
		} else if (erros.get(0).equals("erro")) { // Se o primeiro elemento do ArrayList for "erro".
			JOptionPane.showMessageDialog(this, "Erro ao alterar o Editora!", 
					                      "Erro", JOptionPane.ERROR_MESSAGE);
		} else { // Se o primeiro elemento do ArrayList não for "ok", nem "erro".
			String mensagem = "Foram encontrados os seguintes erros de validação:\n";
			for (String e : erros) // Cria uma mensagem contendo todos os erros armazenados no ArrayList.
				mensagem = mensagem + " * " + e + "\n";
			JOptionPane.showMessageDialog(this, mensagem, "Erro de validação", JOptionPane.ERROR_MESSAGE);
		}
	}
}