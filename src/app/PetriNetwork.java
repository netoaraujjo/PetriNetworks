package app;

import java.util.ArrayList;

public class PetriNetwork {

	private ArrayList<Place> places;
	private ArrayList<Transition> transitions;
	private ArrayList<Edge> edges;
	private ArrayList<Integer> configuration;

	public PetriNetwork(ArrayList<Place> places, ArrayList<Transition> transitions,
						ArrayList<Edge> edges) {
		
		this.places = places;
		this.transitions = transitions;
		this.edges = edges;
		
		for (Place place : this.places) {
			this.configuration.add(place.getCoin());
		}

	}

}
