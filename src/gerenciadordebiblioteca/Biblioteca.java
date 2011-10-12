/*
 * Created on 08/12/2003
 * 
 * Todo Codigo criado por min, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e não seja para fins lucra-
 * tivos capitalistas.
 * 
 */
package gerenciadordebiblioteca;

import java.io.File;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * </p>Coleção de modelos de Componentes e Elementos
 * que irão compor a topologia da rede.</p>
 * 
 * <p>Cada Elemento ou Componente de rede pode ser
 * recuperado usando seu ID. a Classe Biblioteca<br> 
 * é uma extenção da Hashtable, isto é um forma de<br>
 * especialização para armazenar os modelos de elementos</p>
 * 
 * Creado: 08/12/2003 - 21:37:47
 * Modificação Inicial para versão 2.0: 16/01/2004 - 19:31
 * @author Carlos Delfino
 */
public class Biblioteca extends Hashtable
{
	public static final String CAMINHO_COMPLETO_PARA_ELEMENTO = "gerenciadordebiblioteca.Elemento";

	public static final String CAMINHO_COMPLETO_PARA_TIPO_ELEMENTO = "gerenciadordebiblioteca.ModeloElemento";

	/**
	 * <p>Construtor usado quando a propria biblioteca<br>
	 * criará uma tabela para ser adicionada a biblioteca
	 * principal.</p>
	 * 
	 */
	private Biblioteca()
	{}

	/**
	 * <p>Construtor principal da biblioteca, recebe o nome do<br>
	 * arquivo em formato String, para recupera-lo no Sistema <br>
	 * de Arquivos da maquina, se for uma URL, pode pega-lo em um<br>
	 * site qualquer.</p>
	 * 
	 * <p>O arquivo então será usado para carregar o modelo
	 * de biblioteca, o paramentro deve ser passado <b>obrigatoriamente</b>
	 * usando a notação absoluta ou relativa ao diretorio
	 * que a aplicação foi iniciada. Este arquivo deve ser
	 * no formato XML.</p>
	 * 
	 * <p>Criação: 09/12/2003 - 19:54:14</p>
	  * Modificação Inicial para versão 2.0: 16/01/2004 - 19:31
	  * 
	 * @param p_arquivoXML - nome do arquivo que contem os dados formatado em XML.
	 */
	public Biblioteca(String p_arquivoXML)
	{
		// proforme para trabalhar com XML via DOM
		DocumentBuilderFactory l_oDocumentoXML = DocumentBuilderFactory.newInstance();
		try
		{
			// cria todos os modelos
			makeModelos(l_oDocumentoXML.newDocumentBuilder().parse(new File(p_arquivoXML)));
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Construtor usado quando já se possui o conteudo XML carregado em
	 * um Objeto Document.
	 * 
	 * @param p_DocumentXML - objeto do tipo Document
	 */
	public Biblioteca(Document p_DocumentXML)
	{
		makeModelos(p_DocumentXML);
	}

	/**
	 * Metodo criado para resumir o codigo, apenas agrupa a criação dos modelos.
	 * Ele chama os metodos privados responsaveis por gerar os Componentes, Elementos e
	 * Sub-Elementos.
	 * 
	 * Recebe como parametro o Objeto Document que possui a biblioteca em formato XML.
	 * 
	 * @param p_documentoXML
	 */
	private void makeModelos(Document p_documentoXML)
	{
		// Pega a lista de Modelos de Componentes de 
		// dentro do documento xml e coloca dentro da biblioteca
		putAll(getBibliotecaModelosComponente(p_documentoXML));

		//idem mas para Modelos de Elementos.
		putAll(getBibliotecaModelosElemento(p_documentoXML));

		// Neste ele baseado na lista de Modelos de 
		// Componentes atualiza a biblioteca de Elementos
		// adcionando seus subelementos.
		makeBibliotecaTipoSubElemento(p_documentoXML);

	}

	/**
	 * Pega os componentes definido dentro do Objeto Document
	 * passado em xml dentro do paramentro que é a representação
	 * das definiçoes dos componentes.
	 * 
	 * Retorna um Objeto Biblioteca com os componentes mapeados por
	 * seu idComp.
	 * 
	 */
	private Biblioteca getBibliotecaModelosComponente(Document xml)
	{

		// Cria uma biblioteca basica para ser usada como base.
		Biblioteca bibliotecaMComponentes = new Biblioteca();

		// Recupera apenas as tags que criam Componentes.
		NodeList componentesXML = xml.getElementsByTagName("Componente");

		// Conforme a quantidade de componentes definidos
		// associa ao objeto componente.
		for (int i = 0; i < componentesXML.getLength(); i++)
		{
			// pega uma tag XML especifica.
			Element componenteXML = (Element)componentesXML.item(i);

			// cria um Modelo de Componente padrão com as informações
			// existentes na Tag XML recuperada.
			ModeloComponente modeloComponente = ModeloComponente.make(componenteXML);

			// Adicionar tipo de componente à lista Hashtable
			bibliotecaMComponentes.put(modeloComponente.getIdModelo(), modeloComponente);
		}

		// retorna a Bibliotecabasica
		return bibliotecaMComponentes;
	}

	/**
	 * Identico ao metodo pegaListaModelosComponente, mas trabalha com 
	 * os Elementos.
	 * 
	 * @param xml
	 * @return Biblioteca - 
	 */
	private Biblioteca getBibliotecaModelosElemento(Document xml)
	{

		Biblioteca bibliotecaMElementos = new Biblioteca();
		NodeList elementosXML = xml.getElementsByTagName("Elemento");

		for (int i = 0; i < elementosXML.getLength(); i++)
		{
			Element elementoXML = (Element)elementosXML.item(i);

			// Instancia um Modelo de elemento
			ModeloElemento modeloElemento = null;
			try
			{
				modeloElemento = ModeloElemento.make(elementoXML);
			} catch (ClassNotFoundException e)
			{
				JOptionPane.showMessageDialog(
					null,
					"Verifique seu arquivo de biblioteca, pois o mesmo está indicando um tipo de Elemento não Existente",
					"Erro no Arquivo de Biblioteca",
					JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				System.exit(1);
			} catch (InstantiationException e)
			{
				JOptionPane.showMessageDialog(
					null,
					"Verifique Verifique o tipo no arquivo de biblioteca e veja se a classe está ok",
					"Erro na Classe Modelo",
					JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				System.exit(1);
			} catch (IllegalAccessException e)
			{
				JOptionPane.showMessageDialog(
					null,
					"Verifique Verifique o tipo no arquivo de biblioteca e veja se a classe está ok",
					"Erro na Classe Modelo",
					JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				System.exit(1);
			}

			// Adicionar tipo de componente à lista Hashtable
			bibliotecaMElementos.put(modeloElemento.getIdModelo(), modeloElemento);
		}
		bibliotecaMElementos.rehash();
		return bibliotecaMElementos;
	}

	/**
	 * Este Metodo, permite a biblioteca recuperar 
	 * os subElementos, isto é os componentes que
	 * compõem o Elementos em depterminadas posições.
	 * por exemplo sendo o elemento inicio de um canal, 
	 * o mesmo será um Subelemento de inicio de canal,
	 * sendo compostos por um numero x de componentes.
	 * 
	 * o mesmo recebe xml como um Objeto Document, que 
	 * é o arquivo XML que possui a montagem do elemento.
	 * 
	 * Este metodo é privado pois é usado somente pela 
	 * propria biblioteca quando ela é criada usando o 
	 * construtor que se utiliza do arquivo XML.
	 *  
	 * @param xml
	 */
	private void makeBibliotecaTipoSubElemento(Document xml)
	{

		// Coletar subcomponentes para o caso de início de canal
		NodeList l_subElementosXML = xml.getElementsByTagName("SubElemento");

		for (int j = 0; j < l_subElementosXML.getLength(); j++)
		{

			NodeList l_componentesXML = null;
			ModeloComponente l_ModeloComponente = null;
			ModeloElemento l_modeloElemento = null;

			// Pega a posição do subElemento
			String posicaoSubE = ((Element)l_subElementosXML.item(j)).getAttribute("posicao");

			// pega apenas os tipos de subelementos que forem 
			// de inicio do canal								
			if (posicaoSubE.equalsIgnoreCase(Elemento.INICIAL))
			{

				String idElemento = ((Element)l_subElementosXML.item(j)).getAttribute("idElemento");

				// Resgata o Tipo Elemento da Biblioteca
				l_modeloElemento = (ModeloElemento)get(idElemento);

				l_componentesXML =
					((Element)l_subElementosXML.item(j)).getElementsByTagName("Componentes");
				for (int i = 0; i < l_componentesXML.getLength(); i++)
				{

					String idComp = ((Element)l_componentesXML.item(i)).getAttribute("idComp");
					String idMas = ((Element)l_componentesXML.item(i)).getAttribute("idMas");

					ModeloComponente tmp = ((ModeloComponente)get(idComp));
					l_ModeloComponente = (ModeloComponente)tmp.copia((Element)l_componentesXML.item(i));

					l_ModeloComponente.setIdMas(idMas);
					l_modeloElemento.addMComponenteInicial(l_ModeloComponente);

				}
			}

			// Agora pega os tipos de subelementos que forem
			// de passagem do canal
			if (posicaoSubE.equalsIgnoreCase(Elemento.PASSAGEM))
			{
				String tipoElemento = ((Element)l_subElementosXML.item(j)).getAttribute("idElemento");

				// Resgata o Tipo Elemento da Biblioteca
				ModeloElemento tElem = (ModeloElemento)get(tipoElemento);

				l_componentesXML =
					((Element)l_subElementosXML.item(j)).getElementsByTagName("Componentes");

				for (int i = 0; i < l_componentesXML.getLength(); i++)
				{

					String idComp = ((Element)l_componentesXML.item(i)).getAttribute("idComp");
					String idMas = ((Element)l_componentesXML.item(i)).getAttribute("idMas");

					l_ModeloComponente = null;

					ModeloComponente tmp = ((ModeloComponente)get(idComp));
					l_ModeloComponente = (ModeloComponente)tmp.copia((Element)l_componentesXML.item(i));

					l_ModeloComponente.setIdMas(idMas);
					tElem.addMComponentePassagem(l_ModeloComponente);

				}
			}

			// e agora apenas os finais de canal.
			if (posicaoSubE.equalsIgnoreCase(Elemento.FINAL))
			{

				String tipoNo = ((Element)l_subElementosXML.item(j)).getAttribute("idElemento");

				// Resgata o Tipo Elemento da Biblioteca
				ModeloElemento tElem = (ModeloElemento)get(tipoNo);

				l_componentesXML =
					((Element)l_subElementosXML.item(j)).getElementsByTagName("Componentes");
				for (int i = 0; i < l_componentesXML.getLength(); i++)
				{

					String idComp = ((Element)l_componentesXML.item(i)).getAttribute("idComp");
					String idMas = ((Element)l_componentesXML.item(i)).getAttribute("idMas");

					l_ModeloComponente = null;

					ModeloComponente tmp = ((ModeloComponente)get(idComp));
					l_ModeloComponente = (ModeloComponente)tmp.copia((Element)l_componentesXML.item(i));

					l_ModeloComponente.setIdMas(idMas);
					tElem.addMComponenteFinal(l_ModeloComponente);

				}
			}
		}
	}

	public Modelos get(String id)
	{
		return (Modelos)super.get(id);
	}

	/**
	 * Metodo chamado automaticamente quando a Biblioteca é trado como String
	 * Ele é quem da a saida antiga do modo textual. 
	 */
	public String toString()
	{

		int numcomp = size();
		int count = 1;

		String print = "Foram definidos ao todo " + numcomp + " Hardwares de rede:\n";

		try
		{

			for (Enumeration idcomps = keys(); idcomps.hasMoreElements();)
			{

				Modelos modelo = get((String)idcomps.nextElement());
				print += "\t"
					+ count
					+ " --> "
					+ modelo.getIdModelo()
					+ ", nome: "
					+ modelo.getNomeModelo();

				if (modelo instanceof ModeloComponente)
				{
					ModeloComponente tComp = (ModeloComponente)modelo;
				}
				print += "\n";
				if (modelo instanceof ModeloElemento)
				{
					ModeloElemento tElem = (ModeloElemento)modelo;
					List compList = tElem.getListMComponentesIniciais();

					if (compList != null)
					{
						print += "\t   SubElemento inicio de canal composto por:\n";

						for (int i = 0; i < compList.size(); i++)
						{
							ModeloComponente tComp = (ModeloComponente)compList.get(i);

							print += "\t\t" + (i + 1) + " --> " + tComp.getIdModelo();
							print += ", nome: " + tComp.getNomeModelo();

							String idMas = tComp.getIdMas();
							if (idMas != null)
								print += ", Id usado pela Mas: " + idMas;

							print += "\n";

						}
					}
					compList = tElem.getListMComponentesPassagens();

					if (compList != null)
					{
						print += "\t   SubElemento de passagem de canal composto por:\n";
						for (int i = 0; i < compList.size(); i++)
						{
							ModeloComponente tComp = (ModeloComponente)compList.get(i);
							print += "\t\t" + (i + 1) + "--> " + tComp.getIdModelo();
							print += ", nome: " + tComp.getNomeModelo();

							String idMas = tComp.getIdMas();
							if (idMas != null)
								print += ", Id usado pela Mas: " + idMas;

							print += "\n";
						}
					}

					compList = tElem.getListMComponentesFinais();
					if (compList != null)
					{
						print += "\t   SubElemento final de canal composto por:\n";
						for (int i = 0; i < compList.size(); i++)
						{

							ModeloComponente tComp = (ModeloComponente)compList.get(i);
							print += "\t\t" + (i + 1) + "--> " + tComp.getIdModelo();
							print += ", nome: " + tComp.getNomeModelo();
							String idMas = tComp.getIdMas();
							if (idMas != null)
								print += ", Id usado pela Mas: " + idMas;

							print += "\n";
						}
					}
				}
				count++;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return print;
	}
	/**
	 * Este método, deve ser envocado quando se quer criar um
	 * novo Elemento de Rede, seja ele um Nó Físico da Rede
	 * ou um Link de conexão entre Nós (a Fibra).
	 * 
	 * Deve se tomar muito cuidado para não se confundir este 
	 * Elemento com o "Modelo de Elemento (MElemento)", utilizado
	 * para gerá-lo, já que MElemento é o modelo que será 
	 * copiado (Clonado) para dar vida ao Nó ou Link dentro da
	 * topologia da rede conforme os parâmetros passado para o 
	 * método.
	 * 
	 * Outra confusão muito comun é a coincidência de nomes entre
	 * este elemento e o Element da linguagem XML. O Element da 
	 * Linguagem XML é apenas uma tag que pode ter qualquer nome,
	 * quando a mesma tem o nome Elemento, ela está definindo um
	 * Modelo de Elemento.
	 * 
	 * @param elementoXML - Um Elemento XML definido no arquivo da topologia
	 * @return Elemento - Um Elemento Fisico da Rede.
	 */
	public Elemento novoElemento(Element elementoXML)
		throws ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		Elemento elemento = null;

		// Pega na biblioteca o modelo de Elemento
		// Especificado pelo atributo tipo
		ModeloElemento modelo = (ModeloElemento)get(elementoXML.getAttribute("idelemento"));
		elemento = (Elemento)modelo.makeElemento((Element)elementoXML);

		return elemento;
	}
	public DefaultTreeModel getArvoreDaBiblioteca()
	{
		DefaultMutableTreeNode l_dmtn = new DefaultMutableTreeNode("Biblioteca:");
		DefaultMutableTreeNode l_dmtnComponentes = new DefaultMutableTreeNode("Componentes:");
		DefaultMutableTreeNode l_dmtnElementos = new DefaultMutableTreeNode("Elementos:");

		l_dmtn.add(l_dmtnComponentes);
		l_dmtn.add(l_dmtnElementos);

		for (Enumeration idcomps = keys(); idcomps.hasMoreElements();)
		{

			Modelos modelo = get((String)idcomps.nextElement());
			// se for componente adiciona a lista de componentes
			if (modelo instanceof ModeloComponente)
			{
				ModeloComponente tComp = (ModeloComponente)modelo;

				DefaultMutableTreeNode l_dmtnComponente =
					new DefaultMutableTreeNode(
						modelo.getIdModelo() + ", nome: " + modelo.getNomeModelo());

				// adciona o componente a sua lista
				l_dmtnComponentes.add(l_dmtnComponente);
			}

			// Caso seja Elemento adciona a lista de Elementos.
			else if (modelo instanceof ModeloElemento)
			{
				ModeloElemento tElem = (ModeloElemento)modelo;

				DefaultMutableTreeNode l_dmtnElemento =
					new DefaultMutableTreeNode(
						modelo.getIdModelo() + ", nome: " + modelo.getNomeModelo());

				// adciona o Elemento a sua lista
				l_dmtnElementos.add(l_dmtnElemento);

				// pega a lista de componentes com compõem o elemento
				// como nno inicial
				List compList = tElem.getListMComponentesIniciais();

				if (compList != null)
				{
					DefaultMutableTreeNode l_dmtnElementoInicial =
						new DefaultMutableTreeNode("Sub-Elemento Inicial:");

					l_dmtnElemento.add(l_dmtnElementoInicial);

					for (int i = 0; i < compList.size(); i++)
					{
						ModeloComponente tComp = (ModeloComponente)compList.get(i);

						DefaultMutableTreeNode l_dmtnComponenteInicial =
							new DefaultMutableTreeNode(
								tComp.getIdModelo()
									+ ", nome: "
									+ tComp.getNomeModelo()
									+ ((tComp.getIdMas() != null) ? " Id: " + tComp.getIdMas() : ""));
						l_dmtnElementoInicial.add(l_dmtnComponenteInicial);
					}
				}

				compList = tElem.getListMComponentesPassagens();

				if (compList != null)
				{
					DefaultMutableTreeNode l_dmtnElementoPassagem =
						new DefaultMutableTreeNode("Sub-Elemento Passagem:");

					l_dmtnElemento.add(l_dmtnElementoPassagem);

					for (int i = 0; i < compList.size(); i++)
					{
						ModeloComponente tComp = (ModeloComponente)compList.get(i);
						DefaultMutableTreeNode l_dmtnComponentePassagem =
							new DefaultMutableTreeNode(
								tComp.getIdModelo()
									+ ", nome: "
									+ tComp.getNomeModelo()
									+ ((tComp.getIdMas() != null) ? " Id: " + tComp.getIdMas() : ""));
						l_dmtnElementoPassagem.add(l_dmtnComponentePassagem);
					}
				}

				compList = tElem.getListMComponentesFinais();
				if (compList != null)
				{
					DefaultMutableTreeNode l_dmtnElementoFinal =
						new DefaultMutableTreeNode("Sub-Elemento Final:");

					l_dmtnElemento.add(l_dmtnElementoFinal);
					for (int i = 0; i < compList.size(); i++)
					{

						ModeloComponente tComp = (ModeloComponente)compList.get(i);
						DefaultMutableTreeNode l_dmtnComponenteFinal =
							new DefaultMutableTreeNode(
								tComp.getIdModelo()
									+ ", nome: "
									+ tComp.getNomeModelo()
									+ ((tComp.getIdMas() != null) ? " Id: " + tComp.getIdMas() : ""));
						l_dmtnElementoFinal.add(l_dmtnComponenteFinal);
					}
				}
			}
		}

		return new DefaultTreeModel(l_dmtn);
	}
}
