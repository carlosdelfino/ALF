/*
 * Criado em 14/12/2003
 *
 * Para alterar o gabarito para este arquivo gerado v� para
 * Janela&gt;Prefer�ncias&gt;Java&gt;Gera��o de C�digos&gt;C�digo e Coment�rios
 */
package biblioteca;

/**
 * @author Carlos Delfino
 *
 * Para alterar o gabarito para este coment�rio do tipo gerado v� para
 * Janela&gt;Prefer�ncias&gt;Java&gt;Gera��o de C�digos&gt;C�digo e Coment�rios
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
