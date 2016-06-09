package app;

import java.util.ArrayList;

public class PetriNetwork {

	private ArrayList<Place> places;
	private ArrayList<Transition> transitions;
	private ArrayList<Edge> edges;
	private ArrayList<Integer> configuration = new ArrayList<Integer>();

	public PetriNetwork(ArrayList<Place> places, ArrayList<Transition> transitions, ArrayList<Edge> edges) {

		this.places = places;
		this.transitions = transitions;
		this.edges = edges;

		for (Place place : this.places) {
			this.configuration.add(place.getCoin());
		}

	}

	public ArrayList<Place> getPlaces() {
		return places;
	}

	public void setPlaces(ArrayList<Place> places) {
		this.places = places;
	}

	public ArrayList<Transition> getTransitions() {
		return transitions;
	}

	public void setTransitions(ArrayList<Transition> transitions) {
		this.transitions = transitions;
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}

	public void setEdges(ArrayList<Edge> edges) {
		this.edges = edges;
	}

	public ArrayList<Integer> getConfiguration() {
		return configuration;
	}

	public void setConfiguration(ArrayList<Integer> configuration) {
		this.configuration = configuration;
	}

	@Override
	public String toString() {
		String str = "";
		
		str += "Lugares: " + places + "\n";
		str += "Transições: " + transitions + "\n";
		str += "Arestas: " + edges + "\n";
		str += "Configuração inicial: " + configuration + "\n";
		
		return str;
	}

}
