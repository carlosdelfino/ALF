/*
 * Created on 09/12/2003
 * 
 * Todo Codigo criado por min, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e não seja para fins lucra-
 * tivos capitalistas.
 * 
 */
package biblioteca;


import java.util.ArrayList;

import org.w3c.dom.Element;

/**
 * Explique aqui o que é este tipo
 * Criação: 09/12/2003 - 20:06:26
 * @author: Carlos Delfino
 * 
 */
public class Componente implements Elemento, Modelos
{
	private String nome;
	private String id;
	private boolean selfAlarm;
	private boolean outAlarm;
	private boolean maskAlarm;
	public String idMas;

	/**
	 * Explique aqui o que este metodo faz.
	 * @return
	 */
	public static Componente geraModeloComponente()
	{
		return new Componente();
	}
	
	public static Componente geraModeloComponente(Element elem){

		Componente modelo = geraModeloComponente();
		
		// pega o ID do Componente;
		modelo.setId(elem.getAttribute("id"));

		// Pega o nome que foi atribuido ao Componente.
		modelo.setNome(elem.getAttribute("nome"));

		if (Boolean.valueOf(elem.getAttribute("selfalarm")).
												booleanValue()) 
		{
			modelo.setSelfAlarm(true);
		}
			
		if (Boolean.valueOf(elem.getAttribute("outalarm")).
												booleanValue())
		{
			modelo.setOutAlarm(true);
		}
			
		if (Boolean.valueOf(elem.getAttribute("maskalarm")).
												booleanValue()) {
			modelo.setMaskAlarm(true);
		}
			
		return modelo;
	}
	/**
	 * Explique aqui o que este metodo faz.
	 * @param b
	 */
	public void setSelfAlarm(boolean b)
	{
		this.selfAlarm = b;
		
	}
	/**
	 * Explique aqui o que este metodo faz.
	 * @param b
	 */
	public void setOutAlarm(boolean b)
	{
		this.outAlarm = b;
		
	}
	/**
	 * Explique aqui o que este metodo faz.
	 * @param b
	 */
	public void setMaskAlarm(boolean b)
	{
		maskAlarm = b;
		
	}
	/**
	 * Explique aqui o que este metodo faz.
	 * @return
	 */
	public String getIdMas()
	{
		return idMas;
	}

	/**
	 * Explique aqui o que este metodo faz.
	 * @param string
	 */
	public void setIdMas(String string)
	{
		idMas = string;
	}

	public Elemento copia() throws CloneNotSupportedException{
		return (Elemento)this.clone();
	}

	/* (não-Javadoc)
	 * @see biblioteca.Elemento#getNome()
	 */
	public String getNome() {
		return nome;
	}

	/* (não-Javadoc)
	 * @see biblioteca.Elemento#setNome(java.lang.String)
	 */
	public void setNome(String nome) {
		this.nome = nome;
		
	}

	/* (não-Javadoc)
	 * @see biblioteca.Elemento#setId(java.lang.String)
	 */
	public void setId(String id) {
		this.id = id;
	}

	/* (não-Javadoc)
	 * @see biblioteca.Elemento#getId()
	 */
	public String getId() {
		return id;
	}

	/* (não-Javadoc)
	 * @see biblioteca.Elemento#getIdsNosPosteriores()
	 */
	public ArrayList getIdsNosPosteriores() {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	/* (não-Javadoc)
	 * @see biblioteca.Elemento#getIdsNosAnteriores()
	 */
	public ArrayList getIdsNosAnteriores() {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	/* (não-Javadoc)
	 * @see biblioteca.Elemento#addIdNoPosterior()
	 */
	public void addIdNoPosterior() {
		// TODO Stub de método gerado automaticamente
	}

	/* (não-Javadoc)
	 * @see biblioteca.Elemento#addIdNoAnterior()
	 */
	public void addIdNoAnterior() {
		// TODO Stub de método gerado automaticamente
		
	}

	/* (não-Javadoc)
	 * @see biblioteca.Elemento#getNosPosteriores()
	 */
	public ArrayList getNosPosteriores() {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	/* (não-Javadoc)
	 * @see biblioteca.Elemento#getNosAnteriores()
	 */
	public ArrayList getNosAnteriores() {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	/* (não-Javadoc)
	 * @see biblioteca.Elemento#addNoPosterior()
	 */
	public void addNoPosterior() {
		// TODO Stub de método gerado automaticamente
		
	}

	/* (não-Javadoc)
	 * @see biblioteca.Elemento#addNoAnterior()
	 */
	public void addNoAnterior() {
		// TODO Stub de método gerado automaticamente
		
	}

	/* (não-Javadoc)
	 * @see biblioteca.Elemento#getNoAnterior()
	 */
	public Elemento getNoAnterior() {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	/* (não-Javadoc)
	 * @see biblioteca.Elemento#getNoPosterior()
	 */
	public Elemento getNoPosterior() {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	/* (não-Javadoc)
	 * @see biblioteca.Elemento#getNoAnterior(int)
	 */
	public Elemento getNoAnterior(int lambda) {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	/* (não-Javadoc)
	 * @see biblioteca.Elemento#getNoPosterior(int)
	 */
	public Elemento getNoPosterior(int lambda) {
		// TODO Stub de método gerado automaticamente
		return null;
	}	

}
