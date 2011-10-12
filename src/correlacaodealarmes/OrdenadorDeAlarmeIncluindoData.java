/*
 * ComparatorAlarmeComData.java Criado em 27/02/2004
 *
 * Todo Codigo criado abaixo, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e não seja para fins lucra-
 * tivos capitalistas.
 * 
 */
package correlacaodealarmes;

import geradorautomaticodealarmes.Alarme;

import java.util.Comparator;

/**
 * @author Carlos Delfino
 * @email suporte@carlosdelfino.eti.br
 * @site http://www.carlosdelfino.eti.br
 * 
 * Digite aqui o que esta classe faz.
 * Nome: 
 */
class OrdenadorDeAlarmeIncluindoData implements Comparator
{

	/**
	 * 
	 */
	public OrdenadorDeAlarmeIncluindoData()
	{
		super();
	}

	/**
	 * Este algoritmo está sujeito a falhas na compração
	 * portanto verifique sua atuação e me envie qualquer
	 * falha que perceber!
	 * 
	 * Ele compara primeiro a nivel de componentes, se diferente 
	 * usa a diferença dos componentes se forem os mesmos componentes
	 * ele retorna a comparação pelo tempo.
	 * 
	 * @param p_alarmeBase
	 * @param p_alarmeComparado 
	 */
	
	/* (não-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Object p_alarmeBase, Object p_alarmeComparado)
	{
		int fatorBase = ((Alarme)p_alarmeBase).getFator();
		
		int fatorComparador = ((Alarme)p_alarmeComparado).getFator();
		
		//Usado para comparar se os dois componentes são maiores ou menores
		int fatorprimario = fatorBase - fatorComparador;
		
		int fatorBaseLong = ((Alarme)p_alarmeBase).getTempoSegundos();
		int fatorComparadorLong = ((Alarme)p_alarmeComparado).getTempoSegundos();
		
		// usado para comparar se o tempo dos são maiores ou menores.
		long fatorprimarioLong = fatorBaseLong - fatorComparadorLong;
		if (fatorprimario == 0 ){
			return (int)fatorprimarioLong;
		}
		
		return fatorprimario;
	}

}
