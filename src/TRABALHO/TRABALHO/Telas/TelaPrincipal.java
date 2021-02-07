package TRABALHO.Telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JToolBar;

import TRABALHO.Clínica;

import javax.swing.Box;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// Classe da tela principal, tele iniciada junto do sistema
public class TelaPrincipal {

	// Modelo
	private Clínica clínica;

	// Elementos da interface
	private JFrame frm;
	private JPanel panel;
	private JMenuBar menuBar;
	private JMenu mnCadastrar;
	private JMenuItem mntmFuncionrio;
	private JMenuItem mntmNewMenuItem_1;
	private JMenuItem mntmVisitante;
	private JMenu mnAgendamento;
	private JMenuItem mntmAgendamento;
	private JMenu mnInternação;
	private JMenuItem mntmNovaConsulta;
	private JMenu mnAdministrativo;
	private JMenu mnAjuda;

	// Método construtor
	public TelaPrincipal(Clínica clínica) {

		this.clínica = clínica;

		initialize();

	}

	// Método de tudo que é criado junto do TelaPrincipal, contém as ações dos elementos da TelaPrincipal
	private void initialize() {

		frm = new JFrame();
		frm.setTitle("Clínica Atender Bem");
		frm.setBounds(100, 100, 450, 300);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().setLayout(null);
		frm.setExtendedState(JFrame.MAXIMIZED_BOTH);

		panel = new JPanel();
		panel.setBounds(0, 0, 434, 225);
		frm.getContentPane().add(panel);

		menuBar = new JMenuBar();
		frm.setJMenuBar(menuBar);

		mnCadastrar = new JMenu("");
		mnCadastrar.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/TRABALHO/Imagens/CADASTROicon.png")));
		mnCadastrar.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 12));
		menuBar.add(mnCadastrar);
		mnCadastrar.setToolTipText("CADASTRAR");

		mntmFuncionrio = new JMenuItem("Funcionário");
		mnCadastrar.add(mntmFuncionrio);

		mntmNewMenuItem_1 = new JMenuItem("Paciente");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Abre a TelaCadastroPaciente
				TelaCadastroPaciente cp = new TelaCadastroPaciente(clínica);
				cp.setVisible(true);
			}
		});
		mnCadastrar.add(mntmNewMenuItem_1);

		mntmVisitante = new JMenuItem("Visitante");
		mnCadastrar.add(mntmVisitante);

		mnAgendamento = new JMenu("");
		mnAgendamento.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/TRABALHO/Imagens/CONSULTAicon.png")));
		mnAgendamento.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 12));
		menuBar.add(mnAgendamento);
		mnAgendamento.setToolTipText("AGENDAMENTO");

		mntmAgendamento = new JMenuItem("Agenda");
		mntmAgendamento.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// Abre a TelaAgenda
				TelaAgenda ag = new TelaAgenda(clínica);
				ag.setVisible(true);
			}
		});
		mnAgendamento.add(mntmAgendamento);

		mntmNovaConsulta = new JMenuItem("Nova Consulta");
		mntmNovaConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Abre a TelaNovaConsulta
				TelaNovaConsulta nc = new TelaNovaConsulta(clínica);
				nc.setVisible(true);

			}
		});
		mnAgendamento.add(mntmNovaConsulta);

		mnInternação = new JMenu("");
		mnInternação.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/TRABALHO/Imagens/INTERNACAOicon.png")));
		mnInternação.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 12));
		menuBar.add(mnInternação);
		mnInternação.setToolTipText("INTERNAÇÃO");

		mnAdministrativo = new JMenu("");
		mnAdministrativo.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/TRABALHO/Imagens/ADMINicon.png")));
		mnAdministrativo.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 12));
		menuBar.add(mnAdministrativo);
		mnAdministrativo.setToolTipText("ADMINISTRATIVO");

		mnAjuda = new JMenu("");
		mnAjuda.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/TRABALHO/Imagens/AJUDAicon.png")));
		mnAjuda.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 12));
		menuBar.add(mnAjuda);
		mnAjuda.setToolTipText("AJUDA");

	}

	// Método que inicializa o programa
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					// Cria o objeto Clínica, que será utilizado enquanto a aplicação estiver rodando por todas as classes
					Clínica clínica = new Clínica();

					// Abre a TelaPrincicpal
					TelaPrincipal frame = new TelaPrincipal(clínica);
					frame.frm.setVisible(true);

					// Popula a clínica
					clínica.criarRegistros();

					// Abre a TelaLogin
					TelaLogin tela = new TelaLogin(clínica);
					tela.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
