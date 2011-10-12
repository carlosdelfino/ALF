package biblioteca;

/**
 * 
 * @author Carlos Delfino
 * email suporte@carlosdelfino.eti.br
 * site http://www.carlosdelfino.eti.br
 *
 * Para alterar o gabarito para este comentário do tipo gerado vá para
 * Janela&gt;Preferências&gt;Java&gt;Geração de Códigos&gt;Código e Comentários
 */
public interface Modelos {
	
	/**
	 * <p>Pega o nome que identifica o Modelo.</p>
	 * <p>este nome é definido na TagXML como <i>nome</i>.</p>
	 * 
	 * <code><Componente id="identificador" <i>nome="nome do componente"</i> /></code>
	 * 
	 * @return uma string contendo o nome do Modelo.
	 * 
	 */
	public String getNomeModelo();
	
	/**
	 * <p>Define o nome que identifica o Modelo.</p>
	 * <p>este nome é definido na TagXML como <i>nome</i>.</p>
	 * 
	 * <code><Componente id="identificador" <i>nome="nome do componente"</i> /></code>
	 * 
	 */
	public void setNomeModelo(String nome);
	
	/**
	 * <p>Define o ID que identifica o Modelo. Este ID será
	 * usado em Biblioteca para resgatar o modelo.</p>
	 * 
	 * <p>Este ID é definido na TagXML como <i>id</i>.</p>
	 * 
	 * <code><Componente <i>id="identificador"</i> nome="nome do componente" /></code>
	 * 
	 */
	public void setIdModelo(String id);
	
	/**
	 * <p>Retorna o ID que identifica o Modelo. Este ID será
	 * usado em Biblioteca para resgatar o modelo.</p>
	 * 
	 * <p>Este ID é definido na TagXML como <i>id</i>.</p>
	 * 
	 * <code><Componente <i>id="identificador"</i> nome="nome do componente" /></code>
	 *
	 * @return uma String contendo o ID do Modelo. 
	 */
	public String getIdModelo();
	
//	/**
//	 * <p> Gera uma copia do Modelo, usando os dados 
//	 * contidos no Object elementoXML, este parametro
//	 * pode ser qualquer tipo de objeto, conforme a 
//	 * classe do objeto, ele retornara um tipo de copia.
//	 * por exemplo se eu passar um objeto XML ele usa o 
//	 * Modelo para criar um Componente ou Elemento de Rede
//	 * configurado com os parametros o Objeto XML define,
//	 * mas seu passar um outro Modelo(um Modelo de Elemento)
//	 * ela me cria uma copia do Modelo de Componente para
//	 * compor o Elemento de Rede.
//	 * 
//	 * @param elementoXML
//	 * @return um Objeto conforme o contexto que esta sendo usado.
//	 */
//	public Object copia(Object elementoXML);
//	
}
