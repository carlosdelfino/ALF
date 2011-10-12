/*
 * ActionListenerAdicionarRemoverComponentes.java Criado em 09/04/2004
 *
 * Use somente com o conhecimento e autoriza��o do autor.
 * Este codigo n�o deve ser usado com fins lucrativos, sem
 * autoriza��o por escrito do autor.
 * 
 */
package gui.actionlisteners;

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

	/**
	 * @param p_parente
	 */
	public ActionListenerAdicionarRemoverAlarmes(PanelSimuladorDeAlarmes p_parente)
	{
		
			setParente(p_parente);
			setJTextField(p_parente.getJTFComponnenteOrigemAlarme());
			
			setListModel(p_parente.getjListModelAlarmes());
			setJList(p_parente.getJListDeAlarmes());

			setLinhaDeStatus(p_parente.getLinhaDeStatus());
	}

}
