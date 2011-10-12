/*
 * Created on 11/12/2003
 * 
 * Todo Codigo criado por min, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e não seja para fins lucra-
 * tivos capitalistas.
 * 
 */
package gerenciadordebiblioteca;

import java.util.List;
import java.util.ListIterator;

import org.w3c.dom.Element;

import gerenciadordetopologia.Canal;

/**
 * Explique aqui o que é este tipo
 * Criação: 11/12/2003 - 20:47:11
 * @author Carlos Delfino
 * 
 */
public interface Elemento 
{
	String FINAL = "final";
	String PASSAGEM = "passagem";
	String INICIAL = "inicial";
	String NO_CENTRAL = "NC";
	String NO_LOCAL = "NL";
	String PREFIXO_LINK = "f";
	String LINK = "Link";
	
	
	public String getNome();
	
	/**
	 * Define o Nome do Elemento;
	 * @param string
	 */
	public void setNome(String string);
	
	/** 
	 * Pega o ID do Elemento da rede
	 * o id é composto pela letra 
	 * "n" seguido pelo numero de ordem
	 * 
	 * @return o ID do Elemento em formato string, composto por "n" + numero de ordem
	 */
	public String getId();
	
	public void setId(String idComp);
	/**
	 * @return
	 */
	public Elemento[] getElementoPosterior();
	
	public void putElementoPosterior(Elemento elemento);
	
	public void setSubComponentes(ListIterator liSubNoInicial,
												   ListIterator liSubNoPassagem,
												   ListIterator liSubNoFinal,
												   Element elementoXML);

	public List getComponentesPassagem();
	public List getComponentesFinais();
	public List getComponentesIniciais();

	public void setTipo(String p_tipo);
	public String getTipo();
	/**
	 * @param p_string
	 * @return
	 */
	public boolean isTipo(String p_string);
	/**
	 * @param p_canal
	 * @return
	 */
	public List getComponentesIniciais(Canal p_canal);
	/**
	 * @param p_canal
	 * @return
	 */
	public List getComponentesFinais(Canal p_canal);
	/**
	 * @param p_canal
	 * @return
	 */
	public List getComponentesPassagem(Canal p_canal);

}