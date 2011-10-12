/*
 * ActionListenerAdicionarRemoverComponentes.java Criado em 09/04/2004
 *
 * Use somente com o conhecimento e autorização do autor.
 * Este codigo não deve ser usado com fins lucrativos, sem
 * autorização por escrito do autor.
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
public class ActionListenerAdicionarRemoverComponentes extends ActionListenerAdicionarRemover
{

	/**
	 * @param p_parente
	 */
	public ActionListenerAdicionarRemoverComponentes(PanelSimuladorDeAlarmes p_parente)
	{
		setJTextField(p_parente.getJTFComponentesAFalhar());
		setListModel(p_parente.getjListModelComponentesAFalhar());
		setJList(p_parente.getJListDeComponentesAFalhar());
		
		setJBottonAdicionar(p_parente.getJBAdicionarComponentesAFalhar());
		setJBottonRemover(p_parente.getJBRemoverComponentesAFalhar());
		

		setLinhaDeStatus(p_parente.getLinhaDeStatus());
	}

}
