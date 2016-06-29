package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
import javax.swing.filechooser.FileFilter;

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
		operationsPanel = new JPanel(new GridLayout(4, 1));
		
		spanningTreeButton =  new JButton("Árvore de cobertura");
		spanningTreeButton.addActionListener(new SpanningTreeHandler());
		
		blockingStatesButton = new JButton("Estados bloqueantes");
		blockingStatesButton.addActionListener(new BlockingStatesHandler());
		
		nonLimitedStatesButton = new JButton("Estados não-limitados");
		nonLimitedStatesButton.addActionListener(new NonLimitedStatesHandler());
		
		networkConservationButton = new JButton("Conservação da rede");
		networkConservationButton.addActionListener(new NetworkConservationHandler());
		
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
	
	
	
	/**
	 * @author neto
	 * Classe interna responsável por manipular os eventos de abertura de arquivo
	 */
	private class OpenFileHandler implements ActionListener { 
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("Clicou para abrir arquivo");
			JFileChooser fileChooser = new JFileChooser();
			int returnVal = fileChooser.showOpenDialog(MainWindow.this);
		}
		
	}
	
	
	/**
	 * @author neto
	 * Classe interna responsável por obter a arvore de cobertura
	 */
	private class SpanningTreeHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("Clicou para árvore de cobertura");
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
			// TODO Auto-generated method stub
			System.out.println("Clicou para conservção da rede");
		}
		
	}
	
}
