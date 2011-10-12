/*
 * Created on 11/12/2003
 * 
 * Todo Codigo criado por min, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e não seja para fins lucra-
 * tivos capitalistas.
 * 
 */
package biblioteca;

import geradorautomaticoderotas.Canal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.TreeMap;

import org.w3c.dom.Element;

import util.Coordenadas;

/**
 * Explique aqui o que é este tipo
 * Criação: 11/12/2003 - 19:26:59
 * 
 * @author Carlos Delfino
 * 
 */
public abstract class ElementoNo implements Elemento
{

	private Coordenadas coordenadas = new Coordenadas();

	// Contem os components modelos para este elemento
	private Map modelosComponentes = new TreeMap();

	// Contem referencias para os componentes contidos nos subElementos
	private Map componentes = new TreeMap();

	// Conten os componentes agrupados por sub elementos.
	private Map componentesFinais = new TreeMap();
	private Map componentesIniciais = new TreeMap();
	private Map componentesPassagem = new TreeMap();

	private String nome;
	private String id;

	private String tipo;

	private List subNoInicial = new ArrayList();
	private List subNoPassagem = new ArrayList();
	private List subNoFinal = new ArrayList();

	public ElementoNo()
	{}
	public ElementoNo(
		ListIterator p_liSubNoInicial,
		ListIterator p_liSubNoPassagem,
		ListIterator p_liSubNoFinal,
		Element p_elementoXML)
	{

		setId(p_elementoXML.getAttribute("id"));
		setNome(p_elementoXML.getAttribute("nome"));

		setSubComponentes(p_liSubNoInicial, p_liSubNoPassagem, p_liSubNoFinal, p_elementoXML);

	}
	synchronized public void setSubComponentes(
		ListIterator p_liSubNoInicial,
		ListIterator p_liSubNoPassagem,
		ListIterator p_liSubNoFinal,
		Element p_elementoXML)
	{
		while (p_liSubNoInicial.hasNext())
		{
			Componente l_componente = ((ModeloComponente)p_liSubNoInicial.next()).copia(this);

			IdMas l_idMas = l_componente.getIdMas();

			if (!modelosComponentes.containsKey(l_idMas.toString()))
			{
				modelosComponentes.put(l_idMas.toString(), l_componente);
			} else
			{
				l_componente = (Componente)modelosComponentes.get(l_idMas.toString());
			}

			subNoInicial.add(l_componente);
		}

		while (p_liSubNoPassagem.hasNext())
		{
			Componente l_componente = ((ModeloComponente)p_liSubNoPassagem.next()).copia(this);

			IdMas l_idMas = l_componente.getIdMas();

			if (!modelosComponentes.containsKey(l_idMas.toString()))
			{
				modelosComponentes.put(l_idMas.toString(), l_componente);
			} else
			{
				l_componente = (Componente)modelosComponentes.get(l_idMas.toString());
			}

			subNoPassagem.add(l_componente);
		}

		while (p_liSubNoFinal.hasNext())
		{
			Componente l_componente = ((ModeloComponente)p_liSubNoFinal.next()).copia(this);

			IdMas l_idMas = l_componente.getIdMas();

			if (!modelosComponentes.containsKey(l_idMas.toString()))
			{
				modelosComponentes.put(l_idMas.toString(), l_componente);
			} else
			{
				l_componente = (Componente)modelosComponentes.get(l_idMas.toString());
			}

			subNoFinal.add(l_componente);
		}

		((ArrayList)subNoFinal).trimToSize();
		((ArrayList)subNoPassagem).trimToSize();
		((ArrayList)subNoInicial).trimToSize();

	}

	public String getNome()
	{
		return nome;
	}

	/* (não-Javadoc)
	 * @see biblioteca.Elemento#setNome(java.lang.String)
	 */
	public void setNome(String nome)
	{
		this.nome = nome;

	}

	/* (não-Javadoc)
	 * @see biblioteca.Elemento#setId(java.lang.String)
	 */
	public void setId(String id)
	{
		this.id = id;
		//int numno = Integer.parseInt(id.substring(1));
		Iterator iteratorSubNI = this.subNoInicial.iterator();
		while (iteratorSubNI.hasNext())
		{
			Componente comp = (Componente)iteratorSubNI.next();
			comp.setNumeroElemento(id);
		}
	}

	/* (não-Javadoc)
	 * @see biblioteca.Elemento#getId()
	 */
	public String getId()
	{
		return id;
	}

	public void setTipo(String tipoElemento)
	{
		this.tipo = tipoElemento;
	}
	/* (não-Javadoc)
	 * @see biblioteca.No#getTipoModelo()
	 */
	public String getTipo()
	{
		return tipo;
	}

	/**
	 * @return
	 */
	public List getComponentesPassagem()
	{
		return this.subNoPassagem;
	}

	/**
	 * @return
	 */
	public List getComponentesFinais()
	{
		return this.subNoFinal;
	}

	/**
	 * @return
	 */
	public List getComponentesIniciais()
	{
		return this.subNoInicial;
	}
	/* (não-Javadoc)
	 * @see biblioteca.Elemento#getComponentesIniciais(topologia.Canal)
	 */
	public List getComponentesIniciais(Canal p_canal)
	{
		return getComponentes(p_canal, getComponentesIniciais(), componentesIniciais);
	}

	/* (não-Javadoc)
	 * @see biblioteca.Elemento#getComponentesPassagem(topologia.Canal)
	 */
	public List getComponentesPassagem(Canal p_canal)
	{
		return getComponentes(p_canal, getComponentesPassagem(), componentesPassagem);
	}
	/* (não-Javadoc)
	 * @see biblioteca.Elemento#getComponentesFinais(topologia.Canal)
	 */
	public List getComponentesFinais(Canal p_canal)
	{
		return getComponentes(p_canal, getComponentesFinais(), componentesFinais);
	}

	/**
	 * pega os componentes deste elemento, sendo que
	 * deve ser informado o Canal no qual este elemento vai compor
	 * a lista elementos do subelemento (inicial, passagem,final) e 
	 * um mapa que irá quardar estes Componentes, para evitar repetições.
	 * 
	 * Este metodo cria o componente baseando-se no modelo da lista
	 * Subelemento, e usando o mapa para armazenar os Componentes e 
	 * indexando pela string do ID da Mas, evitando assim que haja 
	 * repetição de componentes na estrutura.
	 * 
	 * @param p_canal
	 * @param l_subNo
	 * @param p_componentes
	 * @return
	 */
	private List getComponentes(Canal p_canal, List l_subNo, Map p_componentesSubElemento)
	{

		// Lista temporaria dos componentes do Elemento
		List l_compTmp = new ArrayList();

		// interage em cima de todos os componentes do subelemento passado
		for (Iterator iter = l_subNo.iterator(); iter.hasNext();)
		{

			// gera uma copia do Componente do Subelemento forncido
			Componente l_componenteTmp = ((Componente)iter.next());

			// Faz uma copia para testar se o elemento já existe
			Componente l_componente = l_componenteTmp.copia(this, p_canal);

			// o ID da Mas em Formato String para trabalhar com ele
			IdMas l_idMasString = l_componente.getIdMas();

			/*
			 *  Se o componente criado já tiver com seu ID
			 * no mapa subelemento ele apenas resgata do mapa.
			 * Caso contrario ele verifica se existe no mapa
			 * geral do elemento, existindo, ele pega do mapa
			 * geral, adiciona no subelemento, e no temporario.
			 * e em ultimo caso, ele pega o novo elemento e 
			 * adiciona em todos os mapas e ao temporario.
			 * 
			 */
			if (p_componentesSubElemento.containsKey(l_idMasString))
			{
				l_componente = (Componente)p_componentesSubElemento.get(l_idMasString);
			} else
			{
				//System.out.println("ele Não existe no subelemento");
				if (componentes.containsKey(l_idMasString))
				{
					//	System.out.println("ele existe no geral");
					l_componente = (Componente)componentes.get(l_idMasString);
				} else
				{
					//	System.out.println("ele Não existe no geral");
					componentes.put(l_idMasString, l_componente);
				}
				p_componentesSubElemento.put(l_idMasString, l_componente);
				//l_compTmp.add(l_componente);

			}
			l_compTmp.add(l_componente);

		}

		return l_compTmp;

	}

	public String toString()
	{
		return "Elemento do Tipo " + getTipo() + ", ID: " + getId() + ", nome: " + getNome() + "\n";
	}
	/**
	 * @return
	 */
	public Coordenadas getCoordenadas()
	{

		return coordenadas;
	}
	/**
	 * @return
	 */
	public double getLatitude()
	{
		return coordenadas.getLatitude();
	}
	/**
	 * @return
	 */
	public double getLongitude()
	{
		// TODO Stub de método gerado automaticamente
		return coordenadas.getLongitude();
	}
	/**
	 * @param p_string
	 */
	public void setLongitude(String p_string)
	{
		if(p_string.equals(""))	coordenadas.setLongitude(0);
		else coordenadas.setLongitude(Integer.valueOf(p_string).intValue());
	}

	/**
	 * @param p_string
	 */
	public void setLatitude(String p_string)
	{
		if(p_string.equals(""))	coordenadas.setLatitude(0);
		else coordenadas.setLatitude(Integer.valueOf(p_string).intValue());
	}

}
