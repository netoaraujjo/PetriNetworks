package app;

import java.util.ArrayList;

public class Tree {

	private PetriNetwork node;
	private ArrayList<Tree> childrens;
	private int[][] incidenceMatrix;

	private boolean terminal;
	private boolean duplicate;
	private boolean blockade;
	private boolean limited;

	public Tree(PetriNetwork node, int[][] incidenceMatrix) {
		this.node = node.clone();
		this.incidenceMatrix = incidenceMatrix;
		this.duplicate = false;
		this.blockade = false;
		this.limited = false;
		// Enquanto não for feito o teste, o nó é considerado terminal
		this.terminal = true;

		// Avaliação da função de transição para todas as transições
		// for (Transition transition : node.getTransitions()) {
		// PetriNetwork new_node = node.transitionMovement(transition);
		//
		// // Se existe pelo menos uma transição habilitada a disparar, o nó é
		// // desmarcado como terminal
		// if (new_node != null) {
		// this.terminal = false;
		//
		// Tree new_tree = new Tree(new_node, incidenceMatrix);
		// this.childrens.add(new_tree);
		// }
		// }

	}

	public PetriNetwork getNode() {
		return node;
	}

	public void setNode(PetriNetwork node) {
		this.node = node;
	}

	public ArrayList<Tree> getChildrens() {
		return childrens;
	}

	public void setChildrens(ArrayList<Tree> childrens) {
		this.childrens = childrens;
	}

	public int[][] getIncidenceMatrix() {
		return incidenceMatrix;
	}

	public void setIncidenceMatrix(int[][] incidenceMatrix) {
		this.incidenceMatrix = incidenceMatrix;
	}

	public boolean isTerminal() {
		return terminal;
	}

	public void setTerminal(boolean terminal) {
		this.terminal = terminal;
	}

	public boolean isDuplicate() {
		return duplicate;
	}

	public void setDuplicate(boolean duplicate) {
		this.duplicate = duplicate;
	}

	public boolean isBlockade() {
		return blockade;
	}

	public void setBlockade(boolean blockade) {
		this.blockade = blockade;
	}

	public boolean isLimited() {
		return limited;
	}

	public void setLimited(boolean limited) {
		this.limited = limited;
	}

	/*
	 * Imprime matriz de incidência
	 */
	public String incidenceMatrixToString() {
		String str = "";

		for (int i = 0; i < this.incidenceMatrix.length; i++) {
			for (int j = 0; j < this.incidenceMatrix[i].length; j++) {
				str += this.incidenceMatrix[i][j] + " ";
			}
			str += "\n";
		}

		return str;
	}

	@Override
	public String toString() {
		return "Tree [node=" + node.getConfiguration() + ", childrens=" + childrens + "]";
	}

}
