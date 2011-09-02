/*
 * Criado em 15/12/2003
 *
 * Para alterar o gabarito para este arquivo gerado vá para
 * Janela&gt;Preferências&gt;Java&gt;Geração de Códigos&gt;Código e Comentários
 */
package biblioteca;

import java.util.ArrayList;

import org.w3c.dom.Element;

/**
 * @author Carlos Delfino
 *
 * Para alterar o gabarito para este comentário do tipo gerado vá para
 * Janela&gt;Preferências&gt;Java&gt;Geração de Códigos&gt;Código e Comentários
 */
public class Link extends Componente implements Elemento, Modelos {
	

	private boolean ccw;
	private String nome;
	private String id;
	

	/* (não-Javadoc)
	 * @see biblioteca.Elemento#getNome()
	 */
	public String getNome() {
		return this.nome;
	}

	/* (não-Javadoc)
	 * @see biblioteca.Elemento#setNome(java.lang.String)
	 */
	public void setNome(String nome) {
		this.nome = nome;
		
	}

	/* (não-Javadoc)
	 * @see biblioteca.Elemento#getId()
	 */
	public String getId() {
		return this.id;
	}

	/* (não-Javadoc)
	 * @see biblioteca.Elemento#setId(java.lang.String)
	 */
	public void setId(String id) {
		this.id = id;
		
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
	 * @see biblioteca.Modelos#copia()
	 */
	public Elemento copia() throws CloneNotSupportedException {
		// TODO Stub de método gerado automaticamente
		return (Elemento)clone();
	}
	/**
	 * Explique aqui o que este metodo faz.
	 * @return
	 */
	public Link(Element elem){

		// pega o ID do Componente;
		setId(elem.getAttribute("id"));

		// Pega o nome que foi atribuido ao Componente.
		setNome(elem.getAttribute("nome"));

		if (Boolean.valueOf(elem.getAttribute("selfalarm")).
												booleanValue()) 
		{
			setSelfAlarm(true);
		}
			
		if (Boolean.valueOf(elem.getAttribute("outalarm")).
												booleanValue())
		{
			setOutAlarm(true);
		}
			
		if (Boolean.valueOf(elem.getAttribute("maskalarm")).
												booleanValue()) {
			setMaskAlarm(true);
		}
	}
	
	public void setCCW(String ccw){
		this.ccw = Boolean.getBoolean(ccw);
	}
	public void setCCW(boolean ccw){
		this.ccw = ccw;
	}
	
	public String getIdNoPosterior() {
		String id = getId();
		return ("n" + id.substring(3,5));
	}
	public String getIdNoAnterior() {
		String id = getId();
		return ("n" + id.substring(1,3));
	}	


}
