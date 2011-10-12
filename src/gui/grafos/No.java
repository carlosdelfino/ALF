/*
 * Node.java Criado em 04/02/2004
 *
 * Todo Codigo criado abaixo, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e não seja para fins lucra-
 * tivos capitalistas.
 * 
 */
package gui.grafos;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;

/**
 * @author Carlos Delfino
 * @email suporte@carlosdelfino.eti.br
 * @site http://www.carlosdelfino.eti.br
 * 
 * Digite aqui o que esta classe faz.
 * Nome: 
 */
public class No implements Comparable
{
	public final static int CIRCULO_DUPLO = 2;
	public final static int CIRCULO_SIMPLES = 1;
	public final static int PERSONALIZADO = 99;

	private int tipo;

	private double dx;
	private double dy;

	private boolean fixo;

	private String lbl;

	private double x;
	private double y;

	/**
	 * 
	 */
	public No()
	{}
	/**
	 * @param p_string
	 */
	public No(String p_lbl)
	{
		setLbl(p_lbl);
	}

	/**
	 * @return
	 */
	public double getDx()
	{
		return dx;
	}
	/**
	 * @return
	 */
	public double getDy()
	{
		return dy;
	}

	/**
	 * @return
	 */
	public String getLbl()
	{
		return lbl;
	}
	/**
	 * 
	 */
	public int getTipo()
	{
		return tipo;

	}

	/**
	 * @return
	 */
	public double getX()
	{
		return x;
	}

	/**
	 * @return
	 */
	public double getY()
	{
		return y;
	}

	/**
	 * @return
	 */
	public boolean isFixo()
	{
		return fixo;
	}
	/**
	 * @param fm
	 * @return
	 */
	public void makeIcone(
		No pick,
		Graphics offgraphics,
		FontMetrics fm,
		Color selectColor,
		Color fixedColor,
		Color nodeColor)
	{

		// pega o comprimento do retangulo, mais 5 pontos, 
		// com base no comprimento do nome do no;
		int l_comprimento = fm.stringWidth(getLbl()) + 5;
		// acresenta mais 4 pontos de gap.
		int l_altura = fm.getHeight() + 4;

		int l_xInicio = ((int)x) - l_comprimento / 2;
		int l_yInicio = ((int)y) - l_altura / 2;

		int l_xInicioCirculo = l_xInicio - 10;
		int l_yInicioCirculo = (((int)y) - l_comprimento / 2) - 10;

		int l_xFinalCirculo = l_comprimento + 20;
		int l_yFinalCirculo = l_xFinalCirculo;

		// defino a cor do no,
		// se for no fixo pinta de vermelho (fixedColor)
		// se for outro tipo de no, pinta da cor padrão (nodeColor)
		offgraphics.setColor((this == pick) ? selectColor : (isFixo() ? fixedColor : nodeColor));

		switch (getTipo())
		{
			case PERSONALIZADO :

				makeIconePersonalizado(pick, offgraphics, fm, selectColor, fixedColor, nodeColor);
				break;
			case CIRCULO_SIMPLES :

				offgraphics.fillOval(
					l_xInicioCirculo - 1,
					l_yInicioCirculo - 1,
					l_xFinalCirculo + 2,
					l_xFinalCirculo + 2);

				offgraphics.setColor(Color.black);
				offgraphics.drawOval(
					l_xInicioCirculo,
					l_yInicioCirculo,
					l_xFinalCirculo,
					l_yFinalCirculo);
				break;
			case CIRCULO_DUPLO :

				offgraphics.fillOval(
					l_xInicioCirculo - 1,
					l_yInicioCirculo - 1,
					l_xFinalCirculo + 2,
					l_yFinalCirculo + 2);

				offgraphics.setColor(Color.black);
				offgraphics.drawOval(
					l_xInicioCirculo,
					l_yInicioCirculo,
					l_xFinalCirculo,
					l_yFinalCirculo);
				offgraphics.drawOval(
					l_xInicioCirculo + 5,
					l_yInicioCirculo + 5,
					l_xFinalCirculo - 10,
					l_yFinalCirculo - 10);
				break;
			default :
				offgraphics.fillRect(l_xInicio, l_yInicio, l_comprimento, l_altura);

				// desenha a borda do retangulo com a cor preta.
				offgraphics.setColor(Color.black);
				offgraphics.drawRect(l_xInicio, l_yInicio, l_comprimento - 1, l_altura - 1);
		}

		// Escreve a etiqueta do no.
		offgraphics.drawString(
			getLbl(),
			((int)x) - (l_comprimento - 5) / 2,
			(((int)y) - (l_altura - 4) / 2) + fm.getAscent());
	}
	/**
	 * Deve ser um codigo bem simples e rapido pois e constantemente
	 * executado pelo paint do painel.
	 */
	private void makeIconePersonalizado(
		No pick,
		Graphics offgraphics,
		FontMetrics fm,
		Color selectColor,
		Color fixedColor,
		Color nodeColor)
	{
		// TODO Stub de método gerado automaticamente

	}

	/**
	 * @param p_d
	 */
	public void setDx(double p_d)
	{
		dx = p_d;
	}

	/**
	 * @param p_d
	 */
	public void setDy(double p_d)
	{
		dy = p_d;
	}

	/**
	 * @param p_b
	 */
	public void setFixo(boolean p_b)
	{
		fixo = p_b;
	}

	/**
	 * @param p_string
	 */
	public void setLbl(String p_string)
	{
		lbl = p_string;
	}
	public void setTipo(int p_tipo)
	{
		tipo = p_tipo;

	}

	/**
	 * @param p_d
	 */
	public void setX(double p_d)
	{
		x = p_d;
	}

	/**
	 * @param p_d
	 */
	public void setY(double p_d)
	{
		y = p_d;
	}
	public String toString()
	{
		return "No: " + getLbl() + " em (" + getX() + ", " + getY() + ").";
	}
	
	public boolean equals(No p_no){
		return getLbl().equals(p_no.getLbl());
	}
	/* (não-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o)
	{
		
		return getLbl().compareTo(((No)o).getLbl());
	}

}
