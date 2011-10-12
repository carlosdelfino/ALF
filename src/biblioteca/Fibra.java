/*
 * Fibra.java Criado em 17/01/2004
 *
 * Todo Codigo criado abaixo, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e não seja para fins lucra-
 * tivos capitalistas.
 * 
 */
package biblioteca;

import org.w3c.dom.Element;

/**
 * @author Carlos Delfino
 * email suporte@carlosdelfino.eti.br
 * site http://www.carlosdelfino.eti.br
 * 
 * Digite aqui o que esta classe faz.
 * Nome: 
 */
public class Fibra extends Componente{
	
	/**
	 * Inicializa uma nova Fibra como Componente da Rede
	 * Cuidado para não confundir Fibra com Link, já que cada
	 * Link é composto por duas Fibra, e Cada Fibra compôe uma 
	 * direção do link.
	 * 
	 * @param p_componente
	 * @param p_elemento
	 */
	public Fibra(ModeloComponente p_componente, Elemento p_elemento) {
		super(p_componente,p_elemento);
	}

	/**
	 * @param link
	 */
	public Fibra(ElementoLink link) {
		setId(link.getId());
		setNome(link.getNome());
		
		setNumeroElemento();
		setInfo1(link.getIdNoAnterior());
		setInfo2(link.getIdNoPosterior());
	}

	/**
	 * @param elementoXML
	 */
	public Fibra(Element elementoXML) {
		setId(elementoXML.getAttribute("id"));
		setNome(elementoXML.getAttribute("nome"));
	}
}
