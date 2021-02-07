package TRABALHO;

import java.util.ArrayList;
import java.util.Date;
import java.util.Observer;

// Classe do cadastro dos funcionários do setor administrativo, que herda da classe Funcionário
public class Médico extends Funcionário {

	// Atributos
	private String espMédico;
	private String crm;
	private ArrayList<Agendamento> agenda = new ArrayList<Agendamento>();

	// Método construtor
	public Médico(String nome, Date dataNascimento, String telefone, String cpf, Date dataAdmissão, Double salário,
			String turno, Usuário usuário, String espMédico, String crm) {
		super(nome, dataNascimento, telefone, cpf, dataAdmissão, salário, turno, usuário);

		this.espMédico = espMédico;
		this.crm = crm;

	}

	// Metodo que reescreve o método equals para localizar em uma list de Médico
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		Médico m = (Médico) o;
		Boolean médicoNome = obterNome().equals(m.obterNome());

		return médicoNome;

	}

	// Métodos do atributo agenda
	public void addAgenda(Agendamento a) {
		agenda.add(a);
	}

	public ArrayList<Agendamento> obterAgenda() {
		return agenda;
	}

	public Boolean verificarAgendaMédico(Agendamento a) {
		return agenda.contains((Agendamento) a);

	}

	public Integer obterPosiçãoDoAgendamento(Agendamento a) throws Exception {
		Integer posição = agenda.indexOf(a);
		if (posição < 0) {
			throw new Exception();
		}
		return posição;
	}

	public void excluirConsulta(Agendamento a) {
		agenda.remove(a);

	}

	// Métodos encapsulamento

	
	public String obterEspMédico() {
		return espMédico;
	}

	public String obterCrm() {
		return crm;
	}

	public void editarEspMédico(String espMédico) {
		this.espMédico = espMédico;
		
	}

	public void editarCrm(String crm) {
		this.crm = crm;
	}

}
