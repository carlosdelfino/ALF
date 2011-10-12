/*
 * ActionListenerExibirDominioFalhas.java Criado em 09/04/2004
 *
 * Todo Codigo criado abaixo, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e não seja para fins lucra-
 * tivos capitalistas.
 * 
 */
package gui.actionlisteners;

import java.awt.event.ActionEvent;

import javax.xml.parsers.ParserConfigurationException;

import gui.PrincipalWindows;

/**
 * @author Carlos Delfino
 * @email suporte@carlosdelfino.eti.br
 * @site http://www.carlosdelfino.eti.br
 * 
 * Digite aqui o que esta classe faz.
 * Nome: 
 */
public final class ActionListenerExibirDominioFalhas extends ActionListenerCarregador
{
	/**
	 * @param p_windows
	 */
	public ActionListenerExibirDominioFalhas(PrincipalWindows p_windows)
	{
		setParente(p_windows);
	}

	public void actionPerformed(java.awt.event.ActionEvent e)
	{
		//DEBUG
		System.out.println("ExibirDominioFalhas");
		String l_cmd = e.getActionCommand();

		pegaDominioMudaTab(e, parente.TAB_DOMINIO_TEXTO);
	}

	/**
	 * @param e
	 * @param p_string
	 */
	private void pegaDominioMudaTab(ActionEvent e, String p_tab)
	{

		try
		{
			parente.getJTextAreaDominio().setText(
				parente.getAlgoritimoDeCorrelacaoDeAlarmes().getDominio().toString());
		} catch (ParserConfigurationException e1)
		{
			// TODO Bloco de captura gerado automaticamente
			e1.printStackTrace();
		}

		if (p_tab != null)
			mudaTab(p_tab);

	}

	private void mudaTab(String p_tab)
	{
		parente.getJTabbedPaneGeral().setSelectedIndex(
			parente.getJTabbedPaneGeral().indexOfTab(parente.TAB_GERENCIA));
		parente.getJTabbedPaneCanais().setSelectedIndex(
			parente.getJTabbedPaneGerencia().indexOfTab(p_tab));
	}
}
