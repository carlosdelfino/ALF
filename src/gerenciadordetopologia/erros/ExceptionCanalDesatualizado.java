/*
 * ExceptionCanalDesatualizado.java Criado em 27/01/2004
 *
 * Todo Codigo criado abaixo, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e não seja para fins lucra-
 * tivos capitalistas.
 * 
 */
package gerenciadordetopologia.erros;

/**
 * @author Carlos Delfino
 * @email suporte@carlosdelfino.eti.br
 * @site http://www.carlosdelfino.eti.br
 * 
 * Digite aqui o que esta classe faz.
 * Nome: 
 */
public class ExceptionCanalDesatualizado extends Exception
{
	private static String msg = "O canal pode estar sendo atualizado por outra \n thread verifique isto, e solicite uma atualização\n";
	public ExceptionCanalDesatualizado(){
		super(msg);
	}
}
