/*
 * ActionListenerAdicionarRemoverComponentes.java Criado em 09/04/2004
 *
 * Use somente com o conhecimento e autorização do autor.
 * Este codigo não deve ser usado com fins lucrativos, sem
 * autorização por escrito do autor.
 * 
 */
package gui.actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;

/**
 * @author Carlos Delfino
 * email suporte@carlosdelfino.eti.br
 * site http://www.carlosdelfino.eti.br
 * 
 * 
 */
public class ActionListenerAdicionarRemover implements ActionListener
{

	private JButton buttonAdicionar;
	private JButton buttonRemover;
	
	
	public final static String cmd_Adicionar = "cmd_Adicionar";
	public final static String cmd_Remover = "cmd_Remover";

	private JTextField linhaDeStatus;

	private JList jList;

	private JTextField jTextField;

	private DefaultListModel listmodel;

	/**
	 * @param p_field
	 */
	protected void setLinhaDeStatus(JTextField p_field)
	{
		linhaDeStatus = p_field;

	}

	/* (não-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e)
	{
		String cmd = e.getActionCommand();

		if (cmd.equals(cmd_Remover))
		{
			remover(jTextField.getText());
		} else if (cmd.equals(cmd_Adicionar))
		{
			adicionar(jTextField.getText());
		}
	}
	protected Object remover(Object p_dado)
	{
		Object objectRemovido = null;
		Object objectARemover = jList.getSelectedValue();
		if (listmodel.getSize() != 0)
		{
			if (jList.getSelectedIndex() == -1)
			{
				if (listmodel.removeElement(p_dado))
				{
					objectRemovido = objectARemover;
				} 
			
			} else
			{
				objectRemovido = listmodel.remove(jList.getSelectedIndex());
				if (listmodel.getSize() == 0)
					getJButtonRemover().setEnabled(false);
			}
		}
		return objectRemovido;
	}
	/**
	 * 
	 */
	private JButton getJButtonRemover()
	{
		return buttonRemover;
		
	}

	protected void adicionar(Object p_dado)
	{
		if (!p_dado.equals(""))
		{
			getJButtonAdicionar().setEnabled(true);
			if (!listmodel.contains(p_dado))
				listmodel.addElement(p_dado);
		}
	}

	/**
	 * 
	 */
	private JButton getJButtonAdicionar()
	{
		return buttonAdicionar;
		
	}

	/**
	 * @param p_list
	 */
	protected void setJList(JList p_list)
	{
		jList = p_list;

	}

	/**
	 * @param p_field
	 */
	protected void setJTextField(JTextField p_field)
	{
		jTextField = p_field;

	}

	/**
	 * @param p_model
	 */
	protected void setListModel(DefaultListModel p_model)
	{
		listmodel = p_model;

	}
	/**
	 * @param p_button
	 */
	protected void setJBottonRemover(JButton p_button)
	{
		buttonAdicionar = p_button;
	}

	/**
	 * @param p_button
	 */
	protected void setJBottonAdicionar(JButton p_button)
	{
		buttonRemover = p_button;
	}

	/**
	 * 
	 */
	protected JTextField getJTextField()
	{
return jTextField;
		
	}


}
