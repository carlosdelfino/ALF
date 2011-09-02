/*
 * Created on 09/12/2003
 * 
 * Todo Codigo criado por min, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e n�o seja para fins lucra-
 * tivos capitalistas.
 * 
 */

package executaveis;
import topologia.Topologia;
import biblioteca.Biblioteca;


/**
 * Classe base, aqui est� o que podemos chamar de contrato
 * principal das classes.
 * 
 * Quando chamado cria a biblioteca de elementos, depois cria 
 * o mapa da rede, e posteriormente gera um arquivo XML com
 * a montagem da rede Fisica.
 * 
 * Cria��o: 09/12/2003 - 19:49:00
 * @author: Carlos Delfino
 * 
 */
public class Principal
{
	/**
	 * Classe base, aqui est� o que podemos chamar de contrato
	 * principal das classes.
	 * 
	 * Quando chamado cria a biblioteca de elementos, depois cria 
	 * o mapa da rede, e posteriormente gera um arquivo XML com
	 * a montagem da rede Fisica.
	 * 
	 * Cria��o: 09/12/2003 - 19:49:00
	 * @author: Carlos Delfino
	 * 
	 */
	//Nome e Localiza��o dos arquivos de informa��es.
	private static final String arqBiblioteca = "cnf/biblioteca.xml";
	private static final String arqTopologia  = "cnf/topologia.xml";
	private static final String arqRotaFisica = "cnf/rotafisica.xml";
	private static final String arqMapeamento = "cnf/mapeamento.xml";
	   
		
	private static Biblioteca biblioteca;
	private static Topologia topologia; 
	public static void main(String[] args)
	{
		System.out.println("Gerando Biblioteca");
		// Instancia a biblioteca com o conte�do do arquivo
		// biblioteca.xml.
		biblioteca = new Biblioteca(arqBiblioteca);
			
		// Gera uma representa��o textual da Biblioteca de componentes.
		System.out.println(biblioteca.toString());
			
		System.out.println("Gerando Mapa da Rede");
		// Gera o Mapa da Rede.
		// processo similar ao da Biblioteca, passa a 
		// biblioteca como argumenta para se basear
		// nos dados da mesma para obter os elementos 
		// da rede
		topologia = new Topologia(arqTopologia, biblioteca);
		
		System.out.println("Mapa por ordem numerica dos N�s");
		// imprime uma vers�o textual da Topologia, agora que 
		// ela foi transformada em um objeto Java, pode 
		// ser usada para montar tamb�m uma visualiza��o grafica
		System.out.println(topologia.toString());
			
	}
}

