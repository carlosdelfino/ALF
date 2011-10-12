/*
 * Filtro.java Criado em 21/02/2004
 *
 * Todo Codigo criado abaixo, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e não seja para fins lucra-
 * tivos capitalistas.
 * 
 */
package correlacaodealarmes;

import simuladordarede.Alarme;
import simuladordarede.Alarmes;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import geradorautomaticoderotas.Canais;
import geradorautomaticoderotas.Canal;

/**
 * @author Carlos Delfino
 * @email suporte@carlosdelfino.eti.br
 * @site http://www.carlosdelfino.eti.br
 * 
 * Digite aqui o que esta classe faz.
 * Nome: 
 */
class SelecionadorDeAlarmes
{
	// Equivale ao conjunto T no qual a Mas se refere
	private Alarmes alarmesValidosClasseA2 = new Alarmes();

	// Irá dar origem ao conjunto PC1 do AFA
	private Alarmes alarmesValidosClasseA1eA3 = new Alarmes();
	// Alarmes recebidos para serem tratados

	private Alarmes alarmes;
	// Canais recebidos como referencia.
	private Canais canais;

	private Alarmes alarmesValidos = new Alarmes();

	/**
	 * Esta classe faz a seleção dos alarmes conforme a classe
	 * 1 e 3 ou 2, isto é se são selfalarmes ou se são outalarmes.
	 * Esta classe também separa os alarmes que indicam falhas já 
	 * detectadas anteriormente e as descarta.
	 * 
	 * @param p_canais
	 * @param p_alarmes
	 */
	public SelecionadorDeAlarmes(Canais p_canais, Alarmes p_alarmes)
	{
		setAlarmes(p_alarmes);
		setCanais(p_canais);

		alarmesValidos = removeAlarmesForaDeCanais(alarmes);
		// este metodo não faz nada ainda, está aqui por um questão 
		// de estruturação do algoritimo.
		descartaAlarmeQueIdenficaFalhaJaConhecida(alarmesValidos);

		separaAlarmesConformeClasseOrigem(alarmesValidos);

	}
	/**
	 * Separa os alarmes conforme sua classe de Origem.
	 * O primeiro elemento da array contem os alarmes classe 1 e 3
	 * o segundo conseguentemente contem os alarmes classe 2
	 * 
	 * @param p_alarmes
	 * @return - retorna um array contendo o primeiro os alarmes da classe A1 e A3 e no segundo os elementos da Classe A2
	 */
	private void separaAlarmesConformeClasseOrigem(Alarmes p_alarmes)
	{
		Alarmes l_alarmesClasse1e3 = new Alarmes();
		Alarmes l_alarmesClasse2 = new Alarmes();

		for (Iterator iterAlarmes = alarmesValidos.iterator(); iterAlarmes.hasNext();)
		{
			Alarme l_alarme = (Alarme)iterAlarmes.next();
			if (l_alarme.getIdMasOrigem().isSelfAlarm())
			{
				l_alarmesClasse1e3.add(l_alarme);
			} else if (l_alarme.getIdMasOrigem().isOutAlarm())
			{
				l_alarmesClasse2.add(l_alarme);
			}
		}

		alarmesValidosClasseA1eA3 = l_alarmesClasse1e3;
		alarmesValidosClasseA2 = l_alarmesClasse2;
	}
	/**
	 * Descarta alarmes que indicam falhas já conhecidas.
	 * 
	 * @param p_alarmes
	 */
	private void descartaAlarmeQueIdenficaFalhaJaConhecida(Alarmes p_alarmes)
	{
		// TODO Stub de método gerado automaticamente

	}
	/**
	 * retonar uma lista com os alarmes que tem a origem anexada a um canal.
	 * 
	 * @param p_alarmes
	 * @return
	 */
	private Alarmes removeAlarmesForaDeCanais(Alarmes p_alarmes)
	{
		Alarmes l_alarmesValidos = (Alarmes)p_alarmes.clone();

		for (Iterator iterCanais = canais.iterator(); iterCanais.hasNext();)
		{
			Canal canal = (Canal)iterCanais.next();
			for (Iterator iterAlarmes = alarmesValidos.iterator(); iterAlarmes.hasNext();)
			{
				Alarme alarme = (Alarme)iterAlarmes.next();

				if (!canal.contains(alarme.getIdMasOrigem()))
				{
					iterAlarmes.remove();
				}

			}
		}
		return l_alarmesValidos;

	}
	/**
	 * 
	 * @param p_alarmes
	 */
	private void setAlarmes(Alarmes p_alarmes)
	{
		this.alarmes = p_alarmes;
	}

	/**
	 * 
	 * @param p_canais
	 */
	private void setCanais(Canais p_canais)
	{
		this.canais = p_canais;
	}
	/**
	 * 
	 */
	public Alarmes getAlarmesValidosClasseA2()
	{
		if (alarmesValidosClasseA2 != null && alarmesValidosClasseA2.isEmpty())
		{
			separaAlarmesConformeClasseOrigem(alarmesValidos);
		}
		return alarmesValidosClasseA2;

	}
	/**
	 * 
	 */
	public Alarmes getAlarmesValidosClasseA1eA3()
	{
		if (alarmesValidosClasseA2 != null && alarmesValidosClasseA2.isEmpty())
		{
			separaAlarmesConformeClasseOrigem(alarmesValidos);
		}
		return alarmesValidosClasseA1eA3;
	}
	/**
	 * 
	 */
	public Set getComponentesCandidatos()
	{
		Set l_candidatos = new TreeSet();
		for (Iterator iter = getAlarmesValidosClasseA1eA3().iterator(); iter.hasNext();)
		{
			l_candidatos.add(((Alarme)iter.next()).getIdMasOrigem());
			
		}
		return l_candidatos;
		
	}

}
