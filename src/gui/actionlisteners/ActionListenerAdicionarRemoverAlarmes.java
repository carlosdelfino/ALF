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

import javax.swing.JTextField;

import geradorautomaticodealarmes.SimuladorDeEmissaoDeAlarmes;
import gui.PanelSimuladorDeAlarmes;

/**
 * @author Carlos Delfino
 * email suporte@carlosdelfino.eti.br
 * site http://www.carlosdelfino.eti.br
 * 
 * 
 */
public class ActionListenerAdicionarRemoverAlarmes extends ActionListenerAdicionarRemover
{

	private SimuladorDeEmissaoDeAlarmes SimuladorDeEmissaoDeAlarmes;

	private JTextField jTextField2;

	/**
	 * @param p_parente
	 */
	public ActionListenerAdicionarRemoverAlarmes(PanelSimuladorDeAlarmes p_parente)
	{
		setSimuladorDeEmissaoDeAlarmes(p_parente.getSimuladorDeEmissaoDeAlarmes());

		setJTextField(p_parente.getJTFComponnenteOrigemAlarme());
		setJTextField2(p_parente.getJTFNivelDoAlarme());

		setListModel(p_parente.getjListModelAlarmes());
		setJList(p_parente.getJListDeAlarmes());

		setJBottonAdicionar(p_parente.getJBAdicionarAlarmes());
		setJBottonRemover(p_parente.getJBRemoverAlarmes());

		setLinhaDeStatus(p_parente.getLinhaDeStatus());
	}

	/**
	 * @param p_alarmes
	 */
	private void setSimuladorDeEmissaoDeAlarmes(SimuladorDeEmissaoDeAlarmes p_simulador)
	{
		SimuladorDeEmissaoDeAlarmes = p_simulador;
		
	}

	/**
	 * @param p_field
	 */
	private void setJTextField2(JTextField p_field)
	{
		jTextField2 = p_field;

	}
	public void actionPerformed(ActionEvent e)
	{
		String cmd = e.getActionCommand();
		
		Object objectoRemovido = null;

		if (cmd.equals(cmd_Remover))
		{
			objectoRemovido = remover(SimuladorDeEmissaoDeAlarmes.removeAlarme(getJTextField().getText(),getJTextField2().getText()));
		} else if (cmd.equals(cmd_Adicionar))
		{
			adicionar(SimuladorDeEmissaoDeAlarmes.addAlarme(getJTextField().getText(),getJTextField2().getText()));
		}
	}

	/**
	 * 
	 */
	private JTextField getJTextField2()
	{
		return jTextField2;
	}

}
