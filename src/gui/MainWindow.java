package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileNameExtensionFilter;

import app.Edge;
import app.Node;
import app.PetriNetwork;
import app.Place;
import app.State;
import app.Transition;
import app.Tree;

public class MainWindow extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Barra de menu
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu helpMenu;
	
	// Opcoes do menu file
	private JMenuItem newMenuItem;
	private JMenuItem openMenuItem;
	private JMenuItem saveMenuItem;
	private JMenuItem exitMenuItem;
	private JMenuItem exportMenuItem;
	
	// Opcoes do menu help
	private JMenuItem aboutMenuItem;
	private JMenuItem helpMenuItem;
	
	
	// Painel e botoes de operacao
	private JPanel operationsPanel;
	private JButton spanningTreeButton;
	private JButton blockingStatesButton;
	private JButton nonLimitedStatesButton;
	private JButton networkConservationButton;
	private JButton reachabelStateButton;
	
	
	// Painel da barra de ferramentas
	private JPanel toolbarPanel;
	
	// Barra de ferramentas file
	private JToolBar fileToolbar;
	private JButton newFileButton;
	private JButton openFileButton;
	private JButton saveFileButton;
	
	// Barra de ferramentas network components
	private JToolBar networkComponentsToolbar;
	private JButton addPlaceButton;
	private JButton addTransitionButton;
	private JButton addEdgeButton;
	
	
	// Painel centra e painel de abas
	private JPanel centerPanel;
	private JTabbedPane networksPanel;
	private ArrayList<TabPanel> tabs; 

	
	/**
	 * Construtor da classe MainWindow
	 */
	public MainWindow() {
		super("Petri Networks");
		
		this.createMenuBar();
		
		this.createToolbar();
		
		this.createOperationsPanel();
		
		this.createNetworksPanel();
		
	}
	
	
	/**
	 * Cria barra de menus
	 */
	private void createMenuBar() {
		menuBar = new JMenuBar();
		
		fileMenu = new JMenu("Arquivo");
		newMenuItem = new JMenuItem("Novo");
		
		openMenuItem = new JMenuItem("Abrir arquivo");
		openMenuItem.addActionListener(new OpenFileHandler());
		
		saveMenuItem = new JMenuItem("Salvar arquivo");
		exportMenuItem = new JMenuItem("Exportar");
		exitMenuItem = new JMenuItem("Sair");
		
//		fileMenu.add(newMenuItem);
		fileMenu.add(openMenuItem);
//		fileMenu.add(saveMenuItem);
//		fileMenu.add(exportMenuItem);
//		fileMenu.addSeparator();
//		fileMenu.add(exitMenuItem);
		
		helpMenu = new JMenu("Ajuda");
		helpMenuItem = new JMenuItem("Ajuda");
		aboutMenuItem = new JMenuItem("Sobre");
		
		helpMenu.add(helpMenuItem);
		helpMenu.add(aboutMenuItem);
		
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
		
		this.setJMenuBar(menuBar);
	}
	
	
	/**
	 * Cria barra de ferramentas
	 */
	private void createToolbar() {
		toolbarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		String iconsDir = "icons/";
		
		fileToolbar = new JToolBar();
		fileToolbar.setName("File");
		
		// Componentes da barra de ferramentas de arquivo
		Icon newFileIcon = new ImageIcon(getClass().getClassLoader().getResource(iconsDir + "new.png"));
		newFileButton = new JButton(newFileIcon);
		newFileButton.setToolTipText("Novo arquivo");
		
		Icon openFileIcon = new ImageIcon(getClass().getClassLoader().getResource(iconsDir + "open.png"));
		openFileButton = new JButton(openFileIcon);
		openFileButton.setToolTipText("Abrir arquivo");
		openFileButton.addActionListener(new OpenFileHandler());
		
		Icon saveFileIcon = new ImageIcon(getClass().getClassLoader().getResource(iconsDir + "save.png"));
		saveFileButton = new JButton(saveFileIcon);
		saveFileButton.setToolTipText("Salvar arquivo");
		
//		fileToolbar.add(newFileButton);
		fileToolbar.add(openFileButton);
//		fileToolbar.add(saveFileButton);
		
		
		
		// Componentes da barra de ferramentas de rede
		networkComponentsToolbar = new JToolBar();
		networkComponentsToolbar.setName("Network components");
		
		Icon newPlaceIcon = new ImageIcon(getClass().getClassLoader().getResource(iconsDir + "circle_stroked.png"));
		addPlaceButton = new JButton(newPlaceIcon);
		addPlaceButton.setToolTipText("Adicionar lugar");
		
		Icon newTransitionIcon = new ImageIcon(getClass().getClassLoader().getResource(iconsDir + "pipe.png"));
		addTransitionButton = new JButton(newTransitionIcon);
		addTransitionButton.setToolTipText("Adicionar transição");
		
		Icon newEdgeIcon = new ImageIcon(getClass().getClassLoader().getResource(iconsDir + "arrow_alt_right.png"));
		addEdgeButton = new JButton(newEdgeIcon);
		addEdgeButton.setToolTipText("Adicionar aresta");
		
		networkComponentsToolbar.add(addPlaceButton);
		networkComponentsToolbar.add(addTransitionButton);
		networkComponentsToolbar.add(addEdgeButton);
		
		
		
		toolbarPanel.add(fileToolbar);
//		toolbarPanel.add(networkComponentsToolbar);
		
		this.add(toolbarPanel, BorderLayout.NORTH);
	}
	
	
	/**
	 * Cria painel com os botoes de operacao
	 */
	private void createOperationsPanel() {
		operationsPanel = new JPanel(new GridLayout(5, 1));
		
		spanningTreeButton =  new JButton("Árvore de cobertura");
		spanningTreeButton.addActionListener(new SpanningTreeHandler());
		
		blockingStatesButton = new JButton("Estados bloqueantes");
		blockingStatesButton.addActionListener(new BlockingStatesHandler());
		
		nonLimitedStatesButton = new JButton("Estados não-limitados");
		nonLimitedStatesButton.addActionListener(new NonLimitedStatesHandler());
		
		networkConservationButton = new JButton("Conservação da rede");
		networkConservationButton.addActionListener(new NetworkConservationHandler());
		
		reachabelStateButton = new JButton("Estado alcansável");
		reachabelStateButton.addActionListener(new ReachableStateHandler());
		
		operationsPanel.add(spanningTreeButton);
		operationsPanel.add(blockingStatesButton);
		operationsPanel.add(nonLimitedStatesButton);
		operationsPanel.add(networkConservationButton);
		operationsPanel.add(reachabelStateButton);
		
		this.add(operationsPanel, BorderLayout.WEST);
	}
	
	
	/**
	 * Cria painel de abas
	 */
	private void createNetworksPanel() {
		tabs = new ArrayList<>();
		networksPanel = new JTabbedPane();		
		this.add(networksPanel, BorderLayout.CENTER);
	}
	
	
	/**
	 * @param petriNetwork
	 */
	private void addTab(PetriNetwork petriNetwork, String fileName) {
		TabPanel tab = new TabPanel(petriNetwork, fileName);
		tabs.add(tab);
		networksPanel.add(fileName, tab);
		networksPanel.setSelectedIndex(networksPanel.getTabCount()-1);
	}
	
	private void addTab(Tree tree, String fileName) {
		TabPanel tab = new TabPanel(tree, fileName);
		tabs.add(tab);
		networksPanel.add(fileName, tab);
		networksPanel.setSelectedIndex(networksPanel.getTabCount()-1);
	}
	
	
	/***************************************************************************
	 * Manipuladores de eventos
	 **************************************************************************/
	
	/**
	 * @author neto
	 * Classe interna responsavel por manipular os eventos de abertura de arquivo
	 */
	private class OpenFileHandler implements ActionListener { 
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
//			System.out.println("Clicou para abrir arquivo");
			JFileChooser fileChooser = new JFileChooser();
			
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos rdp", "rdp");
			
			fileChooser.setFileFilter(filter);
			
			int returnVal = fileChooser.showOpenDialog(MainWindow.this);
			
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				
				fileChooser.getSelectedFile(); // retorna um File
				
//				System.out.println("Arquivo selecionado: " + fileChooser.getSelectedFile().getAbsolutePath());
				
				try {
					Scanner input = new Scanner(fileChooser.getSelectedFile());
					
					String placesTmp = input.nextLine();
					String transitionsTmp = input.nextLine();
					ArrayList<String> edgesTmp = new ArrayList<>();
					while (input.hasNext()) {
						edgesTmp.add(input.nextLine());
					}
					
					/*System.out.println(placesTmp);
					System.out.println(transitionsTmp);
					System.out.println(edgesTmp);*/
					
					String placesStr[] = placesTmp.trim().split(";");
					String transitionsStr[] = transitionsTmp.trim().split(";");
					String edgesStr[][] = new String[edgesTmp.size()][3];
					
					for (int i = 0; i < edgesTmp.size(); i++) {
						edgesStr[i] = edgesTmp.get(i).trim().split("-");
					}
					
					ArrayList<Place> places = new ArrayList<>();
					
					for (String placeStr : placesStr) {
						String place[] = placeStr.split(",");
						places.add(new Place(place[0], Integer.parseInt(place[1])));
					}
					
					ArrayList<Transition> transitions = new ArrayList<>();
					Map<String, ArrayList<Edge>> edges = new HashMap<>();
					
					for (String transitionStr : transitionsStr) {
//						System.out.println("Add " + transitionStr + " a transicoes");
						transitions.add(new Transition(transitionStr, false));
						edges.put(transitionStr, new ArrayList<Edge>());
					}
					
					
					/*for (Transition transition : transitions) {
						System.out.println(transition.getLabel());
					}
					for (Place place : places) {
						System.out.println(place.getLabel());
					}
					System.out.println("Chaves do hash");
					System.out.println(edges.keySet());*/
					
					
//					System.out.println("Dentro da construcao do hash");
					for (int i = 0; i < edgesStr.length; i++) {
//						System.out.println(edgesStr[i][0]);
						
						State origin = null;
						State destiny = null;
						
						// Verifica se o hashmap tem o label da origem como chave
						if (edges.containsKey(edgesStr[i][0])) {
//							System.out.println("Origem eh chave do hash/transicao");
							// origem e uma transicao
							for (Transition transition : transitions) {
								if (transition.getLabel().equals(edgesStr[i][0])) {
									origin = transition;
									break;
								}
							}
							
							for (Place place : places) {
								if (place.getLabel().equals(edgesStr[i][2])) {
									destiny = place;
									break;
								}
							}
						} else {
							// origem e um lugar
//							System.out.println("Origem eh lugar");
							for (Place place : places) {
//								System.out.println("comparando " + edgesStr[i][0] + " com: " + place.getLabel());
								if (place.getLabel().equals(edgesStr[i][0])) {
//									System.out.println(edgesStr[i][0] + " = " + place.getLabel());
									origin = place;
									break;
								}
							}
							
							for (Transition transition : transitions) {
								if (transition.getLabel().equals(edgesStr[i][2])) {
									destiny = transition;
									break;
								}
							}
						}
						
						
						
						Edge edge = new Edge(origin, destiny, Integer.parseInt(edgesStr[i][1]));
						
						if (origin instanceof Transition) {
							edges.get(origin.getLabel()).add(edge);
						} else {
							edges.get(destiny.getLabel()).add(edge);
						}
						
						
						
					}

					PetriNetwork petriNetwork = new PetriNetwork(places, transitions, edges);
					
					addTab(petriNetwork, fileChooser.getSelectedFile().getName());
					
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(MainWindow.this, "Erro ao abrir o arquivo!", "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		
	}
	
	
	/**
	 * @author neto
	 * Classe interna responsável por obter a arvore de cobertura
	 */
	private class SpanningTreeHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			TabPanel tab = tabs.get(networksPanel.getSelectedIndex());
			PetriNetwork petriNet = tab.getPetriNetwork();
			
			Node node = new Node(petriNet, new ArrayList<ArrayList<Integer>>(), new ArrayList<ArrayList<Integer>>());
			Tree tree = new Tree(node);
			tree.generatedTree();
			
			addTab(tree, tab.getFileName().replace(".", "_") + "_tree");
		}
		
	}
	
	
	/**
	 * @author neto
	 * Classe interna responsável por obter os estados bloqueados
	 */
	private class BlockingStatesHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("Clicou para estados bloqueados");
		}
		
	}
	
	
	/**
	 * @author neto
	 * Classe interna responsável por obter os estados nao-limitados
	 */
	private class NonLimitedStatesHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("Clicou para estados nao limitados");
		}
		
	}
	
	
	/**
	 * @author neto
	 * Classe interna responsável por calcular a conservacao da rede
	 */
	private class NetworkConservationHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			TabPanel tab = tabs.get(networksPanel.getSelectedIndex());
			Tree tree = tab.getTree();
			
			if (tree.conservatedTree()) {
				JOptionPane.showMessageDialog(null, "A árvore é conservativa.");
			} else {
				JOptionPane.showMessageDialog(null, "A árvore não é conservativa.");
			}
		}
		
	}
	
	
	/**
	 * @author neto
	 * Classe interna responsável por verifica se um no eh alcansavel
	 */
	private class ReachableStateHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			TabPanel tab = tabs.get(networksPanel.getSelectedIndex());
			Tree tree = tab.getTree();
			
			String config = JOptionPane.showInputDialog("Digite a configuração do estado a ser verificado (ex.: 1 0 0 2 4) :");
			String configs[] = config.split(" ");
			
			ArrayList<Integer> configuration = new ArrayList<>();
			
			for (String c : configs) {
				configuration.add(Integer.parseInt(c));
			}
			
			boolean reachable = tree.reachableNode(configuration);
			System.out.println(reachable);
			
			addTab(tree, tab.getFileName().replace(".", "_") + "_reachable");
			
		}
		
	}
	
}
