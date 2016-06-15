package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		// Lugares da rede
		Place p1, p2, p3, p4;
		p1 = new Place("p1", 1);
		p2 = new Place("p2", 0);
		p3 = new Place("p3", 0);
		p4 = new Place("p4", 0);

		// Transições da rede
		Transition t1, t2, t3;
		t1 = new Transition("t1", false);
		t2 = new Transition("t2", false);
		t3 = new Transition("t3", false);

		ArrayList<Place> places = new ArrayList<Place>();
		ArrayList<Transition> transitions = new ArrayList<Transition>();
		Map<String, ArrayList<Edge>> edges = new HashMap<>();

		// Adicionando lugares ao ArrayList
		places.add(p1);
		places.add(p2);
		places.add(p3);
		places.add(p4);

		// Adicionando transições ao ArrayList
		transitions.add(t1);
		transitions.add(t2);
		transitions.add(t3);

		// Adicionando arestas ao ArrayList
		ArrayList<Edge> edgesList = new ArrayList<Edge>();
		edgesList.add(new Edge(p1, t1, 1));
		edgesList.add(new Edge(t1, p2, 1));
		edgesList.add(new Edge(t1, p3, 1));
		edges.put(t1.getLabel(), edgesList);

		edgesList = new ArrayList<Edge>();
		edgesList.add(new Edge(p2, t2, 1));
		edgesList.add(new Edge(t2, p1, 1));
		edges.put(t2.getLabel(), edgesList);

		edgesList = new ArrayList<Edge>();
		edgesList.add(new Edge(p2, t3, 1));
		edgesList.add(new Edge(p3, t3, 1));
		edgesList.add(new Edge(t3, p3, 1));
		edgesList.add(new Edge(t3, p4, 1));
		edges.put(t3.getLabel(), edgesList);

		// Criando rede de Petri inicial
		PetriNetwork petriNetwork = new PetriNetwork(places, transitions, edges);
		System.out.println("[ORIGINAL]:\n" + petriNetwork);

		System.out.println("TRANSIÇÕES POSSÍVEIS:");
		for (Transition transition : petriNetwork.getTransitions()) {
			System.out.println("[" + transition + "]:");
			PetriNetwork pn = petriNetwork.transitionMovement(transition);

			if (pn != null) {
				System.out.println(pn);
			} else {
				System.out.println("Transição está inativa!\n");
			}
		}

		int[][] incidenceMatrix = new int[transitions.size()][places.size()];

		for (int i = 0; i < incidenceMatrix.length; i++) {
			// Transição relativa ao número da linha
			Transition transition = petriNetwork.getTransitions().get(i);

			for (int j = 0; j < incidenceMatrix[i].length; j++) {
				// Lugar relativo ao número da coluna
				Place place = petriNetwork.getPlaces().get(j);

				// Valor a ser inserido na matriz de incidência
				int value = 0;

				// Arestas da transição atual
				for (Edge edge : petriNetwork.getEdges().get(transition.getLabel())) {

					// Se a transição atual for origem e o destino for o lugar
					// atual
					if (edge.getOrigin().getLabel().equals(transition.getLabel())
							&& edge.getDestiny().getLabel().equals(place.getLabel())) {

						// Incrementa valor de acordo com o peso da aresta
						value += edge.getWeight();

						// Se a transição atual for destino e a origem for o
						// lugar atual
					} else if (edge.getDestiny().getLabel().equals(transition.getLabel())
							&& edge.getOrigin().getLabel().equals(place.getLabel())) {

						// Decrementa valor de acordo com o peso da aresta
						value -= edge.getWeight();

					}

				}

				incidenceMatrix[i][j] = value;

			}

		}

		System.out
				.println("\n[Matriz de Incidência]:\n" + incidenceMatrixToString(incidenceMatrix));

	}

	public static String incidenceMatrixToString(int[][] incidenceMatrix) {
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
