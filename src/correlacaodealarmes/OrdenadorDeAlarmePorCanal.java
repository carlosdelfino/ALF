/*
 * OrdenadorDeAlarmesPorCanal.java Criado em 21/02/2004
 *
 * Todo Codigo criado abaixo, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e não seja para fins lucra-
 * tivos capitalistas.
 * 
 */
package correlacaodealarmes;

import simuladordarede.Alarme;

import java.util.Comparator;
import java.util.Iterator;

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
class OrdenadorDeAlarmePorCanal implements Comparator
{

	private Canais canais;

	/**
	 * @param p_canais
	 */
	public OrdenadorDeAlarmePorCanal(Canais p_canais)
	{
		setCanais(p_canais);
	}

	/**
	 * @param p_canais
	 */
	private void setCanais(Canais p_canais)
	{
		canais = p_canais;

	}

	public int compare(Object p_alarmeBase, Object p_alarmeComparador)
	{
		Alarme l_alarmeBase = (Alarme)p_alarmeBase;
		Alarme l_alarmeComparador = (Alarme)p_alarmeComparador;
		
		Canal l_canalBase = null;
		Canal l_canalComparador = null;
		for (Iterator iter = canais.iterator(); iter.hasNext();)
		{
			Canal l_canal = (Canal)iter.next();
			// pegunta ao canal qual a posição do 
			// componente que originou este alarme
			int l_indexAlarmeBase = l_canal.indexOf(l_alarmeBase.getIdMasOrigem());
			if (l_indexAlarmeBase >= 0) l_canalBase = l_canal;

			int l_indexAlarmeComparador = l_canal.indexOf(l_alarmeComparador.getIdMasOrigem());
			if (l_indexAlarmeComparador >= 0) l_canalComparador = l_canal;
		}
		
		return 0;
	}
//	/* (não-Javadoc)
//	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
//	 */
//	public int compare(Object p_alarmeBase, Object p_alarmeComparador)
//	{
//		int l_indexIdMasBase = -1;
//		int l_indexCanalBase = -1;
//
//		int l_indexCanalComparado = -1;
//		int l_indexIdMas2 = -1;
//
//		for (Iterator iter = canais.iterator(); iter.hasNext();)
//		{
//			Canal l_canal = (Canal)iter.next();
//
//			int l_indexCanal1 = canais.indexOf(l_canal);
//
//			int l_indexIdMas1 = -1;
//			l_indexIdMas2 = -1;
//
//			// verifica se o componenteBase existe no canal, se sim, tenta achar o segundo!
//			// caso os dois existam ele retorna a diferença da posição de ambos.
//			if ((l_indexIdMas1 = l_canal.indexOf(((Alarme)p_alarmeBase).getIdMasOrigem())) < 0)
//				continue;
//			else if (
//				(l_indexIdMas2 = l_canal.indexOf(((Alarme)p_alarmeComparador).getIdMasOrigem())) < 0)
//			{
//				l_indexIdMasBase = l_indexIdMasBase < 0 ? l_indexIdMas1 : l_indexIdMasBase;
//				l_indexCanalBase = l_indexCanalBase < 0 ? l_indexCanal1 : l_indexCanalBase;
//				continue;
//			}
//
//			return l_indexIdMas1 - l_indexIdMas2;
//		}
//		if (l_indexIdMasBase > 0)
//		{
//			l_indexCanalComparado = -1;
//			l_indexIdMas2 = -1;
//
//			for (Iterator iter = canais.iterator(); iter.hasNext();)
//			{
//				Canal l_canal = (Canal)iter.next();
//				l_indexCanalComparado = canais.indexOf(l_canal);
//				if ((l_indexIdMas2 = l_canal.indexOf(((Alarme)p_alarmeComparador).getIdMasOrigem()))
//					> -1)
//					break;
//			}
//
//		}
//
//		l_indexCanalBase = (l_indexCanalBase * (int)10E4) + (Integer.MAX_VALUE - (int)10E4);
//		l_indexCanalComparado = (l_indexCanalComparado * (int)10E4) + (Integer.MAX_VALUE - (int)10E4);
//
//		return 0;
//	}
}
