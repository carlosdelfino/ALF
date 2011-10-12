/*
 * Created on 09/12/2003
 * 
 * Todo Codigo criado por min, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e não seja para fins lucra-
 * tivos capitalistas.
 * 
 */
package gerenciadordebiblioteca;

import geradorautomaticodealarmes.Alarme;

import org.w3c.dom.Element;

import gerenciadordetopologia.Canal;

/**
 * Explique aqui o que é este tipo
 * 
 * Criação 09/12/2003 - 20:06:26
 * @author Carlos Delfino
 * 
 */
public class Componente implements Comparable
{
	public static final String CCW = "ccw";
	public static final String CW = "cw";
	public static final String FIBRA = "fibra";

	public static final String ID_MAS = "idMas";

	public static final int MASK_ALARM = 3;
	public static final int OUT_ALARM = 2;
	public static final int PASSIVE = 0;
	public static final String S_MASK_ALARM = "3";
	public static final String S_OUT_ALARM = "2";
	public static final String S_PASSIVE = "0";
	public static final String S_SELF_ALARM = "1";
	public static final int SELF_ALARM = 1;
	public static final String SWITCH = "sw";
	private Canal canal;

	private Elemento elemento;
	private String id;

	private IdMas idMas = new IdMas();
	private ModeloComponente modeloComponente;

	private String nome;

	// indica se o elemento pode ou não possuir mais de Componente como este
	private boolean unitario = true;
	
	/**
	 * 
	 */
	public Componente()
	{
	}
	/**
	 * 
	 * @param p_componente
	 * @param p_oElemento
	 * @param p_canal
	 */
	public Componente(Componente p_componente, Elemento p_oElemento, Canal p_canal)
	{

		setModeloComponente(p_componente.getModeloComponente());
		setElemento(p_oElemento);

		// sendo fibra ele substitui os dois campos, pelo
		// numero do no anterior e posterior.
		if (getId().equalsIgnoreCase(Componente.FIBRA))
		{
			if(p_canal.getSentidoElemento(p_oElemento).equalsIgnoreCase(Componente.CW)){
				setInfo1(p_oElemento.getId().substring(1, 3));
				setInfo2(p_oElemento.getId().substring(3, 5));
			}else{
				setInfo1(p_oElemento.getId().substring(3, 5));
				setInfo2(p_oElemento.getId().substring(1, 3));
			}
		}else{
			setCanal(p_canal);
		}
		expandeIdMas(p_canal, p_oElemento);
		
		idMas.setComponente(this);

	}
	/**
	 * @param elementoXML
	 */
	public Componente(ModeloComponente modeloComponente, Element elementoXML)
	{
		setId(modeloComponente.getIdModelo());
		setNome(modeloComponente.getNomeModelo());

		setMaskAlarm(modeloComponente.isMaskAlarm());
		setOutAlarm(modeloComponente.isOutAlarm());
		setSelfAlarm(modeloComponente.isSelfAlarm());

		setIdMas(elementoXML.getAttribute(Componente.ID_MAS));
		
		idMas.setComponente(this);

	}	/**
	 * 
	 * @param p_oModeloComponente
	 * @param p_oElemento
	 */
	public Componente(ModeloComponente p_oModeloComponente, Elemento p_oElemento)
	{

		setModeloComponente(p_oModeloComponente);
		setElemento(p_oElemento);

		// se não for uma fibra substitui o X pelo numero do no	
		if (!getNumeroNo().equalsIgnoreCase(ModeloComponente.S_PASSIVE))
		{
			setNumeroElemento(p_oElemento.getId());
		}
		// sendo fibra ele substitui os dois campos, pelo
		// numero do no anterior e posterior.
		else
		{
			setInfo1(p_oElemento.getId().substring(1, 3));
			setInfo2(p_oElemento.getId().substring(3, 5));
		}
		
		idMas.setComponente(this);
	}
	/* (não-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object p_componente)
	{
		
		return idMas.compareTo(((Componente)p_componente).getIdMas());
	}

	/**
	 * 
	 * @param p_elemento
	 * @param p_canal
	 * @return
	 */
	public Componente copia(Elemento p_elemento, Canal p_canal)
	{
		if (this.isUnitario()){
			setCanal(p_canal);
			return this;
		}
		else{
			Componente l_componente = new Componente(this, p_elemento, p_canal);
			return l_componente;
		}
	}

	/**
	 * @return
	 */
	public Alarme emitirAlarme()
	{
		if (isOutAlarm() || isSelfAlarm())
		{
			return new Alarme(getNome(),getIdMas(),0);
		}
		else
			return null;
	}
	/**
	 * @param p_idMas - ID do componente que falho.
	 * @return
	 */
	public Alarme emitirAlarme(ID p_idMas)
	{
		if (getIdMas().equals(p_idMas) && isSelfAlarm()){
			return new Alarme(getNome(),getIdMas(),0);
		}else if (isOutAlarm())
		return new Alarme(getNome(),getIdMas(),0);
		else
			return null;
	}

	/**
	 * 
	 * @param p_oCanal
	 * @param p_oElemento
	 * @return
	 */
	public void expandeIdMas(Canal p_oCanal, Elemento p_oElemento)
	{
		idMas.expandeIdMas(p_oElemento, p_oCanal);
	}
	/**
	 * 
	 */
	private String getCategoria()
	{
		return idMas.getCategoria();
		
	}
	public String getId()
	{
		return id;
	}

	/**
	 * 
	 * @return
	 */
	public IdMas getIdMas()
	{
		return idMas;
	}

	/**
	 * @return
	 */
	public String getInfo1()
	{
		return idMas.getInfo1();
	}

	/**
	 * @return
	 */
	public String getInfo2()
	{
		return idMas.getInfo2();
	}
	/**
	 * @return
	 */
	private ModeloComponente getModeloComponente()
	{
		return this.modeloComponente;
	}

	/**
	 * 
	 * @return
	 */
	public String getNome()
	{
		return this.nome;
	}

	/**
	 * @return
	 */
	private String getNumeroNo()
	{
		return idMas.getNumeroDoElemento();
	}
	/**
	 * @return
	 */
	public boolean isMaskAlarm()
	{
		return idMas.isMaskAlarm();
	}

	/**
	 * @return
	 */
	public boolean isOutAlarm()
	{
		return idMas.isOutAlarm();
	}
	/**
	 * @return
	 */
	public boolean isSelfAlarm()
	{
		return idMas.isSelfAlarm();
	}
	/**
	 * @return
	 */
	private boolean isSwitch()
	{
		return getId().equalsIgnoreCase(Componente.SWITCH);
	}

	/**
	 * @return
	 */
	private boolean isUnitario()
	{
		return unitario;
	}

	/**
	 * @param p_canal
	 */
	private void setCanal(Canal p_canal)
	{
		canal = p_canal;

		String l_comprimentoOnda = canal.getComprimentoDeOnda();
		String l_comprimentoOndaAtual = canal.getComprimentoDeOndaAtual();

		if (elemento.isTipo(Elemento.NO_CENTRAL)
			&& this.isSwitch()
			&& (l_comprimentoOnda != l_comprimentoOndaAtual))
		{
				canal.setComprimentoDeOndaAtual(l_comprimentoOnda);
				l_comprimentoOndaAtual = l_comprimentoOnda;
				
		}

		setComprimentoDeOnda(l_comprimentoOndaAtual);

	}
	/**
	 * 
	 */
	private void setCategoria()
	{
		if (isSelfAlarm())
		{
			if (isMaskAlarm())
			{
				setCategoria(S_MASK_ALARM);
			} else
			{
				setCategoria(S_SELF_ALARM);
			}
		} else if (isOutAlarm())
		{
			setCategoria(S_OUT_ALARM);
		}

	}

	/**
	 * 
	 * @param p_Categoria
	 */
	synchronized private void setCategoria(String p_Categoria)
	{
		idMas.setCategoria(p_Categoria);

		if (p_Categoria.equals(S_SELF_ALARM))
		{
			setSelfAlarm(true);
		} else if (p_Categoria.equals(S_OUT_ALARM))
		{
			setOutAlarm(true);
		} else if (p_Categoria.equals(S_MASK_ALARM))
		{
			setMaskAlarm(true);
		}
	}
	/**
	 * @param l_comprimentoOnda
	 */
	private void setComprimentoDeOnda(String l_comprimentoOnda)
	{
		if (getInfo2().equalsIgnoreCase("z"))
		{
			setInfo2(l_comprimentoOnda);
		}
		if (getInfo1().equalsIgnoreCase("z"))
		{
			setInfo1(l_comprimentoOnda);
		}
	}

	/**
	 * @param p_oElemento
	 */
	private void setElemento(Elemento p_oElemento)
	{
		elemento = p_oElemento;
		setNumeroElemento(p_oElemento.getId());
	}

	public void setId(String id)
	{
		this.id = id;
	}
	/**
	 * 
	 * @param p_IdMas
	 */
	public void setIdMas(String p_IdMas)
	{
		idMas.setIdMas(p_IdMas);
	}
	/**
	 * @param l_comprimentoOnda
	 */
	private void setInfo1(int l_comprimentoOnda)
	{
		setInfo1(String.valueOf(l_comprimentoOnda));
	}

	/**
	 * 
	 * @param p_info1
	 */
	public void setInfo1(String p_info1)
	{
		idMas.setInfo1(p_info1);
	}
	/**
	 * @param l_comprimentoOnda
	 */
	private void setInfo2(int l_comprimentoOnda)
	{
		setInfo2(String.valueOf(l_comprimentoOnda));
	}

	/**
	 * @param p_info2
	 */
	public void setInfo2(String p_info2)
	{
		idMas.setInfo2(p_info2);
	}

	/**
	 * Explique aqui o que este metodo faz.
	 * 
	 * @param b
	 */
	public void setMaskAlarm(boolean b)
	{
		idMas.setMaskAlarm(b);

	}
	//	public void setTipoElemento(String tipoElemento) {
	//		this.tipoElemento = tipoElemento;
	//	}
	/**
	 * 
	 * @param modeloComponente
	 */
	public void setModeloComponente(ModeloComponente p_modeloComponente)
	{
		modeloComponente = p_modeloComponente;
		
		setId(modeloComponente.getIdModelo());

		setNome(modeloComponente.getNomeModelo());

		setIdMas(modeloComponente.getIdMas());

		setCategoria(modeloComponente.getCategoria());

		if (idMas.getInfo2().indexOf("a") != -1 ||
		    idMas.getInfo2().indexOf("d") != -1 ||
		    idMas.getInfo2().indexOf("e") != -1 ||
		    idMas.getInfo2().indexOf("y") != -1 ||
		    idMas.getInfo1().indexOf("y") != -1 ||
		    idMas.getInfo2().indexOf("x") != -1 ||
		    idMas.getInfo2().indexOf("z") != -1 ||
		    idMas.getInfo1().indexOf("z") != -1 )
		{
			unitario = false;
		}
	}

	/**
	 * 
	 * @param nome
	 */
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	public void setNumeroElemento()
	{
		idMas.setNumeroDoElemento(S_PASSIVE);
	}
	public void setNumeroElemento(String idElemento)
	{
		idMas.setNumeroDoElemento(idElemento);
	}
	/**
	 * Explique aqui o que este metodo faz.
	 * @param b
	 */
	public void setOutAlarm(boolean b)
	{
		idMas.setOutAlarm(b);

	}

	/**
	 * Explique aqui o que este metodo faz.
	 * @param b
	 */
	public void setSelfAlarm(boolean b)
	{
		idMas.setSelfAlarm(b);

	}

	public String toString()
	{
		return getId() + " - " + getNome() + " - " + getIdMas();
	}
}
