/*
 * ExceptionTopologiaNaoDefinida.java Criado em 24/01/2004
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
public class ExceptionTopologiaNaoDefinida extends Exception
{
	static String msg = "A topologia n�o foi defina ainda, use o metodo setTopologia() ou cria o simulador com uma topologia padr�o";
	
	/**
	 * Cria uma Exception para Topologia n�o definida. 
	 *
	 */
	public ExceptionTopologiaNaoDefinida(){
		super(msg);
	}
}
