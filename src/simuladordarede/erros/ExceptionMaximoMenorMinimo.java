/*
 * ExceptionMaximoMenorMinimo.java Criado em 31/01/2004
 *
 * Todo Codigo criado abaixo, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e não seja para fins lucra-
 * tivos capitalistas.
 * 
 */
package simuladordarede.erros;

/**
 * @author Carlos Delfino
 * email suporte@carlosdelfino.eti.br
 * site http://www.carlosdelfino.eti.br
 * 
 * Digite aqui o que esta classe faz.
 * Nome: 
 */
public class ExceptionMaximoMenorMinimo extends Exception
{
	private static String msg = "\nO numero fornecido para o Valor máximo é menor que o valor mínimo!\n";
	
	public ExceptionMaximoMenorMinimo(){
		super(msg);
	}

	/**
	 * @param msg
	 */
	public ExceptionMaximoMenorMinimo(String p_msg)
	{
		super(msg + "*** " + p_msg + " ***\n");
	}
}
