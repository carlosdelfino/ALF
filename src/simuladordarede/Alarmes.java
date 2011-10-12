/*
 * Alarmes.java Criado em 24/01/2004
 *
 * Todo Codigo criado abaixo, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e não seja para fins lucra-
 * tivos capitalistas.
 * 
 */
package simuladordarede;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Set;

import biblioteca.IdMas;

/**
 * @author Carlos Delfino
 * email suporte@carlosdelfino.eti.br
 * site http://www.carlosdelfino.eti.br
 * 
 * A classe Alarmes deve armazenar todos os alarmes emitidos na rede
 * ela também é usada de forma a armazenar grupos de alarmes durante o processamento.
 * ela deve prover mecanismos compativeis com o FrameWork Collection, mas de
 * forma especifica a representar um grupo de alarmes.
 * A classe alarmes também deve ser informada de ocorrencia de eventos na rede, 
 * tais como novos canais, novos alarmes, remoção de alarmes mudança de topologia.
 * 
 * Esta classe deve somente armazenar alarmes, e pode recuperar um alarme
 * conforme um Id ou mesmo Canal.
 */
public class Alarmes implements OuvinteDeEventos, Cloneable
{
	ArrayList alarmes= new ArrayList();

	/**
	 * 
	 */
	public Alarmes()
	{
	}

	/**
	 * @param p_alarmes
	 */
	public Alarmes(Alarmes p_alarmes)
	{
		alarmes.addAll(p_alarmes.toCollection());
	}
	/**
	 * Cria uma nova coleção de Alarmes
	 * 
	 * @param p_alarmes
	 */
	public Alarmes(Collection p_alarmes)
	{
		for (Iterator l_iteratorAlarmes = p_alarmes.iterator(); l_iteratorAlarmes.hasNext();)
		{
			alarmes.add((Alarme)l_iteratorAlarmes.next());
			
		}
		
	}

	/**
	 * 
	 * @param p_alarme
	 */
	public boolean add(Alarme p_alarme)
	{
		if (p_alarme != null)
			return alarmes.add(p_alarme);
		return false;
	}

	public Object clone()
	{
		Alarmes l_alarmes = new Alarmes();
		l_alarmes.addAll((Collection)alarmes.clone());
		
		return l_alarmes;
	}

	/**
	 * @param p_object
	 */
	private void addAll(Collection p_alarmes)
	{
		for (Iterator l_iteratorAlarmes = p_alarmes.iterator(); l_iteratorAlarmes.hasNext();)
		{
			alarmes.add((Alarme)l_iteratorAlarmes.next());
			
		}
	}

	/**
	 * 
	 * @param l_a
	 */
	public Alarme get(int l_index)
	{
		return (Alarme)alarmes.get(l_index);
	}

	/*
	 *  (não-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		StringBuffer l_strB = new StringBuffer();
		for (Iterator l_iter = iterator(); l_iter.hasNext();)
		{
			l_strB.append(l_iter.next() + "\n");
		}
		return l_strB.toString();
	}
	public boolean contains(IdMas p_idMas)
	{
		return indexOf(p_idMas) > -1;
	}

	/**
	 * @param l_alarme1
	 * @return
	 */
	public int indexOf(Alarme p_alarme)
	{
		for (int i = 0; i < size(); i++)
			if (p_alarme.equals(get(i)))
				return i;
		return -1;
	}


	public int indexOf(IdMas p_idMas)
	{
		if (p_idMas == null)
		{
			for (int i = 0; i < size(); i++)
				if (get(i) == null)
					return i;
		} else
		{
			for (int i = 0; i < size(); i++)
				if (p_idMas.equals(get(i).getIdMasOrigem()))
					return i;
		}
		return -1;

	}

	/* (não-Javadoc)
	 * @see java.util.Collection#size()
	 */
	public int size()
	{
		return alarmes.size();
	}

	/* (não-Javadoc)
	 * @see java.util.Collection#clear()
	 */
	public void clear()
	{
		alarmes.clear();
	}

	/* (não-Javadoc)
	 * @see java.util.Collection#isEmpty()
	 */
	public boolean isEmpty()
	{
		return alarmes.isEmpty();
	}

	/* (não-Javadoc)
	 * @see java.util.Collection#toArray()
	 */
	public Alarme[] toArray() {
		int l_size = alarmes.size();
		Alarme[] alarme = new Alarme[l_size];
		System.arraycopy(alarmes.toArray(),0,alarme,0,l_size);
  		return alarme;
	}
	/* (não-Javadoc)
	 * @see java.util.Collection#contains(java.lang.Object)
	 */
	public boolean contains(Alarme o)
	{
		return indexOf(o) >=0;
	}

	/* (não-Javadoc)
	 * @see java.util.Collection#remove(java.lang.Object)
	 */
	public boolean remove(Alarme o)
	{
		return alarmes.remove(o);
	}

	/* (não-Javadoc)
	 * @see java.util.Collection#addAll(java.util.Collection)
	 */
	public boolean addAll(Alarmes c)
	{
		return alarmes.addAll(c.toCollection());
	}
	

	/* (não-Javadoc)
	 * @see java.util.Collection#containsAll(java.util.Collection)
	 */
	public boolean containsAll(Alarmes c)
	{
		return alarmes.containsAll(c.toCollection());
	}

	/* (não-Javadoc)
	 * @see java.util.Collection#removeAll(java.util.Collection)
	 */
	public boolean removeAll(Alarmes c)
	{
		return alarmes.removeAll(c.toCollection());
	}

	/* (não-Javadoc)
	 * @see java.util.Collection#retainAll(java.util.Collection)
	 */
	public boolean retainAll(Alarmes c)
	{
		return alarmes.retainAll(c.toCollection());
	}

	/* (não-Javadoc)
	 * @see java.util.Collection#iterator()
	 */
	public Iterator iterator()
	{
		return alarmes.iterator();
	}

	/**
	 * @param p_j
	 * @param p_alarme
	 */
	public Alarme set(int p_j, Alarme p_alarme)
	{
		return (Alarme)alarmes.set(p_j,p_alarme);
		
	}

	/* (não-Javadoc)
	 * @see java.util.Collection#toArray(java.lang.Object[])
	 */
	public Object[] toArray(Alarme[] a)
	{
		return alarmes.toArray(a);
	}

	/**
	 * @param l_alarmes
	 */
	public void addAll(Set l_alarmes)
	{
		for (Iterator l_iteratorAlarmes = l_alarmes.iterator(); l_iteratorAlarmes.hasNext();)
		{
			add((Alarme)l_iteratorAlarmes.next());
			
		}
		
	}

	/**
	 * @return
	 */
	public ListIterator listIterator()
	{
		return alarmes.listIterator();
	}

	/**
	 * @return
	 */
	public Collection toCollection()
	{
		return alarmes;
	}

	/**
	 * ordena os alarmes conforme sua posição dentro do Canal.
	 * 
	 */
	public void ordena()
	{
		Collections.sort(alarmes);
		
	}

	/**
	 * @param p_i
	 * @return
	 */
	public ListIterator listIterator(int p_i)
	{
		return alarmes.listIterator(p_i);
	}

	/**
	 * @param p_i
	 */
	public void remove(int p_i)
	{
		alarmes.remove(p_i);
		
	}


}