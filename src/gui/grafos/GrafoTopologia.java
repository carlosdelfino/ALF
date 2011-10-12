/*
 * PainelGrafoNovo.java Criado em 04/02/2004
 *
 * Todo Codigo criado abaixo, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e não seja para fins lucra-
 * tivos capitalistas.
 * 
 */
package gui.grafos;

import java.awt.Font;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author Carlos Delfino
 * @email suporte@carlosdelfino.eti.br
 * @site http://www.carlosdelfino.eti.br
 * 
 * Esta classe deve ser uma extenção do Panel, pois ela
 * precisa sobreescrever o metodo update, para atualizar
 * a Janela!
 * 
 * Nome: 
 */
public class GrafoTopologia extends Grafo
{
	public GrafoTopologia(){
		setFontePadraoDosNos( new Font("Tahoma", 0, 14));
	}

	/**
	 * @param p_object
	 */
	public void addNos(Collection p_nos)
	{
		for (Iterator l_itrationNos = p_nos.iterator(); l_itrationNos.hasNext();)
		{
			No l_no = (No)l_itrationNos.next();
			
			addNo(l_no);
		}
	}

	/**
	 * @param p_collection
	 */
	public void addArestas(Collection p_aresta)
	{
		for (Iterator l_iteratorArestas = p_aresta.iterator(); l_iteratorArestas.hasNext();)
		{
			Aresta l_aresta = (Aresta)l_iteratorArestas.next();
			addAresta(l_aresta);

			for (Iterator l_iteratorNos = getNos().iterator(); l_iteratorNos.hasNext();)
			{
				No l_no = (No)l_iteratorNos.next();
				if (l_no.getLbl().equalsIgnoreCase(l_aresta.getFrom()))
				{
					l_aresta.setIndexFrom(getNos().indexOf(l_no));
				} else if(l_no.getLbl().equalsIgnoreCase(l_aresta.getTo()))
				{
					l_aresta.setIndexTo(getNos().indexOf(l_no));
				}
				

			}

		}

	}	/**
	* @param l_aresta
	*/
  void addAresta(Aresta l_aresta)
  {
	  if (l_aresta.getLen() == 0)
	  {
		  l_aresta.setLen(DISTANCIA_PADRAO);
	  }
	  arestas.add(l_aresta);
  }
}