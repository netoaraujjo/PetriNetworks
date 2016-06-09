package app;

public class Edge {

	private Place place;
	private Transition transition;
	private int weight;

	public Edge(Place place, Transition transition, int weight) {
		this.place = place;
		this.transition = transition;
		this.weight = weight;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public Transition getTransition() {
		return transition;
	}

	public void setTransition(Transition transition) {
		this.transition = transition;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return place.getLabel() + "-" + weight + "-" + transition.getLabel();
	}

}
