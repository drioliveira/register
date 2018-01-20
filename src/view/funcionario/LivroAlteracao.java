package view.funcionario;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.text.*;

import controller.LivroController;
import model.genero.Genero;
import model.editora.Editora;
import model.autor.Autor;

@SuppressWarnings("serial")
public class LivroAlteracao extends JDialog {
	private JLabel lbTitulo, lbPreco, lbGenero, lbEditora, lbAutor;
	private JTextField tfTitulo;
	private JFormattedTextField tfPreco;
	private JComboBox<String> cbGenero;
	private JComboBox<String> cbEditora;
	private JComboBox<String> cbAutor;
	private JButton btSalvar;
	private Container cp; // Container para organizar os componentes na janela.	
	private LivroController fc;
	private int idLivro; // Id do Livro a ser alterado.
	private int linhaSelecionada; // �ndice da linha selecionada no JTable.
	private ModeloTabela mtTabela; // Modelo que define a estrutura da tabela.

	// Construtor.
	public LivroAlteracao(int idLivro, String titulo, String preco, String genero, String editora, String autor, int linha, ModeloTabela mtTabela) { 
		// Instancia��o e configura��o dos componentes de interface.
		setTitle("Altera��o de Livros"); // T�tulo da janela.
		setSize(500, 335); // Tamanho da janela em pixels.
		setLocationRelativeTo(null); // Centraliza a janela na tela.
		setModal(true); // Torna a janela "modal" (janela que n�o permite acesso a outras janelas abertas).
		
		lbTitulo = new JLabel("Altera��o de Livros");
		lbTitulo.setFont(new Font("Arial", Font.BOLD, 19)); // Ajusta a fonte do JLabel.
		
		lbTitulo = new JLabel("Titulo");
		lbPreco = new JLabel("Pre�o (R$)");
		lbGenero = new JLabel("Genero");
		lbEditora = new JLabel("Editora");
		lbAutor = new JLabel("Autor");
		
		tfTitulo = new JTextField();
		
        tfPreco = new JFormattedTextField();
		// Inclui uma m�scara de entrada no campo Pre�o.
		DecimalFormat df = new DecimalFormat("##,###.00"); // #: d�gitos opcionais; 0: d�gitos obrigat�rios.
        NumberFormatter nf = new NumberFormatter(df);
        nf.setFormat(df); // Define o formato num�rico, conforme a m�scara configurada.
        nf.setAllowsInvalid(false); // Impede que o usu�rio digite strings n�o compat�veis com a m�scara definida.
        // Associa a m�scara configurada ao JFormattedTextField.
        tfPreco.setFormatterFactory(new DefaultFormatterFactory(nf));
		
		
        cbGenero = new JComboBox<>();
		fc = new LivroController();
		// Retorna um ArrayList de objetos Genero, contendo o Id e a Descri��o dos generos cadastrados.
		ArrayList<Genero> generos = fc.recuperarGeneros();	
		if (generos != null) { // Se existir pelo menos um genero cadastrado.
			for (Genero c : generos) // Recupera o Id e a Descri��o de cada objeto Genero.
				cbGenero.addItem(c.getIdGenero() + "-" + c.getDescricao());
		} else { // Se n�o existir nenhum genero cadastrado.
			JOptionPane.showMessageDialog(this, "N�o existem generos cadastrados!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		
		
		
		cbEditora = new JComboBox<>();
		fc = new LivroController();
		// Retorna um ArrayList de objetos Editora, contendo o Id e a Descri��o dos generos cadastrados.
		ArrayList<Editora> editoras = fc.recuperarEditora();	
		if (editoras != null) { // Se existir pelo menos um editora cadastrado.
			for (Editora c : editoras) // Recupera o Id e a Descri��o de cada objeto editora.
				cbEditora.addItem(c.getIdEditora() + "-" + c.getNome_Editora());
		} else { // Se n�o existir nenhum editora cadastrado.
			JOptionPane.showMessageDialog(this, "N�o existem editoras cadastradas!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		
		cbAutor = new JComboBox<>();
		fc = new LivroController();
		// Retorna um ArrayList de objetos Editora, contendo o Id e a Descri��o dos generos cadastrados.
		ArrayList<Autor> autores = fc.recuperarAutor();	
		if (autores != null) { // Se existir pelo menos um editora cadastrado.
			for (Autor c : autores) // Recupera o Id e a Descri��o de cada objeto editora.
				cbAutor.addItem(c.getIdAutor() + "-" + c.getNome_Autor());
		} else { // Se n�o existir nenhum editora cadastrado.
			JOptionPane.showMessageDialog(this, "N�o existem autores cadastradas!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		
		
		btSalvar = new JButton("Salvar");
		
		// Carrega os dados do Livro selecionado no JTable de Livros.
		tfTitulo.setText(titulo);
		
		tfPreco.setText(preco);
				
		this.idLivro = idLivro; // Recebe o id do Livro selecionado no JTable.
		this.linhaSelecionada = linha; // Recebe o �ndice da linha selecionada no JTable.
		this.mtTabela = mtTabela; // Recebe o modelo que define a estrutura da tabela.

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
		btSalvar.setBounds(200, 250, 100, 25);

		// Adi��o dos componentes de interface ao container.
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
		cp.add(btSalvar);

		// Declara��o do processador de evento referente ao clique no bot�o Cadastrar.
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btSalvarAction();
			}
		});
	} // Final do construtor.

	private void btSalvarAction() { // M�todo acionado pelo clique no bot�o Salvar.
		fc = new LivroController();
		
		// Envia os dados do Livro (informados no formul�rio) ao controller. 
		// O controller retorna ent�o um ArrayList contendo os erros de valida��o encontrados. 
		ArrayList<String> erros = fc.Alterar(this.idLivro,
											 tfTitulo.getText(),
											 tfPreco.getText(),
				                             cbGenero.getSelectedItem().toString(),
				                             cbEditora.getSelectedItem().toString(),
				                             cbAutor.getSelectedItem().toString());
		

		if (erros.get(0).equals("ok")) { // Se o primeiro elemento do ArrayList for "ok".
			JOptionPane.showMessageDialog(this, "Livro alterado com sucesso!", 
					                      "Informa��o", JOptionPane.INFORMATION_MESSAGE);
			// Atualiza os dados do JTable ap�s a altera��o do Livro.
			this.mtTabela.setValueAt(tfTitulo.getText(), this.linhaSelecionada, 1);
			this.mtTabela.setValueAt(tfPreco.getText().replace(".", "").replace(",", "."), this.linhaSelecionada, 2);
			this.mtTabela.setValueAt(cbGenero.getSelectedItem().toString(), this.linhaSelecionada, 3);
			this.mtTabela.setValueAt(cbEditora.getSelectedItem().toString(), this.linhaSelecionada, 4);
			this.mtTabela.setValueAt(cbAutor.getSelectedItem().toString(), this.linhaSelecionada, 5);
			this.setVisible(false); // Fecha a janela.
		} else if (erros.get(0).equals("erro")) { // Se o primeiro elemento do ArrayList for "erro".
			JOptionPane.showMessageDialog(this, "Erro ao alterar o Livro!", 
					                      "Erro", JOptionPane.ERROR_MESSAGE);
		} else { // Se o primeiro elemento do ArrayList n�o for "ok", nem "erro".
			String mensagem = "Foram encontrados os seguintes erros de valida��o:\n";
			for (String e : erros) // Cria uma mensagem contendo todos os erros armazenados no ArrayList.
				mensagem = mensagem + " * " + e + "\n";
			JOptionPane.showMessageDialog(this, mensagem, "Erro de valida��o", JOptionPane.ERROR_MESSAGE);
		}
	}
}