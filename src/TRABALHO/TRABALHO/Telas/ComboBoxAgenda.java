package TRABALHO.Telas;

import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import TRABALHO.Clínica;
import TRABALHO.Médico;

// Classe ComboBox
public class ComboBoxAgenda extends AbstractListModel<Médico>implements ComboBoxModel<Médico> {

	// Modelo
	private Clínica clínica;

	// Atributos
	private Médico selecionado;

	// Método construtor
	public ComboBoxAgenda(Clínica clínica) {
		this.clínica = clínica;

		// Observador
		this.clínica.addObserver(new Observer() {
			public void update(Observable o, Object arg) {
				// Observador informado
				clínicaModificada(arg);
			}
		});
	}

	// Observador informado
	private void clínicaModificada(Object arg) {

		if (arg.getClass() == Médico.class) {
			// Evento: Médico adicionado
			Médico m = (Médico) arg;
			Integer posição;
			try {
				posição = clínica.obterPosiçãoDoMédico(m);
				// Notifica altera inclusão de linha no ComboBox
				fireIntervalAdded(this, posição, posição);
			} catch (Exception e) {
				throw new Error(e);
			}
		}
	}

	

	public Médico getElementAt(int index) {
		// Visualização do Médico gerado automaticamente com toString
		return clínica.obterMédicoPorPosição(index);
	}

	public int getSize() {
		return clínica.obterNúmeroDeMédicos();
	}

	public Object getSelectedItem() {
		return selecionado;
	}

	public void setSelectedItem(Object anItem) {
		// Médico seleciona no ComboBox
		if (anItem != selecionado) {
			selecionado = (Médico) anItem;
			fireContentsChanged(this, -1, -1);

		}

	}

}
