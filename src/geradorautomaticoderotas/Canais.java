/*
 * Criado em 20/12/2004
 * Canais.java
 *
 * Todo Codigo criado abaixo, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e não seja para fins lucra-
 * tivos capitalistas.
 * 
 */
package geradorautomaticoderotas;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import geradorautomaticoderotas.erros.ExceptionCanalDesatualizado;
import gui.grafos.Aresta;
import gui.grafos.No;
import biblioteca.Componente;
import biblioteca.IdMas;

/**
 * @author Carlos Delfino
 * email suporte@carlosdelfino.eti.br
 * site http://www.carlosdelfino.eti.br
 * 
 */
public class Canais extends ArrayList
{

	/**
	 * 
	 */
	private Topologia aTopologia;

	public Canais()
	{}

	/**
	 * 
	 * @param l_aTopologia
	 * @param l_oArqCanais
	 */
	public Canais(Topologia l_aTopologia, String l_oArqCanais)
	{
		setTopologia(l_aTopologia);

		//	proforme para trabalhar com XML via DOM
		DocumentBuilderFactory l_FabObj = DocumentBuilderFactory.newInstance();

		// Carregar listas na memória a partir dos arquivos
		try
		{
			Document canaisXML = l_FabObj.newDocumentBuilder().parse(new File(l_oArqCanais));
			this.addAll(makeCanais(canaisXML));

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public boolean addAll(List p_colecao)
	{
		/* 
		 * aqui ele deve informar a todos os objetos cadastrados que ouve
		 * adição de novos canais na estrutura.
		 * 
		 */

		return super.addAll(p_colecao);
	}

	public boolean add(Canal p_canal)
	{
		/* 
		 * aqui ele deve informar a todos os objetos cadastrados que ouve
		 * adição de novos canais na estrutura.
		 * 
		 */

		return super.add(p_canal);
	}

	/**
	 * 
	 * @param p_aTopologia
	 */
	private void setTopologia(Topologia p_aTopologia)
	{
		aTopologia = p_aTopologia;
	}

	/**
	 * 
	 * @param canaisXML
	 * @return
	 */
	private List makeCanais(Document canaisXML)
	{
		NodeList elementos = canaisXML.getElementsByTagName("Canal");
		int numelementos = elementos.getLength();
		List lista = new ArrayList(numelementos);

		for (int i = 0; i < numelementos; i++)
		{
			Element l_canalxml = (Element)elementos.item(i);

			Canal canal = new Canal(l_canalxml, aTopologia);
			lista.add(canal);
		}
		return lista;
	}

	/**
	 * @return
	 */
	public ArrayList getCanais()
	{
		return this;
	}

	/**
	 * @param p_i
	 * @return
	 */
	public Canal getCanal(int p_i)
	{
		return (Canal)this.get(p_i);
	}

	/**
	 * 
	 * @param p_i
	 * @return
	 */
	public List listIdMasComponentesCanal(int p_i)
	{
		try
		{
			return getCanal(p_i).getListIdMasComponentes();
		} catch (ExceptionCanalDesatualizado e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Retorna um Array de int com Dois elementos, o primeiro
	 * indica o indice do primeiro canal ao qual o elemento pertence, o segundo
	 * indica o indice dentro do canal.
	 * 
	 * @param p_idMas
	 * @return
	 */
	public int[] firstIndexOf(IdMas p_idMas)
	{
		int[] l_indice = { -1, -1 };

		for (Iterator l_iter = iterator(); l_iter.hasNext();)
		{
			Canal l_canal = (Canal)l_iter.next();

			l_indice[0]++;
			l_indice[1] = l_canal.indexOf(p_idMas);
			if (l_indice[1] >= 0)
				return l_indice;
		}

		l_indice[0] = (l_indice[1] == -1 ? l_indice[0] : -1);

		return l_indice;
	}

	/**
	 * @param i
	 * @return
	 */
	public RotaFisica getRotaFisicaCanal(int p_i)
	{
		return getCanal(p_i).getRotaFisica();
	}

	public DefaultTreeModel getArvoreDaTopologia()
	{
		DefaultMutableTreeNode l_dmtn = new DefaultMutableTreeNode("Listagem dos Canais");

		for (Iterator l_iteratorCanais = this.iterator(); l_iteratorCanais.hasNext();)
		{
			Canal l_canal = (Canal)l_iteratorCanais.next();
			DefaultMutableTreeNode l_dmtnCanais =
				new DefaultMutableTreeNode("Canal: " + l_canal.getID() + " Nome: " + l_canal.getNome());
			l_dmtn.add(l_dmtnCanais);

			int l_count = 0;
			int l_fim = l_canal.sizeComponentes();
			for (Iterator l_iteratorComponente = l_canal.getRotaFisica().iterator();
				l_iteratorComponente.hasNext();
				)
			{
				l_count++;
				l_fim--;

				Componente l_componente = (Componente)l_iteratorComponente.next();

				DefaultMutableTreeNode l_dmtnComponentes =
					new DefaultMutableTreeNode(l_componente.getIdMas());
				l_dmtnCanais.add(l_dmtnComponentes);

			}

		}
		return new DefaultTreeModel(l_dmtn);
	}

	public String toString()
	{
		StringBuffer l_bString = new StringBuffer("Listagem dos Canais\n");

		for (Iterator l_iteratorCanais = this.iterator(); l_iteratorCanais.hasNext();)
		{
			Canal l_canal = (Canal)l_iteratorCanais.next();
			l_bString.append("Canal: " + l_canal.getID() + " Nome: " + l_canal.getNome() + "\n\t");

			int l_count = 0;
			int l_fim = l_canal.sizeComponentes();
			for (Iterator l_iteratorComponente = l_canal.getRotaFisica().iterator();
				l_iteratorComponente.hasNext();
				)
			{
				l_count++;
				l_fim--;

				Componente l_componente = (Componente)l_iteratorComponente.next();

				l_bString.append(l_componente.getIdMas());

				l_bString.append(l_fim == 0 ? ". " : ", ");

				if (l_count == 6)
				{
					l_bString.append("\n\t");
					l_count = 0;
				}
			}

			l_bString.append("\n");

		}
		l_bString.append("\n");

		return l_bString.toString();
	}

	/**
	 * @param p_i
	 * @param p_i2
	 * @return
	 */
	public IdMas getIdComponente(int p_indiceCanal, int p_indiceComponente)
		throws ExceptionCanalDesatualizado
	{
		Canal l_canal = (Canal)get(p_indiceCanal);
		return (IdMas)l_canal.getListIdMasComponentes().get(p_indiceComponente);
	}

	/**
	 * @param l_idMas
	 * @return
	 */
	public Componente getComponente(IdMas p_idMas)
	{
		for (Iterator l_iteratorCanais = iterator(); l_iteratorCanais.hasNext();)
		{
			Canal l_canal = (Canal)l_iteratorCanais.next();
			if (l_canal.getRotaFisica().contains(p_idMas))
				return l_canal.getRotaFisica().get(p_idMas);
		}
		return null;
	}

	/**
	 * @return
	 */
	public String getParDeComponentes()
	{
		StringBuffer l_sb = new StringBuffer();
		for (Iterator l_iteratorCanais = getCanais().iterator(); l_iteratorCanais.hasNext();)
		{
			Canal l_canal = (Canal)l_iteratorCanais.next();

			String l_anterior = null;

			for (Iterator l_iteratorRotaFisica = l_canal.getRotaFisica().iterator();
				l_iteratorRotaFisica.hasNext();
				)
			{
				Componente l_componenteId = (Componente)l_iteratorRotaFisica.next();
				if (l_anterior == null)
				{
					l_anterior = l_componenteId.getIdMas().toString();
				} else
				{
					l_sb.append(l_anterior + "-" + l_componenteId.getIdMas().toString() + ",");
					l_anterior = l_componenteId.getIdMas().toString();
				}
			}

		}
		return l_sb.toString();
	}

	/**
	 * 
	 */
	public Collection getNosGrafoPorComponentes()
	{
		Collection l_nos = new ArrayList();

		for (Iterator l_iteratorNos = getCanais().iterator(); l_iteratorNos.hasNext();)
		{
			Canal l_canal = (Canal)l_iteratorNos.next();

			for (Iterator l_iteratorRotafisica = l_canal.getRotaFisica().iterator();
				l_iteratorRotafisica.hasNext();
				)
			{
				Componente l_componente = (Componente)l_iteratorRotafisica.next();

				No l_no = new No(l_componente.getIdMas().toString());

				//l_no.setX(l_elemento.getLatitude());
				//l_no.setY(l_elemento.getLongitude());

				if (l_componente.getId().equalsIgnoreCase("lap"))
					l_no.setFixo(true);

				//l_no.setTipo(
				//	l_componente.isTipo(Elemento.NO_CENTRAL) ? No.CIRCULO_DUPLO : No.CIRCULO_SIMPLES);

				l_nos.add(l_no);
			}
		}

		return l_nos;

	}

	/**
	 * 
	 */
	public Collection getArestasGrafoPorComponentes()
	{

		Collection l_arestas = new ArrayList();

		for (Iterator l_iteratorNos = getCanais().iterator(); l_iteratorNos.hasNext();)
		{
			Canal l_canal = (Canal)l_iteratorNos.next();

			Iterator l_iteratorRotafisica = l_canal.getRotaFisica().iterator();
			
			Componente l_componenteAnterior = null;
			
			if (l_iteratorRotafisica.hasNext())
				l_componenteAnterior = (Componente)l_iteratorRotafisica.next();


			while (l_iteratorRotafisica.hasNext())
			{
				Componente l_componente = (Componente)l_iteratorRotafisica.next();

				Aresta l_aresta = new Aresta();

				l_aresta.setFrom(l_componenteAnterior.getIdMas().toString());

				l_aresta.setTo(l_componente.getIdMas().toString());

				l_arestas.add(l_aresta);

				l_componenteAnterior = l_componente;

			}
		}

		return l_arestas;
	}

}
