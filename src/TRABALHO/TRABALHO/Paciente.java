package TRABALHO;

import java.util.Date;

public class Paciente extends Pessoa {

	// Atributos
	private String rg;
	private String endereço;
	
	// Método construtor
	public Paciente(String nome, Date dataNascimento, String telefone, String cpf, String rg, String endereço) {
		super(nome, dataNascimento, telefone, cpf);

		this.rg = rg;
		this.endereço = endereço;
	}

	// Metodo que reescreve o método equals para localizar em uma list de Paciente
	public boolean equals(Object o) {
		Paciente p = (Paciente) o;
		Boolean teste = obterNome().equals(p.obterNome());

		return teste;
	}

	// Métodos encapsulamento
	public String obterRg() {
		return rg;
	}

	public String obterEndereço() {
		return endereço;
	}

	public void editarRg(String rg) {
		this.rg = rg;
	}

	public void editarEndereço(String endereço) {
		this.endereço = endereço;
	}

}
