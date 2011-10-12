/*
 * ModeloComponenteAtivo.java Criado em 17/01/2004
 *
 * Todo Codigo criado abaixo, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e não seja para fins lucra-
 * tivos capitalistas.
 * 
 */
package gerenciadordebiblioteca;

/**
 * @author Carlos Delfino
 * email suporte@carlosdelfino.eti.br
 * site http://www.carlosdelfino.eti.br
 * 
 * Digite aqui o que esta classe faz.
 * Nome: 
 */
public class ModeloComponenteAtivo extends ModeloComponente {

	/**
	 * Inicializa um Componente Ativo da categoria SELF_ALARM
	 * 
	 */
	ModeloComponenteAtivo() {
		setCategoria(SELF_ALARM);
		
	}

	/**
	 * Inicializa um Componente Ativo resebendo a categoria como parametro
	 * 
	 * @param p_categoria
	 */
	ModeloComponenteAtivo(int p_categoria) {
		setCategoria(p_categoria);
	}

}
