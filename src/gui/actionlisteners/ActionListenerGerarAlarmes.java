/*
 * actionListenerGerarAlarmes.java Criado em 14/04/2004
 *
 * Use somente com o conhecimento e autorização do autor.
 * Este codigo não deve ser usado com fins lucrativos, sem
 * autorização por escrito do autor.
 * 
 */
package gui.actionlisteners;

import geradorautomaticodealarmes.SimuladorDeEmissaoDeAlarmes;
import gerenciadordebiblioteca.ID;
import gui.PanelSimuladorDeAlarmes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFormattedTextField;
import javax.swing.ListModel;

/**
 * @author Carlos Delfino
 * email suporte@carlosdelfino.eti.br
 * site http://www.carlosdelfino.eti.br
 * 
 * 
 */
public class ActionListenerGerarAlarmes implements ActionListener
{
	private JFormattedTextField JTextComponentAlarmesPerdidos;

	private JFormattedTextField JTextComponentAlarmesExtras;

	/**
	 * @param p_alarmes
	 */
	public ActionListenerGerarAlarmes(PanelSimuladorDeAlarmes p_painel)
	{
		setSimuladorDeEmissaoDeAlarmes(p_painel.getSimuladorDeEmissaoDeAlarmes());
		setListComponentesAFalhar(p_painel.getjListModelComponentesAFalhar());
		setListModelAlarmes(p_painel.getjListModelAlarmes());
		
		setJTextComponentAlarmesExtras(p_painel.getJTextFieldAlarmesExtras());
		setJTextComponentAlarmesPerdidos(p_painel.getJTextFieldAlarmesPerdidos());
	}

	/**
	 * @param p_field
	 */
	private void setJTextComponentAlarmesPerdidos(JFormattedTextField p_field)
	{
		JTextComponentAlarmesPerdidos = p_field;
	}

	/**
	 * @param p_field
	 */
	private void setJTextComponentAlarmesExtras(JFormattedTextField p_field)
	{
		JTextComponentAlarmesExtras = p_field;		
	}

	private DefaultListModel listModelAlarmes;

	private SimuladorDeEmissaoDeAlarmes simuladorDeEmissaoDeAlarmes;

	private DefaultListModel listComponentesAFalhar;

	/**
	 * @param p_listModelVisualizacao
	 */
	private void setListModelAlarmes(DefaultListModel p_jListAlarmes)
	{
		listModelAlarmes = p_jListAlarmes;
		
	}

	/**
	 * @param p_jList
	 */
	private void setListComponentesAFalhar(DefaultListModel p_listModel)
	{
		listComponentesAFalhar = p_listModel;
	}

	/**
	 * @param p_simulador
	 */
	public void setSimuladorDeEmissaoDeAlarmes(SimuladorDeEmissaoDeAlarmes p_simulador)
	{
		simuladorDeEmissaoDeAlarmes = p_simulador;
		
	}

	public static String cmd_GERAR_ALARMES = "cmd Gerar Alarmes";

	/* (não-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent l_actionEvent)
	{
		int l_numeroElementos = getListComponentesAFalhar().getSize();
		
		List l_componentesFalhos = new ArrayList();
		
		try
		{
			simuladorDeEmissaoDeAlarmes.setMinFalhasExtras(getNumeroDeAlarmesExtras());
			simuladorDeEmissaoDeAlarmes.setMaxFalhasExtras(getNumeroDeAlarmesExtras());
					
			simuladorDeEmissaoDeAlarmes.setMinFalhasPerdidas(getNumeroDeAlarmesPerdidos());
			simuladorDeEmissaoDeAlarmes.setMaxFalhasPerdidas(getNumeroDeAlarmesPerdidos());
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		for (int index = 0; index < getListComponentesAFalhar().getSize(); index++)
		{
			String l_idString = (String)getListComponentesAFalhar().getElementAt(index);
			
			ID l_id = ID.criaNovoID(l_idString);
			
			l_componentesFalhos.add(l_id);
			
			System.out.println(l_idString + " --> " + l_id);
			
			try
			{

				simuladorDeEmissaoDeAlarmes.makeNovosAlames(l_componentesFalhos);
			} catch (Exception e1)
			{
				// TODO Bloco de captura gerado automaticamente
				e1.printStackTrace();
			}
			
			getListModelAlarmes().removeAllElements();
			
			for (Iterator l_iteratorAlarmes = simuladorDeEmissaoDeAlarmes.getAlames().iterator(); l_iteratorAlarmes.hasNext();)
			{
				getListModelAlarmes().addElement(l_iteratorAlarmes.next());
			}
		}
		
	}

	/**
	 * @return
	 */
	private String getNumeroDeAlarmesPerdidos()
	{
		return getJTextComponentAlarmesPerdidos().getText();
	}

	/**
	 * 
	 */
	private JFormattedTextField getJTextComponentAlarmesPerdidos()
	{
		return JTextComponentAlarmesPerdidos;
	}

	/**
	 * @return
	 */
	private String getNumeroDeAlarmesExtras()
	{
		return getJTextComponentAlarmesExtras().getText();
	}

	/**
	 * 
	 */
	private JFormattedTextField getJTextComponentAlarmesExtras()
	{
		return JTextComponentAlarmesExtras;
	}
	/**
	 * 
	 */
	public DefaultListModel getListModelAlarmes()
	{
		return listModelAlarmes;
		
	}

	/**
	 * 
	 */
	private ListModel getListComponentesAFalhar()
	{
		return listComponentesAFalhar;
		
	}

}
