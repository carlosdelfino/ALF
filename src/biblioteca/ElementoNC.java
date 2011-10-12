/*
 * Created on 11/12/2003
 * 
 * Todo Codigo criado por min, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e não seja para fins lucra-
 * tivos capitalistas.
 * 
 */
package biblioteca;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.w3c.dom.Element;

/**
 * Explique aqui o que é este tipo
 * Criação: 11/12/2003 - 19:26:59
 * @author Carlos Delfino
 * 
 */
public class ElementoNC extends ElementoNo {
	/**
	 * 
	 */
	public ElementoNC() {
		setTipo(Elemento.NO_CENTRAL);
	}

	private List elementosPosteriores = new ArrayList();

	public ElementoNC(ModeloElemento p_oModeloElemento, Element elementoXML) {
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
	public ElementoNC(
		ListIterator subNoInicial,
		ListIterator subNoPassagem,
		ListIterator subNoFinal,
		Element elementoXML) {
		super(subNoInicial, subNoPassagem, subNoFinal, elementoXML);
	}

	public void addNoPosterior(ElementoNo no) {
		elementosPosteriores.add(no);
	}

	/* (não-Javadoc)
	 * @see biblioteca.Elemento#getNoPosterior()
	 */
	public Elemento[] getElementoPosterior() {
		Elemento elemento[] =
			new Elemento[this.elementosPosteriores.size()];

		System.arraycopy(
			this.elementosPosteriores.toArray(),
			0,
			elemento,
			0,
			this.elementosPosteriores.size());

		return elemento;
	}

	/* (não-Javadoc)
	 * @see biblioteca.Elemento#putNoPosterior(biblioteca.Elemento)
	 */
	public void putElementoPosterior(Elemento p_oElemento) {
		elementosPosteriores.add(p_oElemento);
		// pego todos os componentes do sub Elemento de Passagem
		// do no posterior para poder setar o info2 do elemento
		// Demux, este será o id do no anterior.		
		Iterator l_SubElementoP =
			p_oElemento.getComponentesPassagem().iterator();
		while (l_SubElementoP.hasNext()) {
			// pego um Componente e testo seu idMas para ver 
			// se o info2 é um Z, caso postivo coloco o num
			Componente l_ComponenteEP =
				(Componente)l_SubElementoP.next();

			//			if (l_ComponenteEP.getInfo2().equalsIgnoreCase("y")){
			//				l_ComponenteEP.setInfo2(getId());
			//			}

		}
		// pego todos os componentes do sub Elemento Finais
		// do no posterior para poder setar o info2 do elemento
		// Demux, este será o id do no anterior.		
		Iterator sEF = p_oElemento.getComponentesFinais().iterator();
		while (sEF.hasNext()) {
			// pego um Componente e testo seu idMas para ver 
			// se o info2 é um Z, caso postivo coloco o num
			Componente cEF = (Componente)sEF.next();

			//			if (cEF.getInfo2().equalsIgnoreCase("y")){
			//				cEF.setInfo2(this.getId());
			//			}

		}

	}
	/* (não-Javadoc)
	 * @see biblioteca.Elemento#isTipo(java.lang.String)
	 */
	public boolean isTipo(String p_tipo) {
		return getTipo().equalsIgnoreCase(p_tipo);
	}

}