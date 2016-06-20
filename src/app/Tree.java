package app;

import java.util.ArrayList;

public class Tree {

	private Node node;

	public Tree(Node node) {
		this.node = node;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public void generatedTree() {

		node.getGeneratedNodes().add(node.getPetriNetwork().getConfiguration());
		node.getPathGenerated().add(node.getPetriNetwork().getConfiguration());

		// Árvore para auxiliar geração dos nós do próximo nível
		Node nodeAux = node;

		// Lista auxiliar para colocar os nós do próximo nível
		ArrayList<Node> listNodes = new ArrayList<Node>();

		while (true) {

			// Testa se o nó já é terminal ou duplicado, para assim não gerar
			// novos filhos
			if (!nodeAux.isTerminal() && !nodeAux.isDuplicate()) {

				// Gera os nós do próximo nível
				nodeAux.generateChildrens();
				for (Node child : nodeAux.getChildrens()) {
					listNodes.add(child);
				}

				// Se nenhum filho foi gerado
				if (listNodes.isEmpty())
					break;

				// Atualização do nó atual
				nodeAux = listNodes.get(0);
				listNodes.remove(0);

			} else {

				// Se nenhum filho foi gerado
				if (listNodes.isEmpty())
					break;

				// Atualização do nó atual
				nodeAux = listNodes.get(0);
				listNodes.remove(0);

			}

		}
	}

	@Override
	public String toString() {
		return "Tree " + node;
	}
	
}
