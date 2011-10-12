/*
 * RotaFisica.java Criado em 13/03/2004
 *
 * Todo Codigo criado abaixo, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e não seja para fins lucra-
 * tivos capitalistas.
 * 
 */
package gerenciadordetopologia;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;

import gerenciadordebiblioteca.Componente;
import gerenciadordebiblioteca.ID;
import gerenciadordebiblioteca.IdMas;

/**
 * @author Carlos Delfino
 * email suporte@carlosdelfino.eti.br
 * site http://www.carlosdelfino.eti.br
 * 
 * Digite aqui o que esta classe faz.
 * Nome: 
 */
public class RotaFisica implements Cloneable
{
	ArrayList rotaFisica = new ArrayList();

	/**
	 * @param p_componente
	 * @return
	 */
	public boolean contains(Componente p_componente)
	{
		for (Iterator iter = rotaFisica.iterator(); iter.hasNext();)
		{
			if (((Componente)iter.next()).equals(p_componente))
				return true;
		}
		return false;
	}

	public boolean contains(IdMas p_idMas)
	{
		for (Iterator iter = rotaFisica.iterator(); iter.hasNext();)
		{
			if (((Componente)iter.next()).getIdMas().equals(p_idMas))
				return true;
		}
		return false;
	}
	public int indexOf(ID p_idMas)
	{
		int count = -1;
		for (Iterator iter = rotaFisica.iterator(); iter.hasNext();)
		{
			count++;
			if (((Componente)iter.next()).getIdMas().equals(p_idMas))
				return count;
		}

		return count;
	}
	/**
	 * @return
	 */
	public int size()
	{
		return rotaFisica.size();
	}
	/**
	 * 
	 */
	public Iterator iterator()
	{
		return rotaFisica.iterator();

	}
	/**
	 * @param l_componente
	 * @return
	 */
	public int indexOf(Componente p_componente)
	{
		return rotaFisica.indexOf(p_componente);
	}
	/**
	 * @param l_indiceProximoComponente
	 */
	public ListIterator listIterator(int p_indiceProximoComponente)
	{
		return rotaFisica.listIterator(p_indiceProximoComponente);

	}
	/**
	 * 
	 */
	private Collection toCollection()
	{
		return rotaFisica;
	}
	/**
	 * @return
	 */
	public boolean isEmpty()
	{
		return rotaFisica.isEmpty();
	}
	/**
	 * 
	 */
	public void clear()
	{
		rotaFisica.clear();

	}
	/**
	 * @param p_indice
	 * @return
	 */
	public Componente get(int p_indice)
	{
		return (Componente)rotaFisica.get(p_indice);
	}

	public Object clone()
	{
		RotaFisica l_rotaFisica = new RotaFisica();
		l_rotaFisica.addAll((Collection)rotaFisica.clone());

		return l_rotaFisica;
	}
	/**
	 * @param p_collection
	 */
	void addAll(Collection p_collection)
	{
		for (Iterator l_iteratorCollection = p_collection.iterator();
			l_iteratorCollection.hasNext();
			)
		{
			rotaFisica.add((Componente)l_iteratorCollection.next());

		}

	}
	/**
	 * Retorna um clone da rota fisica em sentido inverso.
	 * 
	 * Cada vez que é chamado sobre o mesmo objeto retorna 
	 * uma copia desde objeto com a rota invertida.
	 * 
	 * Se for chamado duas vezes, uma sobre a rota original
	 * e uma segunda sobre a rota invertida, o terceiro resultado
	 * será igual ao resultado.
	 * 
	 * @return
	 */
	public RotaFisica getSentidoInverso()
	{
		RotaFisica l_rotaFisica = (RotaFisica)clone();

		l_rotaFisica.inverteOrdem();

		return l_rotaFisica;
	}

	/**
	 * Inverte a ordem da rota fisica em questão.
	 * pode ser usado sem problema em qualquer situação.
	 * se for chamado duas vezes, volta a ordem original. 
	 */
	public void inverteOrdem()
	{
		Collections.reverse(rotaFisica);

	}

	/**
	 * @param p_idMas
	 * @return
	 */
	public Componente get(IdMas p_idMas)
	{
		for (Iterator l_iteratorRF = rotaFisica.iterator(); l_iteratorRF.hasNext();)
		{
			Componente l_componente = (Componente)l_iteratorRF.next();
			if (l_componente.getIdMas().equals(p_idMas))
				return l_componente;
		}
		return null;
	}

}
