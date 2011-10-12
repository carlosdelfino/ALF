/*
 * ExceptionTopologiaNaoDefinida.java Criado em 24/01/2004
 *
 * Todo Codigo criado abaixo, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e não seja para fins lucra-
 * tivos capitalistas.
 * 
 */
package biblioteca.erros;

/**
 * @author Carlos Delfino
 * email suporte@carlosdelfino.eti.br
 * site http://www.carlosdelfino.eti.br
 * 
 * Digite aqui o que esta classe faz.
 * Nome: 
 */
public class ExceptionBibliotecaNaoDefinida extends Exception
{
	static String msg = "A biblioteca não foi defina ainda, use o metodo setBiblioteca()";
	
	/**
	 * Cria uma Exception para Topologia nâo definida. 
	 *
	 */
	public ExceptionBibliotecaNaoDefinida(){
		super(msg);
	}
}
