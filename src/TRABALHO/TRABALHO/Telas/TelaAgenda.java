package TRABALHO.Telas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.table.DefaultTableModel;

import TRABALHO.Agendamento;
import TRABALHO.Clínica;
import TRABALHO.Médico;
import TRABALHO.Paciente;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//Classe da TelaAgenda
public class TelaAgenda extends JDialog {

	// Modelo
	private Clínica clínica;

	// Elementos da interface
	private JComboBox<Médico> comboBox;
	private ComboBoxAgenda combo;
	private JTextField data;
	private JTable table;
	private DateFormat formatoData = DateFormat.getDateInstance();

	// Método construtor
	public TelaAgenda(Clínica clínica) {

		this.clínica = clínica;

		setBounds(100, 100, 691, 415);

		combo = new ComboBoxAgenda(clínica);

		comboBox = new JComboBox<Médico>();
		comboBox.setModel(combo);

		JLabel lblMédico = new JLabel("Médico");

		JLabel lblData = new JLabel("Data");

		data = new JTextField();
		data.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Método que atualiza a tela de acordo com os dados informados
				atualizar();
			}
		});
		data.setColumns(10);
		Date d = new Date();
		data.setText(formatoData.format(d));

		JScrollPane scrollPane = new JScrollPane((Component) null);

		JButton btnIr = new JButton("IR");
		btnIr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Método que atualiza a tela de acordo com os dados informados
				atualizar();

			}
		});

		JButton btnTesteNovoMdico = new JButton("teste novo Médico");
		btnTesteNovoMdico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clínica.addMédicos("MédicoTeste", null, null, null, null, null, null, null, null, null);
				comboBox.setSelectedItem(clínica.dadosMédico("MédicoTeste"));
				
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblData, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMédico))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 550, GroupLayout.PREFERRED_SIZE)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(data, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnIr)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
								.addComponent(btnTesteNovoMdico, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addContainerGap(62, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMédico)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblData)
						.addComponent(btnIr)
						.addComponent(data, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
					.addGap(35)
					.addComponent(btnTesteNovoMdico)
					.addContainerGap())
		);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				// Método do click em um campo da tabela: Adicionar ou excluir o
				// agendamento

				int coluna = table.getSelectedColumn();
				int linha = table.getSelectedRow();

				Date h = ((TableModelAgenda) table.getModel()).obterHorasAgendaPorPosição(linha);
				Date d = ((TableModelAgenda) table.getModel()).obterDatasPorPosição(coluna - 1);

				Médico m = (Médico) combo.getSelectedItem();

				if (m.verificarAgendaMédico(new Agendamento(d, h, m, null))) {

					int opção = JOptionPane.showConfirmDialog(null, "Agenda ocupada, excluir agendamento?", "",
							JOptionPane.YES_NO_OPTION);
					if (opção == 0) {
						clínica.excluirConsulta(new Agendamento(d, h, m, null));

					}

				} else {

					String nome = JOptionPane.showInputDialog("Paciente");

					if (nome != null) {
						Paciente p = clínica.dadosPaciente(nome);

						if (p != null) {
							clínica.addAgendamento(d, h, m, p);
						} else {
							JOptionPane.showMessageDialog(null, "Paciente não localizado!");
						}
					}
				}
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}));
		scrollPane.setViewportView(table);

		getContentPane().setLayout(groupLayout);

		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				// Método da seleção do Médico no combo
				if (e.getStateChange() == ItemEvent.SELECTED) {
					Médico m = (Médico) e.getItem();

				}
			}
		});

		// Conecta o modelo -> visualização
		combo.addListDataListener(new ListDataListener() {

			public void intervalRemoved(ListDataEvent e) {

			}

			public void intervalAdded(ListDataEvent e) {

			}

			// Conecta o combo e a table
			public void contentsChanged(ListDataEvent e) {

				try {
					table.setModel(new TableModelAgenda(combo.getSelectedItem(), dataAgenda()));
				} catch (ParseException e1) {

					e1.printStackTrace();
				}
			}
		});

	}

	// Método que atualiza a tela de acordo com os dados informados
	private void atualizar() {
		dataAgenda();
		try {
			table.setModel(new TableModelAgenda(combo.getSelectedItem(), dataAgenda()));
		} catch (ParseException e) {

			e.printStackTrace();
		}

	}

	// Método que transforma os dados da data em Date
	public Date dataAgenda() {
		String dTexto = data.getText();
		Date d = new Date();
		try {
			d = formatoData.parse(dTexto);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return d;
	}
}
