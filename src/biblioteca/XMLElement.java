/*
 * Created on 08/12/2003
 * 
 * Todo Codigo que eu criar, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e n�o seja para fins lucra-
 * tivos capitalistas.
 * 
 */
package biblioteca;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Todos os componentes, elementos, Subelementos, e outros itens
 * que s�o referenciados como elementos de uma estrutura do XML
 * e que precisam gerar dados para um documento XML devem estender
 * esta Interface
 * 
 * Aqui tem metodos que s�o uteis a uma classe que trabalha com XML
 * e permite tamb�m a padroniza��o dos metodos.
 * 
 * Cria��o: 08/12/2003 - 22:37:58
 * @author: Carlos Delfino
 * 
 */
public interface XMLElement
{

	/**
	 * Retorna um Elemento xml como sendo um n� do argumento passado
	 * @param xmlRota
	 * @return Element 
	 */
	public Element toXMLElement(Document documentoXML);
	
}
