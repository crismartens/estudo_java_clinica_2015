package TRABALHO;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

// Classe fachada do modelo
public class Clínica extends Observable {

	// Atributos
	private ArrayList<Usuário> usuários = new ArrayList<Usuário>();
	private ArrayList<Administrativo> admins = new ArrayList<Administrativo>();
	private ArrayList<Enfermeiro> enfermeiros = new ArrayList<Enfermeiro>();
	private ArrayList<Médico> médicos = new ArrayList<Médico>();
	private ArrayList<Visitante> visitantes = new ArrayList<Visitante>();
	private ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
	private ArrayList<InternaçãoPaciente> internaçãoPacientes = new ArrayList<InternaçãoPaciente>();
	private DateFormat formatoData = DateFormat.getDateInstance();
	private DateFormat formatoHora = DateFormat.getTimeInstance();

	// Método construtor
	public Clínica() {

	}

	// Métodos para adicionar objetos as list da clínica
	public Usuário addUsuário(String login, String senha, Boolean admin) {
		Usuário u = new Usuário(login, senha, admin);
		usuários.add(u);
		setChanged();
		notifyObservers();
		return u;
	}

	public void addAdmins(String nome, Date dataNascimento, String telefone, String cpf, Date dataAdmissão,
			Double salário, String turno, Usuário usuário, String função) {
		Administrativo a = new Administrativo(nome, dataNascimento, telefone, cpf, dataAdmissão, salário, turno,
				usuário, função);
		admins.add(a);
	}

	public void addEnfermeiros(String nome, Date dataNascimento, String telefone, String cpf, Date dataAdmissão,
			Double salário, String turno, Usuário usuário, String corem) {
		Enfermeiro e = new Enfermeiro(nome, dataNascimento, telefone, cpf, dataAdmissão, salário, turno, usuário,
				corem);
		enfermeiros.add(e);
	}

	public Médico addMédicos(String nome, Date dataNascimento, String telefone, String cpf, Date dataAdmissão,
			Double salário, String turno, Usuário usuário, String espMédico, String crm) {
		Médico m = new Médico(nome, dataNascimento, telefone, cpf, dataAdmissão, salário, turno, usuário, espMédico,
				crm);
		médicos.add(m);
		setChanged();
		notifyObservers(m);
		return m;
	}

	public void addVisitante(String nome, Date dataNascimento, String telefone, String cpf, Paciente paciente) {
		Visitante v = new Visitante(nome, dataNascimento, telefone, cpf, paciente);
		visitantes.add(v);
	}

	public void addPaciente(String nome, Date dataNascimento, String telefone, String cpf, String rg, String endereço) {
		Paciente p = new Paciente(nome, dataNascimento, telefone, cpf, rg, endereço);
		pacientes.add(p);
	}

	public void addInternação(Paciente paciente, Médico médico, Integer quarto, Date horaVisita, String status) {
		InternaçãoPaciente i = new InternaçãoPaciente(paciente, médico, quarto, horaVisita, status);
		internaçãoPacientes.add(i);
	}

	public void addAgendamento(Date dataConsulta, Date horaConsulta, Médico médico, Paciente paciente) {
		Agendamento a = new Agendamento(dataConsulta, horaConsulta, médico, paciente);
		médico.addAgenda(a);
		setChanged();
		notifyObservers(a);
	}

	// Validação de login da list de Usuário
	public Boolean verificarUsuário(String usuário, String senha) {
		Usuário u = new Usuário(usuário, senha, null);
		return usuários.contains(u);
	}

	// Validação de cpf na list de Paciente
	public Boolean testeCPF(String cpfTeste) {
		Boolean t = false;
		Paciente x = null;
		for (int i = 0; i < pacientes.size(); i++) {
			x = pacientes.get(i);
			String cpf = x.obterCpf();
			if (cpfTeste.equals(cpf)) {
				t = true;
			}
		}
		return t;
	}

	// Validação de cpf na list de Paciente, retorna o Paciente da list com o
	// cpf informado
	public Paciente verificarCPF(String cpfTeste) {
		Paciente x = null;
		Paciente t = null;
		for (int i = 0; i < pacientes.size(); i++) {
			x = pacientes.get(i);
			String cpf = x.obterCpf();
			if (cpfTeste.equals(cpf)) {
				t = x;
			}
		}
		return t;
	}

	// Validação de nome na list de Paciente
	public Boolean verificarNome(String nomeTeste) {
		Paciente p = new Paciente(nomeTeste, null, null, null, null, null);
		return pacientes.contains(p);
	}

	// Validação de nome na list de Paciente, retorna o Paciente da list com o
	// nome informado
	public Paciente dadosPaciente(String nomeTeste) {
		Paciente p = new Paciente(nomeTeste, null, null, null, null, null);
		Paciente x = null;
		Paciente z = null;
		for (int i = 0; i < pacientes.size(); i++) {
			x = pacientes.get(i);
			if (x.equals(p)) {
				z = x;
			}
		}
		return z;
	}

	// Validação de nome na list de Médico
	public Boolean verificarMédico(String médico) {
		Médico m = new Médico(médico, null, null, null, null, null, null, null, null, null);
		return médicos.contains(m);
	}

	// Validação de nome na list de Médico, retorna o Médico da list com o nome
	// informado
	public Médico dadosMédico(String nome) {
		Médico m = new Médico(nome, null, null, null, null, null, null, null, null, null);
		Médico x = null;
		Médico z = null;
		for (int i = 0; i < médicos.size(); i++) {
			x = médicos.get(i);
			if (x.equals(m)) {
				z = x;
			}
		}
		return z;
	}

	// Validação da data,hora e Médico na list de Agendamento do Médico
	public Boolean verificarAgenda(Date dataTeste, Date horaTeste, Médico m) {
		Agendamento a = new Agendamento(dataTeste, horaTeste, m, null);
		return m.verificarAgendaMédico(a);

	}

	// Validação data,hora e Médico na list de agendamento do Médico, retorna o
	// Agendamento da list com os dados informados
	public Agendamento dadosAgendamento(Date dataTeste, Date horaTeste, Médico m) {
		Agendamento a = new Agendamento(dataTeste, horaTeste, m, null);
		Agendamento x = null;
		Agendamento z = null;

		for (int i = 0; i < m.obterAgenda().size(); i++) {
			x = m.obterAgenda().get(i);
			if (x.equals(a)) {
				z = x;
			}
		}
		return z;
	}

	// Métodos das estruturas das lists
	public int obterNúmeroDeMédicos() {
		return médicos.size();
	}

	public Integer obterPosiçãoDoMédico(Médico m) throws Exception {
		Integer posição = médicos.indexOf(m);
		if (posição < 0) {
			throw new Exception();
		}
		return posição;
	}

	public Médico obterMédicoPorPosição(int posição) {
		return médicos.get(posição);

	}

	// Métodos para remover das lists
	public void removerPaciente(Paciente p) {
		pacientes.remove(p);

	}

	public void removerMédico(Médico m) {
		médicos.remove(m);

	}

	public void removerAdministrativo(Administrativo a) {
		admins.remove(a);

	}

	public void removerEnfermeiro(Enfermeiro e) {
		enfermeiros.remove(e);

	}

	public void removerVisitante(Visitante v) {
		visitantes.remove(v);

	}

	public void removerUsuário(Usuário u) {
		if (u.obterAdmin()) {

		} else {
			usuários.remove(u);
		}

	}

	public void removerInternção(Paciente i) {
		internaçãoPacientes.remove(i);

	}

	public void excluirConsulta(Agendamento a) {
		Médico m = a.obterMédico();
		m.excluirConsulta(a);

	}

	// Método para popular as lists, dados iniciados junto com a clínica
	public void criarRegistros() throws ParseException {
		addUsuário("Admin", "Admin", true);
		addUsuário("User", "User", false);

		addAdmins("Admin 1", null, null, null, null, null, null, null, null);
		addAdmins("Admin 2", null, null, null, null, null, null, null, null);
		addAdmins("Admin 3", null, null, null, null, null, null, null, null);

		addEnfermeiros("Enfermeiro 1", null, null, null, null, null, null, null, null);
		addEnfermeiros("Enfermeiro 2", null, null, null, null, null, null, null, null);
		addEnfermeiros("Enfermeiro 3", null, null, null, null, null, null, null, null);

		Médico m1 = new Médico("Médico 1", null, null, null, null, null, null, null, null, null);
		médicos.add(m1);
		Médico m2 = new Médico("Médico 2", null, null, null, null, null, null, null, null, null);
		médicos.add(m2);
		Médico m3 = new Médico("Médico 3", null, null, null, null, null, null, null, null, null);
		médicos.add(m3);

		addVisitante("Visitante 1", null, null, null, null);
		addVisitante("Visitante 2", null, null, null, null);
		addVisitante("Visitante 3", null, null, null, null);

		Paciente p1 = new Paciente("Paciente 1", formatoData.parse("01/01/2015"), null, "cpf 1", null, null);
		pacientes.add(p1);
		Paciente p2 = new Paciente("Paciente 2", null, null, "cpf 2", null, null);
		pacientes.add(p2);
		Paciente p3 = new Paciente("Paciente 3", null, null, "cpf 3", null, null);
		pacientes.add(p3);

		addInternação(p1, m1, null, null, null);
		addInternação(p2, m2, null, null, null);
		addInternação(p3, m3, null, null, null);

		addAgendamento(formatoData.parse("08/12/2015"), formatoHora.parse("09:00:00"), m1, p1);
		addAgendamento(formatoData.parse("08/12/2015"), formatoHora.parse("10:00:00"), m2, p1);
		addAgendamento(formatoData.parse("08/12/2015"), formatoHora.parse("11:00:00"), m3, p1);
		
		addAgendamento(formatoData.parse("09/12/2015"), formatoHora.parse("09:00:00"), m1, p1);
		addAgendamento(formatoData.parse("09/12/2015"), formatoHora.parse("10:00:00"), m2, p1);
		addAgendamento(formatoData.parse("09/12/2015"), formatoHora.parse("11:00:00"), m3, p1);
		
		addAgendamento(formatoData.parse("10/12/2015"), formatoHora.parse("09:00:00"), m1, p1);
		addAgendamento(formatoData.parse("10/12/2015"), formatoHora.parse("10:00:00"), m2, p1);
		addAgendamento(formatoData.parse("10/12/2015"), formatoHora.parse("11:00:00"), m3, p1);

		addAgendamento(formatoData.parse("08/12/2015"), formatoHora.parse("12:00:00"), m1, p2);
		addAgendamento(formatoData.parse("08/12/2015"), formatoHora.parse("13:00:00"), m2, p2);
		addAgendamento(formatoData.parse("08/12/2015"), formatoHora.parse("14:00:00"), m3, p2);
		
		addAgendamento(formatoData.parse("09/12/2015"), formatoHora.parse("12:00:00"), m1, p2);
		addAgendamento(formatoData.parse("09/12/2015"), formatoHora.parse("13:00:00"), m2, p2);
		addAgendamento(formatoData.parse("09/12/2015"), formatoHora.parse("14:00:00"), m3, p2);
		
		addAgendamento(formatoData.parse("10/12/2015"), formatoHora.parse("12:00:00"), m1, p2);
		addAgendamento(formatoData.parse("10/12/2015"), formatoHora.parse("13:00:00"), m2, p2);
		addAgendamento(formatoData.parse("10/12/2015"), formatoHora.parse("14:00:00"), m3, p2);
		
		addAgendamento(formatoData.parse("08/12/2015"), formatoHora.parse("15:00:00"), m1, p3);
		addAgendamento(formatoData.parse("08/12/2015"), formatoHora.parse("16:00:00"), m2, p3);
		addAgendamento(formatoData.parse("08/12/2015"), formatoHora.parse("17:00:00"), m3, p3);
		
		addAgendamento(formatoData.parse("09/12/2015"), formatoHora.parse("15:00:00"), m1, p3);
		addAgendamento(formatoData.parse("09/12/2015"), formatoHora.parse("16:00:00"), m2, p3);
		addAgendamento(formatoData.parse("09/12/2015"), formatoHora.parse("17:00:00"), m3, p3);
		
		addAgendamento(formatoData.parse("10/12/2015"), formatoHora.parse("15:00:00"), m1, p3);
		addAgendamento(formatoData.parse("10/12/2015"), formatoHora.parse("16:00:00"), m2, p3);
		addAgendamento(formatoData.parse("10/12/2015"), formatoHora.parse("17:00:00"), m3, p3);
	}

}
