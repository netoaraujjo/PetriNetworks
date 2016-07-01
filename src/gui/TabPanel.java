package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.Edge;
import app.PetriNetwork;
import app.Place;
import app.Transition;

public class TabPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel drawPanel;
	private PetriNetwork petriNetwork;
	private String fileName;
	private Icon image;
	private JLabel imageLabel;
	
	public TabPanel(PetriNetwork petriNetwork, String fileName) {
		this.setLayout(new BorderLayout());
		this.setBackground(new Color(255, 255, 255));
		
		this.petriNetwork = petriNetwork;
		this.fileName = fileName;
		
		drawPanel = new JPanel(new BorderLayout());
		this.add(drawPanel);
		
		drawRdP();
	}
	
	
	
	
	private void drawRdP() {
		
		// dot -Tpng teste.rdp.dot -o teste_rdp.png
		
		String name = fileName.replace(".", "_");
		
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
					output.format("\t\t\t%s -> %s [label=%d]\n", edge.getOrigin().getLabel(), edge.getDestiny().getLabel(), edge.getWeight());
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
			Process p = Runtime.getRuntime().exec("dot -Tpng " + fileName + ".dot -o src/images/" + name + ".png");
			
			while (p.isAlive()) {
				System.out.println("não terminou");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println("finalmente");
			
			System.out.println(name + ".png");
			
			String dir = "images/" + name + ".png";
			
			System.out.println(dir);
			
			image = new ImageIcon(getClass().getClassLoader().getResource(dir));
			
			if (image == null) {
				System.out.println("erro");
			}
			
			imageLabel = new JLabel();
			int imagewidth =  image.getIconWidth();
			int imageHeight = image.getIconHeight();
			
			imageLabel.setSize(imagewidth, imageHeight);
			
			imageLabel.setIcon(image);
			
			drawPanel.add(imageLabel);
		}
	
	}
}
