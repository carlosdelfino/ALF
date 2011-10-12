/*
 * GuiDeTesteParaPaineis.java Criado em 09/04/2004
 *
 * Use somente com o conhecimento e autorização do autor.
 * Este codigo não deve ser usado com fins lucrativos, sem
 * autorização por escrito do autor.
 * 
 */
package gui;

import javax.swing.JFrame;

/**
 * @author Carlos Delfino
 * email suporte@carlosdelfino.eti.br
 * site http://www.carlosdelfino.eti.br
 * 
 * 
 */
public class GuiDeTesteParaPaineis extends JFrame
{

	private PanelSimuladorDeAlarmes painelSimuladorDeAlarmes;

	private javax.swing.JPanel jContentPane = null;  //  @jve:visual-info  decl-index=0 visual-constraint="192,90"

	public static void main(String[] args)
	{
		GuiDeTesteParaPaineis teste = new GuiDeTesteParaPaineis();
		teste.setVisible(true);
		
	}
	/**
	 * This is the default constructor
	 */
	public GuiDeTesteParaPaineis()
	{
		super();
		initialize();
	}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize()
	{
		this.setContentPane(getJContentPane());  // Generated
		this.setSize(909, 526);
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);  // Generated
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
			jContentPane.add(getPainelSimuladorDeAlarmes());
			jContentPane.setLayout(new java.awt.BorderLayout());
		}
		return jContentPane;
	}
	/**
	 * @return
	 */
	private PanelSimuladorDeAlarmes getPainelSimuladorDeAlarmes()
	{
		if (painelSimuladorDeAlarmes == null)
		{
			painelSimuladorDeAlarmes = new PanelSimuladorDeAlarmes();
			
		}

		return painelSimuladorDeAlarmes;
	}
}  //  @jve:visual-info  decl-index=0 visual-constraint="16,10"
