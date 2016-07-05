package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import app.Edge;
import app.Node;
import app.PetriNetwork;
import app.Place;
import app.Transition;
import app.Tree;

public class TabPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JScrollPane drawPanel;
	private PetriNetwork petriNetwork;
	private String fileName;
	private Icon image;
	private JLabel imageLabel;

	private Tree tree;

	private Formatter outputTree;
	private String edges;

	private String separator = File.separator;

	public TabPanel(PetriNetwork petriNetwork, String fileName) {
		this.setLayout(new BorderLayout());
		this.setBackground(new Color(255, 255, 255));

		this.petriNetwork = petriNetwork;
		this.fileName = fileName;

		drawRdP();
	}

	public TabPanel(Tree tree, String fileName, String color) {
		this.tree = tree;
		this.fileName = fileName;

		String name = fileName.replace(".", "_");

		name += "_tree";

		String projectPath = System.getProperty("user.dir");

		try {
			outputTree = new Formatter(name + ".dot");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		edges = "";

		outputTree.format("digraph %s {\n", name);
		outputTree.format("\t%s\n", "graph [pad=\"0.5,0.5\"]");
		outputTree.format("\t%s\n", "node [fillcolor=aquamarine4 shape=circle style=filled]");

		this.drawTree(this.tree.getNode(), color);

		outputTree.format("%s", edges);
		outputTree.format("%s", "}");

		if (outputTree != null) {
			outputTree.close();
		}

		try {
			Process pTree = Runtime.getRuntime().exec("dot -Tpng " + name + ".dot -o " + projectPath + separator + "src"
					+ separator + "images" + separator + name + ".png");

			while (pTree.isAlive()) {
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			String dir = projectPath + separator + "src" + separator + "images" + separator + name + ".png";

			image = new ImageIcon(dir);

			if (image == null) {
				System.out.println("erro");
			}

			imageLabel = new JLabel();
			int imagewidth = image.getIconWidth();
			int imageHeight = image.getIconHeight();

			imageLabel.setIcon(image);

			imageLabel.setSize(imagewidth, imageHeight);

			drawPanel = new JScrollPane(imageLabel);
			drawPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			drawPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

			this.add(drawPanel);

		}
	}

	private void drawTree(Node nodeAux, String color) {
		if (nodeAux != null) {

			String label = nodeAux.getPetriNetwork().configurationToString();

			if (nodeAux.isReachable() && color.equalsIgnoreCase("green")) {
				nodeAux.setReachable(false);
				outputTree.format("\t\t%d [label=\"%s\" fillcolor=green]\n", nodeAux.getNodeId(), label);
			} else if (nodeAux.isBlockade() && color.equalsIgnoreCase("red")) {
				outputTree.format("\t\t%d [label=\"%s\" fillcolor=red]\n", nodeAux.getNodeId(), label);
			} else if (nodeAux.isDuplicate() && color.equalsIgnoreCase("cornflowerblue")) {
				outputTree.format("\t\t%d [label=\"%s\" fillcolor=cornflowerblue]\n", nodeAux.getNodeId(), label);
			} else if (!nodeAux.isLimited() && color.equalsIgnoreCase("yellow")) {
				outputTree.format("\t\t%d [label=\"%s\" fillcolor=yellow]\n", nodeAux.getNodeId(), label);
			} else {
				outputTree.format("\t\t%d [label=\"%s\"]\n", nodeAux.getNodeId(), label);
			}

			if (nodeAux.getChildrens() != null) {
				for (Node child : nodeAux.getChildrens()) {
					// Recursão para desenhar sub-árvore de filhos
					drawTree(child, color);
					
					// Aresta entre árvore e sub-árvore
					edges += String.format("\t\t\t%d -> %d [label=\"%s\"]\n", nodeAux.getNodeId(), child.getNodeId(), child.getParentLabel());
				}
			}
		}

	}

	private void drawRdP() {

		String name = fileName.replace(".", "_");

		String projectPath = System.getProperty("user.dir");

		Formatter output;
		try {
			output = new Formatter(fileName + ".dot");

			output.format("digraph %s {\n", name);
			output.format("\t%s\n", "graph [pad=\"0.5,0.5\" rankdir=LR]");
			output.format("\t%s\n", "node [fillcolor=aquamarine4 shape=circle style=filled]");

			for (Place place : petriNetwork.getPlaces()) {
				output.format("\t\t%s [label=\"%s-%d\"]\n", place.getLabel(), place.getLabel(), place.getQntCoin());
			}

			output.format("\t%s\n", "node [fillcolor=aquamarine4 shape=box style=filled]");

			for (Transition transition : petriNetwork.getTransitions()) {
				output.format("\t\t%s\n", transition.getLabel());
			}

			for (ArrayList<Edge> edges : petriNetwork.getEdges().values()) {
				for (Edge edge : edges) {
					output.format("\t\t\t%s -> %s [label=%d]\n", edge.getOrigin().getLabel(),
							edge.getDestiny().getLabel(), edge.getWeight());
				}
			}

			output.format("%s", "}");

			if (output != null) {
				output.close();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Process p = Runtime.getRuntime().exec("dot -Tpng " + fileName + ".dot -o " + projectPath + separator + "src"
					+ separator + "images" + separator + name + ".png");

			while (p.isAlive()) {}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			String dir = projectPath + separator + "src" + separator + "images" + separator + name + ".png";

			image = new ImageIcon(dir);

			if (image == null) {
				System.out.println("erro");
			}

			imageLabel = new JLabel();
			int imageWidth = image.getIconWidth();
			int imageHeight = image.getIconHeight();

			imageLabel.setSize(imageWidth, imageHeight);

			imageLabel.setIcon(image);

			drawPanel = new JScrollPane(imageLabel);
			drawPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			drawPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

			this.add(drawPanel);
		}

	}

	public PetriNetwork getPetriNetwork() {
		return petriNetwork;
	}

	public Tree getTree() {
		return tree;
	}

	public String getFileName() {
		return fileName;
	}
}
