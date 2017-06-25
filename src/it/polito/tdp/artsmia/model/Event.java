package it.polito.tdp.artsmia.model;

public class Event implements Comparable<Event> {
	
	private Studente studente;
	//private int fase;
	private Exhibition mostra;
	

	public Event(Studente studente, Exhibition mostra) {
		super();
		this.studente = studente;
		//this.fase = fase;
		this.mostra = mostra;
	}


	public Studente getStudente() {
		return studente;
	}


	public void setStudente(Studente studente) {
		this.studente = studente;
	}


//	public int getFase() {
//		return fase;
//	}
//
//
//	public void setFase(int fase) {
//		this.fase = fase;
//	}


	public Exhibition getMostra() {
		return mostra;
	}


	public void setMostra(Exhibition mostra) {
		this.mostra = mostra;
	}

	@Override
	public int compareTo(Event altro) {
		return 0;
	}
	

}
