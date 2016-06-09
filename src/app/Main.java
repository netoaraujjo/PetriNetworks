package app;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<Place> places = new ArrayList<Place>();
		ArrayList<Transition> transitions = new ArrayList<Transition>();
		ArrayList<Edge> edges = new ArrayList<Edge>();
		
		places.add(new Place("p1", 1));
		places.add(new Place("p2", 0));
		places.add(new Place("p3", 0));
		places.add(new Place("p4", 0));

		transitions.add(new Transition("t1", false));
		transitions.add(new Transition("t2", false));
		transitions.add(new Transition("t3", false));

		edges.add(new Edge(new Place("p1", 1), new Transition("t1", false), 1));
		edges.add(new Edge(new Place("p2", 1), new Transition("t1", false), 1));
		edges.add(new Edge(new Place("p3", 1), new Transition("t1", false), 1));
		
		edges.add(new Edge(new Place("p1", 1), new Transition("t2", false), 1));
		edges.add(new Edge(new Place("p2", 1), new Transition("t2", false), 1));
		
		edges.add(new Edge(new Place("p2", 1), new Transition("t3", false), 1));
		edges.add(new Edge(new Place("p3", 1), new Transition("t3", false), 1));
//		edges.add(new Edge(new Place("p3", 1), new Transition("t3", false), 1));
		edges.add(new Edge(new Place("p4", 1), new Transition("t3", false), 1));
		
		PetriNetwork petriNetwork = new PetriNetwork(places, transitions, edges);
		
		System.out.println(petriNetwork);

	}

}
