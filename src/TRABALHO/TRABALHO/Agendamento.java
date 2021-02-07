package TRABALHO;

import java.text.DateFormat;
import java.util.Date;
import java.util.Observable;

// Classe dos agendamentos, cada médico possui ums list de Agendamento
public class Agendamento extends Observable {

	// Atributos
	private Date dataConsulta;
	private Date horaConsulta;
	private Médico médico;
	private Paciente paciente;
	private DateFormat formatoData = DateFormat.getDateInstance();
	private DateFormat formatoHora = DateFormat.getTimeInstance();

	// Método construtor
	public Agendamento(Date data, Date hora, Médico médico, Paciente paciente) {
		this.dataConsulta = data;
		this.horaConsulta = hora;
		this.médico = médico;
		this.paciente = paciente;

	}

	// Metodo que reescreve o método equals para localizar um agendamento em uma list de Agendamento
	public boolean equals(Object o) {
		Agendamento a = (Agendamento) o;
		boolean agendaData = dataConsulta.equals(a.dataConsulta);
		boolean agendaHora = horaConsulta.equals(a.horaConsulta);
		Boolean teste = agendaData && agendaHora;
		return teste;
	}

	// Métodos encapsulamento
	public Date obterDataConsulta() {
		return dataConsulta;
	}

	public Date obterHoraConsulta() {
		return horaConsulta;
	}

	public Médico obterMédico() {
		return médico;
	}

	public Paciente obterPaciente() {
		return paciente;
	}

	public void editarDataConsulta(Date dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public void editarHoraConsulta(Date horaConsulta) {
		this.horaConsulta = horaConsulta;
	}

	public void editarMédico(Médico médico) {
		this.médico = médico;
	}

	public void editarPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

}
