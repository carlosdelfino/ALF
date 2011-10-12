/*
 * Created on 11/12/2003
 * 
 * Todo Codigo criado por min, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e não seja para fins lucra-
 * tivos capitalistas.
 * 
 */
package gerenciadordebiblioteca;

import java.util.ListIterator;

import org.w3c.dom.Element;


/**
 * Explique aqui o que é este tipo
 * Criação: 11/12/2003 - 19:26:59
 * @author Carlos Delfino
 * 
 */
public class ElementoNL extends ElementoNo {
	/**
	 * 
	 */
	public ElementoNL() {
		this.setTipo(Elemento.NO_LOCAL);
	}
	private Elemento elementoPosterior;

	/**
	 * 
	 * @param p_oModeloElemento
	 * @param elementoXML
	 */
	public ElementoNL(ModeloElemento p_oModeloElemento, Element elementoXML) {
		super(
			p_oModeloElemento
				.getListMComponentesIniciais()
				.listIterator(),
			p_oModeloElemento
				.getListMComponentesPassagens()
				.listIterator(),
			p_oModeloElemento
				.getListMComponentesFinais()
				.listIterator(),
			elementoXML);
	}
	/**
	 * @param subNoInicial
	 * @param subNoPassagem
	 * @param subNoFinal
	 */
	public ElementoNL(
		ListIterator subNoInicial,
		ListIterator subNoPassagem,
		ListIterator subNoFinal,
		Element elementoXML) {
		super(subNoInicial, subNoPassagem, subNoFinal, elementoXML);
	}

	/* (não-Javadoc)
	 * @see biblioteca.Elemento#putNoPosterior(biblioteca.Elemento)
	 */
	public void putElementoPosterior(Elemento elemento) {
		this.elementoPosterior = elemento;

		// pego todos os componentes do sub Elemento de Passagem
		// do no posterior para poder setar o info2 do elemento
		// Demux, este será o id do no anterior.		
		//		Iterator sEP = elemento.getComponentesPassagem().iterator();
		//		while (sEP.hasNext()){
		// pego um Componente e testo seu idMas para ver 
		// se o info2 é um Z, caso postivo coloco o num
		//			Componente cEP = (Componente)sEP.next();

		//if (cEP.getInfo2().equalsIgnoreCase("y")){
		//	cEP.setInfo2(this.getId());
		//}

		//		}
		// pego todos os componentes do sub Elemento Finais
		// do no posterior para poder setar o info2 do elemento
		// Demux, este será o id do no anterior.		
		//		Iterator sEF = elemento.getComponentesFinais().iterator();
		//		while (sEF.hasNext()){
		// pego um Componente e testo seu idMas para ver 
		// se o info2 é um Z, caso postivo coloco o num
		//			Componente cEF = (Componente)sEF.next();

		//if (cEF.getInfo2().equalsIgnoreCase("y")){
		///cEF.setInfo2(this.getId());
		//}

		//		}		
	}
	/* (não-Javadoc)
	 * @see biblioteca.Elemento#getNoPosterior()
	 */
	public Elemento[] getElementoPosterior() {
		Elemento[] noP = { elementoPosterior };
		return noP;
	}
	/* (não-Javadoc)
	 * @see biblioteca.Elemento#isTipo(java.lang.String)
	 */
	public boolean isTipo(String p_tipo) {
		return getTipo().equalsIgnoreCase(p_tipo);
	}
}
