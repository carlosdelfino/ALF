/*
 * ListaSuspeitos.java Criado em 21/03/2004
 *
 * Todo Codigo criado abaixo, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e não seja para fins lucra-
 * tivos capitalistas.
 * 
 */
package correlacaodealarmes;

import java.util.ArrayList;

/**
 * @author Carlos Delfino
 * @email suporte@carlosdelfino.eti.br
 * @site http://www.carlosdelfino.eti.br
 * 
 * Digite aqui o que esta classe faz.
 * Nome: 
 */
class ListaSuspeitos extends ArrayList
{
	public int indexOf(Suspeito elem)
	{
		if (elem == null)
		{
			return super.indexOf(elem);
		} else
		{
			for (int i = 0; i < super.size(); i++)
				if (elem.equals((Suspeito)super.get(i)))
					return i;
		}
		return -1;
	}
}
