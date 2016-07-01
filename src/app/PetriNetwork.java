package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PetriNetwork {

	private ArrayList<Place> places;
	private ArrayList<Transition> transitions;
	private Map<String, ArrayList<Edge>> edges = new HashMap<>();
	private ArrayList<Integer> configuration = new ArrayList<Integer>();

	public PetriNetwork(ArrayList<Place> places, ArrayList<Transition> transitions,
			Map<String, ArrayList<Edge>> edges) {

		this.places = places;
		this.transitions = transitions;
		this.edges = edges;

		configurationRefresh();

		transitionsRefresh();

	}

	public PetriNetwork(PetriNetwork petriNetwork) {

		// Cria nova lista de lugares
		this.places = new ArrayList<Place>();

		// Iteração para cada lugar da RdP a ser clona
		for (Place place : petriNetwork.places) {
			this.places.add(place.clone());
		}

		// Cria nova lista de transições
		this.transitions = new ArrayList<Transition>();

		// Cria novo hash de arestas
		this.edges = new HashMap<>();

		// Iteração para cada transição da RdP a ser clonada
		for (Transition transition : petriNetwork.transitions) {
			// Clone da transição atual
			Transition transition_aux = transition.clone();
			this.transitions.add(transition_aux);

			// Cria nova lista de arestas para a transição atual
			ArrayList<Edge> edgesList = new ArrayList<Edge>();

			// Iteração para cada aresta, relacionada a transição atual, da RdP
			// a ser clonada
			for (Edge edge : petriNetwork.edges.get(transition_aux.getLabel())) {
				// Se a transição for origem
				if (edge.getOrigin().getLabel().equals(transition_aux.getLabel())) {

					// Iteração para cada lugar
					for (Place place_aux : this.places) {
						if (place_aux.getLabel().equals(edge.getDestiny().getLabel())) {
							// Adicionado na lista a transição clonada e o lugar
							// clonado
							edgesList.add(new Edge(transition_aux, place_aux, edge.getWeight()));
						}
					}

					// Se a transição for destino
				} else if (edge.getDestiny().getLabel().equals(transition.getLabel())) {

					// Iteração para cada lugar
					for (Place place_aux : this.places) {
						if (place_aux.getLabel().equals(edge.getOrigin().getLabel())) {
							// Adicionado na lista a transição clonada e o lugar
							// clonado
							edgesList.add(new Edge(place_aux, transition_aux, edge.getWeight()));
						}
					}

				}

			}

			// Adicionada ao 'hash' a transição clonada e a lista com as arestas
			// clonada
			this.edges.put(transition_aux.getLabel(), edgesList);

		}

		// Nova configuração baseada na configuração da RdP a ser clonada
		this.configuration = new ArrayList<Integer>();
		for (Integer integer : petriNetwork.configuration) {
			this.configuration.add(new Integer(integer));
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

	public void transitionsRefresh() {

		for (Transition transition : this.transitions) {

			boolean active = false;
			for (Edge edge : this.getEdges().get(transition.getLabel())) {

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

	public Map<String, ArrayList<Edge>> getEdges() {
		return edges;
	}

	public void setEdges(Map<String, ArrayList<Edge>> edges) {
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

	@Override
	public String toString() {
		String str = "";

		str += "Lugares: " + getPlaces() + "\n";
		str += "Transições: " + getTransitions() + "\n";
		str += "Arestas: " + getEdges() + "\n";
		str += "Configuração atual: " + getConfiguration() + "\n";

		return str;
	}

	public String configurationToString() {
		String str = "[ ";

		for (Integer integer : configuration) {
			str += integer + " ";
		}
		str += "]";

		return str;
	}

	public PetriNetwork clone() {
		return new PetriNetwork(this);
	}

	public PetriNetwork transitionMovement(Transition transition) {
		PetriNetwork petriNetwork = clone();
		transition = transition.clone();

		if (transition.isActive()) {

			for (Edge edge : petriNetwork.getEdges().get(transition.getLabel())) {

				// Se a transição for origem
				if (edge.getOrigin().getLabel().equals(transition.getLabel())) {

					Place p = (Place) edge.getDestiny();
					// Só incrementa se a quantidade de fichas for limitada
					// Incrementa fichas de acordo com o peso da aresta
					p.setQntCoin(p.getQntCoin() + edge.getWeight());

					// Se a transição for destino
				} else if (edge.getDestiny().getLabel().equals(transition.getLabel())) {

					Place p = (Place) edge.getOrigin();
					// Só decrementa se a quantidade de fichas for limitada
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

}
