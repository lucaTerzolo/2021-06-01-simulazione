package it.polito.tdp.genes.model;

import java.util.Comparator;

public class ComparatorPesi implements Comparator<Adiacenti> {

	@Override
	public int compare(Adiacenti o1, Adiacenti o2) {
		return (int) (o2.getPeso()-o1.getPeso());
	}

}
