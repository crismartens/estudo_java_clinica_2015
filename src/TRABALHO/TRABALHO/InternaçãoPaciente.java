package TRABALHO;

import java.util.ArrayList;
import java.util.Date;

//Classe da internação do paciente
public class InternaçãoPaciente {

	// Atributos
	private Paciente paciente;
	private Médico médico;
	private ArrayList<Procedimento> procedimento;
	private Integer quarto;
	private Date horaVisita;
	private ArrayList<Visitante> visitante;
	private String status;

	// Método construtor
	public InternaçãoPaciente(Paciente paciente, Médico médico, Integer quarto, Date horaVisita, String status) {
		this.paciente = paciente;
		this.médico = médico;
		procedimento = new ArrayList<Procedimento>();
		this.quarto = quarto;
		this.horaVisita = horaVisita;
		visitante = new ArrayList<Visitante>();
		this.status = status;

	}

	// Métodos encapsulamento
	public Paciente obterPaciente() {
		return paciente;
	}

	public Médico obterMédico() {
		return médico;
	}

	public ArrayList<Procedimento> obterProcedimento() {
		return procedimento;
	}

	public Integer obterQuarto() {
		return quarto;
	}

	public Date obterHoraVisita() {
		return horaVisita;
	}

	public ArrayList<Visitante> obterVisitante() {
		return visitante;
	}

	public String obterStatus() {
		return status;
	}

	public void editarPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public void editarMédico(Médico médico) {
		this.médico = médico;
	}

	public void editarQuarto(Integer quarto) {
		this.quarto = quarto;
	}

	public void editarHoraVista(Date horaVisita) {
		this.horaVisita = horaVisita;
	}

	public void editarStatus(String status) {
		this.status = status;
	}

}
