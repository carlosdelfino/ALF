/*
 * PesquisadorDeCandidados.java Criado em 21/02/2004
 *
 * Todo Codigo criado abaixo, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e não seja para fins lucra-
 * tivos capitalistas.
 * 
 */
package correlacaodealarmes;

import geradorautomaticodealarmes.Alarme;
import geradorautomaticodealarmes.Alarmes;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import gerenciadordetopologia.Canais;
import gerenciadordetopologia.Canal;
import gerenciadordetopologia.RotaFisica;
import gerenciadordebiblioteca.Componente;
import gerenciadordebiblioteca.IdMas;

/**
 * @author Carlos Delfino
 * @email suporte@carlosdelfino.eti.br
 * @site http://www.carlosdelfino.eti.br
 * 
 * Digite aqui o que esta classe faz.
 * Nome: 
 */
class PesquisadorDeCandidados
{

	private Canais canais;

	private Alarmes alarmes;

	/**
	 * @param canais
	 * @param p_object
	 */
	public PesquisadorDeCandidados(Canais p_canais, Alarmes p_alarmes)
	{
		setAlarmes(p_alarmes);
		setCanais(p_canais);
	}

	/**
	 * @param p_canais
	 */
	private void setCanais(Canais p_canais)
	{
		canais = p_canais;
	}

	/**
	 * @param p_alarmes
	 */
	private void setAlarmes(Alarmes p_alarmes)
	{
		alarmes = p_alarmes;
	}

	/**
	 * 
	 */
	public Set getComponentesCandidatos()
	{
		Set l_candidatos = new TreeSet();
		
		//pega um alarme por vez
		for (Iterator iter = alarmes.iterator(); iter.hasNext();)
		{
			Alarme l_alarme = (Alarme)iter.next();
			// talvez eu não precise disto
			Set l_canaisDeOrigemDoAlarme = new TreeSet();
			for (Iterator l_iteratorCanais = canais.iterator(); l_iteratorCanais.hasNext();)
			{
				Canal l_canal = (Canal)l_iteratorCanais.next();
				
				// verifico se a origem do alarme está no canal.
				if (l_canal.contains(l_alarme.getIdMasOrigem()))
				{
					
					// pego uma copia da rota fisica em sentido inverso.
					RotaFisica l_rotaFisica = l_canal.getRotaFisica().getSentidoInverso();
					
					
					int l_indexOrigem = l_rotaFisica.indexOf(l_alarme.getIdMasOrigem());

					for (Iterator l_iteratorRotaFisica = l_rotaFisica.listIterator(l_indexOrigem + 1);
						l_iteratorRotaFisica.hasNext();
						)
					{

						IdMas l_componente = ((Componente)l_iteratorRotaFisica.next()).getIdMas();
						
						// verifica se no canal existe um caminho passivo entre os elementos.
						if (l_canal
							.isCaminhoPassivo(l_componente, l_alarme.getIdMasOrigem()))
						{
							
							l_candidatos.add(l_componente);
						}
					}

				}

			}
		}
		return l_candidatos;

	}
}
