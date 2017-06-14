package it.polito.tdp.artsmia.model;

import java.util.Map;

public class Studente implements Comparable<Studente> {

	private int id;
	private Map<Integer,ArtObject> opere;


	public Studente(int id) {
		super();
		this.id = id;
	}
	public Map<Integer,ArtObject> getOpere() {
		return opere;
	}
	public void setOpere(Map<Integer,ArtObject> opere) {
		this.opere = opere;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Studente other = (Studente) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public int compareTo(Studente s) {
		return -(this.getOpere().size()-s.getOpere().size());
	}
	@Override
	public String toString() {
		return "Studente "+ id;
	}







}
