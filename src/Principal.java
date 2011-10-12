/*
 * Created on 09/12/2003
 * 
 * Todo Codigo criado por min, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e não seja para fins lucra-
 * tivos capitalistas.
 * 
 */

import geradorautomaticoderotas.Topologia;

import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

import javax.xml.parsers.ParserConfigurationException;

import simuladordarede.Alarmes;
import simuladordarede.SimuladorDeEmissaoDeAlarmes;
import simuladordarede.erros.ExceptionMaximoMenorMinimo;
import biblioteca.Biblioteca;
import biblioteca.IdMas;
import biblioteca.erros.ExceptionBibliotecaNaoDefinida;
import correlacaodealarmes.AlgoritimoDeCorrelacaoDeAlarmes;

/**
 * Classe exemplo que interralaciona as classes principais do Sistema
 * foi baseada na Classe principal da Versão I.
 * 
 * @version 2.0
 * 
 * Criação: 09/12/2003 - 19:49:00
 * Modificação Inicial para versão 2.0: 16/01/2004 - 19:31
 * @author Carlos Delfino 
 */
public class Principal
{
	//Nome e Localização dos arquivos de informações.
	
	private static final String arqTopologia = "cnf/topologia.xml";
	private static final String arqBiblioteca = "cnf/biblioteca.xml";
	private static final String arqCanais = "cnf/canais.xml";

	public static void main(String[] args)
	{
		System.out.println("**********************************************************"); //$NON-NLS-1$
		System.out.println("***  Gerando Biblioteca"); //$NON-NLS-1$

		// Instancia a biblioteca com o conteúdo do arquivo
		// biblioteca.xml.
		Biblioteca biblioteca = new Biblioteca(arqBiblioteca);

		// Gera uma representação textual da Biblioteca de componentes.
		//		System.out.println(biblioteca);

		// Instancia a Topologia com o conteúdo do arquivo
		// topologia.xml.
		Topologia topologia = null;
		try
		{
			topologia = new Topologia(arqTopologia, biblioteca);
		} catch (ExceptionBibliotecaNaoDefinida e2)
		{
			e2.printStackTrace();
		}

		// Gera uma representçao textual da Topologia
		//		System.out.println(topologia);

		System.out.println("**********************************************************"); //$NON-NLS-1$
		System.out.println("***  Gerando os Canais"); //$NON-NLS-1$
		//Gera os canais, usando a topologia dada.
		topologia.criarCanais(arqCanais);

		//		System.out.println(canais);

		System.out.println("**********************************************************"); //$NON-NLS-1$
		System.out.println("***  Gerando o Dominio de Fallhas"); //$NON-NLS-1$
		// Gerando o Dominio de Alarmes.
		// O GERADOR AUTOMATICO DE DOMINIO FOI RETIRADO DAQUI POR QUE ELE FAZ 
		// PARTE DO ALGORITMO DE CORRELAÇÃO DE ALARMES, ANTES ESTAVA AQUI PORQUE
		// ERA USADO PELO SIMULADOR, O QUE NÃO É MAIS NECESSARIO.
		//GeradorAutomaticoDeDominio gad = new GeradorAutomaticoDeDominio(topologia.getCanais());
		//		System.out.println(gad);

		// Ativando o Simulador de Emissão de Alarmes
		System.out.println("**********************************************************"); //$NON-NLS-1$
		System.out.println("***  Ativando Simulador de Emissão de Alarmes"); //$NON-NLS-1$
		SimuladorDeEmissaoDeAlarmes simulador = new SimuladorDeEmissaoDeAlarmes(topologia);

		// se foram passados 3 argumentos obrigatoriamente o primeiro
		// é a quantidade minima de alarmes
		if (args.length == 3)
		{
			simulador.setMinFalhas(Integer.parseInt(args[0]));
		} else
		{
			simulador.setMinFalhas(0);
		}

		// tenta setar o maximo de alarmes, caso o numero maximo
		// de alarmes seja menor que o minimo já setado emite um erro
		try
		{
			// se foram passados pelo menos 2 argumentos 
			// pega o maximo como sendo o primeiro.
			// se foram passados 3 argumentos obrigatoriamente
			// o primeiro é a quantidade minima de alarmes e o
			// segundo é a maxima
			if (args.length == 3)
			{
				simulador.setMaxFalhas(Integer.parseInt(args[1]));
			} else if (args.length == 2)
			{
				simulador.setMaxFalhas(Integer.parseInt(args[0]));
			} else
			{
				simulador.setMaxFalhas(0);
			}

		} catch (ExceptionMaximoMenorMinimo e1)
		{
			e1.printStackTrace();
		}

		// o ID do elemento vem sempre como sendo o utilmo dos argumentos
		Collection l_colecaoIdMas = new ArrayList();
		for (StringTokenizer l_stringTokenizer = new StringTokenizer(args[args.length - 1], ","); //$NON-NLS-1$
			l_stringTokenizer.hasMoreTokens();
			)
		{
			String l_string = l_stringTokenizer.nextToken();
			IdMas l_idMas = new IdMas(l_string);
			l_colecaoIdMas.add(l_idMas);
		}

		System.out.println("Pegando os Alarmes no simulador para os componentes: " + l_colecaoIdMas); //$NON-NLS-1$

		// tenta pegar os alarmes gerados pelo Simulador
		// podemos ter problemas caso ainda tenha sido 
		// entregue para o simulador:
		//			a Topologia;
		//			os Canais;
		// 			o Gerador Automatico de Dominio de Falhas (GAD)
		// ou caso o Elemento informado não esteja em nenhum canal.
		//  
		Alarmes alarmes = null;

		try
		{
			
			//alarmes = simulador.getNovosAlames(idMas);
			simulador.makeNovosAlames(l_colecaoIdMas);
			alarmes = simulador.getAlames();
		} catch (Exception e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		//		System.out.println("Numero Máximo de alarmes extras esperadas: " + simulador.getMaxFalhas());
		//		System.out.println("Numero Mínimo de alarmes extras esperadas: " + simulador.getMinFalhas());
		System.out.println(alarmes);

		// Sistema de Filtragem e Correlação de Alarmes
		System.out.println("**********************************************************"); //$NON-NLS-1$
		System.out.println("***  Fazendo o Correlacionamento de Alarmes!"); //$NON-NLS-1$
		try
		{
			AlgoritimoDeCorrelacaoDeAlarmes aca =
				new AlgoritimoDeCorrelacaoDeAlarmes(topologia.getCanais(), simulador.getAlarmesXML());
		} catch (ParserConfigurationException e3)
		{
			// TODO Bloco de captura gerado automaticamente
			e3.printStackTrace();
		}

	}
}
