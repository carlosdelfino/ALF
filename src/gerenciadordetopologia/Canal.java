/*
 * Created on 12/12/2003
 * 
 * Todo Codigo criado por min, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e não seja para fins lucra-
 * tivos capitalistas.
 * 
 */
package gerenciadordetopologia;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import gerenciadordetopologia.erros.ExceptionCanalDesatualizado;
import gerenciadordebiblioteca.Componente;
import gerenciadordebiblioteca.Elemento;
import gerenciadordebiblioteca.ElementoLink;
import gerenciadordebiblioteca.ID;
import gerenciadordebiblioteca.IdMas;

/**
 * o Canal nada mais é do que um ArrayList de Nos e Links.
 * temos todos os metodos normais de um array, e mais os
 * metodos que são relacionados com os Canais como um seguencia
 * de Elementos, uma Seguencia de Nos, uma Seguencia de Componentes.
 * 
 * Criação: 12/12/2003 - 06:51:28
 * @author Carlos Delfino
 * 
 */
public class Canal extends ArrayList
{
	/****************************************
	 * Constantes
	 * 
	/****************************************/

	// Constantes para direção
	public static final String CW = "cw";
	public static final String CCW = "ccw";

	// Indica se o Canal precisa ser atualizado ou não
	private boolean atualizado = true;

	/****************************************
	 * Caracteristicas e dados temporarios da 
	 * Classe
	 * 
	 ****************************************/

	// esta variavel irá conterr o comprimento de onda que o canal
	// como destino, isto é o que ele usará para chegar ao seu destino
	// em certas situações pode ser igual ao comprimentoDeOndaAtual;
	private String comprimentoDeOnda;

	// guarda o comprimento de onda 
	// no qual o canal esta trabalhando no exato momento
	private String comprimentoDeOndaAtual;

	// Identificador do Canal
	private String id;

	// Array com todos os IDs da Mas de cada Componente
	// que compõem o canal, como cada vez que preciso resgatar esta
	// lista se demanda uma grande quantidade de processamento
	// achei que seria uma boa ideia, depois do primeiro resgate
	// guarda-lo aqui.
	private ArrayList idMasNaRotaFisica = new ArrayList();

	// este campo irá guardar o sentido em que está usando a topologia.
	private Map mapaDeSentidos;

	// Nome de Identificação do Canal
	private String nome;

	// Idem ao anterior, mas para os componentes
	private RotaFisica rotaFisica = new RotaFisica();

	/**
	 * Esplique aqui porque usar este construtor, e
	 * sua diferença em relação aos outros.
	 * 
	 * Criação: 12/12/2003 - 09:53:35
	 * 
	 * @param p_CanalXML
	 * @param p_aTopologia
	 */
	public Canal(Element p_CanalXML, Topologia p_aTopologia)
	{
		isAtualizado(false);

		// pega os elementos no XML
		NodeList l_aListaNos = p_CanalXML.getElementsByTagName("Elementos");
		int l_NumElementos = l_aListaNos.getLength();

		// Cria o Mapa de tamanho igual ao numero de elementos
		mapaDeSentidos = new Hashtable(l_NumElementos);

		// setá os dados do canal
		setID(p_CanalXML.getAttribute("id"));
		setNome(p_CanalXML.getAttribute("nome"));

		// prepara ambiente para verificar sentido do canal.
		String l_idNoAnterior = null;
		String l_idNo = null;
		String l_sentido = Canal.CW;

		for (int i = 0; i < l_NumElementos; i++)
		{
			Element noXML = (Element)l_aListaNos.item(i);
			l_idNo = noXML.getAttribute("idElemento");
			l_sentido = Componente.CW;

			if ((l_idNoAnterior != null) && (i < l_NumElementos))
			{
				//String l_idLink =
				//	Elemento.PREFIXO_LINK + l_idNoAnterior.substring(1, 3) + l_idNo.substring(1, 3);

				//ElementoLink l_link = p_aTopologia.getLink(l_idLink);
				ElementoLink l_link = p_aTopologia.getLink(l_idNoAnterior, l_idNo);

				// se o link não for encontrado, é porque ele
				// esta criando o canal em sentido anti-horário
				// portanto o sentido deve ser definido como 
				// CCW.
				if (l_link == null)
				{
					l_link = p_aTopologia.getLinkCCW(l_idNoAnterior, l_idNo);
					l_sentido = Componente.CCW;
				}

				mapaDeSentidos.put(l_idNoAnterior, l_sentido);
				mapaDeSentidos.put(l_link.getId(), l_sentido);
				add(l_link);
			}

			add(p_aTopologia.getElemento(l_idNo));

			l_idNoAnterior = l_idNo;
		}
		mapaDeSentidos.put(l_idNo, l_sentido);

		// Chama o Coletor de Lixo para limpar o meio de campo!
		System.gc();

		// Faz uma limpeza no Array de Canais, tirando espaços não usados.
		trimToSize();

		preparaOsComprimentosDeOndas();

		criaRotaFisica();
		makeListIdMasComponentes();
		isAtualizado(true);
	}

	/**
	 * @param p_idMasCA
	 * @return
	 */
	public boolean contains(Componente p_componente)
	{
		return getRotaFisica().contains(p_componente);
	}

	/**
	 * @param p_mas
	 * @return
	 */
	public boolean contains(IdMas p_idMas)
	{
		return rotaFisica.contains(p_idMas);
	}

	private synchronized void criaRotaFisica()
	{
		rotaFisica.clear();
		for (int j = 0; j < size(); j++)
		{
			Elemento l_oElemento = (Elemento)get(j);

			if (j == 0)
				rotaFisica.addAll(l_oElemento.getComponentesIniciais(this));
			else if (j == size() - 1)
				rotaFisica.addAll(l_oElemento.getComponentesFinais(this));
			else
				rotaFisica.addAll(l_oElemento.getComponentesPassagem(this));
		}
	}

	/**
	 * @param p_idCA
	 * @return
	 */
	public Componente get(IdMas p_idMasCA)
	{
		for (Iterator iter = idMasNaRotaFisica.iterator(); iter.hasNext();)
		{
			IdMas l_idMas = (IdMas)iter.next();
			if (l_idMas.equals(p_idMasCA)){
				int l_indice = rotaFisica.indexOf(l_idMas);
				return (Componente)rotaFisica.get(l_indice);
			}
		}
		return null;
	}

	/**
	 * @param l_indice
	 */
	public Componente getComponente(int p_indice)
	{
		return (Componente)rotaFisica.get(p_indice);
	}

	/**
	 * @return
	 */
	public String getComprimentoDeOnda()
	{
		return comprimentoDeOnda;
	}

	/**
	 * @return
	 */
	public String getComprimentoDeOndaAtual()
	{

		return comprimentoDeOndaAtual;
	}

	/**
	 * @return
	 */
	public String getID()
	{
		return this.id;
	}
	
	/**
	 * Retorna um List (Lista) de Id´s da Mas em Formato String
	 * 
	 * @return Um list de strings das Id´s da Mas
	 */
	public List getListIdMasComponentes() throws ExceptionCanalDesatualizado
	{
		if (idMasNaRotaFisica.isEmpty())
		{
			makeListIdMasComponentes();
		}

		return idMasNaRotaFisica;
	}

	/**
	 * @return
	 */
	public String getNome()
	{
		return this.nome;
	}

	/**
	 * @return
	 */
	public Elemento getPrimeiroElemento()
	{
		return (Elemento)get(0);
	}
	
	/**
	 * @return
	 */
	public RotaFisica getRotaFisica()
	{
		if (rotaFisica.isEmpty())
		{
			criaRotaFisica();
		}

		return rotaFisica;
	}

	/**
	 * @param p_oElemento
	 */
	public String getSentidoElemento(Elemento p_oElemento)
	{
		return getSentidoElemento(p_oElemento.getId());

	}
	/**
	 * @param p_idNo
	 * @return 
	 */
	public String getSentidoElemento(String p_idNo)
	{
		return (String)mapaDeSentidos.get(p_idNo);
	}

	/**
	 * @return
	 */
	public Elemento getUltimoElemento()
	{
		return (Elemento)get(size() - 1);
	}

	/**
	 * 
	 * @param p_idMas
	 * @return
	 */
	public int indexOf(IdMas p_idMas)
	{
		// Contador do Indice para encontrar o IdMas na Lista
		int count = -1;

		for (Iterator iter = idMasNaRotaFisica.iterator(); iter.hasNext();)
		{
			count++;
			IdMas l_idMas = (IdMas)iter.next();
			if (l_idMas.equals(p_idMas))
				return count;
		}
		return -1;
	}

	/**
	 * 
	 * @param p_b
	 * @return
	 */
	public boolean isAtualizado(boolean p_b)
	{
		atualizado = p_b;
		return atualizado;

	}

	/**
	 * Procura o caminho passivo conforme é apresentado no paper:
	 *   Fault location at the WDM layer
	 *   Carmen Mas (carmen.mas@epfl.ch), Patrick Thiran (patrick.thiran@epfl.ch)
	 *   and Jean-Yves Le Boudec (leboudec@epfl.ch)
	 *
	 *   Pagina 10 - seção 3.2. Functions
	 * 
	 * Conforme o enunciado, Componente1 tem um Caminho Passivo (PassivePath)
	 * com componente 2 quando ambos estão em um canal simultaneamente, talque
	 * componentes entre estes pertenção as Classes A1 e P de compoentnes.
	 * 
	 * Neste algoritimo assinado pelo metodo:
	 *						boolean isCaminhoPassivo(componente1, componente2)
	 * primeiro ele verifica se o componete 2 está logo em seguida ao componente 1
	 * o que poupa bastante processamento, isto é feito fazendo uma comparação 
	 * entre os Ids dos compoentes, uma vez que estes ID´s tem informações suficiente
	 * para indicar qual componete está a frente do outro, no canal, assim usei o metodo
	 * compareTo do componente 1, este irá retorna -1 caso o componente 1 esteja 
	 * antes do componente 2.
	 * 
	 * caso não estejam um apos o outro, isto é temos componentes entre os dois
	 * procuramos entre os dois um elemento maskAlarm, caso exista é porque o 
	 * caminho não é passivo.
	 * 
	 * @param p_componente1
	 * @param p_componente2
	 * @return - retorna verdadeiro se caminho é passivo, falso caso contrario ou se os dois componentes não estão no mesmo canal.
	 */
	public boolean isCaminhoPassivo(ID p_componente1, ID p_componente2)
	{

		// se componente2 está logo em seguida ao
		// componente1, é caminho passsivo
		if (p_componente1.compareTo(p_componente2) == -1)
			return true;

		// caso contrario, procura para ver se entre os dois existe
		// um componente MaskAlarm, caso não exista remove
		// o alarme do componente 2;

		else
		{
			if (indexOf(p_componente1) > -1 && indexOf(p_componente2) > -1)
			{
				IdMas componente3 = null;
				boolean caminhopassivo = true;

				// pega uma sublista de componentes apartir do componente1
				int l_indiceComponente1 = indexOf(p_componente1);
				int l_indiceComponente2 = indexOf(p_componente2);
				Iterator l_iterComponentes = getRotaFisica().listIterator(l_indiceComponente1 + 1);

				// testa esta lista até chegar ao componente 2
				while (l_iterComponentes.hasNext()
					&& ((componente3 = ((Componente)l_iterComponentes.next()).getIdMas())
						!= p_componente2))
				{

					// se um dos componentes entre componente 1 e componente2
					// for maskAlarme, então o caminho não é passivo!
					if (componente3.isMaskAlarm())
					{
						//caminhopassivo = false;
						return false;
					}
				}

				//if (caminhopassivo)
				//	return true;
			}

		}
		return false;
	}

	/**
	 * Cria a lista de IdMas dos Componentes, caso ela já exista ele 
	 * zera a mesma e cria novamente.
	 * Isto é util caso o canal tenha sido atulizado por qualquer motivo.
	 * Este metodo obrigatoriamente deve ser chamado em caso de alteração do canal.
	 *
	 */
	private synchronized void makeListIdMasComponentes()
	{
		if (rotaFisica.isEmpty())
		{
			criaRotaFisica();
		}

		RotaFisica l_componentes = getRotaFisica();
		for (Iterator iter = l_componentes.iterator(); iter.hasNext();)
		{
			Componente l_componente = (Componente)iter.next();

			idMasNaRotaFisica.add(l_componente.getIdMas());
		}

	}

	/**
	 * 
	 */
	private void preparaOsComprimentosDeOndas()
	{
		// Pega o primeiro Elemento
		Elemento l_primeiroElemento = (Elemento)this.get(0);
		Elemento l_ultimoElemento = (Elemento)this.get(this.size() - 1);

		setComprimentoDeOndaAtual(l_primeiroElemento);
		setComprimentoDeOnda(l_ultimoElemento);

	}

	/**
	 * @param l_ultimoElemento
	 */
	private void setComprimentoDeOnda(Elemento p_ultimoElemento)
	{
		comprimentoDeOnda = p_ultimoElemento.getId().substring(1);
	}

	/**
	 * @param l_primeiroElemento
	 */
	private void setComprimentoDeOndaAtual(Elemento p_primeiroElemento)
	{
		comprimentoDeOndaAtual = p_primeiroElemento.getId().substring(1);
	}

	/**
	 * @param l_comprimentoOnda
	 */
	public void setComprimentoDeOndaAtual(String p_comprimentoOnda)
	{
		comprimentoDeOndaAtual = p_comprimentoOnda;

	}

	/**
	 * 
	 * @param id
	 */
	private void setID(String id)
	{
		this.id = id;

	}

	/**
	 * 
	 * @param nome
	 */
	private void setNome(String nome)
	{
		this.nome = nome;

	}
	
	/**
	 * 
	 * @return
	 */
	public int sizeComponentes()
	{
		return rotaFisica.size();

	}
	/**
	 * 
	 */
	public String toString()
	{
		return getID() + " - " + getNome();
	}
}
