/*
 * Created on 12/12/2003
 * 
 * Novo Arquivo, coloque aqui a licensa de uso deste
 * codigo.
 * 
 */
package gerenciadordetopologia;

import gui.grafos.Aresta;
import gui.grafos.No;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import gerenciadordebiblioteca.Biblioteca;
import gerenciadordebiblioteca.Elemento;
import gerenciadordebiblioteca.ElementoLink;
import gerenciadordebiblioteca.ElementoNo;
import gerenciadordebiblioteca.erros.ExceptionBibliotecaNaoDefinida;

/**
 * A Classe Topologia representa o Conjunto de elementos
 * que compõem a rede, através dela podemos obtemos as informações
 * para desenhar um grafo da rede, ou sabermos como chegar de um
 * elemento a outro, por exemplo qual o no que está ligado
 * ao No01, qual o sentido da conexção entre o No05 e No04.
 * 
 * Criação: 12/12/2003 - 07:04:55
 * @author Carlos Delfino
 * 
 */
public class Topologia
{
	private Canais canais;

	// Armazena os Elementos da rede
	// uso uma array porque os elementos são cadastro
	// em ordem numerica, tipo n01, n02, n03, mesmo que 
	// tenham nomes diferentes, e os resgato com um 
	// indice criado com base neste numero do ID.
	private List nos = new ArrayList();

	// faz um mapa dos Links.
	// uso um mapa, pois quando vou resgatar o link
	// eu uso uma composição no anterior mais no posterior
	// para identificalo.
	private Map links = new HashMap();

	private Biblioteca aBiblioteca = null;

	/* Criação: 12/12/2003 - 07:20:12
	 */
	/**
		 * Este construtor recebe como argumento o arquivo topologia.xml
	 * e o objeto biblioteca criado anteriormente.
	 * Com estes dados ele monta a estrutura da rede.
	 *
	 * @param p_arqTopologia
	 * @param p_biblioteca 
	 */
	public Topologia(String p_arqTopologia, Biblioteca p_biblioteca)
		throws ExceptionBibliotecaNaoDefinida
	{

		setBiblioteca(p_biblioteca);
		// proforme para trabalhar com XML via DOM
		loadArquivoXML(p_arqTopologia);
	}

	private void loadArquivoXML(String p_arqTopologia)
		throws FactoryConfigurationError {
		DocumentBuilderFactory fabObj = DocumentBuilderFactory.newInstance();
		
		// Carregar listas na memória a partir dos arquivos Biblioteca e Topologia
		try
		{
			Document l_documentoXML = fabObj.newDocumentBuilder().parse(new File(p_arqTopologia));
			nos.addAll(makeCollectionElementosNos(l_documentoXML));
			links.putAll(makeMapLinks(l_documentoXML));
		
		} catch (Exception e)
		{
			// 
			e.printStackTrace();
		}
		
	}

	/**
	 * 
	 * @param p_aBiblioteca
	 */
	private void setBiblioteca(Biblioteca p_aBiblioteca) throws ExceptionBibliotecaNaoDefinida
	{
		if (p_aBiblioteca == null)
			throw new ExceptionBibliotecaNaoDefinida();
		this.aBiblioteca = p_aBiblioteca;
	}
	/**
	 * Gera uma Collection, mas propriamente uma ArrayList
	 * usa os elementos contidos na biblioteca para esta tarefa.
	 * 
	 * @param p_documentoXML -- Objeto Document em formato XML para ser extraido as informações dos Elementos.
	 * @return Collection (uma ArrayList) com todos os Elementos.
	 */
	private Collection makeCollectionElementosNos(Document p_documentoXML)
		throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		NodeList elementosXML = p_documentoXML.getElementsByTagName("Elemento");
		int l_numElementos = elementosXML.getLength();
		Collection l_colecaoElementos = new ArrayList();

		for (int i = 0; i < l_numElementos; i++)
		{
			Element l_elementoXML = (Element)elementosXML.item(i);
			String l_tipo = l_elementoXML.getAttribute("idelemento");

			if (l_tipo.equalsIgnoreCase(Elemento.NO_LOCAL)
				|| l_tipo.equalsIgnoreCase(Elemento.NO_CENTRAL))
			{
				l_colecaoElementos.add(aBiblioteca.novoElemento(l_elementoXML));
			}

		}
		return l_colecaoElementos;
	}

	/**
	 * Gera uma mapa nome de identificação e Elemento do tipo Link
	 * 
	 * @param p_documentoXML -- Objeto Document em formato XML para ser extraido as informações dos Elementos.
	 * @return Map com todos os elementos mapeados pelo ID
	 */
	private Map makeMapLinks(Document p_documentoXML)
		throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		NodeList elementosXML = p_documentoXML.getElementsByTagName("Elemento");

		int numelementos = elementosXML.getLength();
		Map mapaLinks = new HashMap();

		for (int i = 0; i < numelementos; i++)
		{
			Element elementoXML = (Element)elementosXML.item(i);

			String tipo = elementoXML.getAttribute("idelemento");
			if (tipo.equalsIgnoreCase("Link"))
			{
				// crio um novo link com base no arquivo xml
				Elemento link = (Elemento)aBiblioteca.novoElemento(elementoXML);

				// pego os Ids dos nos que este link liga
				// verifique que esta informação hoje é tirada do
				// id do link.
				String idnoanterior = ((ElementoLink)link).getIdNoAnterior();
				String idnoposterior = ((ElementoLink)link).getIdNoPosterior();

				int indiceant = getNumeroDoIndice(idnoanterior);
				Elemento noanterior = (Elemento)nos.get(indiceant);

				int indicepos = getNumeroDoIndice(idnoposterior);
				Elemento noposterior = (Elemento)nos.get(indicepos);

				noanterior.putElementoPosterior(noposterior);

				mapaLinks.put(link.getId(), link);
			}
		}
		return mapaLinks;
	}

	/**
	 * 
	 * @param l_oNo
	 * @return
	 */
	private int[] getNumeroDoID(Elemento l_oNo)
	{
		String l_oId = l_oNo.getId();

		int[] l_num = new int[2];
		l_num[0] = Integer.parseInt(l_oId.substring(1, 3));
		l_num[1] = Integer.parseInt(l_oId.substring(3, 5));
		return l_num;
	}
	/**
	 * Este metodo Recebe uma String que representa o ID do Nó
	 * então calcula o numero de identificação do Nó na biblioteca.
	 * @param id
	 * @return
	 */
	private int getNumeroDoIndice(String id)
	{
		int numero = Integer.parseInt(id.substring(1)) - 1;
		return numero;
	}

	public String toString()
	{
		int numnos = nos.size();
		StringBuffer print = new StringBuffer();
		print.append("A topologia possui " + numnos + " Nós\n");

		for (int i = 0; i < numnos; i++)
		{
			Elemento elemento = (Elemento)nos.get(i);

			print.append("\tO Nó " + elemento.getId() + " de nome ");
			print.append(elemento.getNome() + " é um nó " + elemento.getTipo() + "\n");

			Object[] noPosterior = elemento.getElementoPosterior();

			if (noPosterior.length > 1)
			{
				int numnosp = noPosterior.length;
				print.append("\tEle está ligado a " + numnosp + " Nós:\n\t\t");
				for (int f = 0; f < numnosp; f++)
				{
					String idnop = ((Elemento)noPosterior[f]).getId();
					print.append("---> " + idnop + ", ");
				}
			} else if (noPosterior[0] != null)
			{
				print.append("\t\tEle está ligado ao nó " + ((Elemento)noPosterior[0]).getId());
			}
			print.append("\n");
		}
		return print.toString();

	}
	/**
	 * Retorna um No da Rede
	 * @param p_idElemento
	 * @return
	 */
	public Elemento getElemento(String p_idElemento)
	{
		return (Elemento)nos.get(getNumeroDoIndice(p_idElemento));

	}
	/**
	 * Retorna um link da rede
	 * @param p_idLink
	 * @return
	 */
	public ElementoLink getLink(String p_idLink)
	{
		return (ElementoLink)links.get(p_idLink);

	}
	/**
	 * Metodo usado para pegar a Fibra que faz o link
	 * no sentido inverso do padrão definido.
	 * Isto é feito usando o seguinte conseito:
	 * Os links entre os nos são cadastrados como linhas e colunas,
	 * a ultima linha e ultima coluna de uma interação são sempre
	 * CCW, por exemplo tenho 6 nos interligados, os nos 1,2,3,4,5 e 6
	 * iniciando um canal da esquerda para a direita, os canais 1,2,3,4,5
	 * serão CW, os canais 1,6,5 serão vistos como CCW, já os 5,4,3 
	 * estritamente nesta ordem serão vistos como CCW.
	 * 
	 * @param p_idLink
	 * @return um Link que une dois Nos
	 */
	public ElementoLink getLinkCCW(String p_idLink)
	{
		return getLink(trocaSentido(p_idLink));
	}
	/**
	 * @param idLink
	 */
	private String trocaSentido(String idLink)
	{
		String numID[] = getStringNumeroDoID(idLink);
		return Elemento.PREFIXO_LINK + numID[1] + numID[0];
	}

	/**
	 * 
	 * @param idLink
	 * @return
	 */
	private String[] getStringNumeroDoID(String idLink)
	{
		String[] num = new String[2];
		num[0] = idLink.substring(1, 3);
		num[1] = idLink.substring(3, 5);
		return num;
	}

	/**
	 * 
	 * @param p_idNoAnterior
	 * @param p_idNo
	 * @return
	 */
	public ElementoLink getLink(String p_idNoAnterior, String p_idNo)
	{
		String l_idLink =
			Elemento.PREFIXO_LINK + p_idNoAnterior.substring(1, 3) + p_idNo.substring(1, 3);
		return getLink(l_idLink);
	}

	/**
	 * 
	 * @param p_idNoAnterior
	 * @param p_idNo
	 * @return
	 */
	public ElementoLink getLinkCCW(String p_idNoAnterior, String p_idNo)
	{
		return getLink(p_idNo, p_idNoAnterior);
	}

	/**
	 * @param arqCanais
	 * @return
	 */
	public void criarCanais(String arqCanais)
	{
		canais = new Canais(this, arqCanais);
	}

	/**
	 * 
	 */
	public Canais getCanais()
	{
		return canais;

	}

	/**
	 * @return
	 */
	public String getParDeNosEmString()
	{
		StringBuffer l_parDeNos = new StringBuffer();
		for (Iterator iter = links.keySet().iterator(); iter.hasNext();)
		{
			l_parDeNos.append(getParDeNosEmString((ElementoLink)links.get(iter.next())) + ",");
		}
		return l_parDeNos.toString();
	}

	/**
	 * @param p_link
	 * @return
	 */
	private String getParDeNosEmString(ElementoLink p_link)
	{
		String idNo1 = p_link.getIdNoAnterior();
		String idNo2 = p_link.getIdNoPosterior();
		return idNo1 + "-" + idNo2;
	}

	/**
	 * 
	 */
	public Collection getNosGrafo()
	{
		Collection l_nos = new ArrayList(getNos().size());

		for (Iterator l_iteratorNos = getNos().iterator(); l_iteratorNos.hasNext();)
		{
			ElementoNo l_elemento = (ElementoNo)l_iteratorNos.next();

			No l_no = new No(l_elemento.getId());

			l_no.setX(l_elemento.getLatitude());
			l_no.setY(l_elemento.getLongitude());

			l_no.setFixo(true);

			l_no.setTipo(
				l_elemento.isTipo(Elemento.NO_CENTRAL) ? No.CIRCULO_DUPLO : No.CIRCULO_SIMPLES);

			l_nos.add(l_no);
		}

		return l_nos;
	}

	/**
	 * 
	 */
	private List getNos()
	{
		return nos;

	}

	/**
	 * @return
	 */
	public Collection getArestasGrafo()
	{
		Set l_idLinks = getLinks().keySet();
		Collection l_arestas = new ArrayList();

		for (Iterator l_iteratorIdLinks = l_idLinks.iterator(); l_iteratorIdLinks.hasNext();)
		{
			ElementoLink l_link = getLink((String)l_iteratorIdLinks.next());
			Aresta l_aresta = new Aresta(l_link.getId());
			l_aresta.setFrom(l_link.getIdNoAnterior());
			l_aresta.setTo(l_link.getIdNoPosterior());

			l_arestas.add(l_aresta);

		}
		return l_arestas;
	}

	/**
	 * 
	 */
	private Map getLinks()
	{
		return links;

	}
	public DefaultTreeModel getArvoreDaTopologia()
		{
			DefaultMutableTreeNode l_dmtn = new DefaultMutableTreeNode("Topologia com " + nos.size() + " nós :");
			
				for (int i = 0; i < nos.size(); i++)
				{
					Elemento elemento = (Elemento)nos.get(i);

					DefaultMutableTreeNode l_dmtnNos = new DefaultMutableTreeNode("Nó " + elemento.getId() + ", nome "+
					elemento.getNome() + " de típo: " + elemento.getTipo());
					
					l_dmtn.add(l_dmtnNos);

					Elemento[] noPosterior = elemento.getElementoPosterior();

					if (noPosterior[0] != null)
					{
					
						for (int f = 0; f < noPosterior.length; f++)
						{
							DefaultMutableTreeNode l_dmtnNosPosteriores = new DefaultMutableTreeNode(noPosterior[f].getId());
							l_dmtnNos.add(l_dmtnNosPosteriores);
						}
					}
				}
			return new DefaultTreeModel(l_dmtn);
		}
}
