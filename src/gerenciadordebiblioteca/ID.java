/*
 * ID.java Criado em 19/03/2004
 *
 * Todo Codigo criado abaixo, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e não seja para fins lucra-
 * tivos capitalistas.
 * 
 */
package gerenciadordebiblioteca;

/**
 * @author Carlos Delfino
 * @email suporte@carlosdelfino.eti.br
 * @site http://www.carlosdelfino.eti.br
 * 
 * Digite aqui o que esta classe faz.
 * Nome: 
 */
public abstract class ID implements Comparable
{
	// EQUIVALE A "y"
	public static final int INFO2_NO_ANTERIOR = -7;

	// EQUIVALE A "z"
	public static final int INFO1_COMPRIMENTO_ONDA = -2;
	
	// EQUIVALE A "z"
	public static final int INFO2_COMPRIMENTO_ONDA = -2;
	
	// EQUIVALE A "e"
	public static final int INFO2_SENTIDO_LASER = -6;
	
	// EQUIVALE A "d"
	public static final int INFO2_SENTIDO_3R = -5;
	
	// EQUIVALE A "a"
	public static final int INFO2_SENTIDO_RX = -4;
	
	// EQUIVALE A "?"
	public static int DESCONHECIDO = -3;

	// EQUIVALE A "?"
	public static final int INFO2_DESCONHECIDO = -3;
	
	// EQUIVALE A "z"
	public static final int INFO1_DESCONHECIDO = -2;
	
	// EQUIVALE A "X"
	public static int ELEMENTO_DESCONHECIDO = -1;
	
	// EQUIVALE A ""
	public static final int CONHECIDO = 0;

	private String[] mapDesconhecidos = {"","x","z","?","a","d","e","y",""};


	protected int numeroDoElemento;
	
	protected int categoria;
	protected int info1;
	protected int info2;

	//	private String idMasString;
	protected boolean selfAlarm;
	protected boolean outAlarm;
	protected boolean maskAlarm;


	public abstract void setNumeroDoElemento(String p_numeroDoElemento);
	public abstract String getNumeroDoElemento();

	/**
	 * @param l_componenteIdString
	 * @return
	 */
	public static ID criaNovoID(String l_componenteIdString)
	{
		ID id;
		if(l_componenteIdString.trim().startsWith("(") && l_componenteIdString.trim().endsWith(")")){
			id = new IdMas(l_componenteIdString);
		}else{
			id = new IDGenerico(l_componenteIdString);
		}
		return id;
	}
	/**
	 * @param info2
	 * @return
	 */
	protected String getDesconhecido(int p_info)
	{
		return mapDesconhecidos[-p_info];
	}
	protected int getDesconhecido(String p_info){
		for (int i = 0; i < mapDesconhecidos.length; i++)
		{
			if (mapDesconhecidos[i].equalsIgnoreCase(p_info))
				return -i; 
		}
		return CONHECIDO;
	}
	/**
	 * @return
	 */
	abstract public int getFator();
	/**
	 * @return
	 */
	abstract public boolean isSelfAlarm();
	/**
	 * @return
	 */
	abstract public boolean isOutAlarm();
}
