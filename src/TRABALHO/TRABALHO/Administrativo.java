package TRABALHO;

import java.util.Date;

// Classe do cadastro dos funcionários do setor administrativo, que herda da classe Funcionário
public class Administrativo extends Funcionário {

	// Atributos
	private String função;

	// Método construtor 
	public Administrativo(String nome, Date dataNascimento, String telefone, String cpf, Date dataAdmissão,
			Double salário, String turno, Usuário usuário, String função) {
		super(nome, dataNascimento, telefone, cpf, dataAdmissão, salário, turno, usuário);

		this.função = função;

	}

	// Métodos encapsulamento
	public String obterFunção() {
		return função;
	}

	public void editarFunção(String função) {
		this.função = função;
	}

}
