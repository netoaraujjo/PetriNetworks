package app;

import java.util.ArrayList;

public class Tree {

	private PetriNetwork node;
	private int[][] incidenceMatrix;

	private boolean terminal;
	private boolean duplicate;
	private boolean blockade;
	private boolean limited;

	private ArrayList<Tree> childrens = new ArrayList<Tree>();
	private ArrayList<ArrayList<Integer>> generatedNodes;
	private ArrayList<ArrayList<Integer>> pathGenerated;

	public Tree(PetriNetwork node, int[][] incidenceMatrix, ArrayList<ArrayList<Integer>> generatedNodes,
			ArrayList<ArrayList<Integer>> pathGenerated) {
		this.node = node.clone();
		this.incidenceMatrix = incidenceMatrix;
		this.duplicate = false;
		this.blockade = false;
		this.limited = false;
		this.generatedNodes = generatedNodes;
		this.pathGenerated = pathGenerated;
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

	public ArrayList<ArrayList<Integer>> getGeneratedNodes() {
		return generatedNodes;
	}

	public void setGeneratedNodes(ArrayList<ArrayList<Integer>> generatedNodes) {
		this.generatedNodes = generatedNodes;
	}

	public ArrayList<ArrayList<Integer>> getPathGenerated() {
		return pathGenerated;
	}

	public void setPathGenerated(ArrayList<ArrayList<Integer>> pathGenerated) {
		this.pathGenerated = pathGenerated;
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

	public void generateChildrens() {

		// Enquanto não for feito o teste, o nó é considerado terminal
		this.terminal = true;

		// Avaliação da função de transição para todas as transições
		for (Transition transition : node.getTransitions()) {

			// Nova RdP a ser criada
			PetriNetwork newNode = node.transitionMovement(transition);

			// Se existe pelo menos uma transição habilitada a disparar, o nó é
			// desmarcado como terminal
			if (newNode != null) {

				// Clona a lista de configurações do caminho
				ArrayList<ArrayList<Integer>> pathGenerated = new ArrayList<ArrayList<Integer>>();
				for (ArrayList<Integer> arrayList : this.pathGenerated) {
					ArrayList<Integer> arrayListAux = new ArrayList<Integer>();
					for (Integer integer : arrayList) {
						arrayListAux.add(new Integer(integer));
					}
					pathGenerated.add(arrayListAux);
				}

				// Iteração sobre todos nós gerados referente ao caminho atual
				for (ArrayList<Integer> configuration : pathGenerated) {
					// Se a nova configuração dominar alguma configuração do
					// caminho
					if (dominates(newNode.getConfiguration(), configuration)) {
						// Seta valor W para todo lugar que tenha mais fichas do
						// que um lugar pertencente a algum nó do caminho
						for (int i = 0; i < configuration.size(); i++) {
							if (newNode.getConfiguration().get(i) > configuration.get(i))
								newNode.getPlaces().get(i).setQntCoin(Place.W);
						}
						newNode.configurationRefresh();
						newNode.transitionsRefresh();
					}
				}

				pathGenerated.add(newNode.getConfiguration());

				this.terminal = false;

				boolean duplicate;
				// RdP já foi criada anteriormente
				if (getGeneratedNodes().contains(newNode.getConfiguration())) {
					duplicate = true;
				} else {
					duplicate = false;
					this.generatedNodes.add(newNode.getConfiguration());
				}

				// Geração de novo nó
				Tree newTree = new Tree(newNode, incidenceMatrix, this.generatedNodes, pathGenerated);
				newTree.setDuplicate(duplicate);
				this.childrens.add(newTree);

			}

		}

	}

	private boolean dominates(ArrayList<Integer> newConfiguration, ArrayList<Integer> configuration) {
		boolean flag = true;

		for (int i = 0; i < newConfiguration.size(); i++) {
			if (newConfiguration.get(i) < configuration.get(i)) {
				flag = false;
				break;
			}
		}

		return flag;
	}

	@Override
	public String toString() {
		return "Tree [node=" + node.getConfiguration() + ", childrens=" + childrens + "]";
	}

}
