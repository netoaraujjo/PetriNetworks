package app;

public class Transition extends State{

	private boolean active;

	public Transition(String label, boolean active) {
		super(label);
		setActive(active);
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return getLabel() + "(" + isActive() + ")";
	}

}
