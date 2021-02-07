package TRABALHO;

//Classe dos procedimentos, cada Agendamento possui ums list de Processo
public class Procedimento {

	// Atributos
	private Funcionário funcionário;
	private String nomeProcedimento;

	// Método construtor
	public Procedimento(Funcionário funcionário, String nomeProcedimento) {
		this.funcionário = funcionário;
		this.nomeProcedimento = nomeProcedimento;
	}

	// Métodos encapsulamento
	public Funcionário obterFuncionário() {
		return funcionário;
	}

	public String obterNomeProcedimento() {
		return nomeProcedimento;
	}

	public void editarFuncionário(Funcionário funcionário) {
		this.funcionário = funcionário;
	}

	public void editarNomeProcedimento(String nomeProcedimento) {
		this.nomeProcedimento = nomeProcedimento;
	}

}
