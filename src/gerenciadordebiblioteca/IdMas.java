/*
 * IdMas.java Criado em 06/01/2004
 *
 * Todo Codigo criado abaixo, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e n�o seja para fins lucra-
 * tivos capitalistas.
 * 
 */
package gerenciadordebiblioteca;

import java.util.StringTokenizer;

import gerenciadordetopologia.Canal;

/**
 * @author Carlos Delfino
 * email suporte@carlosdelfino.eti.br
 * site http://www.carlosdelfino.eti.br
 * 
 * Esta classe representa o ID da Mas.
 * Ela � amplamente utilizada em todo as outras classes.
 *  
 */
public class IdMas extends ID implements Comparable
{

	/**
	 * 
	 */
	public IdMas()
	{
		selfAlarm = false;
		outAlarm = false;
		maskAlarm = false;

		categoria = 0;
		numeroDoElemento = ID.ELEMENTO_DESCONHECIDO;
		info1 = ID.INFO1_DESCONHECIDO;
		info2 = ID.INFO2_DESCONHECIDO;

	}
	/**
	 * Inicializa um novo ID da Mas com os valores passados.
	 * 
	 * @param p_categoria
	 * @param p_numElemento
	 * @param p_info1
	 * @param p_info2
	 */
	public IdMas(String p_categoria, String p_numElemento, String p_info1, String p_info2)
	{
		setCategoria(p_categoria);
		setNumeroDoElemento(p_numElemento);
		setInfo1(p_info1);
		setInfo2(p_info2);

	}
	/**
	 * Inicializa um novo ID da Mas com a string recebida, representando o ID.
	 * 
	 * @param p_IdMas
	 */
	public IdMas(String p_IdMas)
	{
		setIdMas(p_IdMas);

	}
	/**
	 * @param p_IdMas
	 */
	public void setIdMas(String p_IdMas)
	{
		// Caso o Id venha no formato bem resumido
		// o que acontece quando ele � fornecido na biblioteca
		// preciso espandi-lo
		String l_IdMas = "";
		if (p_IdMas.length() == 4)
		{
			for (int i = 0; i < p_IdMas.length() - 1; i++)
			{
				l_IdMas += p_IdMas.substring(i, i + 1) + " ";
			}
			l_IdMas += p_IdMas.substring(p_IdMas.length() - 1);
		} else
		{
			l_IdMas = p_IdMas;
		}

		//	o Id pode vir separado por virgura ou Espa�o
		StringTokenizer l_st = new StringTokenizer(l_IdMas, " ,");

		String categoriaString = l_st.nextToken();

		if (categoriaString.startsWith("("))
			categoria = Integer.parseInt(categoriaString.substring(1));
		else
			categoria = Integer.parseInt(categoriaString);

		String numeroDoElementoString = l_st.nextToken();
		if (numeroDoElementoString.equals(getDesconhecido(ID.ELEMENTO_DESCONHECIDO)))
		{
			numeroDoElemento = ID.ELEMENTO_DESCONHECIDO;
		} else
		{
			numeroDoElemento = Integer.parseInt(numeroDoElementoString);
		}

		String info1String = l_st.nextToken();
		int tmpInfo1 = getDesconhecido(info1String);
		if (tmpInfo1 != ID.CONHECIDO)
		{
			info1 = tmpInfo1;
		} else
		{
			info1 = Integer.parseInt(info1String);
		}

		String info2String = l_st.nextToken();
		if (info2String.endsWith(")"))
		{
			int tmpInfo2 = getDesconhecido(info2String.substring(0, info2String.indexOf(")")));
			if (tmpInfo2 != ID.CONHECIDO)
			{
				info2 = tmpInfo2;
			} else
			{
				info2 = Integer.parseInt(info2String.substring(0, info2String.indexOf(")")));
			}
		} else
		{
			int tmpInfo2 = getDesconhecido(info2String);
			if (tmpInfo2 != ID.CONHECIDO)
			{
				info2 = tmpInfo2;
			} else
			{
				info2 = Integer.parseInt(info2String);
			}
		}

	}
	/**
	 * Retorna o Numero do Elemento no qual o componente que este
	 * id Representa est� contido.
	 * 
	 * @return
	 */
	public String getNumeroDoElemento()
	{
		if (numeroDoElemento < 0)
			return getDesconhecido(numeroDoElemento);
		else
			return String.valueOf(numeroDoElemento);
	}

	/**
	 * Seta a categoria do Componente que este ID representa
	 * 
	 * @param p_categoria
	 */
	public void setCategoria(String p_categoria)
	{
		this.categoria = Integer.parseInt(p_categoria);

	}
	public String toString()
	{
		return "("
			+ getCategoria()
			+ " "
			+ getNumeroDoElemento()
			+ " "
			+ getInfo1()
			+ " "
			+ getInfo2()
			+ ")";
	}

	/**
	 * Seta o Numero do Elemento no qual o Componente que este ID representa
	 * est� presente
	 * 
	 * @param p_numeroDoElemento
	 */
	public void setNumeroDoElemento(String p_numeroDoElemento)
	{
		if (p_numeroDoElemento.substring(0, 1).equalsIgnoreCase(Elemento.PREFIXO_LINK))
		{
			numeroDoElemento = 0;
		} else if (p_numeroDoElemento.length() > 2)
		{
			numeroDoElemento = Integer.parseInt(p_numeroDoElemento.substring(1, 3));
		} else
		{
			numeroDoElemento = Integer.parseInt(p_numeroDoElemento);
		}

	}
	/**
	 * @param p_info1
	 */
	public void setInfo1(String p_info1)
	{
		if (p_info1.startsWith("n"))
			info1 = Integer.parseInt(p_info1.substring(1));
		else
			info1 = Integer.parseInt(p_info1);

	}
	/**
	 * @return
	 */
	public String getInfo1()
	{
		if (info1 < 0)
			return getDesconhecido(info1);
		else
			return String.valueOf(info1);
	}

	/**
	 * Seta a Informa��o numero 2 deste componente
	 * 
	 * @param p_info2
	 */
	public void setInfo2(String p_info2)
	{
		if (p_info2.startsWith("n"))
			info2 = Integer.parseInt(p_info2.substring(1));
		else
			info2 = Integer.parseInt(p_info2);

	}
	/**
	 * Pega a informa��o numero 2 deste componente
	 * 
	 * @return
	 */
	public String getInfo2()
	{
		if (info2 < 0)
			return getDesconhecido(info2);
		else
			return String.valueOf(info2);
	}

	/**
	 * Expande o ID para representar a verdadeira posi��o do componente
	 * na topologia.
	 * 
	 * @param p_oCanal
	 * @param p_oElemento
	 * @return
	 */
	public String expandeIdMas(Elemento p_oElemento, Canal p_oCanal)
	{
		String l_idNo = p_oElemento.getId();

		String l_oSentido = p_oCanal.getSentidoElemento(l_idNo);

		//		String l_idUltimoNoCanal = ((Elemento)p_oCanal.get(p_oCanal.size()-1)).getId();
		String l_comprimentoDeOndaAtual = p_oCanal.getComprimentoDeOndaAtual();

		int l_indiceNoAtual = p_oCanal.indexOf(p_oElemento);
		String l_idNoAnterior = "";
		if ((l_indiceNoAtual > 0) && !(p_oElemento instanceof ElementoLink))
		{
			// Uma vez que o canal � composto pelos nos e links, para facilitar 
			// ele deve pegar o no anterior, e n�o o link por isto ele volta duas
			// casas.
			l_idNoAnterior = ((Elemento)p_oCanal.get(l_indiceNoAtual - 2)).getId();
		}

		int l_fator = 0;

		if (l_oSentido.equalsIgnoreCase("ccw"))
		{
			//String tipo = p_oElemento.getTipoElemento();
			if (p_oElemento instanceof ElementoLink)
			{
				String l_oIdMas = inverteSentido(this).toString();
				return l_oIdMas;
			}
			l_fator = 1;

		}

		switch (info2)
		{
			case ID.INFO2_SENTIDO_RX :
				info2 = 1 + l_fator;
				break;
			case ID.INFO2_SENTIDO_3R :
				info2 = 4 + l_fator;

				break;
			case ID.INFO2_SENTIDO_LASER :
				info2 = 5 + l_fator;

				break;
			case ID.INFO2_COMPRIMENTO_ONDA :
				info2 = Integer.parseInt(l_comprimentoDeOndaAtual);

				break;
			case ID.INFO2_NO_ANTERIOR :
				info2 = getNumeroDoID(l_idNoAnterior);
				break;
		}

		switch (info1)
		{
			case ID.INFO1_COMPRIMENTO_ONDA :
				info1 = Integer.parseInt(l_comprimentoDeOndaAtual);
				break;
		}

		return toString();
	}

	/**
	 * Inverte o sentido de uma identifica��o, � muito usado em caso de fibra
	 * que pode estar se referindo a uma conex�o bi lateral, mas somente � 
	 * indicada uma das dire��es.
	 * 
	 * @param p_idMas
	 * @return
	 */
	private IdMas inverteSentido(IdMas p_idMas)
	{
		String l_categoria = p_idMas.getCategoria();
		String l_numElemento = p_idMas.getNumeroDoElemento();
		String l_info1 = p_idMas.getInfo1();
		String l_info2 = p_idMas.getInfo2();

		return new IdMas(l_categoria, l_numElemento, l_info2, l_info1);
	}

	private int getNumeroDoID(String p_idNo)
	{
		if ((p_idNo == null) || (p_idNo == ""))
		{
			return ID.ELEMENTO_DESCONHECIDO;
		} else
		{
			return Integer.parseInt(p_idNo.substring(1, 3));
		}
	}
	/**
	 * @return
	 */
	public String getCategoria()
	{
		return String.valueOf(categoria);
	}

	/**
	 * @return
	 */
	public int getCategoriaNum()
	{
		return Integer.parseInt(getCategoria());
	}
	/**
	 * @param p_Categoria
	 */
	public void setCategoria(int p_Categoria)
	{
		setCategoria(String.valueOf(p_Categoria));
	}
	/**
	 * 
	 */
	public boolean isSelfAlarm()
	{
		if (categoria == 1 || categoria == 3)
			return true;
		else
			return false;
	}
	/**
	 * @return
	 */
	public boolean isMaskAlarm()
	{
		if (categoria == 3)
			return true;
		else
			return false;
	}
	/**
	 * @return
	 */
	public boolean isOutAlarm()
	{
		if (categoria == 2)
			return true;
		else
			return false;
	}
	/**
	 * @return
	 */
	public boolean isPassive()
	{
		if (categoria == Componente.PASSIVE)
			return true;
		else
			return false;
	}

	/**
	 * @param b
	 */
	public void setSelfAlarm(boolean b)
	{
		if (b)
		{
			setCategoria(Componente.SELF_ALARM);
			selfAlarm = true;
			outAlarm = false;
			maskAlarm = false;
		} else
		{
			setCategoria(Componente.PASSIVE);
			selfAlarm = false;
			outAlarm = false;
			maskAlarm = false;

		}
	}
	/**
	 * @param b
	 */
	public void setOutAlarm(boolean b)
	{
		if (b)
		{
			setCategoria(Componente.OUT_ALARM);
			selfAlarm = false;
			outAlarm = true;
			maskAlarm = false;
		} else
		{
			setCategoria(Componente.PASSIVE);
			selfAlarm = false;
			outAlarm = false;
			maskAlarm = false;

		}
	}
	/**
	 * @param b
	 */
	public void setMaskAlarm(boolean b)
	{
		if (b)
		{
			setCategoria(Componente.MASK_ALARM);
			selfAlarm = true;
			outAlarm = false;
			maskAlarm = true;
		} else
		{
			setCategoria(Componente.PASSIVE);
			selfAlarm = false;
			outAlarm = false;
			maskAlarm = false;

		}
	}
	/**
	 * @param p_componente
	 */
	public void setComponente(Componente p_componente)
	{
		Componente componente = p_componente;
	}
	/* (n�o-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object p_idMas)
	{
		return getFator() - ((IdMas)p_idMas).getFator();
	}

	/**
	 * Pega o Fator de compara��o deste objeto IDMas para ser usado 
	 * no metodo comparaTo.
	 * 
	 * @return - Fator inteiro que indica uma possi��o relativa no universo da topologia data.
	 */
	public int getFator()
	{
		String l_info1 = getInfo1();
		String l_info2 = getInfo2();
		String l_elemento = getNumeroDoElemento();
		String l_categoria = getCategoria();

		l_info1 =
			(l_info1.equalsIgnoreCase("y")
				|| l_info1.equalsIgnoreCase("z")
				|| l_info1.equalsIgnoreCase("?"))
				? "0"
				: l_info1;

		l_info2 =
			(l_info2.equalsIgnoreCase("x")
				|| l_info2.equalsIgnoreCase("a")
				|| l_info2.equalsIgnoreCase("d")
				|| l_info2.equalsIgnoreCase("y")
				|| l_info2.equalsIgnoreCase("z")
				|| l_info2.equalsIgnoreCase("e")
				|| l_info2.equalsIgnoreCase("?"))
				? "0"
				: l_info2;

		l_elemento = l_elemento.equalsIgnoreCase("x") ? "0" : l_elemento;

		/*
		 * yyxxzzvv sendo que:
		 * yy equivale a categoria vezes 1,000,000
		 * xx equivale a elemento vezes     10,000
		 * zz equivale a info1 vezes           100
		 * vv equivale a info2 vezes             1
		 * e a soma de todos.
		 */
		int yy = (int) (new Integer(l_categoria).intValue() * (10E5));
		int xx = (int) (new Integer(l_elemento).intValue() * (10E3));
		int zz = (int) (new Integer(l_info1).intValue() * (10E1));
		int vv = (int) (new Integer(l_info2).intValue());

		return yy + xx + zz + vv;

	}

	/**
	 * Verifica a igualdade entre a instancia do ID e o ID passado
	 * 
	 * @param p_idMas
	 * @return
	 */
	public boolean equals(ID p_idMas)
	{
		return this.compareTo(p_idMas) == 0;
		//		return idMasString.equals(p_idMas.toString());
	}

	/**
	 * @param p_idMas
	 */
	public static String getIdElemento(String p_idMas)
	{
		// Caso o Id venha no formato bem resumido
		// o que acontece quando ele � fornecido na biblioteca
		// preciso espandi-lo
		String l_IdMas = "";
		if (p_idMas.length() == 4)
		{
			for (int i = 0; i < p_idMas.length() - 1; i++)
			{
				l_IdMas += p_idMas.substring(i, i + 1) + " ";
			}
			l_IdMas += p_idMas.substring(p_idMas.length() - 1);
		} else
		{
			l_IdMas = p_idMas;
		}

		//	o Id pode vir separado por virgura ou Espa�o
		StringTokenizer l_st = new StringTokenizer(l_IdMas, " ,");

		l_st.nextToken();

		return l_st.nextToken();

	}
	public int hashCode()
	{
		return toString().hashCode();
	}
	/* (n�o-Javadoc)
	 * @see gerenciadordebiblioteca.ID#compareTo(gerenciadordebiblioteca.ID)
	 */
}
