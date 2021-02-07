package TRABALHO.Telas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import TRABALHO.Clínica;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

// Classe da TelaLogin
public class TelaLogin extends JDialog {
	
	// Modelo
	private Clínica clínica;

	// Elementos da interface
	private final JPanel contentPanel = new JPanel();
	private JTextField login;
	private JPasswordField senha;
	private JLabel lblUsurio;
	private JLabel lblSenha;
	private JLabel autorizado;

	// Método construtor
	public TelaLogin(Clínica clínica) {
		setModal(true);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(1);

			}
		});

		this.clínica = clínica;

		setTitle("Login");
		setBounds(100, 100, 444, 484);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			login = new JTextField();
			login.setColumns(10);
		}
		{
			lblUsurio = new JLabel("Usuário");
			lblUsurio.setHorizontalAlignment(SwingConstants.CENTER);
		}
		{
			lblSenha = new JLabel("Senha");
			lblSenha.setHorizontalAlignment(SwingConstants.CENTER);
		}

		senha = new JPasswordField();
		autorizado = new JLabel("");
		autorizado.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TelaLogin.class.getResource("/TRABALHO/Imagens/atenderBemLM.png")));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel
				.setHorizontalGroup(
						gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup().addGap(84)
										.addComponent(autorizado, GroupLayout.PREFERRED_SIZE, 261,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap(88, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING,
						gl_contentPanel.createSequentialGroup().addContainerGap(198, Short.MAX_VALUE)
								.addComponent(lblNewLabel).addGap(104))
				.addGroup(Alignment.TRAILING,
						gl_contentPanel.createSequentialGroup().addContainerGap(219, Short.MAX_VALUE)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPanel.createSequentialGroup()
												.addComponent(lblUsurio, GroupLayout.PREFERRED_SIZE, 64,
														GroupLayout.PREFERRED_SIZE)
												.addGap(48))
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addComponent(lblSenha, GroupLayout.PREFERRED_SIZE, 89,
												GroupLayout.PREFERRED_SIZE)
										.addGap(36))
								.addComponent(senha, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
								.addComponent(login, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
						.addGap(124)));
		gl_contentPanel
				.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING,
						gl_contentPanel.createSequentialGroup().addContainerGap().addComponent(lblNewLabel).addGap(18)
								.addComponent(lblUsurio).addGap(12)
								.addComponent(login, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18).addComponent(lblSenha).addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(senha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
						.addComponent(autorizado, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						// Método que valida o login
						String loginTeste = login.getText();
						String senhaTeste = senha.getText();

						Boolean teste = clínica.verificarUsuário(loginTeste, senhaTeste);

						if (teste == true) {

							dispose();
						} else {
							login.setText(null);
							senha.setText(null);
							autorizado.setText("Acesso nao autorizado!");
							login.requestFocus();
						}

					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Limpar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						// Método para limpar os dados da tela
						login.setText(null);
						senha.setText(null);
						login.requestFocus();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
