/*
 * IdMas.java Criado em 06/01/2004
 *
 * Todo Codigo criado abaixo, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e não seja para fins lucra-
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
 * Ela é amplamente utilizada em todo as outras classes.
 *  
 */
public class IdMasFuncionando extends ID implements Comparable
{

	private String idMasString;
	private boolean selfAlarm;
	private boolean outAlarm;
	private boolean maskAlarm;

	private String categoria;
	private String info1;
	private String info2;

	/**
	 * 
	 */
	public IdMasFuncionando()
	{
		selfAlarm = false;
		outAlarm = false;
		maskAlarm = false;

		categoria = "0";
		numeroDoElemento = "x";
		info1 = "z";
		info2 = "?";

		doString();

	}
	/**
	 * Inicializa um novo ID da Mas com os valores passados.
	 * 
	 * @param p_categoria
	 * @param p_numElemento
	 * @param p_info1
	 * @param p_info2
	 */
	public IdMasFuncionando(String p_categoria, String p_numElemento, String p_info1, String p_info2)
	{
		setCategoria(p_categoria);
		setNumeroDoElemento(p_numElemento);
		setInfo1(p_info1);
		setInfo2(p_info2);

		doString();

	}

	/**
	 * 
	 */
	private void doString()
	{
		idMasString = "(" + categoria + " " + numeroDoElemento + " " + info1 + " " + info2 + ")";
	}
	/**
	 * Inicializa um novo ID da Mas com a string recebida, representando o ID.
	 * 
	 * @param p_IdMas
	 */
	public IdMasFuncionando(String p_IdMas)
	{
		setIdMas(p_IdMas);
		
		doString();

	}
	/**
	 * @param p_IdMas
	 */
	public void setIdMas(String p_IdMas)
	{
		// Caso o Id venha no formato bem resumido
		// o que acontece quando ele é fornecido na biblioteca
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

		//	o Id pode vir separado por virgura ou Espaço
		StringTokenizer l_st = new StringTokenizer(l_IdMas, " ,");

		categoria = l_st.nextToken();

		if (categoria.startsWith("("))
		{
			categoria = categoria.substring(1);
		}
		numeroDoElemento = l_st.nextToken();
		info1 = l_st.nextToken();
		info2 = l_st.nextToken();
		if (info2.endsWith(")"))
		{
			info2 = info2.substring(0, info2.indexOf(")"));
		}
		
		doString();

	}
	/**
	 * Retorna o Numero do Elemento no qual o componente que este
	 * id Representa está contido.
	 * 
	 * @return
	 */
	public String getNumeroDoElemento()
	{
		return this.numeroDoElemento;
	}

	/**
	 * Seta a categoria do Componente que este ID representa
	 * 
	 * @param p_categoria
	 */
	public void setCategoria(String p_categoria)
	{
		this.categoria = p_categoria;
		
		doString();
	}
	public String toString()
	{
		return idMasString;
	}

	/**
	 * Seta o Numero do Elemento no qual o Componente que este ID representa
	 * está presente
	 * 
	 * @param p_numeroDoElemento
	 */
	public void setNumeroDoElemento(String p_numeroDoElemento)
	{
		if (p_numeroDoElemento.substring(0, 1).equalsIgnoreCase(Elemento.PREFIXO_LINK))
		{
			numeroDoElemento = "0";
		} else if (p_numeroDoElemento.length() > 2)
		{
			numeroDoElemento = p_numeroDoElemento.substring(1, 3);
		} else
		{
			numeroDoElemento = p_numeroDoElemento;
		}

		doString();

	}
	/**
	 * @param p_info1
	 */
	public void setInfo1(String p_info1)
	{
		if (p_info1.length() > 2)
			info1 = p_info1.substring(1);
		else
			info1 = p_info1;
			
		doString();
	}
	/**
	 * @return
	 */
	public String getInfo1()
	{
		return info1;
	}
	/**
	 * Seta a Informação numero 2 deste componente
	 * 
	 * @param p_info2
	 */
	public void setInfo2(String p_info2)
	{
		if (p_info2.length() > 2)
			this.info2 = p_info2.substring(1);
		else
			this.info2 = p_info2;
			
		doString();
	}
	/**
	 * Pega a informação numero 2 deste componente
	 * 
	 * @return
	 */
	public String getInfo2()
	{
		return info2;
	}

	/**
	 * Expande o ID para representar a verdadeira posição do componente
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
			// Uma vez que o canal é composto pelos nos e links, para facilitar 
			// ele deve pegar o no anterior, e não o link por isto ele volta duas
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

		info2 = info2.replaceAll("a", String.valueOf(1 + l_fator));

		info2 = info2.replaceAll("d", String.valueOf(4 + l_fator));

		info2 = info2.replaceAll("e", String.valueOf(5 + l_fator));

		info1 = info1.replaceAll("z", l_comprimentoDeOndaAtual);
		info2 = info2.replaceAll("z", l_comprimentoDeOndaAtual);

		info2 = info2.replaceAll("y", getNumeroDoID(l_idNoAnterior));
		
		doString();

		return toString();
	}

	/**
	 * Inverte o sentido de uma identificação, é muito usado em caso de fibra
	 * que pode estar se referindo a uma conexão bi lateral, mas somente é 
	 * indicada uma das direções.
	 * 
	 * @param p_idMas
	 * @return
	 */
	private IdMasFuncionando inverteSentido(IdMasFuncionando p_idMas)
	{
		String l_categoria = p_idMas.getCategoria();
		String l_numElemento = p_idMas.getNumeroDoElemento();
		String l_info1 = p_idMas.getInfo1();
		String l_info2 = p_idMas.getInfo2();

		return new IdMasFuncionando(l_categoria, l_numElemento, l_info2, l_info1);
	}
	
	private String getNumeroDoID(String p_idNo)
	{
		if ((p_idNo == null) || (p_idNo == ""))
		{
			return "";
		} else
		{
			return p_idNo.substring(1, 3);
		}
	}
	/**
	 * @return
	 */
	public String getCategoria()
	{
		return categoria;
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
		if (categoria.equals("1") || categoria.equals("3"))
			return true;
		else
			return false;
	}
	/**
	 * @return
	 */
	public boolean isMaskAlarm()
	{
		if (categoria.equals("3"))
			return true;
		else
			return false;
	}
	/**
	 * @return
	 */
	public boolean isOutAlarm()
	{
		if (categoria.equals("2"))
			return true;
		else
			return false;
	}
	/**
	 * @return
	 */
	public boolean isPassive()
	{
		if (categoria.equals(Componente.S_PASSIVE))
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
	/* (não-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object p_idMas)
	{
		return getFator() - ((IdMasFuncionando)p_idMas).getFator();
	}
	

	/**
	 * Pega o Fator de comparação deste objeto IDMas para ser usado 
	 * no metodo comparaTo.
	 * 
	 * @return - Fator inteiro que indica uma possição relativa no universo da topologia data.
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

		l_elemento =
			l_elemento.equalsIgnoreCase("x")
				? "0" 
				: l_elemento;

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
	public boolean equals(IdMasFuncionando p_idMas)
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
		// o que acontece quando ele é fornecido na biblioteca
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

		//	o Id pode vir separado por virgura ou Espaço
		StringTokenizer l_st = new StringTokenizer(l_IdMas, " ,");

		l_st.nextToken();
		
		return l_st.nextToken();

	}
	public int hashCode(){
		return idMasString.hashCode();
	}
	
}
