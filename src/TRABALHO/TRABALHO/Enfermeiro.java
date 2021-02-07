package TRABALHO;

import java.util.Date;

// Classe do cadastro dos enfermeiros, que herda da classe Funcionário 
public class Enfermeiro extends Funcionário {

	// Atributos
	private String corem;

	// Método construtor
	public Enfermeiro(String nome, Date dataNascimento, String telefone, String cpf, Date dataAdmissão, Double salário,
			String turno, Usuário usuário, String corem) {
		super(nome, dataNascimento, telefone, cpf, dataAdmissão, salário, turno, usuário);

		this.corem = corem;
	}

	// Métodos encapsulamento
	public String obterCorem() {
		return corem;
	}

	public void editarCorem(String corem) {
		this.corem = corem;
	}

}
