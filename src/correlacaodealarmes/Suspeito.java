/*
 * Candidato.java Criado em 20/03/2004
 *
 * Todo Codigo criado abaixo, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e não seja para fins lucra-
 * tivos capitalistas.
 * 
 */
package correlacaodealarmes;

import biblioteca.IdMas;

/**
 * @author Carlos Delfino
 * @email suporte@carlosdelfino.eti.br
 * @site http://www.carlosdelfino.eti.br
 * 
 * Digite aqui o que esta classe faz.
 * Nome: 
 */
class Suspeito implements Comparable
{
	public static int PB_MEDIA = 50;

	public static final int ALTO = 2;

	private int prioridade;

	private int probabilidade = PB_MINIMA;

	// NIVEIS DE PROBABILIDADE 
	static int PB_MINIMA = 1;
	static int PB_MAXIMA = 100;

	private IdMas idMas;

	/**
	 * @param p_mas
	 */
	public Suspeito(IdMas p_idMas)
	{
		setIdMas(p_idMas);
	}

	/**
	 * @param p_idMas
	 */
	private void setIdMas(IdMas p_idMas)
	{
		idMas = p_idMas;
		
	}
	/**
	 * @param p_idMas
	 */
	IdMas getIdMas()
	{
		return idMas;
		
	}

	public int compareTo(Object p_candidato)
	{
		return idMas.compareTo(((Suspeito)p_candidato).getIdMas());
	}

	/**
	 * @return
	 */
	private int getPrioridade()
	{
		return prioridade;
	}

	/**
	 * @return
	 */
	int getProbabilidade()
	{
		return probabilidade;
	}

	/**
	 * @param p_i
	 */
	public void setPrioridade(int p_i)
	{
		prioridade = p_i;
		
	}

	/**
	 * @param p_i
	 */
	public void setProbabilidade(int p_i)
	{
		probabilidade = p_i;
		
	}

	public boolean equals(Suspeito p_idCandidato){
		
		return getIdMas().equals(p_idCandidato.getIdMas());
		
	}
	/**
	 * @return
	 */
	public boolean isSelfAlarm()
	{
		return idMas.isSelfAlarm();
	}

	/**
	 * 
	 */
	public void aumentaProbabilidade()
	{
		if(probabilidade < Suspeito.PB_MAXIMA)
		probabilidade++;
	}
	public String toString(){
//		return idMas.toString() + " com probabilidade de " + getProbabilidade() + "%";
		return "S" + idMas.toString();
	}
}
