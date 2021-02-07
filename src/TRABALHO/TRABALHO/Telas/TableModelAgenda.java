package TRABALHO.Telas;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import TRABALHO.Agendamento;
import TRABALHO.Clínica;
import TRABALHO.Médico;
import TRABALHO.Paciente;

// Classe TableModel
public class TableModelAgenda extends AbstractTableModel {

	// Modelo
	private Clínica clínica;

	// Atriburos
	private DateFormat formatoData = DateFormat.getDateInstance();
	private DateFormat formatoHora = DateFormat.getTimeInstance(DateFormat.SHORT);
	private ArrayList<Date> horasAgenda = new ArrayList<Date>();
	private ArrayList<Date> datas = new ArrayList<Date>();
	private Médico selecionado;
	private Date data;

	// Método construtor
	public TableModelAgenda(Object o, Date data) throws ParseException {

		this.data = data;
		this.selecionado = (Médico) o;

		// Colunas
		Date dataMenos1 = new Date(data.getTime() - 86400000);
		Date dataMais1 = new Date(data.getTime() + 86400000);
		Date dataMenos2 = new Date(data.getTime() - (2 * 86400000));
		Date dataMais2 = new Date(data.getTime() + (2 * 86400000));

		datas.add(dataMenos2);
		datas.add(dataMenos1);
		datas.add(data);
		datas.add(dataMais1);
		datas.add(dataMais2);

		// Linhas
		horasAgenda.add(formatoHora.parse("08:00:00"));
		horasAgenda.add(formatoHora.parse("09:00:00"));
		horasAgenda.add(formatoHora.parse("10:00:00"));
		horasAgenda.add(formatoHora.parse("11:00:00"));
		horasAgenda.add(formatoHora.parse("12:00:00"));
		horasAgenda.add(formatoHora.parse("13:00:00"));
		horasAgenda.add(formatoHora.parse("14:00:00"));
		horasAgenda.add(formatoHora.parse("15:00:00"));
		horasAgenda.add(formatoHora.parse("16:00:00"));
		horasAgenda.add(formatoHora.parse("17:00:00"));
	
		// Observador para toda a list de Agendamento do Médico
		for (Agendamento a : this.selecionado.obterAgenda()) {
			a.addObserver(new Observer() {
				public void update(Observable o, Object arg) {
					// Evento: Agendamento adicionado
					if (arg.getClass() == Agendamento.class) {

						Agendamento a = (Agendamento) arg;

						try {
							Integer posição = selecionado.obterPosiçãoDoAgendamento(a);
							fireTableRowsInserted(posição, posição);
						} catch (Exception e) {
							throw new Error(e);
						}
					}
				}
			});
		}

	}

	// Número de colunas
	public int getColumnCount() {
		return 6;

	}

	// Número de linhas
	public int getRowCount() {
		return 10;
	}

	// Nome das colunas
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "Hora";
		case 1:
			return formatoData.format(datas.get(0));
		case 2:
			return formatoData.format(datas.get(1));
		case 3:
			return formatoData.format(datas.get(2));
		case 4:
			return formatoData.format(datas.get(3));
		case 5:
			return formatoData.format(datas.get(4));
		}
		return "";
	}

	// Nomes das linhas
	public String getRowName(int row) {
		switch (row) {
		case 0:
			return formatoData.format(horasAgenda.get(0));
		case 1:
			return formatoData.format(horasAgenda.get(1));
		case 2:
			return formatoData.format(horasAgenda.get(2));
		case 3:
			return formatoData.format(horasAgenda.get(3));
		case 4:
			return formatoData.format(horasAgenda.get(4));
		case 5:
			return formatoData.format(horasAgenda.get(5));
		case 6:
			return formatoData.format(horasAgenda.get(6));
		case 7:
			return formatoData.format(horasAgenda.get(7));
		case 8:
			return formatoData.format(horasAgenda.get(8));
		case 9:
			return formatoData.format(horasAgenda.get(9));
		}
		return "";
	}

	// Método que apresenta os valores na tabela
	public Object getValueAt(int rowIndex, int columnIndex) {
		ArrayList<Agendamento> a = selecionado.obterAgenda();
		Agendamento x = null;
		Paciente p = null;
		String z = null;

		if (0 == columnIndex) {
			// Valore da primeira coluna (horários do agendamento)
			for (int j = 0; j < horasAgenda.size(); j++)
				if (j == rowIndex) {
					z = formatoHora.format((horasAgenda.get(j)));

					return z;

				}

		} else {

			for (int i = 0; i < a.size(); i++) {

				x = a.get(i);
				if (formatoData.format((x.obterDataConsulta()))
						.equals(formatoData.format(datas.get(columnIndex - 1)))) {

					if (formatoHora.format((x.obterHoraConsulta()))
							.equals(formatoHora.format(horasAgenda.get(rowIndex)))) {

						p = x.obterPaciente();

					}

				}
			}
		}

		return p;
	}

	// Métodos das list das linhas e colunas da tabela
	public Integer obterHorasAgenda(Date d) throws Exception {
		Integer posição = horasAgenda.indexOf(d);
		if (posição < 0) {
			throw new Exception();
		}
		return posição;
	}

	public Date obterHorasAgendaPorPosição(int posição) {
		return horasAgenda.get(posição);

	}

	public Date obterDatasPorPosição(int posição) {
		return datas.get(posição);

	}

}