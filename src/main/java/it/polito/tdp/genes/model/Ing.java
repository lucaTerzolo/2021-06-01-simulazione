package it.polito.tdp.genes.model;

public class Ing {
	private int id;
	private Genes g;
	
	public Ing(int id) {
		super();
		this.id = id;
	}

	public Genes getG() {
		return g;
	}

	public void setG(Genes g) {
		this.g = g;
	}

	public int getId() {
		return id;
	}
	
}
