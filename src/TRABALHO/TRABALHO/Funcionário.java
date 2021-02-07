package TRABALHO;

import java.util.Date;

// Classe abstrata funcionários, que herda da classe pessoa
public abstract class Funcionário extends Pessoa {

	// Atributos
	private Date dataAdmissão;
	private Double salário;
	private String turno;
	private Usuário usuário;

	// Método construtor
	public Funcionário(String nome, Date dataNascimento, String telefone, String cpf, Date dataAdmissão, Double salário,
			String turno, Usuário usuário) {
		super(nome, dataNascimento, telefone, cpf);

		this.dataAdmissão = dataAdmissão;
		this.salário = salário;
		this.turno = turno;
		this.usuário = usuário;

	}

	// Métodos encapsulamento
	public Date obterDataAdmissão() {
		return dataAdmissão;
	}

	public Double obterSalário() {
		return salário;
	}

	public String obterTurno() {
		return turno;
	}

	public Usuário obterUsuário() {
		return usuário;
	}

	public void editarDataAdmissão(Date dataAdmissão) {
		this.dataAdmissão = dataAdmissão;
	}

	public void editarSalário(Double salário) {
		this.salário = salário;
	}

	public void editarTurno(String turno) {
		this.turno = turno;
	}

	public void editarUsuário(Usuário usuário) {
		this.usuário = usuário;
	}

}
