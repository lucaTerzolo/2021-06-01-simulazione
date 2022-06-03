package it.polito.tdp.genes.model;

public class Evento implements Comparable<Evento>{
	
	private int tempo;
	private Ing ing;
	
	public Evento(int tempo, Ing ing) {
		super();
		this.tempo = tempo;
		this.ing = ing;
	}

	public int getTempo() {
		return tempo;
	}

	public Ing getIng() {
		return ing;
	}

	@Override
	public int compareTo(Evento o) {
		return this.tempo-o.getTempo();
	}
	
}
