/*
 * Alarme.java Criado em 28/01/2004
 *
 * Todo Codigo criado abaixo, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e não seja para fins lucra-
 * tivos capitalistas.
 * 
 */
package geradorautomaticodealarmes;

import org.w3c.dom.Element;


import gerenciadordebiblioteca.ID;
import gerenciadordebiblioteca.IdMas;

/**
 * @author Carlos Delfino
 * @email suporte@carlosdelfino.eti.br
 * @site http://www.carlosdelfino.eti.br
 * 
 * Digite aqui o que esta classe faz.
 * Nome: 
 */
public class Alarme implements Evento, Comparable
{

	/**
	 * @param p_id
	 */
	public Alarme(ID p_id)
	{
		setIdMasOrigem(p_id);
	}

	private ID idMasOrigem;

	private int nivel;
	private String nomeOrigem;

	/**
	 * 
	 */
	public Alarme()
	{
		setIdMasOrigem(new IdMas());
	}

	/**
	 * @param l_alarmeElementXML
	 */
	public Alarme(Element l_alarmeElementXML)
	{
		setNomeOrigem(l_alarmeElementXML.getAttribute("nomeOrigem"));
		setIdMasOrigem(l_alarmeElementXML.getAttribute("idMasOrigem"));
		setNivel(l_alarmeElementXML.getAttribute("nivel"));
		
	}
	/**
	 * @param p_componente
	 */
	public Alarme(IdMas p_idMas)
	{
		
		setIdMasOrigem(p_idMas);
		setNivel(0);
	}

	/**
	 * @param p_string
	 * @param p_mas
	 * @param p_i
	 */
	public Alarme(String p_nomeComponente, IdMas p_idMas, int p_nivel)
	{
		setNomeOrigem(p_nomeComponente);
		setIdMasOrigem(p_idMas);
		setNivel(p_nivel);
	}
	
	/* (não-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object p_alarme)
	{
		/* *****************************************
		 * ATENÇÃO ATENÇÃO ATENÇÃO ATENÇÃO ATENÇÃO
		 * Este metodo se baseia apenas no ID do 
		 * componente de origem e no nivel para fazer
		 * sua comparação entre alarmes.
		 * A data não influencia entre dois alarmes, 
		 * isto siginifica que o algoritmo não diferencia
		 * entre alarmes emitidos em datas diferentes.
		 * A inserção da data neste contexto pode trazer
		 * conseguencias indesajadas.
		 * 
		 * Verifique detalhes no cabeçalho deste metodo 
		 * para usar o Comparator. 
		 */
		ID l_idComparado = ((Alarme)p_alarme).getIdMasOrigem();
		ID l_idBase = getIdMasOrigem();

		int fatorBase = l_idBase.getFator();
		fatorBase *= 100;
		fatorBase += nivel;

		int fatorDoTestado = l_idComparado.getFator();
		fatorDoTestado *= 100;
		fatorDoTestado += ((Alarme)p_alarme).getNivel();

		return fatorBase - fatorDoTestado;
	}

	/**
	 * Verifica a igualdade entre a instancia do ID e o ID passado
	 * 
	 * @param p_idMas
	 * @return
	 */
	public boolean equals(Alarme p_alarme)
	{
		/* *****************************************
		 * ATENÇÃO ATENÇÃO ATENÇÃO ATENÇÃO ATENÇÃO
		 * Fique muito atendo quanto as consequencias
		 * de mudar este metodo,  verifique os motivos
		 * dentro do metodo comparaTo(Object); 
		 */
		return this.compareTo(p_alarme) == 0;
	}

	/**
	 * @return
	 */
	public int getFator()
	{
		return this.getIdMasOrigem().getFator();
	}

	/**
	 * Retorna a origem do alarme, que é o Id da Mas para o componente;
	 * @return
	 */
	public ID getIdMasOrigem()
	{
		return idMasOrigem;
	}

	/**
	 * 
	 */
	int getNivel()
	{
		return nivel;
	}
	/**
	 * @return
	 */
	public int getTempoSegundos()
	{
		return 0;
	}

	/**
	 * Verifica se este alarme é de origem do componente passado
	 * 
	 * @param p_idMas
	 * @return
	 */
	public boolean isOrigem(IdMas p_idMas)
	{
		return p_idMas.equals(getIdMasOrigem());
	}
	/**
	 * @param p_mas
	 */
	private void setIdMasOrigem(ID p_idMas)
	{
		idMasOrigem = p_idMas;
	}
	/**
	 * @param p_string
	 */
	private void setIdMasOrigem(String p_idMas)
	{
		idMasOrigem = new IdMas(p_idMas);
		
	}

	/**
	 * @param p_nivel
	 */
	private void setNivel(int p_nivel)
	{
		nivel = p_nivel;
		
	}

	/**
	 * @param p_string
	 */
	private void setNivel(String p_nivel)
	{
		nivel = Integer.parseInt(p_nivel);
	}

	/**
	 * @param p_nomeComponente
	 */
	private void setNomeOrigem(String p_nomeComponente)
	{
		nomeOrigem = p_nomeComponente;
		
	}

	public String toString()
	{
		String l_nome = getNomeOrigem();
		ID l_idMas = getIdMasOrigem();
		int l_nivel = getNivel();
		String l_string = null;
		
		if (l_nome == null && l_idMas == null)
			l_string = "Alarme Invalido, Componente Desconhecido";
		else
			l_string = "Alarme do" + getNomeOrigem() + " - " + getIdMasOrigem() + " - " + getNivel();			
		return l_string;

	}

	/**
	 * @return
	 */
	public String getNomeOrigem()
	{
		return nomeOrigem;
	}
}
