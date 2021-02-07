package TRABALHO;

// Classe do cadastro dos usuários para acesso ao sistema da clínica
public class Usuário {

	// Atributos
	private String login;
	private String senha;
	private Boolean admin;

	// Método construtor
	public Usuário(String login, String senha, Boolean admin) {
		this.login = login;
		this.senha = senha;
		this.admin = admin;
	}

	// Metodo que reescreve o método equals para localizar em uma list de Usuário
	public boolean equals(Object o) {
		Usuário u = (Usuário) o;
		Boolean usuárioLogin = login.equals(u.login);
		Boolean usuárioSenha = senha.equals(u.senha);
		return usuárioLogin && usuárioSenha;
	}

	// Métodos encapsulamento
	public String obterLogin() {
		return login;
	}

	public Boolean obterAdmin() {
		return admin;
	}

	public String obterSenhaUser() {
		return senha;
	}

	public void editarLogin(String login) {
		this.login = login;
	}

	public void editarSenhaUser(String senhaUser) {
		this.senha = senhaUser;
	}

	public void editarAdmin(Boolean admin) {
		this.admin = admin;
	}

}
