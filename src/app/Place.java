package app;

public class Place {

	private String label;
	private int qntCoin;

	public Place(String label, int qntCoin) {
		this.label = label;
		this.qntCoin = qntCoin;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getCoin() {
		return qntCoin;
	}

	public void setCoin(int coin) {
		this.qntCoin = coin;
	}

}
