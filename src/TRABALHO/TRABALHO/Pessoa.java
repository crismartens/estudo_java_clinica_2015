package TRABALHO;

import java.util.Date;
import java.util.Observable;

//Classe abstrata pessoas
public abstract class Pessoa {

	// Atributos
	private String nome;
	private Date dataNascimento;
	private String telefone;
	private String cpf;

	// Método construtor
	public Pessoa(String nome, Date dataNascimento, String telefone, String cpf) {

		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.telefone = telefone;
		this.cpf = cpf;

	}

	// Metodo que reescreve o método equals para localizar em uma list de Pessoa
	public boolean equals(Object o) {
		Pessoa p = (Pessoa) o;
		Boolean pessoaNome = nome.equals(p.nome);

		return pessoaNome;

	}

	// Métodos encapsulamento
	public String obterNome() {
		return nome;
	}

	public Date obterDataNascimento() {
		return dataNascimento;
	}

	public String obterTelefone() {
		return telefone;
	}

	public String obterCpf() {
		return cpf;
	}

	public void editarNome(String nome) {
		this.nome = nome;

	}

	public void editarDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public void editarTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void editarCpf(String cpf) {
		this.telefone = cpf;
	}

	// Método que escreve o método toString para identificação de um objeto
	// pessoa
	public String toString() {
		return nome;
	}

}
