package app;

public class Transition {

	private String label;
	private boolean active;

	public Transition(String label, boolean active) {
		this.label = label;
		this.active = active;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
