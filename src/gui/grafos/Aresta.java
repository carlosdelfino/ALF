/*
 * Edge.java Criado em 04/02/2004
 *
 * Todo Codigo criado abaixo, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e não seja para fins lucra-
 * tivos capitalistas.
 * 
 */
package gui.grafos;

/**
 * @author Carlos Delfino
 * @email suporte@carlosdelfino.eti.br
 * @site http://www.carlosdelfino.eti.br
 * 
 * Digite aqui o que esta classe faz.
 * Nome: 
 */
public class Aresta implements Comparable
{
	/**
	 * 
	 */
	public Aresta(){}

	private String idTo;
	private String idFrom;
	private String lbl;
	/**
	 * @param p_string
	 */
	public Aresta(String p_lbl)
	{
		setLbl(p_lbl);
	}

	/**
	 * @param p_lbl
	 */
	private void setLbl(String p_lbl)
	{
		lbl = p_lbl;
		
	}

	private int indexFrom;
	private int indexTo;

	private double len;
	/**
	 * @return
	 */
	public int getIndexFrom()
	{
		return indexFrom;
	}

	/**
	 * @return
	 */
	public double getLen()
	{
		return len;
	}

	/**
	 * @return
	 */
	public int getIndexTo()
	{
		return indexTo;
	}

	/**
	 * @param p_i
	 */
	public void setIndexFrom(int p_i)
	{
		indexFrom = p_i;
	}

	/**
	 * @param p_d
	 */
	public void setLen(double p_d)
	{
		len = p_d;
	}

	/**
	 * @param p_i
	 */
	public void setIndexTo(int p_i)
	{
		indexTo = p_i;
	}

	/**
	 * @param p_string
	 */
	public void setFrom(String p_string)
	{
		idFrom = p_string;
		
	}

	/**
	 * @param p_string
	 */
	public void setTo(String p_string)
	{
		idTo = p_string;
		
	}
	public String getTo(){
		return idTo;
	}

	/**
	 * @return
	 */
	public String getFrom()
	{
		return idFrom;
	}

	/* (não-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o)
	{
		Aresta l_aresta = (Aresta)o;
		if (getLbl() == null && l_aresta.getLbl() == null){
			if (getFrom().equals(l_aresta.getFrom()))
				if (getTo().equals(l_aresta.getTo()))
					return 0;
				else
					return 1;
			else
				return -1;
		}
		if (getLbl().equals(l_aresta.getLbl()))
			if (getFrom().equals(l_aresta.getFrom()))
				if (getTo().equals(l_aresta.getTo()))
					return 0;
				else
					return 1;
			else
				return -1;
		else if (getFrom().equals(l_aresta.getFrom()))
			if (getTo().equals(l_aresta.getTo()))
				return 1;

		return -1;
	}

	/**
	 * @return
	 */
	public String getLbl()
	{
		return lbl;
	}

}
