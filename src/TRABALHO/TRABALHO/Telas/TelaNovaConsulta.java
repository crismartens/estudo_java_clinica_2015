package TRABALHO.Telas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import TRABALHO.Agendamento;
import TRABALHO.Clínica;
import TRABALHO.Médico;
import TRABALHO.Paciente;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;

// Classe da TelaNocaConsulta
public class TelaNovaConsulta extends JDialog {

	// Modelo
	private Clínica clínica;

	// Elementos da interface
	private DateFormat formatoData = DateFormat.getDateInstance();
	private DateFormat formatoHora = DateFormat.getTimeInstance(DateFormat.SHORT);
	private final JPanel contentPanel = new JPanel();
	private JTextField nomeMédico;
	private JTextField nomePaciente;
	private JTextField data;
	private JLabel ação;
	private JTextField hora;
	private JButton salvar;
	private JButton verificar;
	private JButton excluir;

	// Método construtor
	public TelaNovaConsulta(Clínica clínica) {
		setModal(true);

		this.clínica = clínica;

		setTitle("Nova Consulta");
		setBounds(100, 100, 476, 318);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Nome do Médico");
			lblNewLabel.setBounds(10, 11, 143, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			nomeMédico = new JTextField();
			nomeMédico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					// Método que valida se a agenda está disponível
					verificarAgenda();
				}
			});
			nomeMédico.setBounds(10, 33, 414, 20);
			contentPanel.add(nomeMédico);
			nomeMédico.setColumns(10);
		}
		{
			JLabel lblNomeDoPaciente = new JLabel("Nome do Paciente");
			lblNomeDoPaciente.setBounds(10, 176, 125, 14);
			contentPanel.add(lblNomeDoPaciente);
		}
		{
			nomePaciente = new JTextField();
			nomePaciente.setBounds(10, 201, 414, 20);
			contentPanel.add(nomePaciente);
			nomePaciente.setColumns(10);
		}
		{
			JLabel lblData = new JLabel("Data");
			lblData.setBounds(10, 64, 46, 14);
			contentPanel.add(lblData);
		}
		{
			data = new JTextField();
			data.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					// Método que valida se a agenda está disponível
					verificarAgenda();
				}
			});
			data.setBounds(10, 89, 186, 20);
			contentPanel.add(data);
			data.setColumns(10);
		}
		{
			ação = new JLabel("");
			ação.setHorizontalAlignment(SwingConstants.TRAILING);
			ação.setBounds(10, 120, 315, 19);
			contentPanel.add(ação);
		}

		JLabel lblHora = new JLabel("Hora");
		lblHora.setBounds(240, 64, 46, 14);
		contentPanel.add(lblHora);

		hora = new JTextField();
		hora.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Método que valida se a agenda está disponível
				verificarAgenda();
			}
		});
		hora.setColumns(10);
		hora.setBounds(238, 89, 186, 20);
		contentPanel.add(hora);

		verificar = new JButton("Verificar");
		verificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Método que valida se a agenda está disponível
				verificarAgenda();

			}
		});
		verificar.setBounds(335, 116, 89, 23);
		contentPanel.add(verificar);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				salvar = new JButton("Salvar");
				salvar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						// Método que adiciona na list de Agendamento do Médico 
						agendar();

					}
				});
				salvar.setActionCommand("OK");
				buttonPane.add(salvar);
				getRootPane().setDefaultButton(salvar);
				{
					excluir = new JButton("Excluir");
					excluir.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							// Método que remove da list de Agendamento do Médico
							excluir();
						}
					});
					excluir.setEnabled(false);
					excluir.setActionCommand("Cancel");
					buttonPane.add(excluir);
				}
			}
			{
				JButton cancelButton = new JButton("Limpar");
				cancelButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						limpar();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}

		}
		limpar();
	}

	// Método para limpar os dados da tela
	private void limpar() {
		salvar.setEnabled(true);

		nomeMédico.setText("");
		nomePaciente.setText("");
		data.setText("");
		hora.setText("");
		ação.setText("");

		nomePaciente.setEditable(false);
		nomePaciente.setFocusable(false);
		nomeMédico.setEditable(true);
		nomeMédico.setFocusable(true);
		data.setEditable(true);
		data.setFocusable(true);
		hora.setEditable(true);
		hora.setFocusable(true);

		verificar.setFocusable(false);

		salvar.setEnabled(false);

		nomeMédico.requestFocus();

		excluir.setEnabled(false);
		verificar.setEnabled(true);
		
	}

	// Método que adiciona na list de Agendamento do Médico
	private void agendar() {

		String p = nomePaciente.getText();
		Paciente pNovo = null;
		pNovo = clínica.dadosPaciente(p);

		if (pNovo != null) {

			String m = nomeMédico.getText();
			Médico mNovo = null;
			mNovo = clínica.dadosMédico(m);

			String h = hora.getText();
			Date horaNovo = null;
			try {
				horaNovo = formatoHora.parse(h);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			String d = data.getText();
			Date dataNovo = null;
			try {
				dataNovo = formatoData.parse(d);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			clínica.addAgendamento(dataNovo, horaNovo, mNovo, pNovo);
			verificar.setEnabled(true);
			verificarAgenda();
		} else {
			ação.setText("Verificar paciente!");
		}

	}

	// Método que valida se a agenda está disponível
	private void verificarAgenda() {
		nomePaciente.setText("");

		nomePaciente.setEditable(false);
		nomePaciente.setFocusable(false);
		nomeMédico.setEditable(true);
		nomeMédico.setFocusable(true);
		data.setEditable(true);
		data.setFocusable(true);
		hora.setEditable(true);
		hora.setFocusable(true);
		salvar.setEnabled(false);

		String m = nomeMédico.getText();
		Médico mTeste = null;
		mTeste = clínica.dadosMédico(m);

		String p = nomePaciente.getText();
		Paciente pTeste = null;
		pTeste = clínica.dadosPaciente(p);

		String h = hora.getText();
		Date horaTeste = null;
		try {
			horaTeste = formatoHora.parse(h);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String d = data.getText();
		Date dataTeste = null;
		try {
			dataTeste = formatoData.parse(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (mTeste == null) {
			ação.setText("Verificar médico!");
		} else if ((horaTeste == null) && (dataTeste == null)) {
			ação.setText("Verificar dados da marcação!");
		} else if (horaTeste == null) {
			ação.setText("Verificar hora!");
		} else if (dataTeste == null) {
			ação.setText("Verificar data!");
		} else if (clínica.verificarAgenda(dataTeste, horaTeste, mTeste)) {
			ação.setText("Agenda ocupada!");
			Agendamento a = clínica.dadosAgendamento(dataTeste, horaTeste, mTeste);
			String nome = a.obterPaciente().obterNome();
			nomePaciente.setText(nome);
			excluir.setEnabled(true);

		} else {
			ação.setText("Agenda livre!");
			nomePaciente.setEditable(true);
			nomePaciente.setFocusable(true);
			nomeMédico.setEditable(false);
			nomeMédico.setFocusable(false);
			data.setEditable(false);
			data.setFocusable(false);
			hora.setEditable(false);
			hora.setFocusable(false);
			salvar.setEnabled(true);
			verificar.setEnabled(false);
			nomePaciente.requestFocus();

		}

	}

	// Método que remove da list de Agendamento do Médico
	private void excluir() {

		String m = nomeMédico.getText();
		Médico mTeste = null;
		mTeste = clínica.dadosMédico(m);

		String p = nomePaciente.getText();
		Paciente pTeste = null;
		pTeste = clínica.dadosPaciente(p);

		String h = hora.getText();
		Date horaTeste = null;
		try {
			horaTeste = formatoHora.parse(h);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String d = data.getText();
		Date dataTeste = null;
		try {
			dataTeste = formatoData.parse(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Agendamento a = clínica.dadosAgendamento(dataTeste, horaTeste, mTeste);

		clínica.excluirConsulta(a);
		verificarAgenda();
		excluir.setEnabled(false);


	}
}
