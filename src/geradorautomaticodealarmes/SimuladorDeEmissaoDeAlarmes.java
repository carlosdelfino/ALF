/*
 * Simulador.java Criado em 24/01/2004
 *
 * Todo Codigo criado abaixo, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e não seja para fins lucra-
 * tivos capitalistas.
 * 
 */
package geradorautomaticodealarmes;

import geradorautomaticodealarmes.erros.ExceptionCanaisNaoDefinidos;
import geradorautomaticodealarmes.erros.ExceptionComponenteNaoAtivo;
import geradorautomaticodealarmes.erros.ExceptionGADNaoDefinido;
import geradorautomaticodealarmes.erros.ExceptionMaximoMenorMinimo;
import geradorautomaticodealarmes.erros.ExceptionTopologiaNaoDefinida;

import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Random;
import java.util.RandomAccess;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import gerenciadordetopologia.Canais;
import gerenciadordetopologia.Canal;
import gerenciadordetopologia.Topologia;
import gerenciadordebiblioteca.Componente;
import gerenciadordebiblioteca.ID;
import gerenciadordebiblioteca.IdMas;

/**
 * @author Carlos Delfino
 * email suporte@carlosdelfino.eti.br
 * site http://www.carlosdelfino.eti.br
 * 
 * Simula a emissão de falhas pelos componentes, nesta versão ela 
 * recebe a topologia e os Canais, quando o método gerarFalhas()
 * é chamado retorna aleatoriamente mensagens de falhas na rede.
 * Serão criados outros metodos onde pode se definir quem vai falhar
 * e como vão falhar!
 */
public class SimuladorDeEmissaoDeAlarmes
{

	/**
	 * 
	 */
	public SimuladorDeEmissaoDeAlarmes()
	{
		
		// TODO Stub de construtor gerado automaticamente
	}
	private int maxFalhasPerdidas;

	private int minFalhasPerdidas;

	/**
	 * 
	 */
	private Random embaralhador = new Random();

//	private GeradorAutomaticoDeDominio gad;

	// numero maximo de falhas que serão inseridas alem da solicitada
	private int maxFalhasExtras = 0;

	// numero minimo de falhas que serão inseridas alem da solicitada
	private int minFalhasExtras = 0;

	private Random random = new Random();

	// Coleção de Falhas geradas
	private Alarmes alarmes = new Alarmes();

	private Topologia topologia;
	private Canais canais;

	/**
	 * Inicializa o Simulador com os Canais que podem ser usados
	 * para simular um conjunto de falhas.
	 * Quando se solicitar ao simulador uma lista de falhas, ele
	 * consultara os canais existentes e gerará uma sequencia 
	 * aletoria de alarmes
	 * 
	 * @param topologia
	 * @param canais
	 */
	public SimuladorDeEmissaoDeAlarmes(Topologia p_topologia)
	{
		setTopologia(p_topologia);
		setCanais(p_topologia.getCanais());
	}
	/**
	 * @param p_canais
	 */
	public void setTopologia(Topologia p_topologia)
	{
		topologia = p_topologia;
		setCanais(p_topologia.getCanais());
	}

	/**
	 * Ativa os canais que serã usados neste Simulador
	 * 
	 * @param p_canais
	 */
	private void setCanais(Canais p_canais)
	{
		canais = p_canais;
	}
	/**
	 * @param l_colecaoIdMas
	 * @return
	 */
	public Alarmes getNovosAlames(Collection p_colecaoIdMas)
		throws
			ExceptionComponenteNaoAtivo,
			ExceptionCanaisNaoDefinidos,
			ExceptionTopologiaNaoDefinida,
			ExceptionGADNaoDefinido
	{
		if (canais == null)
			throw new ExceptionCanaisNaoDefinidos();
		if (topologia == null)
			throw new ExceptionTopologiaNaoDefinida();

		makeNovosAlames(p_colecaoIdMas);
		return alarmes;
	}
	/**
	 * 
	 * @param p_idMas
	 * @return
	 * @throws ExceptionTopologiaNaoDefinida
	 * @throws ExceptionCanaisNaoDefinidos
	 * @throws ExceptionComponenteNaoAtivo
	 */
	public Alarmes getNovosAlames(IdMas p_idMas)
		throws
			ExceptionTopologiaNaoDefinida,
			ExceptionCanaisNaoDefinidos,
			ExceptionComponenteNaoAtivo,
			ExceptionGADNaoDefinido
	{
		if (canais == null)
			throw new ExceptionCanaisNaoDefinidos();
		if (topologia == null)
			throw new ExceptionTopologiaNaoDefinida();

		makeNovosAlames(p_idMas);

		return alarmes;
	}

	/**
	 * Zera todas as falhas  e cria novas!
	 * este metodo usa a Classe Random para evitar repitções nas falhas.
	 * 
	 * @param p_idMas
	 * @throws ExceptionComponenteNaoAtivo
	 */
	private synchronized void makeNovosAlames(IdMas p_idMas) throws ExceptionComponenteNaoAtivo
	{

		// o metodo firstIndexOf de Canais, retorna uma matriz de inteiros, cujo o primeiro
		// numero é o indice do canal, e o segundo numero é o indice do elemento encontrado
		// neste IF ele verifica somente se encontrou o elemento dentro de qualquer canal.
		if (canais.firstIndexOf(p_idMas)[1] < 0)
		{
			throw new ExceptionComponenteNaoAtivo("O componente Fornecido Não está em um Canal");
		}

		alarmes.clear();

		makeAlarmes(p_idMas);

		makeAlarmesFalsos();

		makePerdaDeAlarmes();

	}
	/**
	 * @param p_colecaoIdMas
	 */
	public synchronized void makeNovosAlames(Collection p_colecaoIdMas)
		throws ExceptionComponenteNaoAtivo, ExceptionTopologiaNaoDefinida, ExceptionCanaisNaoDefinidos
	{
		if (canais == null)
			throw new ExceptionCanaisNaoDefinidos();
		if (topologia == null)
			throw new ExceptionTopologiaNaoDefinida();

		alarmes.clear();

		for (Iterator iter = p_colecaoIdMas.iterator(); iter.hasNext();)
		{
			IdMas l_idMas = (IdMas)iter.next();
			int index[] = canais.firstIndexOf(l_idMas);
			if (index[1] < 0)
			{
				throw new ExceptionComponenteNaoAtivo(
					"O componente " + l_idMas + "Fornecido Não está em um Canal");
			}

			makeAlarmes(l_idMas);
		}

		makePerdaDeAlarmes();

		makeAlarmesFalsos();

	}

	/**
	 * 
	 */
	private void makePerdaDeAlarmes()
	{
		// TODO Stub de método gerado automaticamente
	}
	/**
	 * 
	 */
	private void makeAlarmesFalsos()
	{
		// Quantidade de alarmes falsos gerados neste metodo
		// este valor será setado conforme a quantidade
		// Maxima e Minima definida para Simulador
		int l_QtdAlarmes;

		if (maxFalhasExtras > 0  && maxFalhasExtras > minFalhasExtras)
			// enquanto o numero ramdomico estiver abaixo do minimo
			// ele irá regerar.
			// o maximo está limitado como argumento 
			// do metodo nextInt do objeto random.		
			do
			{
				l_QtdAlarmes = random.nextInt(maxFalhasExtras);
			} while (l_QtdAlarmes < minFalhasExtras);
		else if (maxFalhasExtras == minFalhasExtras)
			//Se minimo igual a maximo ele usa o valor de maximo para quantidade
			l_QtdAlarmes= maxFalhasExtras;
		else
			// se eu tiver o maximo de falhas definido como 0,
			// ele não usa o objeto random
			l_QtdAlarmes = 0;

		// gera os alarmes falsos
		for (int i = 0; i < l_QtdAlarmes; i++)
		{
			// pega um canal qualquer que fornecerá o elemento
			int l_indice = random.nextInt(canais.size());
			Canal l_canal = (Canal)canais.get(l_indice);

			// Pega um componente qualquer neste canal
			l_indice = random.nextInt(l_canal.sizeComponentes());
			Componente l_componente = l_canal.getComponente(l_indice);

			// solicita ao componente que gere o alarme
			Alarme l_alarme = l_componente.emitirAlarme();

			// verifica se o alarme foi gerado, já que, se o componente
			// for passivo este irá retornar nullo
			if (l_alarme == null)
			{
				i--;
				continue;
			}
			alarmes.add(l_alarme);
		}
	}
	/**
	 * 
	 * @param p_idMas
	 */
	private synchronized void makeAlarmes(IdMas p_idMas)
	{
		// Pede ao gerador de Dominio de Alarmes para fornecer os alarmes
		// Desde componente
		Set l_alarmes = new TreeSet();

		for (Iterator l_iterCanais = canais.iterator(); l_iterCanais.hasNext();)
		{
			Canal l_canal = (Canal)l_iterCanais.next();

			if (l_canal.contains(p_idMas))
			{
				for (Iterator iter = l_canal.getRotaFisica().listIterator(l_canal.indexOf(p_idMas));
					iter.hasNext();
					)
				{
					Componente l_componente = (Componente)iter.next();
					Alarme l_alarme = l_componente.emitirAlarme(p_idMas);
					if (l_alarme != null)
						l_alarmes.add(l_alarme);
					if (l_componente.isMaskAlarm())
						break;
				}
			}

		}
		alarmes.addAll(l_alarmes);

		// Torna a ordem dos alarmes aleatoria
		embaralhar(alarmes);
	}

	/**
	 * @param alarmes
	 */
	private void embaralhar(Alarmes p_alarmes)
	{
		int size = p_alarmes.size();
		if (size < 5 || p_alarmes instanceof RandomAccess) {
			 for (int i=size; i>1; i--)
				  embaralhar(p_alarmes, i-1, embaralhador.nextInt(i));
		} else {
			 Alarme arr[] = p_alarmes.toArray();

			 // Shuffle array
			 for (int i=size; i>1; i--)
				  embaralhar(arr, i-1, embaralhador.nextInt(i));

			 // Dump array back into list
			 ListIterator it = p_alarmes.listIterator();
			 for (int i=0; i<arr.length; i++) {
				  it.next();
				  it.set(arr[i]);
			 }
		}
   	}
	private static void embaralhar(Object[] arr, int i, int j) {
		 Object tmp = arr[i];
		 arr[i] = arr[j];
		 arr[j] = tmp;
	}

	/**
	 * @param p_alarmes
	 * @param p_i
	 * @param p_j
	 */
	private void embaralhar(Alarmes p_alarmes, int p_i, int p_j)
	{
		p_alarmes.set(p_i, p_alarmes.set(p_j, p_alarmes.get(p_i)));
	}
	/**
	 * @return
	 */
	public Canais getCanais()
	{
		return canais;
	}

	/**
	 * @return
	 */
	private Alarmes getFalhas()
	{
		return alarmes;
	}

	/**
	 * @return
	 */
	public int getMaxFalhas()
	{
		return maxFalhasExtras;
	}

	/**
	 * @return
	 */
	public int getMinFalhas()
	{
		return minFalhasExtras;
	}

	/**
	 * @return
	 */
	private Random getRandom()
	{
		return random;
	}

	/**
	 * @return
	 */
	public Topologia getTopologia()
	{
		return topologia;
	}

	/**
	 * @param p_i
	 */
	public void setMaxFalhasExtras(int p_i) throws ExceptionMaximoMenorMinimo
	{
		if (minFalhasExtras > p_i)
			throw new ExceptionMaximoMenorMinimo(p_i);
		maxFalhasExtras = p_i;
	}

	/**
	 * @param p_i
	 */
	public void setMinFalhasExtras(int p_i)
	{
		minFalhasExtras = p_i;
		maxFalhasExtras = maxFalhasExtras < minFalhasExtras ? minFalhasExtras : maxFalhasExtras;
	}

	/**
	 * @param p_random
	 */
	public void setRandom(Random p_random)
	{
		random = p_random;
	}
	/**
	 * @return
	 */
	public Alarmes getAlames()
	{
		return alarmes;
	}
	/**
	 * 
	 */
	public Document getAlarmesXML() throws ParserConfigurationException
	{
		Document l_document =
			DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			
		Element l_elementoRaiz = l_document.createElement("Alarmes");
		l_elementoRaiz.setAttribute("numeroAlarmes",String.valueOf(alarmes.size()));
		
		l_document.appendChild(l_elementoRaiz);
		
		for (Iterator l_iteratorAlarmes = alarmes.iterator(); l_iteratorAlarmes.hasNext();)
		{
			Alarme l_alarme = (Alarme)l_iteratorAlarmes.next();
			
			Element l_alarmeXML = l_document.createElement("Alarme");
			
			l_alarmeXML.setAttribute("nomeOrigem",l_alarme.getNomeOrigem());
			l_alarmeXML.setAttribute("idMasOrigem",l_alarme.getIdMasOrigem().toString());
			l_alarmeXML.setAttribute("nivel",String.valueOf(l_alarme.getNivel()));
			
			l_elementoRaiz.appendChild(l_alarmeXML);
			
		}

		return l_document;
	}
	/**
	 * @param p_string
	 */
	public void setMaxFalhasExtras(String p_string) throws NumberFormatException, ExceptionMaximoMenorMinimo
	{
		setMaxFalhasExtras(Integer.parseInt(p_string));
		
	}
	/**
	 * @param p_string
	 */
	public void setMinFalhasExtras(String p_string)
	{
		setMinFalhasExtras(Integer.parseInt(p_string));
	}
	/**
	 * @param p_string
	 */
	public void setMaxFalhasPerdidas(String p_string)
	{
		setMaxFalhasPerdidas(Integer.parseInt(p_string));
		
	}
	/**
	 * @param p_i
	 */
	private void setMaxFalhasPerdidas(int p_i)
	{
		maxFalhasPerdidas = p_i;
		
	}
	/**
	 * @param p_string
	 */
	public void setMinFalhasPerdidas(String p_string)
	{
		setMinFalhasPerdidas(Integer.parseInt(p_string));
		
	}
	/**
	 * @param p_i
	 */
	private void setMinFalhasPerdidas(int p_i)
	{
		minFalhasPerdidas = p_i;
		
	}
	/**
	 * @param p_string
	 * @param p_string2
	 * @return
	 */
	public Object addAlarme(String p_idComponenteAlarmante, String p_nivelAlarme)
	{
		Alarme l_alarme = new Alarme(ID.criaNovoID(p_idComponenteAlarmante));
		
		return l_alarme;
	}
	/**
	 * @param p_string
	 * @param p_string2
	 * @return
	 */
	public Object removeAlarme(String p_string, String p_string2)
	{
		// TODO Stub de método gerado automaticamente
		return null;
	}
}
