package it.polito.tdp.genes.model;

public class Adiacenti {
	private Genes g1;
	private Genes g2;
	private double peso;
	
	
	public Adiacenti(Genes g1, Genes g2, double peso) {
		super();
		this.g1 = g1;
		this.g2 = g2;
		this.peso = peso;
	}
	
	public Genes getG1() {
		return g1;
	}
	public Genes getG2() {
		return g2;
	}
	public double getPeso() {
		return peso;
	}
	
}
