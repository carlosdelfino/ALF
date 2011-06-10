/*
 * Criado em 14/12/2003
 *
 * Para alterar o gabarito para este arquivo gerado vá para
 * Janela&gt;Preferências&gt;Java&gt;Geração de Códigos&gt;Código e Comentários
 */
package biblioteca;

/**
 * @author Carlos Delfino
 *
 * Para alterar o gabarito para este comentário do tipo gerado vá para
 * Janela&gt;Preferências&gt;Java&gt;Geração de Códigos&gt;Código e Comentários
 */
public interface Modelos extends Cloneable {
	
	/**
	 * Explique aqui o que este metodo faz.
	 * @return
	 */
	public String getNome();
	
	/**
	 * Explique aqui o que este metodo faz.
	 * @param string
	 */
	public void setNome(String string);
	
	/**
	 * Explique aqui o que este metodo faz.
	 * @param idComp
	 */
	public void setId(String idComp);
	
	/**
	 * Retorna o ID dste componente.
	 * 
	 */
	public String getId();
	

	public Elemento copia() throws CloneNotSupportedException;	

}
