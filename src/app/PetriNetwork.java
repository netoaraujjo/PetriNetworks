package app;

import java.util.ArrayList;

public class PetriNetwork {

	private ArrayList<Place> places;
	private ArrayList<Transition> transitions;
	private ArrayList<Edge> edges;
	private ArrayList<Integer> configuration = new ArrayList<Integer>();
	private ArrayList<Integer> initialConfiguration = new ArrayList<Integer>();

	public PetriNetwork(ArrayList<Place> places, ArrayList<Transition> transitions, ArrayList<Edge> edges) {

		this.places = places;
		this.transitions = transitions;
		this.edges = edges;

		configurationRefresh();
		this.initialConfiguration = this.configuration;
		
		transitionsRefresh();

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
	
	public void transitionsRefresh() {
		
		for (Transition transition : this.transitions) {

			boolean active = false;
			for (Edge edge : this.edges) {

				// Transição é o destino da aresta
				if (transition.getLabel().equals(edge.getDestiny().getLabel())) {
					Place p = (Place) edge.getOrigin();
					// Se pelo menos um lugar não tem fichas suficientes, a
					// transição se torna inativa
					if (p.getQntCoin() < edge.getWeight()) {
						active = false;
						break;
					} else if (p.getQntCoin() >= edge.getWeight()) {
//						System.out.println(transition.getLabel() + " -> " + p + " | " + edge);
						active = true;
					}
				}

			}

			// Ativa ou desativa a transição, dependendo do retorno do for
			// anterior
			transition.setActive(active);
		}
		
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
	
	public void configurationRefresh() {
//		Esvazia array
		this.configuration = new ArrayList<Integer>();
		
//		Atualiza array com os novos valores das fichas
		for (Place place : this.places) {
			this.configuration.add(place.getQntCoin());
		}
	}

	public ArrayList<Integer> getInitialConfiguration() {
		return initialConfiguration;
	}

	public void setInitialConfiguration(ArrayList<Integer> initialConfiguration) {
		this.initialConfiguration = initialConfiguration;
	}

	@Override
	public String toString() {
		String str = "";

		str += "Lugares: " + getPlaces() + "\n";
		str += "Transições: " + getTransitions() + "\n";
		str += "Arestas: " + getEdges() + "\n";
		str += "Configuração inicial: " + getInitialConfiguration() + "\n";
		str += "Configuração atual: " + getConfiguration() + "\n";

		return str;
	}

}
