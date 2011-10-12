package correlacaodealarmes;

import java.util.Comparator;

/**
 * @author Carlos Delfino
 * @email suporte@carlosdelfino.eti.br
 * @site http://www.carlosdelfino.eti.br
 * 
 * Digite aqui o que esta classe faz.
 * Nome: EscalonadorDeCandidados
 */
class ComparadorDeSuspeitosPorProbabilidade implements Comparator
{

	/* (não-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Object p_suspeito1, Object p_suspeito2)
	{
		Suspeito l_suspeito1 = (Suspeito)p_suspeito1;
		Suspeito l_suspeito2 = (Suspeito)p_suspeito2;
		return l_suspeito2.getProbabilidade() - l_suspeito1.getProbabilidade();
	}
}
