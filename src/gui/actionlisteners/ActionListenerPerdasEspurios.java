/*
 * ActionListenerPerdasEspurios.java Criado em 15/04/2004
 *
 * Use somente com o conhecimento e autorização do autor.
 * Este codigo não deve ser usado com fins lucrativos, sem
 * autorização por escrito do autor.
 * 
 */
package gui.actionlisteners;

import geradorautomaticodealarmes.SimuladorDeEmissaoDeAlarmes;
import gui.PanelSimuladorDeAlarmes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.text.JTextComponent;

/**
 * @author Carlos Delfino
 * email suporte@carlosdelfino.eti.br
 * site http://www.carlosdelfino.eti.br
 * 
 * 
 */
public class ActionListenerPerdasEspurios implements ActionListener
{

	private JTextComponent JTextComponentAlarmesExtras;

	private JTextComponent JTextComponentAlarmesPerdidos;

	/**
	 * @param p_alarmes
	 */
	public ActionListenerPerdasEspurios(PanelSimuladorDeAlarmes p_painel)
	{
		setSimuladorDeAlarmes(p_painel.getSimuladorDeEmissaoDeAlarmes());
		setJTextComponentAlarmesExtras(p_painel.getJTextFieldAlarmesExtras());
		setJTextComponentAlarmesPerdidos(p_painel.getJTextFieldAlarmesPerdidos());
	}

	/**
	 * @param p_field
	 */
	private void setJTextComponentAlarmesPerdidos(JTextComponent p_field)
	{
		JTextComponentAlarmesPerdidos = p_field;
		
	}

	/**
	 * @param p_field
	 */
	private void setJTextComponentAlarmesExtras(JTextComponent p_field)
	{
		JTextComponentAlarmesExtras = p_field;
		
	}

	private SimuladorDeEmissaoDeAlarmes simulador;

	/**
	 * @param p_simulador
	 */
	private void setSimuladorDeAlarmes(SimuladorDeEmissaoDeAlarmes p_simulador)
	{
		simulador = p_simulador;
		
	}

	/* (não-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			simulador.setMaxFalhasExtras(getAlarmesExtras());
			simulador.setMinFalhasExtras(getAlarmesExtras());
			
			simulador.setMaxFalhasPerdidas(getAlarmesPerdidos());
			simulador.setMinFalhasPerdidas(getAlarmesPerdidos());
			
		} catch (Exception e1)
		{
			// TODO Bloco de captura gerado automaticamente
			e1.printStackTrace();
		}
		
		System.out.println("Perdas e Espurios");

	}

	/**
	 * @return
	 */
	private String getAlarmesPerdidos()
	{
		return JTextComponentAlarmesPerdidos.getText();
	}

	/**
	 * @return
	 */
	private String getAlarmesExtras()
	{
		return JTextComponentAlarmesExtras.getText();
	}

}
