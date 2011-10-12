/*
 * OrdenaListaPorTamanho.java Criado em 25/03/2004
 *
 * Todo Codigo criado abaixo, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e não seja para fins lucra-
 * tivos capitalistas.
 * 
 */
package util;

import java.util.Comparator;
import java.util.Set;

/**
 * @author Carlos Delfino
 * @email suporte@carlosdelfino.eti.br
 * @site http://www.carlosdelfino.eti.br
 * 
 * Digite aqui o que esta classe faz.
 * Nome: 
 */
public class OrdenaConjuntosPorTamanho implements Comparator
{

	/**
	 * 
	 */
	public OrdenaConjuntosPorTamanho()
	{
	}

	private boolean ordemMenorParaMaior = true;

	/**
	 * @param p_b
	 */
	public OrdenaConjuntosPorTamanho(boolean p_b)
	{
		ordemMenorParaMaior = p_b;
	}

	/* (não-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Object o1, Object o2)
	{
		if (ordemMenorParaMaior)
			return ((Set)o1).size() - ((Set)o2).size();
		else
			return ((Set)o2).size() - ((Set)o1).size();
	}

}
