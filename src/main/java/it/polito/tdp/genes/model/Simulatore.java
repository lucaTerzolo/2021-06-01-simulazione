package it.polito.tdp.genes.model;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;

public class Simulatore {
	//dati ingresso
	private int n; //numero di mesi
	private Genes g;
	
	private int tMax=36;
	
	//dati uscita
	private List<Genes> inStudio;
	
	//situazione mondo
	private Graph<Genes,DefaultWeightedEdge> grafo;
	private List<Ing> ing;
	
	//coda eventi
	private Queue<Evento> coda;
	
	public Simulatore() {
		
		ing=new ArrayList<>();
	}
	
	public void init(Genes g, int n,Graph<Genes,DefaultWeightedEdge> grafo) {
		coda=new PriorityQueue<>();
		this.n=n;
		this.g=g;
		this.grafo=grafo;
		
		for(int id=0;id<n;id++)
			ing.add(new Ing(id));
		
		//caricamento eventi
		if(this.grafo.edgesOf(g).size()==0)
			throw new IllegalArgumentException("Vertice di partenza isolato") ;
		for(Ing i: ing) {
			this.coda.add(new Evento(0,i));
			i.setG(g);
		}
	}
	
	public void run() {
		while(!coda.isEmpty()) {
			Evento e =coda.poll();
			int t=e.getTempo();
			Ing i=e.getIng();
			Genes gen=i.getG();
			
			if(t<tMax) {
				double prob=Math.random();
				if(prob<0.3) {
					this.coda.add(new Evento(t+1,i));
				}else {
					double sommaPesi=0;
					for(DefaultWeightedEdge ed:this.grafo.edgesOf(g)) {
						sommaPesi+=this.grafo.getEdgeWeight(ed);
					}
					double caso=Math.random()*sommaPesi;
					sommaPesi=0;
					Genes daAnalizzare=null;
					for(DefaultWeightedEdge ed:this.grafo.edgesOf(g)) {
						sommaPesi+=this.grafo.getEdgeWeight(ed);
						if(sommaPesi>caso) {
							daAnalizzare=Graphs.getOppositeVertex(this.grafo, ed, gen);
							break;
						}
					}
					this.coda.add(new Evento(t+1,i));
					i.setG(daAnalizzare);
				}
			}
		}
	}
	
	public List<Genes> getInStudio(){
		List<Genes> result=new ArrayList<>();
		for(Ing in:ing) {
			result.add(in.getG());
		}
		return result;
	}
}
