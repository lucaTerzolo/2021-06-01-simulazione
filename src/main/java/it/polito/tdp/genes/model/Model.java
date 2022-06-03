package it.polito.tdp.genes.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.genes.db.GenesDao;

public class Model {
	
	private Map<String,Genes> idMap;
	private Graph<Genes,DefaultWeightedEdge> grafo;
	private GenesDao dao;
	private Simulatore sim;
	
	public Model() {
		idMap=new HashMap<>();
		dao=new GenesDao();

		dao.getAllGenes(idMap);
		sim=new Simulatore();
	}
	
	public void creaGrafo() {
		grafo=new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.grafo, dao.getVertici(idMap));
		
		for(Adiacenti a:dao.getArchi(idMap)) {
			Graphs.addEdge(this.grafo, a.getG1(), a.getG2(), a.getPeso());
		}
	}

	public int vertici() {
		return this.grafo.vertexSet().size();
	}

	public int archi() {
		return this.grafo.edgeSet().size();
	}

	public List<Genes> getAllVertici() {
		List<Genes> result=new ArrayList<>(this.grafo.vertexSet());
		return result;
	}
	
	public List<Adiacenti> getAdiacenti(Genes g){
		List<Adiacenti> result=new ArrayList<>();
		
		for(DefaultWeightedEdge e: this.grafo.edgesOf(g)) {
			if(grafo.getEdgeSource(e).equals(g))
				result.add(new Adiacenti(g,grafo.getEdgeTarget(e),grafo.getEdgeWeight(e)));
			else
				result.add(new Adiacenti(g,grafo.getEdgeSource(e),grafo.getEdgeWeight(e)));
		}
		result.sort(new ComparatorPesi());
		
		return result;
	}
	
	public List<Genes> simula(Genes g,int n){
		sim.init(g, n, grafo);
		sim.run();
		return sim.getInStudio();
	}
}
