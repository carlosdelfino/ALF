/*
 * ACA.java Criado em 21/02/2004
 *
 * Todo Codigo criado abaixo, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e não seja para fins lucra-
 * tivos capitalistas.
 * 
 */
package correlacaodealarmes;

import java.util.List;

import simuladordarede.Alarme;
import simuladordarede.Alarmes;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import geradorautomaticoderotas.Canais;

/**
 * @author Carlos Delfino
 * @email suporte@carlosdelfino.eti.br
 * @site http://www.carlosdelfino.eti.br
 * 
 * Digite aqui o que esta classe faz.
 * Nome: 
 */
public class AlgoritimoDeCorrelacaoDeAlarmes
{

	// Alarmes que serão trabalhados
	private Alarmes alarmes;

	/*
	 * Canais recem recebidos pelo ACA, pouco utilizados.
	 */
	private Canais canais = new Canais();

	/*
	 * Variavel que contem todos os canais que estão sendo usados
	 * no ACA aqui será armazenado os canais antigos por um tempo 
	 * limite a ser definido, o default é 5 minutos.
	 * 
	 */
	private Canais canaisACA = new Canais();

	private EscalonadorDeCandidados edc;

	private ElimanadorDeRedundancia edr;

	private GeradorAutomaticoDeDominio gad;

	private PesquisadorDeCandidados pdc;

	private SelecionadorDeAlarmes sda;

	/**
	 * O nosso ACA equivale ao AFA produzido pela Mas
	 * No AFA existem como entrada os canais C = {cj}, os 
	 * alarmes recebidos R = {ai}
	 * 
	 * @param topologia
	 * @param canais
	 * @param alarmes
	 */
	public AlgoritimoDeCorrelacaoDeAlarmes(List p_canais, Alarmes p_alarmes)
	{

		setCanais(p_canais);

		setAlarmes(p_alarmes);

		inicializar();

	}
	/**
	 * @param p_document
	 * @param p_document2
	 */
	public AlgoritimoDeCorrelacaoDeAlarmes(List p_canais, Document p_alarmesXML)
	{
		setCanais(p_canais);
		setAlarmes(p_alarmesXML);

		inicializar();
	}
	/**
	 * @param p_alarmesXML
	 */
	private void setAlarmes(Document p_alarmesXML)
	{
		if (alarmes == null) alarmes = new Alarmes();
		
		NodeList l_alarmesXML = p_alarmesXML.getElementsByTagName("Alarme");
		
		int l_numeroAlarmes = l_alarmesXML.getLength();
		
		for (int i = 0; i < l_numeroAlarmes; i++)
		{
			Element l_elemento = (Element)l_alarmesXML.item(i);
			Alarme l_alarme = new Alarme(l_elemento);
			alarmes.add(l_alarme);
		}

	}
	private void inicializar()
	{
		//DEBUG Algoritimo de Correlação de Alarmes
		System.out.println("\n*********************************");
		System.out.println("** Todos os alarmes recebidos:  ");
		System.out.println(alarmes);

		System.out.println("** Eliminando Alarmes invalidos e Classificando-os (Modulo 2 - Alarming Discarting_1)! ");
		sda = new SelecionadorDeAlarmes(canaisACA, alarmes);

		System.out.println("\n************************************");
		System.out.println("** Alarmes de Componentes A2 (saida T do Modulo 2 - Alarming Discarting_1): ");
		System.out.println(sda.getAlarmesValidosClasseA2());
		System.out.println(
			"** Alarmes de Componentes A1 e A3, (saida PC1 do Modulo 2 - Alarming Discarting_1): ");
		System.out.println(sda.getAlarmesValidosClasseA1eA3());

		System.out.println("** Eliminando Alarmes A2 Redundantes (Modulo 1 - Alarming Discarting_2)! ");
		edr = new ElimanadorDeRedundancia(canais, sda.getAlarmesValidosClasseA2());
		System.out.println("Alarmes A2 Restantes, não redundantes (saida T do Modulo 2 - Alarming Discarting_2)! ");
		System.out.println(edr.getAlarmesNaoRedundantes());

		/*
		 * 		Removi as linhas abaixo pois percebi que é mais indicado
		 *		para o sistema que não trabalhe com os possiveis candidatos 
		 *      de forma separada, mas sim com a junção dos alarmes se descubra o responsaveis.
		 */
		//		System.out.println("** Buscando Componentes Candidatos! ");
		//		pdc = new PesquisadorDeCandidados(canais, edr.getAlarmesNaoRedundantes());
		//		System.out.println("** Componentes Candidatos 2");
		//		System.out.println(pdc.getComponentesCandidatos());

		System.out.println("** Gerando Dominio de Falhas (Modulo 5 - (Domain_Calc)! ");
		GeradorAutomaticoDeDominio gad = new GeradorAutomaticoDeDominio(canais);

		System.out.println("** Selecionando Componentes Candidatos");
		edc =
			new EscalonadorDeCandidados(
				canais,
				gad,
				edr.getAlarmesNaoRedundantes(),
				sda.getAlarmesValidosClasseA1eA3());
		System.out.println(edc.getComponentesFalhos());
	}

	/**
	 * @param p_alarmes
	 */
	private void setAlarmes(Alarmes p_alarmes)
	{
		this.alarmes = p_alarmes;

	}

	/**
	 * Uma vez que recebo uma nova lista de canais, automaticamente
	 * a lista antiga do AFA é acrecida desta nova lista recebida, 
	 * atualizando assim a referencia da lista recebida.
	 * 
	 * @param p_canais
	 */
	private void setCanais(List p_canais)
	{
		this.canais.addAll(p_canais);

		/*
		 * O uso do clone em p_canais se deve ao meu interesse
		 * somente nas listas de canais, que conterá por sua
		 * vez a lista de componentes.
		 * 
		 * Antes eu usava o clone, mas estudando o mecanismode copia de addAll, 
		 * percebi que faz exatamente o que eu pretendia.
		 */
		this.canaisACA.addAll(p_canais);
		/*
		 * aqui é bom que ele cadastrar este objeto como um
		 * interessado em saber que existem novos canais na estrutura
		 * o objeto da Classe Canais tem os metodos para 
		 * alertar ao AFA sobre isto!Ele se equivale ao Rec_channel da Mas
		 */
	}

	/**
	 * @param p_gad
	 */
	private void setGeradorAutomaticoDeDominios(GeradorAutomaticoDeDominio p_gad)
	{
		this.gad = p_gad;

	}
	/**
	 * @return
	 */
	public GeradorAutomaticoDeDominio getDominio()
	{
		if (gad == null){
			gad = new GeradorAutomaticoDeDominio(getCanais());
		}
		return gad;
	}
	/**
	 * 
	 */
	private Canais getCanais()
	{
		return canais;
		
	}
}
