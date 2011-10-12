/*
 * PanelSimulador.java Criado em 09/04/2004
 *
 * Todo Codigo criado abaixo, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e não seja para fins lucra-
 * tivos capitalistas.
 * 
 */
package gui;

import gui.actionlisteners.ActionListenerAdicionarRemover;
import gui.actionlisteners.ActionListenerAdicionarRemoverAlarmes;
import gui.actionlisteners.ActionListenerAdicionarRemoverComponentes;

import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Carlos Delfino
 * @email suporte@carlosdelfino.eti.br
 * @site http://www.carlosdelfino.eti.br
 * 
 * Digite aqui o que esta classe faz.
 * Nome: 
 */
public class PanelSimuladorDeAlarmes extends JPanel
{
	private DefaultListModel jListModelAlarmes;
	private PrincipalWindows parente;
	private JTextField linhaDeStatus;
	private javax.swing.JButton jBAdicionarComponentesAFalhar = null;
	private javax.swing.JButton jBRemoverComponentesAFalhar = null;
	private javax.swing.JButton jButton2 = null;
	private javax.swing.JButton jButton3 = null;
	private javax.swing.JButton jBAdicionarAlarmes = null;
	private javax.swing.JButton jBRemoverAlarmes = null;
	private javax.swing.JLabel jLabel = null;
	private javax.swing.JLabel jLabel1 = null;
	private javax.swing.JLabel jLabel2 = null;
	private javax.swing.JLabel jLabel3 = null;
	private javax.swing.JLabel jLabel4 = null;
	private javax.swing.JLabel jLabel5 = null;
	private javax.swing.JList jListDeComponentesAFalhar = null;
	private DefaultListModel jListModelComponentesAFalhar;
	private javax.swing.JPanel jPanel = null;
	private javax.swing.JPanel jPanel1 = null;
	private javax.swing.JPanel jPanel2 = null;
	private javax.swing.JPanel jPanel3 = null;
	private javax.swing.JPanel jPanel4 = null;
	private javax.swing.JScrollPane jScrollPane = null;
	private javax.swing.JScrollPane jScrollPane1 = null;
	private javax.swing.JSlider jSlider = null;
	private javax.swing.JSlider jSlider1 = null;
	private javax.swing.JTextField jTFComponentesAFalhar = null;
	private javax.swing.JTextField jTextField1 = null;
	private javax.swing.JTextField jTextField2 = null;
	private javax.swing.JTextField jTFComponnenteOrigemAlarme = null;
	private javax.swing.JTextField jTFNivelDoAlarme = null;
	private javax.swing.JList jListDeAlarmes = null;
/**
	 * This is the default constructor
	 */
	public PanelSimuladorDeAlarmes()
	{
		super();
		initialize();
	}
	/**
	 * @return
	 */
	private ActionListener getActionListenerAdicionarRemoverComponentes()
	{
		return new ActionListenerAdicionarRemoverComponentes(this);
	}
	private ActionListener getActionListenerAdicionarRemoverAlarmes()
	{
		return new ActionListenerAdicionarRemoverAlarmes(this);
	}
	/**
	 * 
	 */
	public DefaultListModel getjListModelComponentesAFalhar()
	{
		if (jListModelComponentesAFalhar == null)
		{
			jListModelComponentesAFalhar = new DefaultListModel();
			
		}
	return jListModelComponentesAFalhar;
		
		
	}
	/**
	 * This method initializes jBAdicionarComponentesAFalhar
	 * 
	 * @return javax.swing.JButton
	 */
	public javax.swing.JButton getJBAdicionarComponentesAFalhar() {
		if(jBAdicionarComponentesAFalhar == null) {
			try {
				jBAdicionarComponentesAFalhar = new javax.swing.JButton();  // Generated
				jBAdicionarComponentesAFalhar.setText("Adicionar");  // Generated
				jBAdicionarComponentesAFalhar.setActionCommand(ActionListenerAdicionarRemover.cmd_Adicionar);  // Generated
				jBAdicionarComponentesAFalhar.setEnabled(false);  // Generated
				jBAdicionarComponentesAFalhar.addActionListener(getActionListenerAdicionarRemoverComponentes());
			}
			catch (java.lang.Throwable e) {
				//  Do Something
			}
		}
		return jBAdicionarComponentesAFalhar;
	}
	/**
	 * This method initializes jBRemoverComponentesAFalhar
	 * 
	 * @return javax.swing.JButton
	 */
	public javax.swing.JButton getJBRemoverComponentesAFalhar() {
		if(jBRemoverComponentesAFalhar == null) {
			try {
				jBRemoverComponentesAFalhar = new javax.swing.JButton();  // Generated
				jBRemoverComponentesAFalhar.setText("Remover");  // Generated
				jBRemoverComponentesAFalhar.setActionCommand(ActionListenerAdicionarRemover.cmd_Remover);  // Generated
				jBRemoverComponentesAFalhar.setEnabled(false);  // Generated
				jBRemoverComponentesAFalhar.addActionListener(getActionListenerAdicionarRemoverComponentes());

			}
			catch (java.lang.Throwable e) {
				//  Do Something
			}
		}
		return jBRemoverComponentesAFalhar;
	}
	/**
	 * This method initializes jButton2
	 * 
	 * @return javax.swing.JButton
	 */
	private javax.swing.JButton getJButton2() {
		if(jButton2 == null) {
			try {
				jButton2 = new javax.swing.JButton();  // Generated
				jButton2.setText("Gerar Alarmes");  // Generated
				jButton2.setPreferredSize(new java.awt.Dimension(150,80));  // Generated
			}
			catch (java.lang.Throwable e) {
				//  Do Something
			}
		}
		return jButton2;
	}
	/**
	 * This method initializes jButton3
	 * 
	 * @return javax.swing.JButton
	 */
	private javax.swing.JButton getJButton3() {
		if(jButton3 == null) {
			try {
				jButton3 = new javax.swing.JButton();  // Generated
				jButton3.setText("Gerar Perdas e Alarmes");  // Generated
			}
			catch (java.lang.Throwable e) {
				//  Do Something
			}
		}
		return jButton3;
	}
	/**
	 * This method initializes jBAdicionarAlarmes
	 * 
	 * @return javax.swing.JButton
	 */
	private javax.swing.JButton getJBAdicionarAlarmes() {
		if(jBAdicionarAlarmes == null) {
			try {
				jBAdicionarAlarmes = new javax.swing.JButton();  // Generated
				jBAdicionarAlarmes.setText("Adicionar");  // Generated
				jBAdicionarAlarmes.setPreferredSize(new java.awt.Dimension(80,25));  // Generated
				jBAdicionarAlarmes.setActionCommand(ActionListenerAdicionarRemover.cmd_Adicionar);  // Generated
				jBAdicionarAlarmes.addActionListener(getActionListenerAdicionarRemoverAlarmes());

			}
			catch (java.lang.Throwable e) {
				//  Do Something
			}
		}
		return jBAdicionarAlarmes;
	}
	/**
	 * This method initializes jBRemoverAlarmes
	 * 
	 * @return javax.swing.JButton
	 */
	private javax.swing.JButton getJBRemoverAlarmes() {
		if(jBRemoverAlarmes == null) {
			try {
				jBRemoverAlarmes = new javax.swing.JButton();  // Generated
				jBRemoverAlarmes.setText("Remover");  // Generated
				jBRemoverAlarmes.setPreferredSize(new java.awt.Dimension(80,25));  // Generated
				jBRemoverAlarmes.setActionCommand(ActionListenerAdicionarRemover.cmd_Remover);  // Generated
				jBRemoverAlarmes.addActionListener(getActionListenerAdicionarRemoverAlarmes());

			}
			catch (java.lang.Throwable e) {
				//  Do Something
			}
		}
		return jBRemoverAlarmes;
	}
	/**
	 * This method initializes jLabel
	 * 
	 * @return javax.swing.JLabel
	 */
	private javax.swing.JLabel getJLabel() {
		if(jLabel == null) {
			try {
				jLabel = new javax.swing.JLabel();  // Generated
				jLabel.setText("Componentes a Falhar: ");  // Generated
				jLabel.setPreferredSize(new java.awt.Dimension(150,15));  // Generated
			}
			catch (java.lang.Throwable e) {
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
	private javax.swing.JLabel getJLabel1() {
		if(jLabel1 == null) {
			try {
				jLabel1 = new javax.swing.JLabel();  // Generated
				jLabel1.setText("Alarmes Extras: ");  // Generated
			}
			catch (java.lang.Throwable e) {
				//  Do Something
			}
		}
		return jLabel1;
	}
	/**
	 * This method initializes jLabel2
	 * 
	 * @return javax.swing.JLabel
	 */
	private javax.swing.JLabel getJLabel2() {
		if(jLabel2 == null) {
			try {
				jLabel2 = new javax.swing.JLabel();  // Generated
				jLabel2.setText("Alarmes a Perder: ");  // Generated
			}
			catch (java.lang.Throwable e) {
				//  Do Something
			}
		}
		return jLabel2;
	}
	/**
	 * This method initializes jLabel3
	 * 
	 * @return javax.swing.JLabel
	 */
	private javax.swing.JLabel getJLabel3() {
		if(jLabel3 == null) {
			try {
				jLabel3 = new javax.swing.JLabel();  // Generated
				jLabel3.setText("Componente de Origem do Alarme:");  // Generated
			}
			catch (java.lang.Throwable e) {
				//  Do Something
			}
		}
		return jLabel3;
	}
	/**
	 * This method initializes jLabel4
	 * 
	 * @return javax.swing.JLabel
	 */
	private javax.swing.JLabel getJLabel4() {
		if(jLabel4 == null) {
			try {
				jLabel4 = new javax.swing.JLabel();  // Generated
				jLabel4.setText("Nível/Tipo de Alarme");  // Generated
			}
			catch (java.lang.Throwable e) {
				//  Do Something
			}
		}
		return jLabel4;
	}
	/**
	 * This method initializes jLabel5
	 * 
	 * @return javax.swing.JLabel
	 */
	private javax.swing.JLabel getJLabel5() {
		if(jLabel5 == null) {
			try {
				jLabel5 = new javax.swing.JLabel();  // Generated
				jLabel5.setText("-");  // Generated
				jLabel5.setFont(new java.awt.Font("MS Sans Serif", java.awt.Font.BOLD, 24));  // Generated
			}
			catch (java.lang.Throwable e) {
				//  Do Something
			}
		}
		return jLabel5;
	}
	/**
	 * This method initializes jListDeComponentesAFalhar
	 * 
	 * @return javax.swing.JList
	 */
	public javax.swing.JList getJListDeComponentesAFalhar() {
		if(jListDeComponentesAFalhar == null) {
			try {
				jListDeComponentesAFalhar = new javax.swing.JList();  // Generated
				jListDeComponentesAFalhar.setModel(getjListModelComponentesAFalhar());
				jListDeComponentesAFalhar.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);  // Generated
			}
			catch (java.lang.Throwable e) {
				//  Do Something
			}
		}
		return jListDeComponentesAFalhar;
	}
	/**
	 * This method initializes jPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJPanel() {
		if(jPanel == null) {
			try {
				jPanel = new javax.swing.JPanel();  // Generated
				jPanel.add(getJScrollPane(), null);  // Generated
				jPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Alarmes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", java.awt.Font.BOLD, 14), null));  // Generated
				jPanel.setPreferredSize(new java.awt.Dimension(250,45));  // Generated
			}
			catch (java.lang.Throwable e) {
				//  Do Something
			}
		}
		return jPanel;
	}
	/**
	 * This method initializes jPanel1
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJPanel1() {
		if(jPanel1 == null) {
			try {
				jPanel1 = new javax.swing.JPanel();  // Generated
				java.awt.GridBagConstraints consGridBagConstraints6 = new java.awt.GridBagConstraints();  // Generated
				java.awt.GridBagConstraints consGridBagConstraints7 = new java.awt.GridBagConstraints();  // Generated
				java.awt.GridBagConstraints consGridBagConstraints5 = new java.awt.GridBagConstraints();  // Generated
				java.awt.GridBagConstraints consGridBagConstraints8 = new java.awt.GridBagConstraints();  // Generated
				java.awt.GridBagConstraints consGridBagConstraints9 = new java.awt.GridBagConstraints();  // Generated
				java.awt.GridBagConstraints consGridBagConstraints10 = new java.awt.GridBagConstraints();  // Generated
				consGridBagConstraints9.gridx = 4;  // Generated
				consGridBagConstraints9.gridy = 1;  // Generated
				consGridBagConstraints9.anchor = java.awt.GridBagConstraints.CENTER;  // Generated
				consGridBagConstraints9.insets = new java.awt.Insets(5,5,5,5);  // Generated
				consGridBagConstraints5.gridx = -1;  // Generated
				consGridBagConstraints5.gridy = -1;  // Generated
				consGridBagConstraints6.gridx = -1;  // Generated
				consGridBagConstraints6.gridy = -1;  // Generated
				consGridBagConstraints6.fill = java.awt.GridBagConstraints.NONE;  // Generated
				consGridBagConstraints6.gridwidth = 4;  // Generated
				consGridBagConstraints8.gridy = 1;  // Generated
				consGridBagConstraints8.gridx = 3;  // Generated
				consGridBagConstraints8.anchor = java.awt.GridBagConstraints.CENTER;  // Generated
				consGridBagConstraints8.insets = new java.awt.Insets(5,5,5,5);  // Generated
				consGridBagConstraints10.gridy = 2;  // Generated
				consGridBagConstraints10.gridx = 2;  // Generated
				consGridBagConstraints10.gridheight = 2;  // Generated
				consGridBagConstraints10.gridwidth = 3;  // Generated
				consGridBagConstraints5.insets = new java.awt.Insets(5,5,5,5);  // Generated
				consGridBagConstraints10.insets = new java.awt.Insets(5,5,5,5);  // Generated
				consGridBagConstraints10.fill = java.awt.GridBagConstraints.BOTH;  // Generated
				consGridBagConstraints6.insets = new java.awt.Insets(5,5,5,5);  // Generated
				consGridBagConstraints7.gridx = 0;  // Generated
				consGridBagConstraints7.gridy = 1;  // Generated
				consGridBagConstraints7.gridheight = 2;  // Generated
				consGridBagConstraints7.gridwidth = 2;  // Generated
				consGridBagConstraints7.anchor = java.awt.GridBagConstraints.WEST;  // Generated
				consGridBagConstraints7.insets = new java.awt.Insets(5,5,5,5);  // Generated

				consGridBagConstraints6.anchor = java.awt.GridBagConstraints.EAST;  // Generated
				jPanel1.setLayout(new java.awt.GridBagLayout());  // Generated
				jPanel1.add(getJLabel(), consGridBagConstraints5);  // Generated
				jPanel1.add(getJTFComponentesAFalhar(), consGridBagConstraints6);  // Generated
				jPanel1.add(getJBAdicionarComponentesAFalhar(), consGridBagConstraints8);  // Generated
				jPanel1.add(getJBRemoverComponentesAFalhar(), consGridBagConstraints9);  // Generated
				jPanel1.add(getJPanel4(), consGridBagConstraints7);  // Generated
				jPanel1.add(getJButton2(), consGridBagConstraints10);  // Generated
				jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Inserção de Componentes Falhos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", java.awt.Font.BOLD, 14), java.awt.Color.black));  // Generated
				jPanel1.setPreferredSize(new java.awt.Dimension(500,220));  // Generated
			}
			catch (java.lang.Throwable e) {
				//  Do Something
			}
		}
		return jPanel1;
	}
	/**
	 * This method initializes jPanel2
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJPanel2() {
		if(jPanel2 == null) {
			try {
				jPanel2 = new javax.swing.JPanel();  // Generated
				java.awt.GridBagConstraints consGridBagConstraints11 = new java.awt.GridBagConstraints();  // Generated
				java.awt.GridBagConstraints consGridBagConstraints12 = new java.awt.GridBagConstraints();  // Generated
				java.awt.GridBagConstraints consGridBagConstraints13 = new java.awt.GridBagConstraints();  // Generated
				java.awt.GridBagConstraints consGridBagConstraints14 = new java.awt.GridBagConstraints();  // Generated
				java.awt.GridBagConstraints consGridBagConstraints141 = new java.awt.GridBagConstraints();  // Generated
				java.awt.GridBagConstraints consGridBagConstraints16 = new java.awt.GridBagConstraints();  // Generated
				java.awt.GridBagConstraints consGridBagConstraints15 = new java.awt.GridBagConstraints();  // Generated
				consGridBagConstraints141.gridy = 1;  // Generated
				consGridBagConstraints141.anchor = java.awt.GridBagConstraints.WEST;  // Generated
				consGridBagConstraints141.insets = new java.awt.Insets(10,10,10,10);  // Generated
				consGridBagConstraints16.gridy = 0;  // Generated
				consGridBagConstraints16.gridwidth = 0;  // Generated
				consGridBagConstraints16.insets = new java.awt.Insets(10,10,10,10);  // Generated
				consGridBagConstraints16.gridheight = 2;  // Generated
				consGridBagConstraints16.gridx = 3;  // Generated
				consGridBagConstraints16.fill = java.awt.GridBagConstraints.VERTICAL;  // Generated
				consGridBagConstraints15.anchor = java.awt.GridBagConstraints.WEST;  // Generated
				consGridBagConstraints15.insets = new java.awt.Insets(10,10,10,10);  // Generated
				consGridBagConstraints11.anchor = java.awt.GridBagConstraints.WEST;  // Generated
				consGridBagConstraints12.gridy = 1;  // Generated
				consGridBagConstraints12.anchor = java.awt.GridBagConstraints.WEST;  // Generated
				consGridBagConstraints13.anchor = java.awt.GridBagConstraints.WEST;  // Generated
				consGridBagConstraints14.gridy = 1;  // Generated
				consGridBagConstraints14.anchor = java.awt.GridBagConstraints.WEST;  // Generated
				jPanel2.setLayout(new java.awt.GridBagLayout());  // Generated
				jPanel2.add(getJLabel1(), consGridBagConstraints15);  // Generated
				jPanel2.add(getJLabel2(), consGridBagConstraints141);  // Generated
				jPanel2.add(getJTextField1(), consGridBagConstraints11);  // Generated
				jPanel2.add(getJTextField2(), consGridBagConstraints12);  // Generated
				jPanel2.add(getJSlider(), consGridBagConstraints13);  // Generated
				jPanel2.add(getJSlider1(), consGridBagConstraints14);  // Generated
				jPanel2.add(getJButton3(), consGridBagConstraints16);  // Generated
				jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Controle de Espúrios e Perdas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("MS Sans Serif", java.awt.Font.BOLD, 14), java.awt.Color.black));  // Generated
				jPanel2.setPreferredSize(new java.awt.Dimension(500,110));  // Generated
			}
			catch (java.lang.Throwable e) {
				//  Do Something
			}
		}
		return jPanel2;
	}
	/**
	 * This method initializes jPanel3
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJPanel3() {
		if(jPanel3 == null) {
			try {
				jPanel3 = new javax.swing.JPanel();  // Generated
				java.awt.GridBagConstraints consGridBagConstraints19 = new java.awt.GridBagConstraints();  // Generated
				java.awt.GridBagConstraints consGridBagConstraints18 = new java.awt.GridBagConstraints();  // Generated
				java.awt.GridBagConstraints consGridBagConstraints20 = new java.awt.GridBagConstraints();  // Generated
				java.awt.GridBagConstraints consGridBagConstraints21 = new java.awt.GridBagConstraints();  // Generated
				java.awt.GridBagConstraints consGridBagConstraints23 = new java.awt.GridBagConstraints();  // Generated
				java.awt.GridBagConstraints consGridBagConstraints24 = new java.awt.GridBagConstraints();  // Generated
				java.awt.GridBagConstraints consGridBagConstraints22 = new java.awt.GridBagConstraints();  // Generated
				consGridBagConstraints23.insets = new java.awt.Insets(0,10,0,10);  // Generated
				consGridBagConstraints19.weightx = 0.0D;  // Generated
				consGridBagConstraints19.fill = java.awt.GridBagConstraints.NONE;  // Generated
				consGridBagConstraints19.gridy = 1;  // Generated
				consGridBagConstraints19.insets = new java.awt.Insets(0,10,0,10);  // Generated
				consGridBagConstraints19.gridx = 2;  // Generated
				consGridBagConstraints21.insets = new java.awt.Insets(2,10,2,0);  // Generated
				consGridBagConstraints24.gridy = 1;  // Generated
				consGridBagConstraints24.gridx = 1;  // Generated
				consGridBagConstraints24.gridwidth = 1;  // Generated
				consGridBagConstraints22.insets = new java.awt.Insets(0,10,0,10);  // Generated
				consGridBagConstraints22.gridwidth = 2;  // Generated
				consGridBagConstraints18.weightx = 0.0D;  // Generated
				consGridBagConstraints18.fill = java.awt.GridBagConstraints.NONE;  // Generated
				consGridBagConstraints18.gridy = 1;  // Generated
				consGridBagConstraints18.insets = new java.awt.Insets(0,10,0,10);  // Generated
				consGridBagConstraints20.gridy = 1;  // Generated
				consGridBagConstraints20.insets = new java.awt.Insets(2,10,2,0);  // Generated
				jPanel3.setLayout(new java.awt.GridBagLayout());  // Generated
				jPanel3.add(getJLabel3(), consGridBagConstraints22);  // Generated
				jPanel3.add(getJLabel4(), consGridBagConstraints23);  // Generated
				jPanel3.add(getJTFComponnenteOrigemAlarme(), consGridBagConstraints18);  // Generated
				jPanel3.add(getJTFNivelDoAlarme(), consGridBagConstraints19);  // Generated
				jPanel3.add(getJBAdicionarAlarmes(), consGridBagConstraints21);  // Generated
				jPanel3.add(getJBRemoverAlarmes(), consGridBagConstraints20);  // Generated
				jPanel3.add(getJLabel5(), consGridBagConstraints24);  // Generated
				jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Adição e Remoção de Alarmes Manual", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", java.awt.Font.BOLD, 14), java.awt.Color.black));  // Generated
				jPanel3.setPreferredSize(new java.awt.Dimension(500,100));  // Generated
				
			}
			catch (java.lang.Throwable e) {
				//  Do Something
			}
		}
		return jPanel3;
	}
	/**
	 * This method initializes jPanel4
	 * 
	 * @return javax.swing.JPanel
	 */
	private javax.swing.JPanel getJPanel4() {
		if(jPanel4 == null) {
			try {
				jPanel4 = new javax.swing.JPanel();  // Generated
				jPanel4.add(getJScrollPane1(), null);  // Generated
				jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));  // Generated
				jPanel4.setPreferredSize(new java.awt.Dimension(210,140));  // Generated
			}
			catch (java.lang.Throwable e) {
				//  Do Something
			}
		}
		return jPanel4;
	}
	/**
	 * This method initializes jScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private javax.swing.JScrollPane getJScrollPane() {
		if(jScrollPane == null) {
			try {
				jScrollPane = new javax.swing.JScrollPane();  // Generated
				jScrollPane.setViewportView(getJListDeAlarmes());  // Generated
				jScrollPane.setPreferredSize(new java.awt.Dimension(240,400));  // Generated
			}
			catch (java.lang.Throwable e) {
				//  Do Something
			}
		}
		return jScrollPane;
	}
	/**
	 * This method initializes jScrollPane1
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private javax.swing.JScrollPane getJScrollPane1() {
		if(jScrollPane1 == null) {
			try {
				jScrollPane1 = new javax.swing.JScrollPane();  // Generated
				jScrollPane1.setViewportView(getJListDeComponentesAFalhar());  // Generated
				jScrollPane1.setPreferredSize(new java.awt.Dimension(200,125));  // Generated
			}
			catch (java.lang.Throwable e) {
				//  Do Something
			}
		}
		return jScrollPane1;
	}
	/**
	 * This method initializes jSlider
	 * 
	 * @return javax.swing.JSlider
	 */
	private javax.swing.JSlider getJSlider() {
		if(jSlider == null) {
			try {
				jSlider = new javax.swing.JSlider();  // Generated
				jSlider.setSnapToTicks(true);  // Generated
				jSlider.setMaximum(10);  // Generated
				jSlider.setValue(5);  // Generated
				jSlider.setPreferredSize(new java.awt.Dimension(100,20));  // Generated
			}
			catch (java.lang.Throwable e) {
				//  Do Something
			}
		}
		return jSlider;
	}
	private javax.swing.JSlider getJSlider1() {
			if(jSlider1 == null) {
				try {
					jSlider1 = new javax.swing.JSlider();  // Generated
					jSlider1.setSnapToTicks(true);  // Generated
					jSlider1.setMaximum(10);  // Generated
					jSlider1.setValue(5);  // Generated
					jSlider1.setPreferredSize(new java.awt.Dimension(100,20));  // Generated
				}
				catch (java.lang.Throwable e) {
					//  Do Something
				}
			}
			return jSlider1;
		}		/**
	 * This method initializes jTFComponentesAFalhar
	 * 
	 * @return javax.swing.JTextField
	 */
	public javax.swing.JTextField getJTFComponentesAFalhar() {
		if(jTFComponentesAFalhar == null) {
			try {
				jTFComponentesAFalhar = new javax.swing.JTextField();  // Generated
				jTFComponentesAFalhar.setColumns(20);  // Generated
				jTFComponentesAFalhar.setPreferredSize(new java.awt.Dimension(200,20));  // Generated
				jTFComponentesAFalhar.setActionCommand(ActionListenerAdicionarRemover.cmd_Adicionar);  // Generated
				jTFComponentesAFalhar.addActionListener(getActionListenerAdicionarRemoverComponentes());
				jTFComponentesAFalhar.addCaretListener(new javax.swing.event.CaretListener() { 
					public void caretUpdate(javax.swing.event.CaretEvent e) {
						if(getJTFComponentesAFalhar().getText().equals(""))  
							getJBAdicionarComponentesAFalhar().setEnabled(false);
						else
							getJBAdicionarComponentesAFalhar().setEnabled(true);
							
					}
				});
			}
			catch (java.lang.Throwable e) {
				//  Do Something
			}
		}
		return jTFComponentesAFalhar;
	}
	/**
	 * This method initializes jTextField1
	 * 
	 * @return javax.swing.JTextField
	 */
	private javax.swing.JTextField getJTextField1() {
		if(jTextField1 == null) {
			try {
				jTextField1 = new javax.swing.JTextField();  // Generated
				jTextField1.setColumns(5);  // Generated
				jTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);  // Generated
				jTextField1.setText("0");  // Generated
				jTextField1.setPreferredSize(new java.awt.Dimension(55,20));  // Generated
			}
			catch (java.lang.Throwable e) {
				//  Do Something
			}
		}
		return jTextField1;
	}
	/**
	 * This method initializes jTextField2
	 * 
	 * @return javax.swing.JTextField
	 */
	private javax.swing.JTextField getJTextField2() {
		if(jTextField2 == null) {
			try {
				jTextField2 = new javax.swing.JTextField();  // Generated
				jTextField2.setColumns(5);  // Generated
				jTextField2.setText("0");  // Generated
				jTextField2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);  // Generated
				jTextField2.setPreferredSize(new java.awt.Dimension(55,20));  // Generated
			}
			catch (java.lang.Throwable e) {
				//  Do Something
			}
		}
		return jTextField2;
	}
	/**
	 * This method initializes jTFComponnenteOrigemAlarme
	 * 
	 * @return javax.swing.JTextField
	 */
	public javax.swing.JTextField getJTFComponnenteOrigemAlarme() {
		if(jTFComponnenteOrigemAlarme == null) {
			try {
				jTFComponnenteOrigemAlarme = new javax.swing.JTextField();  // Generated
				jTFComponnenteOrigemAlarme.setColumns(15);  // Generated
				jTFComponnenteOrigemAlarme.setActionCommand("ActionListenerAdicionarRemover.cmd_Adicionar");  // Generated
				jTFComponnenteOrigemAlarme.addActionListener(getActionListenerAdicionarRemoverAlarmes());
				
			}
			catch (java.lang.Throwable e) {
				//  Do Something
			}
		}
		return jTFComponnenteOrigemAlarme;
	}
	/**
	 * This method initializes jTFNivelDoAlarme
	 * 
	 * @return javax.swing.JTextField
	 */
	private javax.swing.JTextField getJTFNivelDoAlarme() {
		if(jTFNivelDoAlarme == null) {
			try {
				jTFNivelDoAlarme = new javax.swing.JTextField();  // Generated
				jTFNivelDoAlarme.setColumns(5);  // Generated
				jTFNivelDoAlarme.setActionCommand("ActionListenerAdicionarRemover.cmd_Adicionar");  // Generated
				jTFNivelDoAlarme.addActionListener(getActionListenerAdicionarRemoverAlarmes());

			}
			catch (java.lang.Throwable e) {
				//  Do Something
			}
		}
		return jTFNivelDoAlarme;
	}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize()
	{
		java.awt.GridBagConstraints consGridBagConstraints2 = new java.awt.GridBagConstraints();  // Generated
		java.awt.GridBagConstraints consGridBagConstraints4 = new java.awt.GridBagConstraints();  // Generated
		java.awt.GridBagConstraints consGridBagConstraints3 = new java.awt.GridBagConstraints();  // Generated
		java.awt.GridBagConstraints consGridBagConstraints17 = new java.awt.GridBagConstraints();  // Generated
		consGridBagConstraints17.insets = new java.awt.Insets(2,2,2,2);  // Generated
		consGridBagConstraints4.gridy = 2;  // Generated
		consGridBagConstraints2.gridy = 1;  // Generated
		consGridBagConstraints2.gridx = 1;  // Generated
		consGridBagConstraints2.insets = new java.awt.Insets(2,2,2,2);  // Generated
		consGridBagConstraints4.insets = new java.awt.Insets(2,2,2,2);  // Generated
		consGridBagConstraints3.gridy = -1;  // Generated
		consGridBagConstraints3.gridwidth = 1;  // Generated
		consGridBagConstraints3.gridheight = 3;  // Generated
		consGridBagConstraints3.insets = new java.awt.Insets(2,2,2,2);  // Generated
		consGridBagConstraints3.fill = java.awt.GridBagConstraints.BOTH;  // Generated
		this.setLayout(new java.awt.GridBagLayout());  // Generated
		this.add(getJPanel(), consGridBagConstraints3);  // Generated
		this.add(getJPanel1(), consGridBagConstraints17);  // Generated
		this.add(getJPanel2(), consGridBagConstraints2);  // Generated
		this.add(getJPanel3(), consGridBagConstraints4);  // Generated
		this.setSize(775, 460);
		this.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));  // Generated
	}
	/**
	 * 
	 */
	public JTextField getLinhaDeStatus()
	{
		if (linhaDeStatus == null && getParente() != null)
		{
			linhaDeStatus = getParente().getLinhaDeStatus();
		}

		return linhaDeStatus;
		
	}
	/**
	 * 
	 */
	private PrincipalWindows getParente()
	{
		return parente;
		
	}
	/**
	 * @param p_windows
	 */
	public void setParente(PrincipalWindows p_windows)
	{
		parente = p_windows;
	}
	/**
	 * This method initializes jListDeAlarmes
	 * 
	 * @return javax.swing.JList
	 */
	public javax.swing.JList getJListDeAlarmes() {
		if(jListDeAlarmes == null) {
			try {
				jListDeAlarmes = new javax.swing.JList();  // Generated
				jListDeAlarmes.setModel(getjListModelAlarmes());
			}
			catch (java.lang.Throwable e) {
				//  Do Something
			}
		}
		return jListDeAlarmes;
	}
	/**
	 * @return
	 */
	public DefaultListModel getjListModelAlarmes()
	{
	
			if (jListModelAlarmes == null)
			{
				jListModelAlarmes = new DefaultListModel();
			
			}
		return jListModelAlarmes;
		
		
	}
}
