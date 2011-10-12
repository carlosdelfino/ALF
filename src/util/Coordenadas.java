/*
 * Coordenadas.java Criado em 08/04/2004
 *
 * Todo Codigo criado abaixo, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e não seja para fins lucra-
 * tivos capitalistas.
 * 
 */
package util;

/**
 * @author Carlos Delfino
 * @email suporte@carlosdelfino.eti.br
 * @site http://www.carlosdelfino.eti.br
 * 
 * Digite aqui o que esta classe faz.
 * Nome: 
 */
public class Coordenadas
{
	private double longitude = 0;
	private double latitude = 0;
	
	/**
	 * @return
	 */
	public double getLatitude()
	{
		return latitude;
	}

	/**
	 * @return
	 */
	public double getLongitude()
	{
		return longitude;
	}

	/**
	 * @param p_d
	 */
	public void setLatitude(double p_d)
	{
		latitude = p_d;
	}

	/**
	 * @param p_d
	 */
	public void setLongitude(double p_d)
	{
		longitude = p_d;
	}

}
