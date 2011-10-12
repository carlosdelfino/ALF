/*
 * ExceptionCanaisNaoDefinidos.java Criado em 24/01/2004
 *
 * Todo Codigo criado abaixo, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e não seja para fins lucra-
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
	static String msg = "Os canais não forão defino ainda, use o metodo setCanais() ou crie o simulador com os Canais padrões";
	
	/**
	 * Cria uma Exception padrão para Canais não definidos 
	 *
	 */
	public ExceptionCanaisNaoDefinidos(){
		super(msg);
	}

}
