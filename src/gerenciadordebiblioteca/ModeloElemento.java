/*
 * ModeloElemento.java Criado em 17/01/2004
 *
 * Todo Codigo criado abaixo, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e não seja para fins lucra-
 * tivos capitalistas.
 * 
 */
package gerenciadordebiblioteca;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

/**
 * @author Carlos Delfino
 * email suporte@carlosdelfino.eti.br
 * site http://www.carlosdelfino.eti.br
 * 
 * Digite aqui o que esta classe faz.
 * Nome: 
 */
public class ModeloElemento implements Modelos
{

	private static String LONGITUDE = "longitude";
	private static String LATIDUDE = "latitude";

	private static final String ID_ELEMENTO = "idelemento";

	private static final String TIPO = "tipo";

	private static final String ID = "id";

	private static final String NOME = "nome";

	private String idModelo;

	private String nomeModelo;

	private List subElementoPassagem;

	private List subElementoFinal;

	private List subElementoInicial;

	static public ModeloElemento make(Element modeloElementoXML)
		throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{

		Class l_classeTipoElemento = Class.forName(makeNomeClasseModeloElemento(modeloElementoXML));
		ModeloElemento modeloElemento = (ModeloElemento)l_classeTipoElemento.newInstance();

		modeloElemento.setIdModelo(modeloElementoXML.getAttribute(ModeloElemento.ID));
		modeloElemento.setNomeModelo(modeloElementoXML.getAttribute(ModeloElemento.NOME));

		return modeloElemento;
	}

	/**
	 * @param modeloElementoXML
	 * @return
	 */
	private static String makeNomeClasseModeloElemento(Element modeloElementoXML)
	{
		String l_nomeClasseTipoElemento = Biblioteca.CAMINHO_COMPLETO_PARA_TIPO_ELEMENTO;
		String l_tipo = modeloElementoXML.getAttribute(ModeloElemento.TIPO);
		l_tipo.replaceFirst(l_tipo.substring(0, 1), l_tipo.substring(0, 1).toUpperCase());
		l_nomeClasseTipoElemento += l_tipo;
		return l_nomeClasseTipoElemento;
	}

	private static String makeNomeClasseElemento(Element modeloElementoXML)
	{
		String l_nomeClasseTipoElemento = Biblioteca.CAMINHO_COMPLETO_PARA_ELEMENTO;
		
		String l_tipo = modeloElementoXML.getAttribute(ModeloElemento.ID_ELEMENTO);
		if (l_tipo.length() > 2)
		{
			l_tipo.replaceFirst(l_tipo.substring(0, 1), l_tipo.substring(0, 1).toUpperCase());
			l_nomeClasseTipoElemento += l_tipo;
		} else
		{
			l_tipo.replaceFirst(l_tipo.substring(0, 1), l_tipo.substring(0, 1).toUpperCase());
			l_tipo.replaceFirst(l_tipo.substring(1, 2), l_tipo.substring(1, 2).toUpperCase());
			l_nomeClasseTipoElemento += l_tipo;
		}
		return l_nomeClasseTipoElemento;
	}
	/* (não-Javadoc)
	 * @see biblioteca.Modelos#pegaNomeModelo()
	 */
	public String getNomeModelo()
	{
		return nomeModelo;
	}

	/* (não-Javadoc)
	 * @see biblioteca.Modelos#defineNomeModelo(java.lang.String)
	 */
	public void setNomeModelo(String p_nome)
	{
		nomeModelo = p_nome;
	}

	/* (não-Javadoc)
	 * @see biblioteca.Modelos#defineIdModelo(java.lang.String)
	 */
	public void setIdModelo(String p_id)
	{
		idModelo = p_id;
	}

	/* (não-Javadoc)
	 * @see biblioteca.Modelos#pegaIdModelo()
	 */
	public String getIdModelo()
	{
		return idModelo;
	}

	/* (não-Javadoc)
	 * @see biblioteca.Modelos#copia(java.lang.Object)
	 */
	public Elemento makeElemento(Element p_elementoXML)
		throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{

		String p_idElemento = p_elementoXML.getAttribute(ModeloElemento.ID_ELEMENTO);

		Class l_classeElemento = Class.forName(makeNomeClasseElemento(p_elementoXML));
		Elemento l_elemento = (Elemento)l_classeElemento.newInstance();

		l_elemento.setId(p_elementoXML.getAttribute(ModeloElemento.ID));
		l_elemento.setNome(p_elementoXML.getAttribute(ModeloElemento.NOME));

		if (!l_elemento.isTipo(Elemento.LINK))
		{
			l_elemento.setSubComponentes(
				getListMComponentesIniciais().listIterator(),
				getListMComponentesPassagens().listIterator(),
				getListMComponentesFinais().listIterator(),
				p_elementoXML);

			((ElementoNo)l_elemento).setLongitude(
				p_elementoXML.getAttribute(ModeloElemento.LONGITUDE));
			((ElementoNo)l_elemento).setLatitude(p_elementoXML.getAttribute(ModeloElemento.LATIDUDE));
		} else
		{
			l_elemento.setSubComponentes(
				null,
				getListMComponentesPassagens().listIterator(),
				null,
				p_elementoXML);
		}

		return l_elemento;

	}

	/**
	 * @param p_mComponente
	 */
	public void addMComponenteInicial(ModeloComponente p_mComponente)
	{
		// como o campo subElementoInicial, nem sempre existe 
		// nos modelos de Elementos ele é verificado aqui, se estiver
		// vasio é criado aqui mesmo.
		if (subElementoInicial == null)
		{
			subElementoInicial = new ArrayList();
		}
		subElementoInicial.add(p_mComponente);
	}
	public void addMComponenteFinal(ModeloComponente p_mComponente)
	{
		if (subElementoFinal == null)
		{
			subElementoFinal = new ArrayList();
		}
		subElementoFinal.add(p_mComponente);
	}
	public void addMComponentePassagem(ModeloComponente tComp)
	{
		if (subElementoPassagem == null)
		{
			subElementoPassagem = new ArrayList();
		}
		subElementoPassagem.add(tComp);
	}
	/**
	 * pega os modelos de SubComponentes
	 * 
	 */
	public List getListMComponentesIniciais()
	{
		return subElementoInicial;
	}
	public List getListMComponentesFinais()
	{
		return subElementoFinal;
	}
	public List getListMComponentesPassagens()
	{
		return subElementoPassagem;
	}

}