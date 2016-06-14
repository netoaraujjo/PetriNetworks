package app;

import java.util.ArrayList;

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
		ArrayList<Edge> edges = new ArrayList<Edge>();

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
		edges.add(new Edge(p1, t1, 1));
		edges.add(new Edge(t1, p2, 1));
		edges.add(new Edge(t1, p3, 1));

		edges.add(new Edge(t2, p1, 1));
		edges.add(new Edge(p2, t2, 1));

		edges.add(new Edge(p2, t3, 1));
		edges.add(new Edge(p3, t3, 1));
		edges.add(new Edge(t3, p3, 1));
		edges.add(new Edge(t3, p4, 1));

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

	}

}