package app;

public class Transition extends State {

	private boolean active;

	public Transition(String label, boolean active) {
		super(label);
		setActive(active);
	}

	public Transition(Transition transition) {
		super(transition.getLabel());
		setActive(transition.isActive());
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

	public Transition clone() {
		// deep copy
		return new Transition(this);
	}

}
