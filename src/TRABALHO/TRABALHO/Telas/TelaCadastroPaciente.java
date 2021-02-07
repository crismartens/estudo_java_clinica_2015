package TRABALHO.Telas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import TRABALHO.Clínica;
import TRABALHO.Paciente;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.awt.event.ActionEvent;

//Classe da TelaCadastroPaciente
public class TelaCadastroPaciente extends JDialog {
	
	// Modelo
	private Clínica clínica;

	// Elementos da interface
	private DateFormat formatoData = DateFormat.getDateInstance();
	private final JPanel contentPanel = new JPanel();
	private JTextField nome;
	private JTextField endereço;
	private JTextField cpf;
	private JTextField rg;
	private JTextField telefone;
	private JTextField dataNascimento;
	private JLabel pesquisa;
	private JButton pesquisar;
	private JButton salvar;
	private JButton excluir;

	// Método construtor
	public TelaCadastroPaciente(Clínica clínica) {
		setModal(true);

		this.clínica = clínica;

		setTitle("Cadastro de Paciente");
		setBounds(100, 100, 450, 428);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNomeDoFuncionrio = new JLabel("Nome do Paciente");
			lblNomeDoFuncionrio.setBounds(10, 13, 195, 14);
			contentPanel.add(lblNomeDoFuncionrio);
		}
		{
			nome = new JTextField();
			nome.setBounds(10, 38, 414, 20);
			contentPanel.add(nome);
			nome.setColumns(10);
		}

		JLabel lblEndereço = new JLabel("Endereço");
		lblEndereço.setBounds(10, 67, 136, 14);
		contentPanel.add(lblEndereço);

		endereço = new JTextField();
		endereço.setBounds(10, 92, 414, 20);
		contentPanel.add(endereço);
		endereço.setColumns(10);
		{
			JLabel lblCpf = new JLabel("CPF");
			lblCpf.setBounds(10, 173, 195, 14);
			contentPanel.add(lblCpf);
		}
		{
			cpf = new JTextField();
			cpf.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					pesquisar();
				}
			});
			cpf.setBounds(10, 198, 207, 20);
			contentPanel.add(cpf);
			cpf.setColumns(10);
		}
		{
			JLabel lblRg = new JLabel("RG");
			lblRg.setBounds(10, 229, 46, 14);
			contentPanel.add(lblRg);
		}
		{
			rg = new JTextField();
			rg.setBounds(10, 254, 207, 20);
			contentPanel.add(rg);
			rg.setColumns(10);
		}
		{
			JLabel lblTelefone = new JLabel("Telefone");
			lblTelefone.setBounds(10, 123, 113, 14);
			contentPanel.add(lblTelefone);
		}
		{
			telefone = new JTextField();
			telefone.setBounds(10, 142, 207, 20);
			contentPanel.add(telefone);
			telefone.setColumns(10);
		}
		{
			JLabel lblDataNascimento = new JLabel("Data Nascimento");
			lblDataNascimento.setBounds(10, 295, 148, 14);
			contentPanel.add(lblDataNascimento);
		}
		{
			dataNascimento = new JTextField();
			dataNascimento.setBounds(10, 320, 207, 20);
			contentPanel.add(dataNascimento);
			dataNascimento.setColumns(10);
		}

		pesquisar = new JButton("Pesquisar");
		pesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Método que pesquisa o cpf informado na list de Paciente
				pesquisar();

			}
		});
		pesquisar.setBounds(303, 198, 121, 21);
		contentPanel.add(pesquisar);

		pesquisa = new JLabel("");
		pesquisa.setBounds(229, 218, 195, 17);
		contentPanel.add(pesquisa);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				salvar = new JButton("Salvar");
				salvar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						// Método que grava um novo Paciente na list de Paciente
						
						String nomeNovo = nome.getText();

						String dataNascimentoNovo = dataNascimento.getText();
						String telefoneNovo = telefone.getText();
						String cpfNovo = cpf.getText();
						String rgNovo = rg.getText();
						String endereçoNovo = endereço.getText();

						Date dataNovo = null;

						// Método para verificar se todos os campos estão preeenchidos
						if ((nomeNovo.equals("")) || (dataNascimentoNovo.equals("")) || (telefoneNovo.equals(""))
								|| (rgNovo.equals("")) || (endereçoNovo.equals(""))) {

							pesquisa.setText("Preencha todos os campos!");

						} else {

							try {

								dataNovo = formatoData.parse(dataNascimentoNovo);

							} catch (ParseException e) {

								e.printStackTrace();
							}

							clínica.addPaciente(nomeNovo, dataNovo, telefoneNovo, cpfNovo, rgNovo, endereçoNovo);
							pesquisar();

						}
					}
				});
				salvar.setActionCommand("OK");
				buttonPane.add(salvar);
				getRootPane().setDefaultButton(salvar);
				{
					excluir = new JButton("Excluir");
					excluir.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							// Método para excluir um Paciente da list de Paciente
							excluir();
						}
					});
					excluir.setActionCommand("Cancel");
					buttonPane.add(excluir);
				}
			}
			{
				JButton cancelButton = new JButton("Limpar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						// Método para limpar os dados da tela
						limpar();

					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}

		}
		// Método para limpar os dados da tela
		limpar();
	}

	// Método para excluir um Paciente da list de Paciente
	private void excluir() {
		String cpfTeste = cpf.getText();

		Paciente p = clínica.verificarCPF(cpfTeste);

		clínica.removerPaciente(p);

		cpf.setText(cpfTeste);

		excluir.setEnabled(false);
		pesquisar();

	}

	// Método que pesquisa o cpf informado na list de Paciente
	private void pesquisar() {

		String cpfTeste = cpf.getText();

		if (cpfTeste.equals("")) {

			pesquisa.setText("Preencha o CPF!");

		} else {
			Boolean teste = clínica.testeCPF(cpfTeste);

			if (teste == true) {
				pesquisa.setText("");

				Paciente p = clínica.verificarCPF(cpfTeste);

				nome.setText(p.obterNome());
				endereço.setText(p.obterEndereço());
				telefone.setText(p.obterTelefone());
				rg.setText(p.obterRg());
				if (p.obterDataNascimento() != null) {
					dataNascimento.setText(formatoData.format(p.obterDataNascimento()));
				} else {
					dataNascimento.setText("");
				}

				nome.setEditable(false);
				nome.setFocusable(false);
				endereço.setEditable(false);
				endereço.setFocusable(false);
				telefone.setEditable(false);
				telefone.setFocusable(false);
				rg.setEditable(false);
				rg.setFocusable(false);
				dataNascimento.setEditable(false);
				dataNascimento.setFocusable(false);
				salvar.setEnabled(false);

				cpf.setEditable(true);
				cpf.setFocusable(true);

				excluir.setEnabled(true);
			} else {

				salvar.setEnabled(true);
				pesquisa.setText("Paciente não cadastrado.");
				nome.setEditable(true);
				nome.setFocusable(true);
				endereço.setEditable(true);
				endereço.setFocusable(true);
				telefone.setEditable(true);
				telefone.setFocusable(true);
				rg.setEditable(true);
				rg.setFocusable(true);
				dataNascimento.setEditable(true);
				dataNascimento.setFocusable(true);
				nome.setText(null);
				endereço.setText(null);
				telefone.setText(null);
				rg.setText(null);
				dataNascimento.setText(null);
				nome.requestFocus(true);
				cpf.setEditable(false);
				cpf.setFocusable(false);
			}
		}
	}

	// Método para limpar os dados da tela
	private void limpar() {
		nome.setText(null);
		endereço.setText(null);
		telefone.setText(null);
		cpf.setText(null);
		rg.setText(null);
		dataNascimento.setText(null);
		pesquisa.setText("");
		nome.setEditable(false);
		nome.setFocusable(false);
		endereço.setEditable(false);
		endereço.setFocusable(false);
		telefone.setEditable(false);
		telefone.setFocusable(false);
		cpf.setText(null);
		rg.setEditable(false);
		rg.setFocusable(false);
		dataNascimento.setEditable(false);
		dataNascimento.setFocusable(false);
		salvar.setEnabled(false);
		excluir.setEnabled(false);
		cpf.requestFocus();
		pesquisar.setFocusable(false);
		cpf.setEditable(true);
		cpf.setFocusable(true);
	}

}
