package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		/*
		 * -------------------- Simula leitura de arquivo --------------------
		 */
		// Lugares da rede
		Place p1, p2, p3, p4;
		p1 = new Place("p1", 1);
		p2 = new Place("p2", 0);
		p3 = new Place("p3", 0);
		p4 = new Place("p4", 0);

		// Transiï¿½ï¿½es da rede
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

		// Adicionando transiï¿½ï¿½es ao ArrayList
		transitions.add(t1);
		transitions.add(t2);
		//transitions.add(t3);

		// Adicionando arestas ao ArrayList
		ArrayList<Edge> edgesList = new ArrayList<Edge>();
		edgesList.add(new Edge(p1, t1, 1));
		edgesList.add(new Edge(t1, p2, 1));
		//edgesList.add(new Edge(t1, p3, 1));
		edges.put(t1.getLabel(), edgesList);

		edgesList = new ArrayList<Edge>();
		edgesList.add(new Edge(p2, t2, 1));
		edgesList.add(new Edge(t2, p3, 1));
		edgesList.add(new Edge(t2, p4, 1));
		edges.put(t2.getLabel(), edgesList);

		edgesList = new ArrayList<Edge>();
		edgesList.add(new Edge(p2, t3, 1));
		edgesList.add(new Edge(p3, t3, 1));
		edgesList.add(new Edge(t3, p3, 1));
		edgesList.add(new Edge(t3, p4, 1));
		edges.put(t3.getLabel(), edgesList);
		/*
		 * -------------------------------------------------------------------
		 */

		/*
		 * Cria rede de Petri inicial
		 */
		PetriNetwork petriNetwork = new PetriNetwork(places, transitions, edges);
		System.out.println("[ORIGINAL]:\n" + petriNetwork);
		/*
		 * ---------------------------------------------------------------------
		 */

		Node node = new Node(petriNetwork, new ArrayList<ArrayList<Integer>>(), new ArrayList<ArrayList<Integer>>());
		Tree tree = new Tree(node);
		tree.generatedTree();
		System.out.println("\n[Árvore]:\n" + tree);
		
		System.out.println("\nÁrvore conservativa: " + tree.conservatedTree());
		
		ArrayList<Integer> nodeX = new ArrayList<Integer>();
		nodeX.add(1);
		nodeX.add(1);
		nodeX.add(17);
		nodeX.add(0);
				
		System.out.println("\nNó alcançável: " + tree.reachableNode(nodeX));

	}

}
