package gui;

import gerenciadordetopologia.Canais;
import gerenciadordetopologia.Topologia;
import gui.actionlisteners.ActionListenerCarregaBiblioteca;
import gui.actionlisteners.ActionListenerCarregaCanais;
import gui.actionlisteners.ActionListenerCarregaTopologia;
import gui.actionlisteners.ActionListenerExibirDominioFalhas;

import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.xml.parsers.ParserConfigurationException;

import geradorautomaticodealarmes.SimuladorDeEmissaoDeAlarmes;
import geradorautomaticodealarmes.erros.ExceptionTopologiaNaoDefinida;
import gerenciadordebiblioteca.Biblioteca;
import gerenciadordebiblioteca.erros.ExceptionBibliotecaNaoDefinida;
import correlacaodealarmes.AlgoritimoDeCorrelacaoDeAlarmes;

/*
 * PrincipalWindows.java Criado em 31/01/2004
 *
 * Todo Codigo criado abaixo, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e não seja para fins lucra-
 * tivos capitalistas.
 * 
 */

/**
 * @author Carlos Delfino
 * @email suporte@carlosdelfino.eti.br
 * @site http://www.carlosdelfino.eti.br
 * 
 * Digite aqui o que esta classe faz.
 * Nome: 
 */
public class PrincipalWindows extends JFrame
{
	public static void main(String[] args) throws ClassNotFoundException
	{
		try
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		PrincipalWindows pw = new PrincipalWindows();

	}

	final public String TAB_BIBLIOTECA = "Biblioteca";

	final public String TAB_CANAIS = "Canais";

	final public String TAB_CANAIS_GRAFICO = "Grafico dos Canais";

	final public String TAB_CANAIS_TEXTO = "Canais Textual";

	final public String TAB_DOMINIO = "Dominio de Alarmes";

	final public String TAB_DOMINIO_TEXTO = "Dominio de Alarmes";

	final public String TAB_GERENCIA = "Gerência";

	final public String TAB_GERENCIA_SIMULADOR = "Simulador de Falhas";

	final public String TAB_TOPOLOGIA = "Topologia";

	final public String TAB_TOPOLOGIA_GRAFICO = "Grafico da Topologia";

	final public String TAB_TOPOLOGIA_TEXTO = "Topologia Textual";
	
	final public String ABILITA = "enableAT_";

	final public String ABILITA_TAB_CANAIS = ABILITA + TAB_CANAIS;

	final public String ABILITA_TAB_TOPOLOGIA = ABILITA + TAB_DOMINIO;

	private AlgoritimoDeCorrelacaoDeAlarmes aca;

	private String arqBiblioteca;

	private String arqCanais;

	private String arqTopologia;

	private Biblioteca biblioteca;
	private javax.swing.JButton jButtonBiblioteca = null;
	private javax.swing.JButton jButtonCanais = null;
	private javax.swing.JButton jButtonDominio = null;
	private javax.swing.JButton jButtonGrafoCanais = null;
	private javax.swing.JButton jButtonGrafoTopologia = null;
	private javax.swing.JButton jButtonTopologia = null;

	private javax.swing.JPanel jContentPane = null;

	private javax.swing.JMenuBar jJMenuBar = null;
	private javax.swing.JLabel jLabel = null;
	private javax.swing.JLabel jLabel1 = null;
	private javax.swing.JMenu jMenu = null;
	private javax.swing.JMenu jMenuBiblioteca = null;
	private javax.swing.JMenu jMenuGerencia = null;
	private javax.swing.JMenu jMenuTopologia = null;
	private javax.swing.JMenuItem jMICarregarBiblioteca = null;
	private javax.swing.JMenuItem jMICarregarCanais = null;
	private javax.swing.JMenuItem jMICarregarTopologia = null;
	private javax.swing.JMenuItem jMIExibirDominioFalhas = null;
	private javax.swing.JMenuItem jMIHelp = null;
	private javax.swing.JMenuItem jMIPegarFalhasSimulador = null;
	private javax.swing.JMenuItem jMISair = null;
	private javax.swing.JOptionPane jOptionPaneDesconhecido = null;
	private javax.swing.JPanel jPanelBiblioteca = null;
	private javax.swing.JPanel jPanelCanais = null;
	private javax.swing.JPanel jPanelDominio = null;
	private javax.swing.JPanel jPanelGrafoCanais = null;
	private javax.swing.JPanel jPanelGrafoTopologia = null;
	private javax.swing.JPanel jPanelSimuladorFalhas = null;
	private javax.swing.JPanel jPanelTopologia = null;
	private javax.swing.JScrollPane jScrollPane1 = null;

	private javax.swing.JScrollPane jScrollPaneBiblioteca = null;
	private javax.swing.JScrollPane jScrollPaneCanais = null;
	private javax.swing.JScrollPane jScrollPaneDominio = null;
	private javax.swing.JScrollPane jScrollPaneGrafoCanais = null;
	private javax.swing.JScrollPane jScrollPaneTopologia = null;
	private javax.swing.JTabbedPane jTabbedPaneCanais = null;
	private javax.swing.JTabbedPane jTabbedPaneGeral = null;
	private javax.swing.JTabbedPane jTabbedPaneGerencia = null;
	private javax.swing.JTabbedPane jTabbedPaneTopologia = null;
	private javax.swing.JTree jTreeBiblioteca = null;
	private javax.swing.JTree jTreeCanais = null;
	private javax.swing.JTree jTreeDominio = null;
	private javax.swing.JTree jTreeTopologia = null;
	private javax.swing.JTextField linhaDeStatus = null;

	private PanelSimuladorDeAlarmes panelSimulador;

	private SimuladorDeEmissaoDeAlarmes simulador;

	private SimuladorDeEmissaoDeAlarmes simuladorDeEmissaoDealarmes;

	protected boolean StageChangeListerDominioAtivo = false;


	private Topologia topologia;
	/**
	 * This is the default constructor
	 */
	public PrincipalWindows()
	{
		super();
		initialize();

	}
	/**
	 * @param p_string
	 * @param p_b
	 */
	public void abilitaTab(String p_tab)
	{
		getJTabbedPaneGeral().setEnabledAt(getJTabbedPaneGeral().indexOfTab(p_tab), true);

		if (p_tab.equals(TAB_TOPOLOGIA))
		{
			getJTabbedPaneTopologia().setEnabled(true);
			getJPanelTopologia().setEnabled(true);
			getJButtonTopologia().setEnabled(true);
			getJScrollPaneTopologia().setEnabled(true);
			getJTreeTopologia().setEnabled(true);
			getJPanelGrafoTopologia().setEnabled(true);
			getJButtonGrafoTopologia().setEnabled(true);
			getJLabel().setEnabled(true);
		} else if (p_tab.equals(TAB_CANAIS))
		{
			getJTabbedPaneCanais().setEnabled(true);
			getJPanelCanais().setEnabled(true);
			getJButtonCanais().setEnabled(true);
			getJScrollPaneCanais().setEnabled(true);
			getJTreeCanais().setEnabled(true);
			getJPanelGrafoCanais().setEnabled(true);
			getJButtonGrafoCanais().setEnabled(true);
			getJLabel1().setEnabled(true);
		} else if (p_tab.equals(TAB_GERENCIA)){
			getJTabbedPaneGerencia().setEnabled(true);
			getJPanelDominio().setEnabled(true);
			getJButtonDominio().setEnabled(true);
			getJScrollPaneDominio().setEnabled(true);
			getJTreeDominio().setEnabled(true);
			getJPanelSimuladorFalhas().add(getPanelDoSimuladorDeFalhas(), null);  // Generated

		}

	}
	/**
	 * @param p_string
	 * @param p_b
	 */
	public void desabilitaTab(String p_tab)
	{
		getJTabbedPaneGeral().setEnabledAt(getJTabbedPaneGeral().indexOfTab(p_tab), true);

	}
	/**
	 * @return
	 */
	private ActionListener getActionListenerCarregarBiblioteca()
	{
		return new ActionListenerCarregaBiblioteca(this);
	}
	private ActionListener getActionListenerCarregarCanais()
	{
		return new ActionListenerCarregaCanais(this);
	}
	private ActionListener getActionListenerCarregarTopologia()
	{
		return new ActionListenerCarregaTopologia(this);
	}
	/**
	 * @return
	 */
	private ActionListener getActionListenerExibirDominioFalhas()
	{
		return new ActionListenerExibirDominioFalhas(this);
	}
	/**
	 * 
	 */
	public AlgoritimoDeCorrelacaoDeAlarmes getAlgoritimoDeCorrelacaoDeAlarmes() throws ParserConfigurationException
	{
		if (aca == null){
			aca = new AlgoritimoDeCorrelacaoDeAlarmes(getCanais().getCanais(),getSimuladorDeEmissaoDeAlarmes().getAlarmesXML());
		}
		return aca;
	}
	/**
	 * @return
	 */
	public String getArqBiblioteca()
	{
		return arqBiblioteca;
	}
	/**
	 * 
	 */
	private String getArqTopologia()
	{
		return arqTopologia;
	}
	/**
	 * @return
	 */
	public Biblioteca getBiblioteca()
	{
		return biblioteca;
	}
	/**
	 * 
	 */
	public Canais getCanais()
	{

		return getTopologia().getCanais();

	}
	/**
	 * @return
	 */
	public File getDiretorioDeConfiguracao()
	{
		return new File("c:\\Correlação de Alarmes\\cnf");
	}

	/**
	 * This method initializes jButtonBiblioteca
	 * 
	 * @return java.awt.Button
	 */
	public javax.swing.JButton getJButtonBiblioteca()
	{
		if (jButtonBiblioteca == null)
		{
			try
			{
				jButtonBiblioteca = new javax.swing.JButton(); // Generated
				jButtonBiblioteca.setName("botaoCarregarBiblioteca"); // Generated
				jButtonBiblioteca.setLabel("Carregar Biblioteca"); // Generated
				jButtonBiblioteca.setActionCommand("cmd_carregarBiblioteca"); // Generated
				jButtonBiblioteca.setText("Carregar Biblioteca"); // Generated
				jButtonBiblioteca.addActionListener(getActionListenerCarregarBiblioteca());
			} catch (java.lang.Throwable e)
			{
				//  Do Something
			}
		}
		return jButtonBiblioteca;
	}
	public javax.swing.JButton getJButtonCanais()
	{
		if (jButtonCanais == null)
		{
			try
			{
				jButtonCanais = new javax.swing.JButton(); // Generated
				jButtonCanais.setLabel("Carregar Canais"); // Generated
				jButtonCanais.setActionCommand("cmd_carregarCanais"); // Generated
				jButtonCanais.setName("BotaoCarregarCanais"); // Generated
				jButtonCanais.setText("Carregar Canais"); // Generated
				jButtonCanais.setEnabled(false); // Generated
				jButtonCanais.addActionListener(getActionListenerCarregarCanais());
			} catch (java.lang.Throwable e)
			{
				//  Do Something
			}
		}
		return jButtonCanais;
	}
	/**
	 * This method initializes jButtonDominio
	 * 
	 * @return javax.swing.JButton
	 */
	private javax.swing.JButton getJButtonDominio() {
		if(jButtonDominio == null) {
			try {
				jButtonDominio = new javax.swing.JButton();  // Generated
				jButtonDominio.setText("Processa e Exibe Dominio");  // Generated
				jButtonDominio.addActionListener(getActionListenerExibirDominioFalhas());
			}
			catch (java.lang.Throwable e) {
				//  Do Something
			}
		}
		return jButtonDominio;
	}
	/**
	 * This method initializes jButtonGrafoCanais
	 * 
	 * @return javax.swing.JButton
	 */
	public javax.swing.JButton getJButtonGrafoCanais()
	{
		if (jButtonGrafoCanais == null)
		{
			try
			{
				jButtonGrafoCanais = new javax.swing.JButton(); // Generated
				jButtonGrafoCanais.setText("Carregar Canais"); // Generated
				jButtonGrafoCanais.setActionCommand("cmd_carregarCanais_topologia"); // Generated
				jButtonGrafoCanais.addActionListener(getActionListenerCarregarCanais());
			} catch (java.lang.Throwable e)
			{
				//  Do Something
			}
		}
		return jButtonGrafoCanais;
	}
	public javax.swing.JButton getJButtonGrafoTopologia()
	{
		if (jButtonGrafoTopologia == null)
		{
			try
			{
				jButtonGrafoTopologia = new javax.swing.JButton(); // Generated
				jButtonGrafoTopologia.setLabel("Carregar Topologia"); // Generated
				jButtonGrafoTopologia.setActionCommand("cmd_carregarTopologia_Grafico"); // Generated
				jButtonGrafoTopologia.setName("BotaoCarregarTopologia"); // Generated
				jButtonGrafoTopologia.setText("Carregar Topologia"); // Generated
				jButtonGrafoTopologia.setEnabled(false); // Generated
				jButtonGrafoTopologia.addActionListener(getActionListenerCarregarTopologia());
			} catch (java.lang.Throwable e)
			{
				//  Do Something
			}
		}
		return jButtonGrafoTopologia;
	}
	/**
	 * This method initializes jButtonTopologia
	 * 
	 * @return java.awt.Button
	 */
	public javax.swing.JButton getJButtonTopologia()
	{
		if (jButtonTopologia == null)
		{
			try
			{
				jButtonTopologia = new javax.swing.JButton(); // Generated
				jButtonTopologia.setLabel("Carregar Topologia"); // Generated
				jButtonTopologia.setActionCommand("cmd_carregarTopologia"); // Generated
				jButtonTopologia.setName("BotaoCarregarTopologia"); // Generated
				jButtonTopologia.setText("Carregar Topologia"); // Generated
				jButtonTopologia.setEnabled(false); // Generated
				jButtonTopologia.addActionListener(getActionListenerCarregarTopologia());
			} catch (java.lang.Throwable e)
			{
				//  Do Something
			}
		}
		return jButtonTopologia;
	}
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJContentPane()
	{
		if (jContentPane == null)
		{
			jContentPane = new javax.swing.JPanel();
			jContentPane.setLayout(new java.awt.FlowLayout());
			jContentPane.add(getJTabbedPaneGeral(), null); // Generated
			jContentPane.add(getLinhaDeStatus(), null); // Generated
			jContentPane.setAutoscrolls(true); // Generated
			jContentPane.setPreferredSize(new java.awt.Dimension(795,550)); // Generated
		}
		return jContentPane;
	}
	/**
	 * This method initializes jJMenuBar
	 * 
	 * @return javax.swing.JMenuBar
	 */
	private javax.swing.JMenuBar getJJMenuBar()
	{
		if (jJMenuBar == null)
		{
			try
			{
				jJMenuBar = new javax.swing.JMenuBar(); // Generated
				jJMenuBar.add(getJMenuBiblioteca()); // Generated
				jJMenuBar.add(getJMenuTopologia()); // Generated
				jJMenuBar.add(getJMenuGerencia()); // Generated
				jJMenuBar.add(getJMenu()); // Generated
			} catch (java.lang.Throwable e)
			{
				//  Do Something
			}
		}
		return jJMenuBar;
	}
	/**
	 * This method initializes jLabel
	 * 
	 * @return javax.swing.JLabel
	 */
	private javax.swing.JLabel getJLabel()
	{
		if (jLabel == null)
		{
			try
			{
				jLabel = new javax.swing.JLabel(); // Generated
				jLabel.setText("O Grafo da Topologia, está sendo elaborado, este é apenas um exemplo");
				// Generated
				jLabel.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 16)); // Generated
				jLabel.setEnabled(false); // Generated
			} catch (java.lang.Throwable e)
			{
				//  Do Something
			}
		}
		return jLabel;
	}
	/**
	 * This method initializes jLabel1
	 * 
	 * @return javax.swing.JLabel
	 */
	private javax.swing.JLabel getJLabel1()
	{
		if (jLabel1 == null)
		{
			try
			{
				jLabel1 = new javax.swing.JLabel(); // Generated
				jLabel1.setText("O Grafo de Canais, está sendo elaborado, este é apenas um exemplo");
				// Generated
				jLabel1.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 16)); // Generated

			} catch (java.lang.Throwable e)
			{
				//  Do Something
			}
		}
		return jLabel1;
	}
	/**
	 * This method initializes jMenu
	 * 
	 * @return javax.swing.JMenu
	 */
	private javax.swing.JMenu getJMenu()
	{
		if (jMenu == null)
		{
			try
			{
				jMenu = new javax.swing.JMenu(); // Generated
				jMenu.add(getJMIHelp()); // Generated
				jMenu.add(getJMISair()); // Generated
				jMenu.setComponentOrientation(java.awt.ComponentOrientation.UNKNOWN); // Generated
				jMenu.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT); // Generated
				jMenu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT); // Generated
				jMenu.setName("Utilitarios"); // Generated
				jMenu.setText("Utilitários"); // Generated
			} catch (java.lang.Throwable e)
			{
				//  Do Something
			}
		}
		return jMenu;
	}
	/**
	 * This method initializes jMenuBiblioteca
	 * 
	 * @return javax.swing.JMenu
	 */
	private javax.swing.JMenu getJMenuBiblioteca()
	{
		if (jMenuBiblioteca == null)
		{
			try
			{
				jMenuBiblioteca = new javax.swing.JMenu(); // Generated
				jMenuBiblioteca.add(getJMICarregarBiblioteca()); // Generated
				jMenuBiblioteca.setName("Biblioteca"); // Generated
				jMenuBiblioteca.setText("Biblioteca"); // Generated
			} catch (java.lang.Throwable e)
			{
				//  Do Something
			}
		}
		return jMenuBiblioteca;
	}
	/**
	 * This method initializes jMenuGerencia
	 * 
	 * @return javax.swing.JMenu
	 */
	private javax.swing.JMenu getJMenuGerencia()
	{
		if (jMenuGerencia == null)
		{
			try
			{
				jMenuGerencia = new javax.swing.JMenu(); // Generated
				jMenuGerencia.add(getJMIExibirDominioFalhas()); // Generated
				jMenuGerencia.add(getJMIPegarFalhasSimulador());  // Generated
				jMenuGerencia.setText("Gerência"); // Generated
				jMenuGerencia.setName("Gerência"); // Generated
			} catch (java.lang.Throwable e)
			{
				//  Do Something
			}
		}
		return jMenuGerencia;
	}
	/**
	 * This method initializes jMenuTopologia
	 * 
	 * @return javax.swing.JMenu
	 */
	private javax.swing.JMenu getJMenuTopologia()
	{
		if (jMenuTopologia == null)
		{
			try
			{
				jMenuTopologia = new javax.swing.JMenu(); // Generated
				jMenuTopologia.add(getJMICarregarTopologia()); // Generated
				jMenuTopologia.add(getJMICarregarCanais()); // Generated
				jMenuTopologia.setName("Topologia"); // Generated
				jMenuTopologia.setText("Topologia"); // Generated
			} catch (java.lang.Throwable e)
			{
				//  Do Something
			}
		}
		return jMenuTopologia;
	}
	/**
	 * This method initializes jMICarregarBiblioteca
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private javax.swing.JMenuItem getJMICarregarBiblioteca()
	{
		if (jMICarregarBiblioteca == null)
		{
			try
			{
				jMICarregarBiblioteca = new javax.swing.JMenuItem(); // Generated
				jMICarregarBiblioteca.setName("Carregar"); // Generated
				jMICarregarBiblioteca.setText("Carregar"); // Generated
				jMICarregarBiblioteca.addActionListener(getActionListenerCarregarBiblioteca());
			} catch (java.lang.Throwable e)
			{
				//  Do Something
			}
		}
		return jMICarregarBiblioteca;
	}
	/**
	 * This method initializes jMICarregarCanais
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private javax.swing.JMenuItem getJMICarregarCanais()
	{
		if (jMICarregarCanais == null)
		{
			try
			{
				jMICarregarCanais = new javax.swing.JMenuItem(); // Generated
				jMICarregarCanais.setName("Carregar Canais"); // Generated
				jMICarregarCanais.setText("Carregar Canais"); // Generated
				jMICarregarCanais.addActionListener(getActionListenerCarregarCanais());

			} catch (java.lang.Throwable e)
			{
				//  Do Something
			}
		}
		return jMICarregarCanais;
	}
	/**
	 * This method initializes jMICarregarTopologia
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private javax.swing.JMenuItem getJMICarregarTopologia()
	{
		if (jMICarregarTopologia == null)
		{
			try
			{
				jMICarregarTopologia = new javax.swing.JMenuItem(); // Generated
				jMICarregarTopologia.setName("Carregar Topologia"); // Generated
				jMICarregarTopologia.setText("Carregar Topologia"); // Generated
				jMICarregarTopologia.addActionListener(getActionListenerCarregarTopologia());
			} catch (java.lang.Throwable e)
			{
				//  Do Something
			}
		}
		return jMICarregarTopologia;
	}
	/**
	 * This method initializes jMIExibirDominioFalhas
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private javax.swing.JMenuItem getJMIExibirDominioFalhas()
	{
		if (jMIExibirDominioFalhas == null)
		{
			try
			{
				jMIExibirDominioFalhas = new javax.swing.JMenuItem(); // Generated
				jMIExibirDominioFalhas.setText("Exibir Dominio de Falhas");  // Generated
				jMIExibirDominioFalhas.addActionListener(getActionListenerExibirDominioFalhas());
			} catch (java.lang.Throwable e)
			{
				//  Do Something
			}
		}
		return jMIExibirDominioFalhas;
	}
	/**
	 * This method initializes jMIHelp
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private javax.swing.JMenuItem getJMIHelp()
	{
		if (jMIHelp == null)
		{
			try
			{
				jMIHelp = new javax.swing.JMenuItem(); // Generated
				jMIHelp.addActionListener(new java.awt.event.ActionListener()
				{
					public void actionPerformed(java.awt.event.ActionEvent e)
					{
						System.out.println("actionPerformed()");
					}
				});
			} catch (java.lang.Throwable e)
			{
				//  Do Something
			}
		}
		return jMIHelp;
	}
	/**
	 * This method initializes jMIPegarFalhasSimulador
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private javax.swing.JMenuItem getJMIPegarFalhasSimulador() {
		if(jMIPegarFalhasSimulador == null) {
			try {
				jMIPegarFalhasSimulador = new javax.swing.JMenuItem();  // Generated
				
			}
			catch (java.lang.Throwable e) {
				//  Do Something
			}
		}
		return jMIPegarFalhasSimulador;
	}
	/**
	 * This method initializes jMISair
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private javax.swing.JMenuItem getJMISair()
	{
		if (jMISair == null)
		{
			try
			{
				jMISair = new javax.swing.JMenuItem(); // Generated
				jMISair.setName("sair"); // Generated
				jMISair.setText("Sair"); // Generated
				jMISair.addActionListener(new java.awt.event.ActionListener()
				{
					public void actionPerformed(java.awt.event.ActionEvent e)
					{
						System.exit(0);
					}
				});
			} catch (java.lang.Throwable e)
			{
				//  Do Something
			}
		}
		return jMISair;
	}
	/**
	 * This method initializes jOptionPaneDesconhecido
	 * 
	 * @return javax.swing.JOptionPane
	 */
	private javax.swing.JOptionPane getJOptionPaneDesconhecido()
	{
		if (jOptionPaneDesconhecido == null)
		{
			try
			{} catch (java.lang.Throwable e)
			{
				//  Do Something
			}
		}
		return jOptionPaneDesconhecido;
	}
	/**
	 * 
	 */
	/**
	 * This method initializes jPanelBiblioteca
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJPanelBiblioteca()
	{
		if (jPanelBiblioteca == null)
		{
			try
			{
				jPanelBiblioteca = new javax.swing.JPanel(); // Generated
				jPanelBiblioteca.add(getJButtonBiblioteca(), null); // Generated
				jPanelBiblioteca.add(getJScrollPaneBiblioteca(), null); // Generated
				jPanelBiblioteca.setBorder(
					javax.swing.BorderFactory.createEtchedBorder(
						javax.swing.border.EtchedBorder.RAISED));
				// Generated
				jPanelBiblioteca.setPreferredSize(new java.awt.Dimension(780,470));  // Generated
			} catch (java.lang.Throwable e)
			{
				//  Do Something
			}
		}
		return jPanelBiblioteca;
	}
	/**
	 * This method initializes grafoPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	/**
	 * This method initializes jPanelCanais
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJPanelCanais()
	{
		if (jPanelCanais == null)
		{
			try
			{
				jPanelCanais = new javax.swing.JPanel(); // Generated
				jPanelCanais.add(getJButtonCanais(), null); // Generated
				jPanelCanais.add(getJScrollPaneCanais(), null); // Generated
				jPanelCanais.setBorder(
					javax.swing.BorderFactory.createEtchedBorder(
						javax.swing.border.EtchedBorder.RAISED));
				// Generated
				jPanelCanais.setPreferredSize(new java.awt.Dimension(780,430));  // Generated
			} catch (java.lang.Throwable e)
			{
				//  Do Something
			}
		}
		return jPanelCanais;
	}
	/**
	 * This method initializes jPanelDominio
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJPanelDominio()
	{
		if (jPanelDominio == null)
		{
			try
			{
				jPanelDominio = new javax.swing.JPanel(); // Generated
				jPanelDominio.add(getJButtonDominio(), null);  // Generated
				jPanelDominio.add(getJScrollPaneDominio(), null);  // Generated
			} catch (java.lang.Throwable e)
			{
				//  Do Something
			}
		}
		return jPanelDominio;
	}
	/**
	 * This method initializes jPanelGrafoCanais
	 * 
	 * @return javax.swing.JPanel
	 */
	public javax.swing.JPanel getJPanelGrafoCanais()
	{
		if (jPanelGrafoCanais == null)
		{
			try
			{
				jPanelGrafoCanais = new javax.swing.JPanel(); // Generated
				jPanelGrafoCanais.add(getJButtonGrafoCanais(), null); // Generated
				jPanelGrafoCanais.add(getJLabel1(), null); // Generated
				jPanelGrafoCanais.add(getJScrollPaneGrafoCanais(), null); // Generated
				jPanelGrafoCanais.setPreferredSize(new java.awt.Dimension(780,430));  // Generated
			} catch (java.lang.Throwable e)
			{
				//  Do Something
			}
		}
		return jPanelGrafoCanais;
	}
	/**
	 * This method initializes jPanelGrafoTopologia
	 * 
	 * @return javax.swing.JPanel
	 */
	public javax.swing.JPanel getJPanelGrafoTopologia()
	{
		if (jPanelGrafoTopologia == null)
		{
			try
			{
				jPanelGrafoTopologia = new javax.swing.JPanel(); // Generated
				jPanelGrafoTopologia.add(getJButtonGrafoTopologia(), null); // Generated
				jPanelGrafoTopologia.add(getJLabel(), null); // Generated
				jPanelGrafoTopologia.add(getJScrollPane1(), null);  // Generated
				jPanelGrafoTopologia.setMinimumSize(new java.awt.Dimension(550, 350)); // Generated
				jPanelGrafoTopologia.setMaximumSize(new java.awt.Dimension(550, 350)); // Generated
				jPanelGrafoTopologia.setEnabled(false); // Generated
				jPanelGrafoTopologia.setBorder(
					javax.swing.BorderFactory.createEtchedBorder(
						javax.swing.border.EtchedBorder.RAISED));
				// Generated
			} catch (java.lang.Throwable e)
			{
				//  Do Something
			}
		}
		return jPanelGrafoTopologia;
	}
	/**
	 * This method initializes jPanelSimuladorFalhas
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJPanelSimuladorFalhas() {
		if(jPanelSimuladorFalhas == null) {
			try {
				jPanelSimuladorFalhas = new javax.swing.JPanel();  // Generated
			}
			catch (java.lang.Throwable e) {
				//  Do Something
			}	


		}
		return jPanelSimuladorFalhas;
	}
	/**
	 * This method initializes jPanelTopologia
	 * 
	 * @return javax.swing.JPanel
	 */
	javax.swing.JPanel getJPanelTopologia()
	{
		if (jPanelTopologia == null)
		{
			try
			{
				jPanelTopologia = new javax.swing.JPanel(); // Generated
				jPanelTopologia.add(getJButtonTopologia(), null); // Generated
				jPanelTopologia.add(getJScrollPaneTopologia(), null); // Generated
				jPanelTopologia.setMaximumSize(new java.awt.Dimension(690, 390)); // Generated
				jPanelTopologia.setEnabled(false); // Generated
				jPanelTopologia.setBorder(
					javax.swing.BorderFactory.createEtchedBorder(
						javax.swing.border.EtchedBorder.RAISED));
				// Generated
			} catch (java.lang.Throwable e)
			{
				//  Do Something
			}
		}
		return jPanelTopologia;
	}
	/**
	 * This method initializes jScrollPane1
	 * 
	 * @return javax.swing.JScrollPane
	 */
	public javax.swing.JScrollPane getJScrollPane1() {
		if(jScrollPane1 == null) {
			try {
				jScrollPane1 = new javax.swing.JScrollPane();  // Generated
				jScrollPane1.setPreferredSize(new java.awt.Dimension(775,445));  // Generated
			}
			catch (java.lang.Throwable e) {
				//  Do Something
			}
		}
		return jScrollPane1;
	}
	/**
	 * This method initializes jScrollPaneBiblioteca
	 * 
	 * @return javax.swing.JScrollPane
	 */
	public javax.swing.JScrollPane getJScrollPaneBiblioteca()
	{
		if (jScrollPaneBiblioteca == null)
		{
			try
			{
				jScrollPaneBiblioteca = new javax.swing.JScrollPane(); // Generated
				jScrollPaneBiblioteca.setViewportView(getJTreeBiblioteca());  // Generated
				jScrollPaneBiblioteca.setAutoscrolls(true); // Generated
				jScrollPaneBiblioteca.setVisible(true); // Generated
				jScrollPaneBiblioteca.setPreferredSize(new java.awt.Dimension(780,470)); // Generated
				jScrollPaneBiblioteca.setMaximumSize(new java.awt.Dimension(600, 400)); // Generated
				jScrollPaneBiblioteca.setMinimumSize(new java.awt.Dimension(500, 400)); // Generated
			} catch (java.lang.Throwable e)
			{
				//  Do Something
			}
		}
		return jScrollPaneBiblioteca;
	}
	/**
	 * This method initializes jScrollPaneCanais
	 * 
	 * @return javax.swing.JScrollPane
	 */
	javax.swing.JScrollPane getJScrollPaneCanais()
	{
		if (jScrollPaneCanais == null)
		{
			try
			{
				jScrollPaneCanais = new javax.swing.JScrollPane(); // Generated
				jScrollPaneCanais.setViewportView(getJTreeCanais());  // Generated
				jScrollPaneCanais.setPreferredSize(new java.awt.Dimension(775,445)); // Generated
				jScrollPaneCanais.setMaximumSize(new java.awt.Dimension(600, 380)); // Generated
				jScrollPaneCanais.setMinimumSize(new java.awt.Dimension(500, 380)); // Generated
				jScrollPaneCanais.setVisible(true); // Generated

				jScrollPaneCanais.setEnabled(false); // Generated
			} catch (java.lang.Throwable e)
			{
				//  Do Something
			}
		}
		return jScrollPaneCanais;
	}
	/**
	 * This method initializes jScrollPaneDominio
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private javax.swing.JScrollPane getJScrollPaneDominio() {
		if(jScrollPaneDominio == null) {
			try {
				jScrollPaneDominio = new javax.swing.JScrollPane();  // Generated
				jScrollPaneDominio.setViewportView(getJTreeDominio());  // Generated
				jScrollPaneDominio.setPreferredSize(new java.awt.Dimension(775,420));  // Generated
			}
			catch (java.lang.Throwable e) {
				//  Do Something
			}
		}
		return jScrollPaneDominio;
	}
	/**
	 * This method initializes jScrollPaneGrafoCanais
	 * 
	 * @return javax.swing.JScrollPane
	 */
	public javax.swing.JScrollPane getJScrollPaneGrafoCanais()
	{
		if (jScrollPaneGrafoCanais == null)
		{
			try
			{
				jScrollPaneGrafoCanais = new javax.swing.JScrollPane(); // Generated
				jScrollPaneGrafoCanais.setPreferredSize(new java.awt.Dimension(775,420)); // Generated
				jScrollPaneGrafoCanais.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				// Generated
				jScrollPaneGrafoCanais.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				// Generated
			} catch (java.lang.Throwable e)
			{
				//  Do Something
			}
		}
		return jScrollPaneGrafoCanais;
	}
	/**
	 * This method initializes jScrollPaneTopologia
	 * 
	 * @return javax.swing.JScrollPane
	 */
	javax.swing.JScrollPane getJScrollPaneTopologia()
	{
		if (jScrollPaneTopologia == null)
		{
			try
			{
				jScrollPaneTopologia = new javax.swing.JScrollPane(); // Generated
				jScrollPaneTopologia.setViewportView(getJTreeTopologia());  // Generated
				jScrollPaneTopologia.setPreferredSize(new java.awt.Dimension(775,445)); // Generated
				jScrollPaneTopologia.setMaximumSize(new java.awt.Dimension(550, 360)); // Generated
				jScrollPaneTopologia.setMinimumSize(new java.awt.Dimension(500, 300)); // Generated
				jScrollPaneTopologia.setVisible(true); // Generated
				jScrollPaneTopologia.setEnabled(false); // Generated
			} catch (java.lang.Throwable e)
			{
				//  Do Something
			}
		}
		return jScrollPaneTopologia;
	}
	/**
	 * This method initializes jTabbedPaneCanais
	 * 
	 * @return javax.swing.JTabbedPane
	 */
	public javax.swing.JTabbedPane getJTabbedPaneCanais()
	{
		if (jTabbedPaneCanais == null)
		{
			try
			{
				jTabbedPaneCanais = new javax.swing.JTabbedPane(); // Generated
				jTabbedPaneCanais.addTab(TAB_CANAIS_TEXTO, null, getJPanelCanais(), null); // Generated
				jTabbedPaneCanais.addTab(TAB_CANAIS_GRAFICO, null, getJPanelGrafoCanais(), null);
				// Generated
				jTabbedPaneCanais.setEnabled(false); // Generated
				jTabbedPaneCanais.setPreferredSize(new java.awt.Dimension(790,460));  // Generated
			} catch (java.lang.Throwable e)
			{
				//  Do Something
			}
		}
		return jTabbedPaneCanais;
	}
	/**
	 * This method initializes jTabbedPaneGeral
	 * 
	 * @return javax.swing.JTabbedPane
	 */
	public javax.swing.JTabbedPane getJTabbedPaneGeral()
	{
		if (jTabbedPaneGeral == null)
		{
			try
			{
				jTabbedPaneGeral = new javax.swing.JTabbedPane(); // Generated
				jTabbedPaneGeral.addTab(TAB_BIBLIOTECA, null, getJPanelBiblioteca(), null);
				// Generated
				jTabbedPaneGeral.addTab(TAB_TOPOLOGIA, null, getJTabbedPaneTopologia(), null);
				// Generated
				jTabbedPaneGeral.addTab(TAB_CANAIS, null, getJTabbedPaneCanais(), null); // Generated
				jTabbedPaneGeral.addTab(TAB_GERENCIA, null, getJTabbedPaneGerencia(), null);  // Generated
				jTabbedPaneGeral.setPreferredSize(new java.awt.Dimension(790,510)); // Generated

				for (int i = 1; i < jTabbedPaneGeral.getTabCount(); i++)
				{
					jTabbedPaneGeral.setEnabledAt(i, false);
				}

			} catch (java.lang.Throwable e)
			{
				//  Do Something
			}
		}
		return jTabbedPaneGeral;
	}
	/**
	 * This method initializes jTabbedPaneGerencia
	 * 
	 * @return javax.swing.JTabbedPane
	 */
	public javax.swing.JTabbedPane getJTabbedPaneGerencia()
	{
		if (jTabbedPaneGerencia == null)
		{
			try
			{
				jTabbedPaneGerencia = new javax.swing.JTabbedPane(); // Generated
				jTabbedPaneGerencia.addTab(TAB_DOMINIO_TEXTO, null, getJPanelDominio(), null);  // Generated
				jTabbedPaneGerencia.addTab(TAB_GERENCIA_SIMULADOR, null, getJPanelSimuladorFalhas(), null);  // Generated
				jTabbedPaneGerencia.setEnabled(false); // Generated
				
			} catch (java.lang.Throwable e)
			{
				//  Do Something
			}
		}
		return jTabbedPaneGerencia;
	}
	/**
	 * This method initializes jTabbedPaneTopologia
	 * 
	 * @return javax.swing.JTabbedPane
	 */
	public javax.swing.JTabbedPane getJTabbedPaneTopologia()
	{
		if (jTabbedPaneTopologia == null)
		{
			try
			{
				jTabbedPaneTopologia = new javax.swing.JTabbedPane(); // Generated
				jTabbedPaneTopologia.addTab(TAB_TOPOLOGIA_TEXTO, null, getJPanelTopologia(), null);
				// Generated
				jTabbedPaneTopologia.addTab(
					TAB_TOPOLOGIA_GRAFICO,
					null,
					getJPanelGrafoTopologia(),
					null);
				// Generated

				jTabbedPaneTopologia.setMaximumSize(new java.awt.Dimension(695, 416)); // Generated
				jTabbedPaneTopologia.setEnabled(false); // Generated
				jTabbedPaneTopologia.setPreferredSize(new java.awt.Dimension(790,485));  // Generated
			} catch (java.lang.Throwable e)
			{
				//  Do Something
			}
		}
		return jTabbedPaneTopologia;
	}
	/**
	 * This method initializes jTreeBiblioteca
	 * 
	 * @return javax.swing.JTree
	 */
	public javax.swing.JTree getJTreeBiblioteca() {
		if(jTreeBiblioteca == null) {
			try {
				jTreeBiblioteca = new javax.swing.JTree();  // Generated
				jTreeBiblioteca.setEnabled(true);  // Generated
				jTreeBiblioteca.setVisible(false);  // Generated
			}
			catch (java.lang.Throwable e) {
				//  Do Something
			}
		}
		return jTreeBiblioteca;
	}
	/**
	 * This method initializes jTreeCanais
	 * 
	 * @return javax.swing.JTree
	 */
	public javax.swing.JTree getJTreeCanais() {
		if(jTreeCanais == null) {
			try {
				jTreeCanais = new javax.swing.JTree();  // Generated
				jTreeCanais.setEnabled(false);  // Generated
				jTreeCanais.setVisible(false);  // Generated
			}
			catch (java.lang.Throwable e) {
				//  Do Something
			}
		}
		return jTreeCanais;
	}
	/**
	 * This method initializes jTreeDominio
	 * 
	 * @return javax.swing.JTree
	 */
	public javax.swing.JTree getJTreeDominio() {
		if(jTreeDominio == null) {
			try {
				jTreeDominio = new javax.swing.JTree();  // Generated
				jTreeDominio.setVisible(false);  // Generated
				jTreeDominio.setEnabled(false);  // Generated
			}
			catch (java.lang.Throwable e) {
				//  Do Something
			}
		}
		return jTreeDominio;
	}
	/**
	 * This method initializes jTreeTopologia
	 * 
	 * @return javax.swing.JTree
	 */
	public javax.swing.JTree getJTreeTopologia() {
		if(jTreeTopologia == null) {
			try {
				jTreeTopologia = new javax.swing.JTree();  // Generated
				jTreeTopologia.setVisible(false);  // Generated
				jTreeTopologia.setEnabled(false);  // Generated
			}
			catch (java.lang.Throwable e) {
				//  Do Something
			}
		}
		return jTreeTopologia;
	}
	/**
	 * This method initializes linhaDeStatus
	 * 
	 * @return javax.swing.JTextField
	 */
	public javax.swing.JTextField getLinhaDeStatus()
	{
		if (linhaDeStatus == null)
		{
			try
			{
				linhaDeStatus = new javax.swing.JTextField(); // Generated
				linhaDeStatus.setPreferredSize(new java.awt.Dimension(400, 20)); // Generated
			} catch (java.lang.Throwable e)
			{
				//  Do Something
			}
		}
		return linhaDeStatus;
	}
	/**
	 * @return
	 */
	private PanelSimuladorDeAlarmes getPanelDoSimuladorDeFalhas()
	{
		if (panelSimulador == null){
			panelSimulador = new PanelSimuladorDeAlarmes(this);
			panelSimulador.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));  // Generated
			
			panelSimulador.setTopologia(getTopologia());
			panelSimulador.setSimuladorDeEmissaoDeAlarmes(getSimuladorDeEmissaoDeAlarmes());
		}
		return panelSimulador;
	}
	/**
	 * @return
	 */
	SimuladorDeEmissaoDeAlarmes getSimuladorDeEmissaoDeAlarmes()
	{
		if (simuladorDeEmissaoDealarmes == null)
		{
			simuladorDeEmissaoDealarmes = new SimuladorDeEmissaoDeAlarmes(getTopologia());

		}

		return simuladorDeEmissaoDealarmes;
	}
	/**
	 * 
	 */
	public Topologia getTopologia()
	{
		
		return topologia;

	}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize()
	{

		this.setJMenuBar(getJJMenuBar()); // Generated
		this.setContentPane(getJContentPane()); // Generated
		this.setContentPane(getJContentPane());  // Generated
		this.setJMenuBar(getJJMenuBar());  // Generated
		this.setSize(800, 600);
		this.setJMenuBar(getJJMenuBar()); // Generated
		this.setContentPane(getJContentPane()); // Generated
		this.setTitle("Correlação de Alarmes"); // Generated
		this.setVisible(true); // Generated
		this.setName("Correlação de Alarmes"); // Generated
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE); // Generated
		this.setComponentOrientation(java.awt.ComponentOrientation.LEFT_TO_RIGHT); // Generated
		PrincipalWindows.setDefaultLookAndFeelDecorated(true);

	}
	/**
	 * 
	 */
	public void makeBiblioteca()
	{
		setBiblioteca(new Biblioteca(getArqBiblioteca()));

	}
	public void makeCanais() throws ExceptionTopologiaNaoDefinida
	{
		if (topologia == null)
			throw new ExceptionTopologiaNaoDefinida();
		topologia.criarCanais(arqCanais);
	}
	/**
	 * 
	 */
	public void makeTopologia() throws ExceptionBibliotecaNaoDefinida
	{
		topologia = new Topologia(getArqTopologia(), biblioteca);

	}
	/**
	 * @param p_string
	 */
	public void setArqBiblioteca(String p_string)
	{
		arqBiblioteca = p_string;

	}
	public void setArqCanais(String p_string)
	{
		arqCanais = p_string;
	}
	/**
	 * @param p_string
	 */
	public void setArqTopologia(String p_string)
	{

		arqTopologia = p_string;
	}
	/**
	 * @param p_object
	 */
	public void setBiblioteca(Biblioteca p_object)
	{
		biblioteca = p_object;
	}
} //  @jve:visual-info  decl-index=0 visual-constraint="10,10"
