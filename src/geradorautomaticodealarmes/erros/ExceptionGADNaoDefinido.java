/*
 * ExceptionGADNaoDefinido.java Criado em 31/01/2004
 *
 * Todo Codigo criado abaixo, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e n�o seja para fins lucra-
 * tivos capitalistas.
 * 
 */
package geradorautomaticodealarmes.erros;

/**
 * @author Carlos Delfino
 * @email suporte@carlosdelfino.eti.br
 * @site http://www.carlosdelfino.eti.br
 * 
 * Digite aqui o que esta classe faz.
 * Nome: 
 */
public class ExceptionGADNaoDefinido extends Exception
{
	static String msg = "Gerador de Dominios n�o foi defino ainda, use o metodo setGAD() ou crie o simulador com um Gerador de Topologia padr�o";
	public ExceptionGADNaoDefinido(){
		super(msg);
	}
}
