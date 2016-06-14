package app;

import java.util.ArrayList;

public class PetriNetwork {

	private ArrayList<Place> places;
	private ArrayList<Transition> transitions;
	private ArrayList<Edge> edges;
	private ArrayList<Integer> configuration = new ArrayList<Integer>();
	private ArrayList<Integer> initialConfiguration = new ArrayList<Integer>();
	private int[][] incidenceMatrix;

	public PetriNetwork(ArrayList<Place> places, ArrayList<Transition> transitions, ArrayList<Edge> edges) {

		this.places = places;
		this.transitions = transitions;
		this.edges = edges;

		this.setIncidenceMatrix(new int[transitions.size()][places.size()]);

		configurationRefresh();
		this.initialConfiguration = this.configuration;

		transitionsRefresh();

	}

	public PetriNetwork(PetriNetwork petriNetwork) {

		this.places = new ArrayList<Place>();
		for (Place place : petriNetwork.places) {
			this.places.add(place.clone());
		}

		this.transitions = new ArrayList<Transition>();
		for (Transition transition : petriNetwork.transitions) {
			this.transitions.add(transition.clone());
		}

		this.edges = new ArrayList<Edge>();
		for (Edge edge : petriNetwork.edges) {
			this.edges.add(edge.clone());
		}

//		FALTA ARRUMAR
//		this.setIncidenceMatrix(new int[transitions.size()][places.size()]);

		this.configuration = new ArrayList<Integer>(petriNetwork.configuration);
		this.initialConfiguration = new ArrayList<Integer>(petriNetwork.initialConfiguration);

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
		// Esvazia array
		this.configuration = new ArrayList<Integer>();

		// Atualiza array com os novos valores das fichas
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

	public PetriNetwork clone() {
		return new PetriNetwork(this);
	}

	public PetriNetwork transitionMovement(Transition transition) {
		PetriNetwork petriNetwork = clone();

		if (transition.isActive()) {

			for (Edge edge : petriNetwork.getEdges()) {

				// Se a transição for origem
				if (edge.getOrigin().getLabel().equals(transition.getLabel())) {

					Place p = (Place) edge.getDestiny();
					// p = p.clone();
					// Incrementa fichas de acordo com o peso da aresta
					p.setQntCoin(p.getQntCoin() + edge.getWeight());

					// Se a transição for destino
				} else if (edge.getDestiny().getLabel().equals(transition.getLabel())) {

					Place p = (Place) edge.getOrigin();
					// p = p.clone();
					// Decrementa fichas de acordo com o peso da aresta
					p.setQntCoin(p.getQntCoin() - edge.getWeight());

				}
			}

			petriNetwork.configurationRefresh();
			petriNetwork.transitionsRefresh();

			return petriNetwork;

		} else {
			return null;
		}
	}

	public int[][] getIncidenceMatrix() {
		return incidenceMatrix;
	}

	public void setIncidenceMatrix(int[][] incidenceMatrix) {
		this.incidenceMatrix = incidenceMatrix;
	}
	
	public String incidenceMatrixToString() {
		String str = "";
		
		for (int i = 0; i < incidenceMatrix.length; i++) {
			for (int j = 0; j < incidenceMatrix[i].length; j++) {
				str += incidenceMatrix[i][j] + " ";
			}
			str += "\n";
		}
		
		return str;
	}

}
