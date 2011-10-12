/*
 * Dominio.java Criado em 08/01/2004
 *
 * Todo Codigo criado abaixo, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e não seja para fins lucra-
 * tivos capitalistas.
 * 
 */
package correlacaodealarmes;

import geradorautomaticodealarmes.Alarmes;

import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import gerenciadordetopologia.Canais;
import gerenciadordetopologia.RotaFisica;
import gerenciadordebiblioteca.Componente;
import gerenciadordebiblioteca.ID;
import gerenciadordebiblioteca.IdMas;

/**
 * @author Carlos Delfino
 * email suporte@carlosdelfino.eti.br
 * site http://www.carlosdelfino.eti.br
 * 
 * A classe Dominio, deve ser capaz de calcular 
 * os componentes que iriam emitir alarmes. caso
 * um deles falhe. ela é um TreeMap, fornecendo a ela
 * um Componente, ela retorna um Conjunto (Set)com os 
 * Componentes que podem emitir um alarme nesta Topologia 
 * ou Canal.
 * 
 */
public class GeradorAutomaticoDeDominio extends TreeMap
{

	private Canais canais;
	/**
	 * 
	 * @param p_Canais
	 */
	public GeradorAutomaticoDeDominio(Canais p_canais)
	{

		setCanais(p_canais);
		
		// Faz um loop e processa todos os canais.
		for (int i = 0; i < p_canais.size(); i++)
		{
			RotaFisica l_rotaFisica = p_canais.getRotaFisicaCanal(i); // Pego um Canal
			int l_qtdComponentes = l_rotaFisica.size(); // pego o numero de componentes do canal.

			proximoComponente : // Label usado para pegar proximo componente, caso encontre um MaskAlarme
			// faz um loop em todos os elementos do canal em questão.
			// para criar o dominio.
			for (Iterator l_iteratorComponentes = l_rotaFisica.iterator();
				l_iteratorComponentes.hasNext();
				)
			{

				// pega o componente do Canal para gerar dominio
				Componente l_componente = ((Componente)l_iteratorComponentes.next());
				// pega o componente
				IdMas l_IdMas = l_componente.getIdMas(); // pega o Id da Mas

				Set l_dominio;
				// se este elemento já foi avaliado, da continuidade no conjunto
				// caso contrario ele cria uma novo conjunto de dominio.
				if (containsKey(l_IdMas))
				{
					l_dominio = (Set)get(l_IdMas);
				} else
				{
					l_dominio = new TreeSet();
				} //fim do if (containsKey(l_IdMas))

				/*
				 * se o componente é capaz de emitir alarme sobre
				 * se mesmo ele deve ser colocado no dominio.
				 * OU seja ele é A1 ou A3
				 */
				if (l_IdMas.isSelfAlarm() || l_IdMas.isMaskAlarm())
				{
					l_dominio.add(l_IdMas);
				} //fim do if (l_IdMas.isSelfAlarm() || l_IdMas.isMaskAlarm())

				/* 
				 * Agora ele pega os componente que o seguem no canal, e so
				 * para de anotar os Alarmes quando encontrar um que 
				 * mascara a falha (Componente A3).
				 */
				int l_indiceProximoComponente = l_rotaFisica.indexOf(l_componente) + 1;
				if (l_indiceProximoComponente <= l_qtdComponentes + 1)
				{
					// pega uma lista de componentes seguintes ao que está sendo testado
					// e processa em loop até encontrar um maskalarm
					for (ListIterator l_listIteratorDominio =
						l_rotaFisica.listIterator(l_indiceProximoComponente);
						l_listIteratorDominio.hasNext();
						)
					{
						Componente l_oProximoComponente = (Componente)l_listIteratorDominio.next();

						if (l_oProximoComponente.isOutAlarm())
							l_dominio.add(l_oProximoComponente.getIdMas());
						else if (l_oProximoComponente.isMaskAlarm())
						{
							put(l_IdMas, l_dominio);
							continue proximoComponente;
						} //fim do  if (l_oProximoComponente.isOutAlarm()) e else

					} //fim do for (ListIterator l_listIteratorDominio = l_rotaFisica.listIterator(l_indiceProximoComponente);l_listIteratorDominio.hasNext();)

				} //fim do if (l_indiceProximoComponente <= l_qtdComponentes + 1)

				// Guarda o dominio para o componente corrente
				put(l_IdMas, l_dominio);

			} //fim do for (Iterator l_iteratorComponentes = l_rotaFisica.iterator();l_iteratorComponentes.hasNext();)

		}
	}
	/**
	 * @param p_canais
	 */
	public void setCanais(Canais p_canais)
	{
		canais = p_canais;

	}
	/**
	 * Gera uma representação Textual do Dominio.
	 */
	public String toString()
	{
		StringBuffer l_strB = new StringBuffer();
		StringBuffer l_strC = new StringBuffer("Os componentes:\n\t");

		int count = 0;

		for (Iterator iter = keySet().iterator(); iter.hasNext();)
		{
			IdMas l_oIdMasChave = (IdMas)iter.next();
			Set l_alarmes = (Set)get(l_oIdMasChave);

			if (!l_alarmes.isEmpty())
			{
				l_strB.append("O componente " + l_oIdMasChave + " falhando,\n");
				l_strB.append("os componentes a seguir emitirão alarmes: \n\t");

				int countalarmes = 0;
				for (Iterator l_listaAlarmes = ((Set)get(l_oIdMasChave)).iterator();
					l_listaAlarmes.hasNext();
					)
				{
					l_strB.append(l_listaAlarmes.next());
					if (l_listaAlarmes.hasNext())
						l_strB.append(", ");
					if (countalarmes++ > 5)
					{
						l_strB.append("\n\t");
						countalarmes = 0;
					}
				}
				l_strB.append("\n\n");
			} else
			{
				l_strC.append(l_oIdMasChave + ", ");

				if (count++ > 5)
				{
					count = 0;
					l_strC.append("\n\t");
				}
			}
		}
		l_strC.append("\n não irão ter quem emita falha\n");
		l_strB.append(l_strC);
		return l_strB.toString();
	}
	/**
	 * @param p_idMas
	 * @return
	 */
	public Alarmes makeAlarmes(IdMas p_idMas)
	{
		Set l_componentes = keySet();
		if (l_componentes.contains(p_idMas))
		{
			Set l_componentesAlarmantes = (Set)get(p_idMas);
			Alarmes l_alarmes = new Alarmes();
			for (Iterator iter = l_componentesAlarmantes.iterator(); iter.hasNext();)
			{
				l_alarmes.add(((Componente)iter.next()).emitirAlarme());
			}
			return l_alarmes;
		}

		return null;
	}
	/**
	 * Retorna os componentes candidatos a serem os 
	 * responsaveis pelo alarme gerado pela origem passada
	 * 
	 * @param p_idComponente
	 * @return
	 */
	public Set getComponentesPossivelmenteFalhos(ID p_idComponente)
	{
		Set l_componentesFalhos = new TreeSet();

		// pega os componentes falhos.
		for (Iterator l_iteratorComponentes = keySet().iterator(); l_iteratorComponentes.hasNext();)
		{
			IdMas l_idComponente = (IdMas)l_iteratorComponentes.next();
			if (getAlarmes(l_idComponente).contains(p_idComponente))
			{

				l_componentesFalhos.add(l_idComponente);
			}
		}
		return l_componentesFalhos;
	}
	/**
	 * @param l_idComponente
	 */
	public Alarmes getAlarmes(ID p_idComponente)
	{
		Alarmes l_alarmes = new Alarmes();
		for (Iterator l_iteratorCAlarmante = ((Collection)get(p_idComponente)).iterator();
			l_iteratorCAlarmante.hasNext();
			)
		{
			IdMas l_idCA = (IdMas)l_iteratorCAlarmante.next();
			l_alarmes.add(canais.getComponente(l_idCA).emitirAlarme(p_idComponente));
		}
		return l_alarmes;

	}
	/**
	 * 
	 */
	public DefaultTreeModel arvoreDoDominio()
	{
		DefaultMutableTreeNode l_dmtn = new DefaultMutableTreeNode("Dominio de Alarmes");
		DefaultMutableTreeNode l_dmtnComponentesSemAlarmes = new DefaultMutableTreeNode("Componentes que não terão alarmes");
		
		int count = 0;

		for (Iterator iter = keySet().iterator(); iter.hasNext();)
		{
			IdMas l_oIdMasChave = (IdMas)iter.next();
			Set l_alarmes = (Set)get(l_oIdMasChave);

			if (!l_alarmes.isEmpty())
			{
				DefaultMutableTreeNode l_dmtnComponenteFalho = new DefaultMutableTreeNode(l_oIdMasChave + " falhando teremos alarmes de:");
				l_dmtn.add(l_dmtnComponenteFalho);

				int countalarmes = 0;
				for (Iterator l_listaAlarmes = ((Set)get(l_oIdMasChave)).iterator();
					l_listaAlarmes.hasNext();
					)
				{
					DefaultMutableTreeNode l_dmtnComponenteAlarmes = new DefaultMutableTreeNode(l_listaAlarmes.next());
					l_dmtnComponenteFalho.add(l_dmtnComponenteAlarmes);
				}
			} else
			{
				l_dmtnComponentesSemAlarmes.add(new DefaultMutableTreeNode(l_oIdMasChave));
			}
		}

		l_dmtn.add(l_dmtnComponentesSemAlarmes);
		
		return new DefaultTreeModel(l_dmtn);
		
		
	}

}
