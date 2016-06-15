package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MainWindow extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel operationsPanel;
	private JButton spanningTreeButton;
	private JButton blockingStatesButton;
	private JButton nonLimitedStatesButton;
	private JButton networkConservationButton;
	
	private JPanel centerPanel;
	private JTabbedPane networksPane;

	
	/**
	 * Construtor da classe MainWindow
	 */
	public MainWindow() {
		super("Petri Networks");
				
		this.createOperationsPanel();
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
