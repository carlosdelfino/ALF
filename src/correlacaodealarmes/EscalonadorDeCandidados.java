/*
 * EscalonadorDeCandidados.java Criado em 21/02/2004
 *
 * Todo Codigo criado abaixo, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e não seja para fins lucra-
 * tivos capitalistas.
 * 
 */
package correlacaodealarmes;

import geradorautomaticoderotas.Canais;
import geradorautomaticoderotas.Canal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import simuladordarede.Alarme;
import simuladordarede.Alarmes;
import biblioteca.Componente;
import biblioteca.IdMas;

/**
 * @author Carlos Delfino
 * @email suporte@carlosdelfino.eti.br
 * @site http://www.carlosdelfino.eti.br
 * 
 * Digite aqui o que esta classe faz.
 * Nome: 
 */
class EscalonadorDeCandidados
{
	Alarmes alarmesDeComponentesA1eA3 = new Alarmes();

	Alarmes alarmesDeComponentesA2 = new Alarmes();

	// Conjunto de Alarmes selecionados
	private Alarmes alarmesSelecionados = new Alarmes();
	private Canais canais;

	// Conjunto dos componentes provavelmente falhos;
	private List componentesFalhos = new ArrayList();

	// Gerador Automatico de Dominio, que terá o dominio dos componentes alarmantes
	// para um componente falho.
	private GeradorAutomaticoDeDominio gad;
	/**
	 * 
	 * 
	 * @param p_componentesCandidatos1
	 * @param p_componentesCandidatos2
	 * @param gad
	 */
	public EscalonadorDeCandidados(
		Canais p_canais,
		GeradorAutomaticoDeDominio p_gad,
		Alarmes p_alarmesDeComponentesA2,
		Alarmes p_alarmesDeComponentesA1eA3)
	{
		setCanais(p_canais);

		setGeradorAutomaticoDeDominios(p_gad);

		setAlarmesOrigemA1eA3(p_alarmesDeComponentesA1eA3);
		setAlarmesOrigemA2(p_alarmesDeComponentesA2);

		unirClassesDeAlarmes(p_alarmesDeComponentesA2, p_alarmesDeComponentesA1eA3);

	}

	/**
	 * @param componentesFalhos
	 */
	private void corrigeProbabilidade(Collection p_CF)
	{}

	/**
	 * 
	 * @return
	 */
	public Collection getComponentesFalhos()
	{
		Set l_componentesSuspeitos = new TreeSet(getComponentesSuspeitos());

		// remove os suspeitos que são selfalarm mas não emitiram alarme
		// esta função tem que ser bem estudada, devido ao problema de perda
		// de alarmes.
		removeSuspeitosSelfAlarmSemAlarme(l_componentesSuspeitos);

		removeSuspeitosPorFaltaDeOutAlarme(l_componentesSuspeitos);

		// identifica os suspeitos mais evidentes, isto é os que está em todos os canais
		// ou na maioria deles.
		identificaSuspeitosDeMaiorEvidencia(l_componentesSuspeitos);

		removeSuspeitosQueImpediriamChegadaDeAlarmes(l_componentesSuspeitos);

		componentesFalhos.addAll(l_componentesSuspeitos);

		Collections.sort(componentesFalhos, new ComparadorDeSuspeitosPorProbabilidade());

		return componentesFalhos;
	}

	/**
	 * @param l_componentesSuspeitos
	 */
	private void removeSuspeitosQueImpediriamChegadaDeAlarmes(Set l_componentesSuspeitos)
	{

		// pego todos os canais, um a um
		for (Iterator l_iteratorCanais = canais.iterator(); l_iteratorCanais.hasNext();)
		{
			Canal l_canal = (Canal)l_iteratorCanais.next();
			// pego todos os suspeitos um a um
			for (Iterator l_iteratorSuspeito = l_componentesSuspeitos.iterator();
				l_iteratorSuspeito.hasNext();
				)
			{
				Suspeito l_suspeito = (Suspeito)l_iteratorSuspeito.next();
				//verifico se cada suspeito está dentro do canal em questão
				if (l_canal.contains(l_suspeito.getIdMas()))
				{

					// se está pego os alarmes um a um 
					for (Iterator l_iteratorAlarmes = alarmesSelecionados.iterator();
						l_iteratorAlarmes.hasNext();
						)
					{
						Alarme l_alarme = (Alarme)l_iteratorAlarmes.next();
						// se o alarme estiver no mesmo canal
						if (l_canal.contains(l_alarme.getIdMasOrigem()))
						{
							// e a origem do alarme estiver antes do suspeito
							if (l_canal.indexOf(l_alarme.getIdMasOrigem())<l_canal.indexOf(l_suspeito.getIdMas()))
							{
								// eu descarto este alarme.
								//o maior problema é que na maioria das vezes, a origem 
								//do alarme está antes do suspeito, mas isto não siginifica
								//que está no mesmo canal.
							}
						}
					}
				}
			}
		}
	}

	private Set getComponentesSuspeitos()
	{
		// cria o lugar onde será guardo os componentes supeitos
		Set l_componentesSuspeitos = new TreeSet();

		// Une todos os suspeitos, sem repetição
		l_componentesSuspeitos.addAll(getSuspeitosComBaseA1());
		l_componentesSuspeitos.addAll(getSuspeitosComBaseA2());

		return l_componentesSuspeitos;
	}

	private Set getSuspeitosComBaseA1()
	{
		TreeSet l_componentesFalhos = new TreeSet();

		// aqui ele vai primeiro pegar os alarmes de origem em componentes Selfalarm.
		for (Iterator l_iteratorAlarmes = alarmesDeComponentesA1eA3.iterator();
			l_iteratorAlarmes.hasNext();
			)
		{
			Suspeito l_idSuspeito = new Suspeito(((Alarme)l_iteratorAlarmes.next()).getIdMasOrigem());
			if (l_idSuspeito.isSelfAlarm())
			{

				l_componentesFalhos.add(l_idSuspeito);
			}
		}
		if (!l_componentesFalhos.isEmpty())
		{
			int l_media = Suspeito.PB_MEDIA / l_componentesFalhos.size();

			for (Iterator iter = l_componentesFalhos.iterator(); iter.hasNext();)
			{
				Suspeito l_idSuspeito = (Suspeito)iter.next();
				l_idSuspeito.setProbabilidade(l_media);
			}
		}
		return l_componentesFalhos;
	}

	/** 
	 * aqui vou procurar os elementos possivelmente falhos atraves dos alarmes
	 * de origem A2 comparando com os elementos encontrados com base nos alarmes
	 * de origem A1 e A3.
	 * 
	 * @return List - 
	 */
	private ArrayList getSuspeitosComBaseA2()
	{

		// tenho que usar minha propria implementação de lista, proque
		// o arraylist usa o metodo equals da classe Objet, mas preciso
		// usar o metodo equals da Classe Suspeito.
		ListaSuspeitos l_listaDeConjuntosDeSuspeitos = new ListaSuspeitos();

		for (Iterator l_iteratorAlarmesCA2 = alarmesDeComponentesA2.iterator();
			l_iteratorAlarmesCA2.hasNext();
			)
		{
			Alarme l_alarme = (Alarme)l_iteratorAlarmesCA2.next();
			IdMas l_idOrigemAlarme = l_alarme.getIdMasOrigem();

			// pego no dominio os possiveis suspeitos para um determinado alarme
			Set l_conjuntoSuspeitos = gad.getComponentesPossivelmenteFalhos(l_idOrigemAlarme);

			// pego cada suspeito e verifico se ele já está identificado
			// se sim aumento sua probabilidade.
			for (Iterator l_iteratorCS = l_conjuntoSuspeitos.iterator(); l_iteratorCS.hasNext();)
			{
				Suspeito l_suspeito = new Suspeito((IdMas)l_iteratorCS.next());
				int l_indiceTmp = l_listaDeConjuntosDeSuspeitos.indexOf(l_suspeito);
				if (l_indiceTmp == -1)
				{
					l_listaDeConjuntosDeSuspeitos.add(l_suspeito);
				}
			}

		}

		return l_listaDeConjuntosDeSuspeitos;
	}

	/**
	 * @param l_conjuntosPorCanaisDeCF
	 */
	private void identificaSuspeitosDeMaiorEvidencia(Set p_componentesSuspeitos)
	{

		//	separa os suspeitos por canal
		Map l_conjuntosPorCanaisDeCS = separaEmConjuntosPorCanal(p_componentesSuspeitos);

		Set l_conjuntoTmp = new TreeSet();
		// pego cada conjunto de suspeitos
		for (Iterator l_iteratorCPCCS = l_conjuntosPorCanaisDeCS.keySet().iterator();
			l_iteratorCPCCS.hasNext();
			)
		{
			Set l_conjuntoSuspeitos = (Set)l_conjuntosPorCanaisDeCS.get(l_iteratorCPCCS.next());

			// pego cada suspeito dentro dos conjuntos
			for (Iterator l_iteratorCS = l_conjuntoSuspeitos.iterator(); l_iteratorCS.hasNext();)
			{
				Suspeito l_suspeito = (Suspeito)l_iteratorCS.next();

				if (l_conjuntoTmp.contains(l_suspeito))
					l_suspeito.aumentaProbabilidade();
				else
					l_conjuntoTmp.add(l_suspeito);
			}
		}
		p_componentesSuspeitos = l_conjuntoTmp;
	}

	/**
	 * @param l_componentesSuspeitos
	 */
	private void removeSuspeitosPorFaltaDeOutAlarme(Set p_componentesSuspeitos)
	{

		ArrayList l_suspeitoParaRemover = new ArrayList();
		for (Iterator l_iteratorCS = p_componentesSuspeitos.iterator(); l_iteratorCS.hasNext();)
		{
			Suspeito l_suspeito = (Suspeito)l_iteratorCS.next();

			Alarmes l_alarmes = gad.getAlarmes(l_suspeito.getIdMas());
			int l_acertos = 0;
			// procuro nos alarmes disponiveis, se temos todos
			// os alarmes que justifica a suspeita.
			proximoCanal : for (
				Iterator l_iteratorCanais = canais.iterator(); l_iteratorCanais.hasNext();)
			{
				Canal l_canal = (Canal)l_iteratorCanais.next();
				int l_indice = l_canal.indexOf(l_suspeito.getIdMas());
				if (l_indice > -1)
				{

					for (Iterator l_idComponentes = l_canal.getRotaFisica().listIterator(l_indice);
						l_idComponentes.hasNext();
						)
					{
						IdMas l_idComponente = ((Componente)l_idComponentes.next()).getIdMas();
						if (l_idComponente.isOutAlarm())
						{
							if (alarmesDeComponentesA2.contains(l_idComponente))
								continue proximoCanal;
							else
							{
								l_suspeitoParaRemover.add(l_suspeito);
							}
						}

					}
				}

			}

		}
		p_componentesSuspeitos.removeAll(l_suspeitoParaRemover);
	}

	/**
	 * @param l_componentesSuspeitos
	 */
	private void removeSuspeitosSelfAlarmSemAlarme(Set p_componentesSuspeitos)
	{
		
		proximoSuspeito:
		for (Iterator l_iteratorCS = p_componentesSuspeitos.iterator(); l_iteratorCS.hasNext();)
		{
			Suspeito l_suspeito = (Suspeito)l_iteratorCS.next();
			if (l_suspeito.isSelfAlarm())
			{
				if (alarmesDeComponentesA1eA3.isEmpty()){
				
					l_iteratorCS.remove();
				}
				else
				{
				
					for (Iterator l_iteratorA1eA2 = alarmesDeComponentesA1eA3.iterator();
						l_iteratorA1eA2.hasNext();
						)
					{
						Alarme l_alarme = (Alarme)l_iteratorA1eA2.next();
						if (!l_suspeito.getIdMas().equals(l_alarme.getIdMasOrigem()))
						{
							l_iteratorCS.remove();
							continue proximoSuspeito;
						}
					}
				}
			}
		}
	}

	/**
	 * @param l_componentesSuspeitos
	 * @return
	 */
	private Map separaEmConjuntosPorCanal(Set p_componentesSuspeitos)
	{

		Map l_mapaCanalSuspeitos = new HashMap();
		for (Iterator l_iteratorCanais = canais.iterator(); l_iteratorCanais.hasNext();)
		{
			Canal l_canal = (Canal)l_iteratorCanais.next();
			Set l_conjuntoSuspeitos = new HashSet();
			for (Iterator l_interatorCS = p_componentesSuspeitos.iterator(); l_interatorCS.hasNext();)
			{
				Suspeito l_suspeito = (Suspeito)l_interatorCS.next();

				if (l_canal.contains(l_suspeito.getIdMas()))
				{

					if (!l_mapaCanalSuspeitos.containsKey(l_canal))
					{
						l_mapaCanalSuspeitos.put(l_canal, l_conjuntoSuspeitos);
					}

					l_conjuntoSuspeitos.add(l_suspeito);

				}
			}

		}

		return l_mapaCanalSuspeitos;
	}

	/**
	 * @param p_alarmesDeComponentesA1eA3
	 */
	private void setAlarmesOrigemA1eA3(Alarmes p_alarmesDeComponentesA1eA3)
	{
		alarmesDeComponentesA1eA3 = p_alarmesDeComponentesA1eA3;

	}

	/**
	 * @param p_alarmesDeComponentesA2
	 */
	private void setAlarmesOrigemA2(Alarmes p_alarmesDeComponentesA2)
	{
		alarmesDeComponentesA2 = p_alarmesDeComponentesA2;

	}

	/**
	 * @param p_canais
	 */
	private void setCanais(Canais p_canais)
	{
		canais = p_canais;

	}

	/**
	 * @param p_gad
	 */
	private void setGeradorAutomaticoDeDominios(GeradorAutomaticoDeDominio p_gad)
	{
		gad = p_gad;

	}

	/**
	 * @param p_componentesCandidatos1
	 */
	private void unirClassesDeAlarmes(Alarmes p_grupo1Alarmes, Alarmes p_grupo2Alarmes)
	{
		/*
		 * Faz a união dos grupos de alarmes A1 e A3  com o Grupo de alarmes A2;
		 */
		alarmesSelecionados.addAll(p_grupo1Alarmes);
		alarmesSelecionados.addAll(p_grupo2Alarmes);

	}
}
