package app;

import java.util.ArrayList;

public class Node {

	private PetriNetwork petriNetwork;

	private boolean terminal;
	private boolean duplicate;
	private boolean blockade;
	private boolean limited;
	private boolean reachable;

	private ArrayList<Node> childrens = new ArrayList<Node>();
	private ArrayList<ArrayList<Integer>> generatedNodes;
	private ArrayList<ArrayList<Integer>> pathGenerated;
	
	private static int id = 0;
	
	private int nodeId;

	public Node(PetriNetwork petriNetwork, ArrayList<ArrayList<Integer>> generatedNodes,
			ArrayList<ArrayList<Integer>> pathGenerated) {
		this.petriNetwork = petriNetwork.clone();
		this.blockade = false;
		this.limited = true;
		this.generatedNodes = generatedNodes;
		this.pathGenerated = pathGenerated;
		
		id += 1;
		
		nodeId = id;
	}
	
	public int getNodeId() {
		return nodeId;
	}

	public PetriNetwork getPetriNetwork() {
		return petriNetwork;
	}

	public void setPetriNetwork(PetriNetwork petriNetwork) {
		this.petriNetwork = petriNetwork;
	}

	public ArrayList<Node> getChildrens() {
		return childrens;
	}

	public void setChildrens(ArrayList<Node> childrens) {
		this.childrens = childrens;
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

	public boolean isReachable() {
		return reachable;
	}

	public void setReachable(boolean reachable) {
		this.reachable = reachable;
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

	public void generateChildrens() {

		// Enquanto não for feito o teste, o nó é considerado terminal
		this.terminal = true;

		this.blockade = true;

		// Avaliação da função de transição para todas as transições
		for (Transition transition : petriNetwork.getTransitions()) {

			if (transition.isActive())
				this.blockade = false;

			// Nova RdP a ser criada
			PetriNetwork newPN = petriNetwork.transitionMovement(transition);

			// Se existe pelo menos uma transição habilitada a disparar, o nó
			// é
			// desmarcado como terminal
			if (newPN != null) {

				// Clona a lista de configurações do caminho
				ArrayList<ArrayList<Integer>> pathGenerated = new ArrayList<ArrayList<Integer>>();
				for (ArrayList<Integer> arrayList : this.pathGenerated) {
					ArrayList<Integer> arrayListAux = new ArrayList<Integer>();
					for (Integer integer : arrayList) {
						arrayListAux.add(new Integer(integer));
					}
					pathGenerated.add(arrayListAux);
				}

				boolean limited = true;
				// Iteração sobre todos nós gerados referente ao caminho
				// atual
				for (ArrayList<Integer> configuration : pathGenerated) {
					// Se a nova configuração dominar alguma configuração do
					// caminho
					if (dominates(newPN.getConfiguration(), configuration)) {
						// Seta valor W para todo lugar que tenha mais fichas do
						// que um lugar pertencente a algum nó do caminho
						for (int i = 0; i < configuration.size(); i++) {
							if (newPN.getConfiguration().get(i) > configuration.get(i)) {
								newPN.getPlaces().get(i).setQntCoin(Place.W);
								limited = false;
							}
						}
						newPN.configurationRefresh();
						newPN.transitionsRefresh();
					}
				}

				pathGenerated.add(newPN.getConfiguration());

				this.terminal = false;

				boolean duplicate;
				// RdP já foi criada anteriormente
				if (getGeneratedNodes().contains(newPN.getConfiguration())) {
					duplicate = true;
				} else {
					duplicate = false;
					this.generatedNodes.add(newPN.getConfiguration());
				}

				// Geração de novo nó
				Node newNode = new Node(newPN, this.generatedNodes, pathGenerated);
				newNode.setDuplicate(duplicate);
				newNode.setLimited(limited);
				this.childrens.add(newNode);

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
		return "[node=" + petriNetwork.getConfiguration() + ", childrens=" + childrens + "]";
	}

}
