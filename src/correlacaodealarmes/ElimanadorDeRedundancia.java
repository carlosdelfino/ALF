/*
 * ExtratorDeRedundancia.java Criado em 21/02/2004
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
import java.util.ListIterator;
import java.util.TreeSet;

import geradorautomaticoderotas.Canais;
import geradorautomaticoderotas.Canal;
import biblioteca.IdMas;

/**
 * @author Carlos Delfino
 * @email suporte@carlosdelfino.eti.br
 * @site http://www.carlosdelfino.eti.br
 * 
 * Digite aqui o que esta classe faz.
 * Nome: 
 */
class ElimanadorDeRedundancia
{
	// Alarmes restante apos a filtragem de redundancia
	private Alarmes alarmesNaoRedundantes;

	// Alarmes recebidos para serem tratados
	private Alarmes alarmes;
	// Canais recebidos como referencia.
	private Canais canais;

	/**
	 * Esta classe tem como função eliminar os alarmes
	 * repedidos, e que sejam redundantes nas respostas.
	 * Por exemplo os alarmes do trio são redudantes em sua
	 * resposta já que os mesmo indicam uma mesma falha;
	 * 
	 * @param p_canais
	 * @param p_alarmes
	 */
	public ElimanadorDeRedundancia(Canais p_canais, Alarmes p_alarmes)
	{
		setAlarmes(p_alarmes);
		setCanais(p_canais);

		// ordena os alarmes conforme sua posição dentro do canal.
		alarmes.ordena();

		eliminaReduncia();

	}
	/**
	 * 
	 */
	private void eliminaReduncia()
	{
		// precisei criar o set remove, porque os alarmes a serem removidos
		// não estão ordenados adequadamente para irem sendo removidos conforme os encontro
		// temos também um problema que os indices mudam conforme vão sendo removidos
		// nisto eu irei precisar de um fator de calculo desta mudança.
		TreeSet remove = new TreeSet();
		
		// lista de alarmes não redundantes acessível por todo o sistema;
		alarmesNaoRedundantes = new Alarmes(alarmes);

		ListIterator l_iterAlarmes = alarmesNaoRedundantes.listIterator();

		while (l_iterAlarmes.hasNext())
		{
			Alarme l_alarme1 = (Alarme)l_iterAlarmes.next();
			IdMas l_componente1 = l_alarme1.getIdMasOrigem();
			ListIterator l_iterAlarmes2 =
				alarmesNaoRedundantes.listIterator(alarmesNaoRedundantes.indexOf(l_alarme1) + 1);

			while (l_iterAlarmes2.hasNext())
			{
				IdMas l_componente2 = ((Alarme)l_iterAlarmes2.next()).getIdMasOrigem();
				for (Iterator l_iteratorCanais = canais.iterator(); l_iteratorCanais.hasNext();)
				{
					if ( ((Canal)l_iteratorCanais.next()).isCaminhoPassivo(l_componente1,l_componente2))
					{
						// adiciona o indice a ser removido no set ordenado para ser removido depois
						// veja detalhes na declaração do set remove.
						remove.add(new Integer(l_iterAlarmes2.nextIndex() - 1));

					}
				}
			}
		}
		int fator = 0;
		for (Iterator iter = remove.iterator(); iter.hasNext();)
		{
			int l_indiceRemove = ((Integer)iter.next()).intValue();

			alarmesNaoRedundantes.remove(l_indiceRemove + fator);
			fator--;
		}
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
	public Alarmes getAlarmesNaoRedundantes()
	{
		if (alarmesNaoRedundantes == null || alarmesNaoRedundantes.isEmpty())
		{
			eliminaReduncia();
		}
		return alarmesNaoRedundantes;

	}
	/**
	 * 
	 */
	public Alarmes getAlarmes()
	{
		return alarmes;

	}

}
