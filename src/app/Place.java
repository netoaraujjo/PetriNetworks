package app;

public class Place extends State {
	
	public final static int W = 999;

	private int qntCoin;

	public Place(String label, int qntCoin) {
		super(label);
		this.qntCoin = qntCoin;
	}

	public Place(Place place) {
		super(new String (place.getLabel()));
		this.qntCoin = place.qntCoin;
	}

	public int getQntCoin() {
		return qntCoin;
	}

	public void setQntCoin(int qntCoin) {
		this.qntCoin = qntCoin;
	}

	@Override
	public String toString() {
		return getLabel() + "(" + getQntCoin() + ")";
	}

	public Place clone() {
		// deep copy
		return new Place(this);
	}

}
