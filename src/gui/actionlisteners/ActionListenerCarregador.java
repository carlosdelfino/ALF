package gui.actionlisteners;

import gui.PrincipalWindows;

import java.awt.event.ActionListener;


/*
 * ActionListenerCarregador.java Criado em 01/02/2004
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
public abstract class ActionListenerCarregador implements ActionListener
{
	PrincipalWindows parente;

	/**
	 * 
	 * @param p_parente
	 */
	public void setParente(PrincipalWindows p_parente)
	{
		parente = p_parente;
	}

	/**
	 * 
	 */
	public PrincipalWindows getParente()
	{
		return parente;
	}

	

}
