package it.polito.tdp.genes.model;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

public class Simulatore {
	//dati ingresso
	private int n;
	private Genes g;
	
	//dati uscita
	private List<Genes> inStudio;
	
	//situazione mondo
	private Graph<Genes,DefaultWeightedEdge> grafo;
	private List<Ing> ing;
	private int tempoTrascorso;
	
	//coda eventi
	private Queue<Evento> coda;
	
	public Simulatore(Graph<Genes,DefaultWeightedEdge> grafo) {
		this.grafo=grafo;
		
	}
	
	public void init(Genes g, int n) {
		coda=new PriorityQueue<>();
		this.n=n;
		this.g=g;
		this.tempoTrascorso=0;
		
		for(int id=0;id<n;id++)
			ing.add(new Ing(id));
		
		//caricamento eventi
		
	}
}
