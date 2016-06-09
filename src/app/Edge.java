package app;

public class Edge {

	private State origin;
	private State destiny;
	private int weight;

	public Edge(State origin, State destiny, int weight) {
		this.origin = origin;
		this.destiny = destiny;
		this.weight = weight;
	}

	public State getOrigin() {
		return origin;
	}

	public void setOrigin(State origin) {
		this.origin = origin;
	}

	public State getDestiny() {
		return destiny;
	}

	public void setDestiny(State destiny) {
		this.destiny = destiny;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return origin.getLabel() + "-" + getWeight() + "-" + destiny.getLabel();
	}

}
