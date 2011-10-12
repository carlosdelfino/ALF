/*
 * ExceptionCanaisNaoDefinidos.java Criado em 24/01/2004
 *
 * Todo Codigo criado abaixo, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e n�o seja para fins lucra-
 * tivos capitalistas.
 * 
 */
package geradorautomaticodealarmes.erros;

/**
 * @author Carlos Delfino
 * email suporte@carlosdelfino.eti.br
 * site http://www.carlosdelfino.eti.br
 * 
 * Digite aqui o que esta classe faz.
 * Nome: 
 */
public class ExceptionCanaisNaoDefinidos extends Exception
{
	static String msg = "Os canais n�o for�o defino ainda, use o metodo setCanais() ou crie o simulador com os Canais padr�es";
	
	/**
	 * Cria uma Exception padr�o para Canais n�o definidos 
	 *
	 */
	public ExceptionCanaisNaoDefinidos(){
		super(msg);
	}

}
