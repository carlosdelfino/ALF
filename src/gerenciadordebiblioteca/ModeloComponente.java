/*
 * Created on 09/12/2003
 * 
 * Todo Codigo criado por min, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e não seja para fins lucra-
 * tivos capitalistas.
 * 
 */
package gerenciadordebiblioteca;

import org.w3c.dom.Element;

/**
 * Classe que cria o modelo referencial de todos os Componentes
 * para esta classe não importa o que o componente faz, ela 
 * apenas é uma representação bem abstrada dos componentes de 
 * rede, para ela o mais importante é se ele é ativo (classe 1,2,3)
 * ou se é passivo. ela segue o conseito de Factory Patterns 
 * 
 * 
 * 
 * Criação: 09/12/2003 - 20:06:26
 * @author Carlos Delfino
 * 
 */
public abstract class ModeloComponente implements Modelos
{
	private Object elemento;
	protected final static int PASSIVE = 0;
	protected final static int SELF_ALARM = 1;
	protected final static int OUT_ALARM = 2;
	protected final static int MASK_ALARM = 3;
	
	protected final static String S_PASSIVE = "0";
	protected final static String S_SELF_ALARM = "1";
	protected final static String S_OUT_ALARM = "2";
	protected final static String S_MASK_ALARM = "3";
	
	private IdMas idMas = new IdMas();
	private String nomeMComponente;
	private String idMComponente;
	
	private boolean selfAlarm;
	private boolean outAlarm;
	private boolean maskAlarm;

	public static ModeloComponente make(Element elementoModeloComponenteXML)
	{
		
		ModeloComponente modeloComponente;
		
		if(Boolean.valueOf(elementoModeloComponenteXML.getAttribute("selfalarm")
						   ).booleanValue())
		{
			modeloComponente = new ModeloComponenteAtivo();
			modeloComponente.isSelfAlarm(true);
		  	if(Boolean.valueOf(elementoModeloComponenteXML.getAttribute("maskalarm")
							   ).booleanValue())
			{
				modeloComponente.isMaskAlarm(true);
			}	
		}else if (Boolean.valueOf(elementoModeloComponenteXML.getAttribute("outalarm")
				 				  ).booleanValue())
		{
			modeloComponente = new ModeloComponenteAtivo();
			modeloComponente.isOutAlarm(true);
			modeloComponente.setCategoria();

		}else{
			modeloComponente = new ModeloComponentePassivo();
		}

		// pega o ID do Componente;
		modeloComponente.setIdModelo(elementoModeloComponenteXML.getAttribute("id"));

		// Pega o nome que foi atribuido ao Componente.
		modeloComponente.setNomeModelo(elementoModeloComponenteXML.getAttribute("nome"));
		
		return modeloComponente;

	}
	
	/**
	 * Cria um novo Modelo de Componente conforme o modelo passado e os 
	 * parametros fornecidos em XML 
	 * 
	 * @param p_modeloComponente
	 * @param p_elementoXML
	 * @return
	 */static public ModeloComponente make(ModeloComponente p_modeloComponente, Element p_elementoXML) {
		
		ModeloComponente modeloComponente;
		
		if (p_modeloComponente.getCategoriaNum() == PASSIVE){
			modeloComponente = new ModeloComponentePassivo();
		}else {
			modeloComponente = new ModeloComponenteAtivo(p_modeloComponente.getCategoriaNum());
		}

		modeloComponente.setIdModelo(p_modeloComponente.getIdModelo());
		modeloComponente.setNomeModelo(p_modeloComponente.getNomeModelo());
		
		return modeloComponente;
	}
	/**
	 * Explique aqui o que este metodo faz.
	 * @param b
	 */
	public void isSelfAlarm(boolean b)
	{
		selfAlarm = b;
	}
	/**
	 * Explique aqui o que este metodo faz.
	 * @param b
	 */
	public void isOutAlarm(boolean b)
	{
		outAlarm = b;
		
	}
	/**
	 * 
	 * @param b
	 */
	public void isMaskAlarm(boolean b)
	{
		maskAlarm = b;
		
	}
	/* (não-Javadoc)
	 * @see biblioteca.Elemento#pegaNomeModelo()
	 */
	public String getNomeModelo() {
		return nomeMComponente;
	}

	/* (não-Javadoc)
	 * @see biblioteca.Elemento#defineNomeModelo(java.lang.String)
	 */
	public void setNomeModelo(String nome) {
		this.nomeMComponente = nome;
		
	}

	/* (não-Javadoc)
	 * @see biblioteca.Elemento#defineIdModelo(java.lang.String)
	 */
	public void setIdModelo(String id) {
		this.idMComponente = id;
	}

	/* (não-Javadoc)
	 * @see biblioteca.Elemento#pegaIdModelo()
	 */
	public String getIdModelo() {
		return idMComponente;
	}
	public ModeloComponente copia(Object obj){
		return ModeloComponente.make(this, (Element)obj);
	}
	public Componente copia(Elemento p_elemento){
		return ModeloComponente.makeComponente(this, p_elemento);
	}
	public Componente copia(ElementoLink p_elemento){
		return ModeloComponente.makeComponente(this, p_elemento);
	}
	/**
	 * @param p_componente
	 * @param p_elemento
	 * @return
	 */
	private static Componente makeComponente(ModeloComponente p_componente, Elemento p_elemento) {
		return new Componente(p_componente, p_elemento);
	}
	private static Componente makeComponente(ModeloComponente p_modeloComponente, ElementoLink p_elemento) {
		return new Fibra(p_modeloComponente, (Elemento)p_elemento);
	}

	/**
	 * Explique aqui o que este metodo faz.
	 */
	public boolean isSelfAlarm()
	{
		return selfAlarm;
		
	}
	/**
	 * Explique aqui o que este metodo faz.
	 */
	public boolean isOutAlarm()
	{
		return outAlarm;
		
	}
	/**
	 * Explique aqui o que este metodo faz.
	 */
	public boolean isMaskAlarm()
	{
		return maskAlarm;
		
	}
	/**
	 * Define o ID da Mas para este Modelo de Componente
	 * 
	 * @param p_idMas
	 */public void setIdMas(String p_idMas)
	{
		idMas.setIdMas(p_idMas);
	}
	/**
	 * pega o Id usado pela Mas.
	 * @return IdMas
	 */
	public String getIdMas()
	{
		return idMas.toString();
	}

	/**
	 * 
	 */
	public void setCategoria() {
		if (isSelfAlarm()){
			if(isMaskAlarm()){
				setCategoria(MASK_ALARM);
			}else{
				setCategoria(SELF_ALARM);
			}
		}else if(isOutAlarm()){
			setCategoria(OUT_ALARM);
		}
		
	}

	/**
	 * Seta a categoria ao qual queste componente pertence.
	 * Deve ser um numero "0", "1", "2" ou "3" em formato string
	 * 
	 * @param p_Categoria
	 */
	public void setCategoria(String p_Categoria) {
		idMas.setCategoria(p_Categoria);
	}
	
	/**
	 * Seta a categoria ao qual este componente pertence.
	 * Recebe a ccateogria em formato inteiro, pode neste caso ser usado uma das constantes
	 * ModeloComponente.SELF_ALARM, ModeloComponente.MASK_ALARM, ModeloComponente.OUT_ALARM
	 * 
	 * @param p_Categoria
	 */
	public void setCategoria(int p_Categoria) {
		idMas.setCategoria(p_Categoria);
	}
	/**
	 * 
	 */
	public String getCategoria() {
		return idMas.getCategoria();
		
	}
	/**
	 * @return
	 */
	private int getCategoriaNum() {
		return idMas.getCategoriaNum();
	}


}
