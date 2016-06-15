package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.MenuBar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;

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
	
	
	// Painel da barra de ferramentas
	private JPanel toolbarPanel;
	
	// Barra de ferramentas file
	private JToolBar fileToolbar;
	private JButton openFileButton;
	private JButton saveFileButton;
	
	// Barra de ferramentas network components
	private JToolBar networkComponentsToolbar;
	private JButton addPlaceButton;
	private JButton addTransitionButton;
	private JButton addEdgeButton;
	
	
	// Painel centra e painel de abas
	private JPanel centerPanel;
	private JTabbedPane networksPane;

	
	/**
	 * Construtor da classe MainWindow
	 */
	public MainWindow() {
		super("Petri Networks");
		
		this.createMenuBar();
		
		this.createToolbar();
		
		this.createOperationsPanel();
		
	}
	
	
	/**
	 * Cria barra de menus
	 */
	private void createMenuBar() {
		menuBar = new JMenuBar();
		
		fileMenu = new JMenu("Arquivo");
		openMenuItem = new JMenuItem("Abrir arquivo");
		saveMenuItem = new JMenuItem("Salvar arquivo");
		exportMenuItem = new JMenuItem("Exportar");
		exitMenuItem = new JMenuItem("Sair");
		
		fileMenu.add(openMenuItem);
		fileMenu.add(saveMenuItem);
		fileMenu.add(exportMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(exitMenuItem);
		
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
		
		fileToolbar = new JToolBar();
		fileToolbar.setName("File");
		
		openFileButton = new JButton("Open file");
		saveFileButton = new JButton("Save file");
		
		fileToolbar.add(openFileButton);
		fileToolbar.add(saveFileButton);
		
		
		networkComponentsToolbar = new JToolBar();
		networkComponentsToolbar.setName("Network components");
		
		addPlaceButton = new JButton("Add place");
		addTransitionButton = new JButton("Add transition");
		addEdgeButton = new JButton("add edge");
		
		networkComponentsToolbar.add(addPlaceButton);
		networkComponentsToolbar.add(addTransitionButton);
		networkComponentsToolbar.add(addEdgeButton);
		
		toolbarPanel.add(fileToolbar);
		toolbarPanel.add(networkComponentsToolbar);
		
		this.add(toolbarPanel, BorderLayout.NORTH);
	}
	
	
	/**
	 * Cria painel com os botoes de operacao
	 */
	private void createOperationsPanel() {
		operationsPanel = new JPanel(new GridLayout(4, 1));
		
		spanningTreeButton =  new JButton("Árvore de cobertura");
		
		blockingStatesButton = new JButton("Estados bloqueantes");
		
		nonLimitedStatesButton = new JButton("Estados não-limitados");
		
		networkConservationButton = new JButton("Conservação da rede");
		
		operationsPanel.add(spanningTreeButton);
		operationsPanel.add(blockingStatesButton);
		operationsPanel.add(nonLimitedStatesButton);
		operationsPanel.add(networkConservationButton);
		
		this.add(operationsPanel, BorderLayout.WEST);
	}
	
	
	/**
	 * Cria painel de abas
	 */
	private void createNetworksPane() {
		
	}
	
}
