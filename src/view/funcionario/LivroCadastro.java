package view.funcionario;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

import controller.LivroController;
import model.genero.Genero;
import model.editora.Editora;
import model.autor.Autor;

@SuppressWarnings("serial")
public class LivroCadastro extends JDialog {
	private JLabel lbTitulo, lbPreco, lbGenero, lbEditora, lbAutor;
	private JTextField tfTitulo;
	private JFormattedTextField tfPreco;
	private JComboBox<String> cbGenero;
	private JComboBox<String> cbEditora;
	private JComboBox<String> cbAutor;
	private JButton btCadastrar;
	private Container cp; // Container para organizar os componentes na janela.	
	private LivroController fc;

	public LivroCadastro() { // Construtor.
		// Instanciação e configuração dos componentes de interface.
		setTitle("Cadastro de livros"); // Título da janela.
		setSize(500, 335); // Tamanho da janela em pixels.
		setLocationRelativeTo(null); // Centraliza a janela na tela.
		setModal(true); // Torna a janela "modal" (janela que não permite acesso a outras janelas abertas).
		
		lbTitulo = new JLabel("Cadastro de livros");
		lbTitulo.setFont(new Font("Arial", Font.BOLD, 19)); // Ajusta a fonte do JLabel.
		
		lbTitulo = new JLabel("Título");
		lbPreco = new JLabel("Preço (R$)");
		lbGenero = new JLabel("Gênero");
		lbEditora = new JLabel("Editora");
		lbAutor = new JLabel("Autor");
		
		tfTitulo = new JTextField();
		
        tfPreco = new JFormattedTextField();
		// Inclui uma máscara de entrada no campo Preço.
		DecimalFormat df = new DecimalFormat("##,###.00"); // #: dígitos opcionais; 0: dígitos obrigatórios.
        NumberFormatter nf = new NumberFormatter(df);
        nf.setFormat(df); // Define o formato numérico, conforme a máscara configurada.
        nf.setAllowsInvalid(false); // Impede que o usuário digite strings não compatíveis com a máscara definida.
        // Associa a máscara configurada ao JFormattedTextField.
        tfPreco.setFormatterFactory(new DefaultFormatterFactory(nf));

		
		cbGenero = new JComboBox<>();
		fc = new LivroController();
		// Retorna um ArrayList de objetos Genero, contendo o Id e a Descrição dos generos cadastrados.
		ArrayList<Genero> generos = fc.recuperarGeneros();	
		if (generos != null) { // Se existir pelo menos um genero cadastrado.
			for (Genero c : generos) // Recupera o Id e a Descrição de cada objeto Genero.
				cbGenero.addItem(c.getIdGenero() + "-" + c.getDescricao());
		} else { // Se não existir nenhum genero cadastrado.
			JOptionPane.showMessageDialog(this, "Não existem gêneros cadastrados!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		
		
		
		cbEditora = new JComboBox<>();
		fc = new LivroController();
		// Retorna um ArrayList de objetos Editora, contendo o Id e a Descrição dos generos cadastrados.
		ArrayList<Editora> editoras = fc.recuperarEditora();	
		if (editoras != null) { // Se existir pelo menos um editora cadastrado.
			for (Editora c : editoras) // Recupera o Id e a Descrição de cada objeto editora.
				cbEditora.addItem(c.getIdEditora() + "-" + c.getNome_Editora());
		} else { // Se não existir nenhum editora cadastrado.
			JOptionPane.showMessageDialog(this, "Não existem editoras cadastradas!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		
		cbAutor = new JComboBox<>();
		fc = new LivroController();
		// Retorna um ArrayList de objetos Editora, contendo o Id e a Descrição dos generos cadastrados.
		ArrayList<Autor> autores = fc.recuperarAutor();	
		if (autores != null) { // Se existir pelo menos um editora cadastrado.
			for (Autor c : autores) // Recupera o Id e a Descrição de cada objeto editora.
				cbAutor.addItem(c.getIdAutor() + "-" + c.getNome_Autor());
		} else { // Se não existir nenhum editora cadastrado.
			JOptionPane.showMessageDialog(this, "Não existem autores cadastrados!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		
		
		btCadastrar = new JButton("Cadastrar");

		cp = getContentPane(); // Instancia o container da janela.
		cp.setLayout(null); // Configura o layout do container como nulo.
		cp.setBackground(new Color(180, 205, 205)); // Configura a cor de fundo do container.

		// Posicionamento dos componentes de interface na janela.
		lbTitulo.setBounds(100, 10, 300, 25); // x, y, largura, altura.
		lbTitulo.setBounds(20, 50, 100, 25);
		tfTitulo.setBounds(100, 50, 360, 25);
		lbPreco.setBounds(20, 90, 100, 25);
		tfPreco.setBounds(100, 90, 100, 25);
		lbGenero.setBounds(20, 130, 250, 25);
		cbGenero.setBounds(100, 130, 250, 25);
		lbEditora.setBounds(16, 210, 250, 25);
		cbEditora.setBounds(100, 210, 250, 25);
		lbAutor.setBounds(25, 170, 250, 25);
		cbAutor.setBounds(100, 170, 250, 25);
		btCadastrar.setBounds(200, 250, 100, 25);

		// Adição dos componentes de interface ao container.
		cp.add(lbTitulo);
		cp.add(lbTitulo);
		cp.add(tfTitulo);
		cp.add(lbPreco);
		cp.add(tfPreco);
		cp.add(lbGenero);
		cp.add(cbGenero);
		cp.add(lbEditora);
		cp.add(cbEditora);
		cp.add(lbAutor);
		cp.add(cbAutor);
		cp.add(btCadastrar);

		// Declaração do processador de evento referente ao clique no botão Cadastrar.
		btCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btCadastrarAction();
			}
		});
	} // Final do construtor.

	private void btCadastrarAction() { // Método acionado pelo clique no botão Cadastrar.
		fc = new LivroController();
		// Envia os dados do livro (informados no formulário) ao controller. 
		// O controller retorna então um ArrayList contendo os erros de validação encontrados. 
		ArrayList<String> erros = fc.Inserir(tfTitulo.getText(),
											 tfPreco.getText().replace(".", "").replace(",", "."),
				                             cbGenero.getSelectedItem().toString(),
				                             cbEditora.getSelectedItem().toString(),
				                             cbAutor.getSelectedItem().toString());

		if (erros.get(0).equals("ok")) { // Se o primeiro elemento do ArrayList for "ok".
			JOptionPane.showMessageDialog(this, "Livro cadastrado com sucesso!", 
					                      "Informação", JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(false); // Fecha a janela.
		} else if (erros.get(0).equals("erro")) { // Se o primeiro elemento do ArrayList for "erro".
			JOptionPane.showMessageDialog(this, "Erro ao cadastrar o livro!", 
					                      "Erro", JOptionPane.ERROR_MESSAGE);
		} else { // Se o primeiro elemento do ArrayList não for "ok", nem "erro".
			String mensagem = "Foram encontrados os seguintes erros de validação:\n";
			for (String e : erros) // Cria uma mensagem contendo todos os erros armazenados no ArrayList.
				mensagem = mensagem + " * " + e + "\n";
			JOptionPane.showMessageDialog(this, mensagem, "Erro de validação", JOptionPane.ERROR_MESSAGE);
		}
	}
}