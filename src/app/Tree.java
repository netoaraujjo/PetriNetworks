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

		// Ã�rvore para auxiliar geraÃ§Ã£o dos nÃ³s do prÃ³ximo
		// nÃ­vel
		Node nodeAux = node;

		// Lista auxiliar para colocar os nÃ³s do prÃ³ximo nÃ­vel
		ArrayList<Node> listNodes = new ArrayList<Node>();

		while (true) {

			// Testa se o nÃ³ jÃ¡ Ã© terminal ou duplicado, para assim
			// nÃ£o
			// gerar
			// novos filhos
			if (!nodeAux.isTerminal() && !nodeAux.isDuplicate()) {

				// Gera os nÃ³s do prÃ³ximo nÃ­vel
				nodeAux.generateChildrens();
				for (Node child : nodeAux.getChildrens()) {
					// Se o pai Ã© nÃ£o-limitado, o filho criado tambÃ©m
					// serÃ¡
					if (!nodeAux.isLimited())
						child.setLimited(false);
					listNodes.add(child);
				}

				// Se nenhum filho foi gerado
				if (listNodes.isEmpty())
					break;

				// AtualizaÃ§Ã£o do nÃ³ atual
				nodeAux = listNodes.get(0);
				listNodes.remove(0);

			} else {

				// Se nenhum filho foi gerado
				if (listNodes.isEmpty())
					break;

				// AtualizaÃ§Ã£o do nÃ³ atual
				nodeAux = listNodes.get(0);
				listNodes.remove(0);

			}

		}
	}

	public boolean conservatedTree() {
		node.getGeneratedNodes().add(node.getPetriNetwork().getConfiguration());
		node.getPathGenerated().add(node.getPetriNetwork().getConfiguration());

		// Ã�rvore para auxiliar geraÃ§Ã£o dos nÃ³s do prÃ³ximo
		// nÃ­vel
		Node nodeAux = node;
		int qtdFichas = 0;
		int qtdFichasFilho = 0;

		for (int i = 0; i < node.getPetriNetwork().getConfiguration().size(); i++) {
			qtdFichas += node.getPetriNetwork().getConfiguration().get(i);
		}

		// Lista auxiliar para colocar os nÃ³s do prÃ³ximo nÃ­vel
		ArrayList<Node> listNodes = new ArrayList<Node>();

		while (true) {

			// Testa se o nÃ³ jÃ¡ Ã© terminal ou duplicado, para assim
			// nÃ£o
			// gerar
			// novos filhos
			if (!nodeAux.isTerminal() && !nodeAux.isDuplicate()) {

				for (Node child : nodeAux.getChildrens()) {
					listNodes.add(child);
					qtdFichasFilho = 0;
					for (Integer weight : child.getPetriNetwork().getConfiguration()) {
						qtdFichasFilho += weight;
					}

					if (qtdFichasFilho != qtdFichas)
						return false;
				}

				// Se nenhum filho foi gerado
				if (listNodes.isEmpty())
					break;

				// AtualizaÃ§Ã£o do nÃ³ atual
				nodeAux = listNodes.get(0);
				listNodes.remove(0);

			} else {

				// Se nenhum filho foi gerado
				if (listNodes.isEmpty())
					break;

				// AtualizaÃ§Ã£o do nÃ³ atual
				nodeAux = listNodes.get(0);
				listNodes.remove(0);

			}

		}

		return true;
	}

	public boolean reachableNode(ArrayList<Integer> configurationX) {

		Node nodeAux = node;
		ArrayList<Node> listNodes = new ArrayList<Node>();

		if (configurationX.size() == nodeAux.getPetriNetwork().getConfiguration().size()) {

			while (true) {
				if (!nodeAux.getChildrens().isEmpty()) {
					int flag = 0;
					for (int i = 0; i < nodeAux.getPetriNetwork().getConfiguration().size(); i++) {
						if (nodeAux.getPetriNetwork().getConfiguration().get(i).equals(Place.W)
								|| nodeAux.getPetriNetwork().getConfiguration().get(i).equals(configurationX.get(i)))
							flag++;
					}
					if (flag == nodeAux.getPetriNetwork().getConfiguration().size()) {
						nodeAux.setReachable(true);
						return true;
					}
					for (Node child : nodeAux.getChildrens()) {
						listNodes.add(child);
					}

					if (listNodes.isEmpty())
						break;

					nodeAux = listNodes.get(0);
					listNodes.remove(0);

				} else {

					if (listNodes.isEmpty())
						break;

					nodeAux = listNodes.get(0);
					listNodes.remove(0);
				}
			}
		}

		return false;
	}

	@Override
	public String toString() {
		return "Tree " + node;
	}

}
