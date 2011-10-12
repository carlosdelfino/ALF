/*
 * Criado em 15/12/2003
 *
 * Para alterar o gabarito para este arquivo gerado vá para
 * Janela&gt;Preferências&gt;Java&gt;Geração de Códigos&gt;Código e Comentários
 */
package gerenciadordebiblioteca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import java.util.ListIterator;

import org.w3c.dom.Element;

import gerenciadordetopologia.Canal;

/**
 * @author Carlos Delfino
 *
 * Para alterar o gabarito para este comentário do tipo gerado vá para
 * Janela&gt;Preferências&gt;Java&gt;Geração de Códigos&gt;Código e Comentários
 */
public class ElementoLink implements Elemento{
	
	
	private Map l_osComponentesPassagem = new HashMap(2);
	private List subLinkPassagem = new ArrayList(2);
	private Elemento elementoPosterior;
	private String id;
	private String nome;
	private String tipoElemento;
	
	public ElementoLink(){
		setTipo("Link");
	}
	
	public ElementoLink(ModeloElemento p_oModeloElemento,  Element elementoXML) {
		setId(elementoXML.getAttribute("id"));
		setNome(elementoXML.getAttribute("nome"));
		
		
		setSubComponentes(p_oModeloElemento.getListMComponentesPassagens().listIterator(),
						  elementoXML);
	}
	
	/**
	 * @param elementoXML
	 */
	public ElementoLink(ListIterator liSubLinkPassagem,  Element elementoXML) {
		setId(elementoXML.getAttribute("id"));
		setNome(elementoXML.getAttribute("nome"));
		setTipo(elementoXML.getAttribute("tipo"));
		
		setSubComponentes(liSubLinkPassagem,elementoXML);
	}

	/**
	 * @param p_tipo 
	 */
	public void setTipo(String p_tipo) {
		this.tipoElemento = p_tipo;
		
	}
	/**
	 * 
	 * @return
	 */
	public String getIdNoPosterior() {
		String id = getId();
		return ("n" + id.substring(3,5));
	}
	/**
	 * 
	 * @return
	 */
	public String getIdNoAnterior() {
		String id = getId();
		return ("n" + id.substring(1,3));
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
	public void setNome(String string) {
		this.nome = string;
		
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
	 * @see biblioteca.Elemento#getTipoElemento()
	 */
	public String getTipo() {
		return tipoElemento;
	}
	/* (não-Javadoc)
	 * @see biblioteca.Elemento#getComponentesPassagem()
	 */
	public List getComponentesPassagem() {
		
		return subLinkPassagem;
		
	}
	/* (não-Javadoc)
	 * @see biblioteca.Elemento#getComponentesFinais()
	 */
	public List getComponentesFinais() {return null;}
	/* (não-Javadoc)
	 * @see biblioteca.Elemento#getComponentesIniciais()
	 */
	public List getComponentesIniciais() {return null;}
	
	
	synchronized public void setSubComponentes(ListIterator liSubLinkPassagem,
											   Element elementoXML){
		setSubComponentes(null,liSubLinkPassagem,null,elementoXML);
	}
	/* (não-Javadoc)
	 * @see biblioteca.Elemento#getElementoPosterior()
	 */
	public Elemento[] getElementoPosterior() {
		Elemento[] elemento = new Elemento[1];
		elemento[0] = this.elementoPosterior;
		return elemento;
	}
	/* (não-Javadoc)
	 * @see biblioteca.Elemento#putElementoPosterior(biblioteca.Elemento)
	 */
	public void putElementoPosterior(Elemento elemento) {
		this.elementoPosterior = elemento;
		
	}
	/* (não-Javadoc)
	 * @see biblioteca.Elemento#setSubComponentes(java.util.ListIterator, java.util.ListIterator, java.util.ListIterator, org.w3c.dom.Element)
	 */
	public void setSubComponentes(ListIterator liSubLinkInicial,
								  ListIterator liSubLinkPassagem,
								  ListIterator liSubNoFinal,
								  Element elementoXML) {
		while(liSubLinkPassagem.hasNext()){
			Fibra l_oComponente = (Fibra)((ModeloComponente)liSubLinkPassagem.next()).copia(this);
			
			subLinkPassagem.add(l_oComponente);
		}
	}

	/* (não-Javadoc)
	 * @see biblioteca.Elemento#isTipo(java.lang.String)
	 */
	public boolean isTipo(String p_tipo) {
		return getTipo().equalsIgnoreCase(p_tipo);
	}
	public String toString()
	{
		return "Elemento do Tipo " + getTipo() + ", ID: " + getId() + ", nome: " + getNome()+"\n";
	}

	/* (não-Javadoc)
	 * @see biblioteca.Elemento#getComponentesIniciais(topologia.Canal)
	 */
	public List getComponentesIniciais(Canal p_canal)
	{
		return null;
	}

	/* (não-Javadoc)
	 * @see biblioteca.Elemento#getComponentesFinais(topologia.Canal)
	 */
	public List getComponentesFinais(Canal p_canal)
	{
		return null;
	}

	/* (não-Javadoc)
	 * @see biblioteca.Elemento#getComponentesPassagem(topologia.Canal)
	 */
	public List getComponentesPassagem(Canal p_canal)
	{
		List l_osComponentes = new ArrayList();
		for (Iterator iter = getComponentesPassagem().iterator(); iter.hasNext();)
		{
			Componente l_componente = ((Componente)iter.next()).copia(this,p_canal);
			String l_idMasString = l_componente.getIdMas().toString();
//			l_componente.expandeIdMas(p_canal,this);
			
			// caso ele já possua este componente na lista
			// ele não irá criar um novo e usará o existente
			// mas se não existir ele adiona o novo!
			if (!l_osComponentesPassagem.containsKey(l_idMasString)){
				l_osComponentes.add(l_componente);
				l_osComponentesPassagem.put(l_idMasString,l_componente);
			}else{
				l_osComponentes.add(l_osComponentesPassagem.get(l_idMasString));
			}
		}
		return l_osComponentes;
	}


}
