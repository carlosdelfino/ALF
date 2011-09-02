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

import org.w3c.dom.Element;

/**
 * Explique aqui o que é este tipo
 * Criação: 11/12/2003 - 19:26:59
 * @author: Carlos Delfino
 * 
 */
public class No implements Elemento, Modelos
{
	private ArrayList nosPosteriores;
	private ArrayList NosAnteriores;
	
	private ArrayList LinksEntrada;
	private ArrayList LinksSaida;

	private String tipo;
	private String nome;
	private String id;
	private ArrayList subNoInicial;
	private ArrayList subNoPassagem;
	private ArrayList subNoFinal;
	private ArrayList idSubNoInicial;
	private ArrayList idSubNoPassagem;
	private ArrayList idSubNoFinal;

	
	public No(){
		nosPosteriores = new ArrayList();
		NosAnteriores = new ArrayList();
	
		LinksEntrada = new ArrayList();
		LinksSaida = new ArrayList();

	}
	public No(Element elem){
		nosPosteriores = new ArrayList();
		NosAnteriores = new ArrayList();
	
		LinksEntrada = new ArrayList();
		LinksSaida = new ArrayList();

		setId(elem.getAttribute("id"));
		setNome(elem.getAttribute("nome"));
		setTipo(elem.getAttribute("tipo"));

	}
	

	/**
	 * @param string
	 */
	private void setTipo(String tipo) {
		this.tipo = tipo;
		
	}

	/**
	 * Adiciona modelos de SubComponentes Iniciais
	 * 
	 * @param idMas
	 * @param object
	 */
	public void adicionaMComponenteInicial(Componente object)
	{
		if (subNoInicial == null) {
			subNoInicial = new ArrayList();
		}
		subNoInicial.add(object);
	}
	public void adicionaMComponenteFinal(Componente tComp)
	{
		if (subNoFinal == null) {
			subNoFinal = new ArrayList();
		}
		subNoFinal.add(tComp);
	}
	public void adicionaMComponentePassagem(Componente tComp)
	{
		if (subNoPassagem == null) {
			subNoPassagem = new ArrayList();
		}
		subNoPassagem.add(tComp);
	}
	/**
	 * pega os modelos de SubComponentes
	 * 
	 */
	public ArrayList pegaListaModelosComponentesIniciais()
	{
		return subNoInicial;
	}
	public ArrayList pegaListaModelosComponentesFinais()
	{
		return subNoFinal;
	}
	public ArrayList pegaListaModelosComponentesPassagens()
	{
		return subNoPassagem;
	}


	/**
	 * Gera uma copia do Nó Modelo, para ser usada na topologia 
	 */
	public Elemento copia() throws CloneNotSupportedException{
		return (Elemento)clone();
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

	/**
	 * @param noposterior
	 */
	public void addNoPosteriorCCW(String noposterior) {
		// TODO Stub de método gerado automaticamente
		
	}

	/* (não-Javadoc)
	 * @see biblioteca.Elemento#getIdNoAnterior()
	 */
	public String getIdNoAnterior() {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	/**
	 * @param noanterior
	 */
	public void addNoAnteriorCCW(String noanterior) {
		// TODO Stub de método gerado automaticamente
		
	}

	/**
	 * @return
	 */
	public String getTipo() {
		// TODO Stub de método gerado automaticamente
		return tipo;
	}

	public ArrayList getNosAnteriores() {
		// TODO Stub de método gerado automaticamente
		return this.NosAnteriores;
	}

	public ArrayList getNosPosteriores() {
		// Verificar qual o problema neste metodo, verifique também o addNoPosterior
		return nosPosteriores;
	}
	/* (não-Javadoc)
	 * @see biblioteca.Elemento#addNoPosterior()
	 */
	public void addNoPosterior(No noPosterior) {
		nosPosteriores.add(noPosterior);
	}

	/* (não-Javadoc)
	 * @see biblioteca.Elemento#addNoAnterior()
	 */
	public void addNoAnterior(No noAnterior) {
	this.NosAnteriores.add(noAnterior);
	}

	/* (não-Javadoc)
	 * @see biblioteca.Elemento#getNoAnterior(int)
	 */
	public No getNoAnterior(int indice) {
		// TODO Stub de método gerado automaticamente
		return null;
	}

	/**
	 * pega o proximo canal conforme o indice passado.
	 */
	public No getNoPosterior(int indice) {
		// TODO Stub de método gerado automaticamente
		return null;
	}


}
