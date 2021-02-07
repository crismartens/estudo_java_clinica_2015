package TRABALHO;

import java.util.Date;

//Classe do cadastro dos visitantes, que herda da classe Pessoa
public class Visitante extends Pessoa {

	// Atributos
	private Paciente paciente;
	
	// Método construtor
	public Visitante(String nome, Date dataNascimento, String telefone, String cpf, Paciente paciente) {
		super(nome, dataNascimento, telefone, cpf);

		this.paciente = paciente;

	}

	// Métodos encapsulamento
	public Paciente obterPaciente() {
		return paciente;
	}

	public void editarPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

}
