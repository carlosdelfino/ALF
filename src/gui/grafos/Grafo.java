/*
 * PainelGrafoNovo.java Criado em 04/02/2004
 *
 * Todo Codigo criado abaixo, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e não seja para fins lucra-
 * tivos capitalistas.
 * 
 */
package gui.grafos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JPanel;
import javax.swing.text.JTextComponent;

/**
 * @author Carlos Delfino
 * @email suporte@carlosdelfino.eti.br
 * @site http://www.carlosdelfino.eti.br
 * 
 * Esta classe deve ser uma extenção do Panel, pois ela
 * precisa sobreescrever o metodo update, para atualizar
 * a Janela!
 * 
 * Nome: 
 */
public class Grafo extends JPanel implements Runnable, MouseListener, MouseMotionListener
{

	private JTextComponent linhaDeStatus;

	private boolean posicionarNosRandomicamente = true;

	// distancia padrão usada nos edges/arestas
	private int distanciaPadraoEntreNos = 50;

	// cores dos arcos/arestas
	final Color arcColor1 = Color.black;
	final Color arcColor2 = Color.pink;
	final Color arcColor3 = Color.red;

	// Lista de Arestas/Links
	Set arestas = new TreeSet();

	private double distanciaDaBorda = 15;

	// cores dos Nós
	private final Color fixedColor = Color.red;

	private Font fontePadraoDosNos = new Font("Tahoma", 0, 8);
	private final Color nodeColor = new Color(250, 220, 100);

	// Lista de Nos
	private Set nos = new TreeSet();

	private Graphics offgraphics;
	private Image offscreen;
	private Dimension offscreensize;

	private No pick;
	private boolean pickFixo;

	private Thread relaxer;
	private final Color selectColor = Color.pink;

	public Grafo()
	{
		initialize();
		addMouseListener(this);
	}
	/**
	* @param l_aresta
	*/
	void adicionaAresta(Aresta l_aresta)
	{
		if (l_aresta.getLen() == 0)
		{
			l_aresta.setLen(distanciaPadraoEntreNos);
		}
		arestas.add(l_aresta);
	}
	/**
	 * @param p_collection
	 */
	public void adicionaArestas(Collection p_aresta)
	{
		for (Iterator l_iteratorArestas = p_aresta.iterator(); l_iteratorArestas.hasNext();)
		{
			Aresta l_aresta = (Aresta)l_iteratorArestas.next();
			adicionaAresta(l_aresta);
		}

	}
	/**
	 * @param p_no
	 */
	public boolean adicionaNo(No p_no)
	{
		return nos.add(p_no);
	}

	protected int adicionaNo(String lbl)
	{
		No l_no = new No();

		// Define a posição inicial dos nos no grafico
		// de forma randomica
		l_no.setX(900 * Math.random());
		l_no.setY(500 * Math.random());

		// Define a posição inicial dos nos no grafico
		//		n.setX(200 + count++);
		//		n.setY(200 + count++);

		// define o nome do Nó
		l_no.setLbl(lbl);

		// Cola o Nó no array
		adicionaNo(l_no);

		//return nos.indexOf(l_no);
		return 0;
	}

	/**
	 * @param p_object
	 */
	public void adicionaNos(Collection p_nos)
	{
		for (Iterator l_iteratorNos = p_nos.iterator(); l_iteratorNos.hasNext();)
		{
			No l_no = (No)l_iteratorNos.next();

			if (adicionaNo(l_no) && isPosicionarNosRandomicamente())
			{
				posicionarNoRandomicamente(l_no);
			}

		}
	}
	/**
	 * 
	 */
	private void posicionarNosRandomicamente()
	{
		for (Iterator l_iteratorNos = getNos().iterator(); l_iteratorNos.hasNext();)
		{
			posicionarNoRandomicamente((No)l_iteratorNos.next());

		}
	}
	/**
	 * @param p_no
	 */
	private void posicionarNoRandomicamente(No p_no)
	{
		Dimension l_dim = getSize();
		p_no.setY(Math.random() * l_dim.getHeight());
		p_no.setX(Math.random() * l_dim.getWidth());

	}
	/**
	 * @return
	 */
	private boolean isPosicionarNosRandomicamente()
	{
		return posicionarNosRandomicamente;
	}
	private void desenhaAresta(Aresta l_aresta)
	{

		int x1 = (int)getNo(l_aresta.getFrom()).getX();
		int y1 = (int)getNo(l_aresta.getFrom()).getY();
		int x2 = (int)getNo(l_aresta.getTo()).getX();
		int y2 = (int)getNo(l_aresta.getTo()).getY();

		Dimension l_dim = new Dimension();

		int len =
			(int)Math.abs(
				Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)) - l_aresta.getLen());

		offgraphics.setColor((len < 10) ? arcColor1 : (len < 20 ? arcColor2 : arcColor3));
		offgraphics.drawLine(x1, y1, x2, y2);
	}

	/**
	 * @param p_string
	 */
	private No getNo(String p_lbl)
	{
		for (Iterator l_iteratorNos = nos.iterator(); l_iteratorNos.hasNext();)
		{
			No l_no = (No)l_iteratorNos.next();
			if (l_no.getLbl().equals(p_lbl))
				return l_no;

		}
		return null;

	}
	public void desenhaNo(No p_no)
	{
		//Pega o objeto de manipulação do comprimento da fonte a ser usado
		FontMetrics fm = offgraphics.getFontMetrics();

		// pego localização do NO
		int x = (int)p_no.getX();
		int y = (int)p_no.getY();

		p_no.makeIcone(pick, offgraphics, fm, selectColor, fixedColor, nodeColor);

	}

	/**
	 * @return
	 */
	public double getDistanciaDaBorda()
	{
		return distanciaDaBorda;
	}

	/**
	 * @return
	 */
	public Font getFontePadrãoDosNos()
	{
		return fontePadraoDosNos;
	}
	/**
	 * 
	 */
	public Set getNos()
	{
		return nos;

	}

	/**
	 * @return
	 */
	public Color getSelectColor()
	{
		return selectColor;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	protected void initialize()
	{
		try
		{
			this.setBorder(
				javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
			// Generated
			this.setVisible(false); // Generated

		} catch (java.lang.Throwable e)
		{
			//  Do Something
		}
	}

	/**
	 * @return
	 */
	public boolean isPickFixo()
	{
		return pickFixo;
	}

	/* (não-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	public void mouseClicked(MouseEvent e)
	{}

	/* (não-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
	 */
	synchronized public void mouseDragged(MouseEvent e)
	{
		{
			pick.setX(e.getX());
			pick.setY(e.getY());
			repaint();

			if (getLinhaDeStatus() != null)
				getLinhaDeStatus().setText(pick.toString());

			e.consume();
		};
	}

	/* (não-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	public void mouseEntered(MouseEvent e)
	{}

	/* (não-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	public void mouseExited(MouseEvent e)
	{}

	/* (não-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	public void mouseMoved(MouseEvent e)
	{}

	/* (não-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	public void mousePressed(MouseEvent e)
	{
		
		// de imediato achei estranho este metodo estar aqui
		// mas quando o coloquei no construtor, ele não mostra
		// o elemento sendo arrastado de uma forma dinamica,
		// apesar de arrasta-lo. 
		addMouseMotionListener(this);

		double l_melhorDistancia = Double.MAX_VALUE;

		// pega a localização onde o mouse foi clicado.
		int x = e.getX();
		int y = e.getY();

		// procura o no mais proximo do local
		// onde o botão do mouse foi precionado.
		for (Iterator l_iteratorNos = getNos().iterator(); l_iteratorNos.hasNext();)
		{
			No l_no = (No)l_iteratorNos.next();

			double dist =Math.pow((l_no.getX() - x),2) + Math.pow((l_no.getY() - y),2);
			
			if (dist < l_melhorDistancia)
			{
				pick = l_no;
				l_melhorDistancia = dist;
			}
		}

		pickFixo = pick.isFixo();
		pick.setFixo(true);
		pick.setX(x);
		pick.setY(y);

		// redesenha o painel
		repaint();

		if (getLinhaDeStatus() != null)
			getLinhaDeStatus().setText(pick.toString());

		// consome este evento.
		e.consume();
	}

	/* (não-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	public void mouseReleased(MouseEvent e)
	{
		removeMouseMotionListener(this);
		if (pick != null)
		{
			pick.setX(e.getX());
			pick.setY(e.getY());
			pick.setFixo(pickFixo);
			if (e.getButton() == MouseEvent.BUTTON3){
				pick.setFixo(!pick.isFixo());
			}
			pick = null;
		}
		repaint();

		e.consume();
	}

	public void paint(Graphics p_graphics)
	{
		// pega o tamanho da janela.
		Dimension l_dimensaoDoPainel = getSize();

		if ((offscreen == null)
			|| (l_dimensaoDoPainel.width != offscreensize.width)
			|| (l_dimensaoDoPainel.height != offscreensize.height))
		{
			offscreen = createImage(l_dimensaoDoPainel.width, l_dimensaoDoPainel.height);
			offscreensize = l_dimensaoDoPainel;

			if (offgraphics != null)
				offgraphics.dispose();

			offgraphics = offscreen.getGraphics();
			offgraphics.setFont(getFontePadrãoDosNos());
		}

		offgraphics.setColor(getBackground());
		offgraphics.fillRect(0, 0, l_dimensaoDoPainel.width, l_dimensaoDoPainel.height);

		// Desenha cada Aresta.
		for (Iterator iteratorArestas = arestas.iterator(); iteratorArestas.hasNext();)
			desenhaAresta((Aresta)iteratorArestas.next());

		//Desenha cada nó no grafico
		//os nos são desenhados depois, para ficarem sobre as arestas
		for (Iterator l_iteratorNos = nos.iterator(); l_iteratorNos.hasNext();)
			desenhaNo((No)l_iteratorNos.next());

		// coloca o grafico no painel 	
		p_graphics.drawImage(offscreen, 0, 0, null);
	}

	private void posicionaAresta(Aresta l_aresta)
	{

		double xTo = getNo(l_aresta.getTo()).getX();
		double xFrom = getNo(l_aresta.getFrom()).getX();
		double yTo = getNo(l_aresta.getTo()).getY();
		double yFrom = getNo(l_aresta.getFrom()).getY();

		double vx = xTo - xFrom;
		double vy = yTo - yFrom;

		double len = Math.sqrt(vx * vx + vy * vy);
		len = (len == 0) ? .0001 : len;

		double f = (l_aresta.getLen() - len) / (len * 3);
		double dx = f * vx;
		double dy = f * vy;

		getNo(l_aresta.getTo()).setDx(getNo(l_aresta.getTo()).getDx() + dx);

		getNo(l_aresta.getTo()).setDy(getNo(l_aresta.getTo()).getDy() + dy);

		getNo(l_aresta.getFrom()).setDx(getNo(l_aresta.getFrom()).getDx() + -dx);

		getNo(l_aresta.getFrom()).setDy(getNo(l_aresta.getFrom()).getDy() + -dy);

	}

	/**
	 * Posiciona o No i conforme os parametros X e Y passados
	 * dentro do objeto (Dimension) d.
	 *  
	 * @param i
	 * @param d
	 */
	private void posisionaNo(No p_no, Dimension d)
	{

		if (!p_no.isFixo())
		{
			p_no.setX(p_no.getX() + Math.max(-5, Math.min(5, p_no.getDx())));
			p_no.setY(p_no.getY() + Math.max(-5, Math.min(5, p_no.getDy())));
		}

		if (p_no.getX() < distanciaDaBorda)
			p_no.setX(distanciaDaBorda);
		else if (p_no.getX() > d.width - distanciaDaBorda)
			p_no.setX(d.getWidth() - distanciaDaBorda);

		if (p_no.getY() < distanciaDaBorda)
			p_no.setY(distanciaDaBorda);
		else if (p_no.getY() > d.getHeight() - distanciaDaBorda)
			p_no.setY(d.getHeight() - distanciaDaBorda);

		p_no.setDx(p_no.getDx() / 2);
		p_no.setDy(p_no.getDy() / 2);
	}

	synchronized void relax()
	{
		for (Iterator l_interatorAresta = arestas.iterator(); l_interatorAresta.hasNext();)
		{
			posicionaAresta((Aresta)l_interatorAresta.next());
		}

		recalculaPosicaoDoNo();

		for (Iterator l_iteratorNos = getNos().iterator(); l_iteratorNos.hasNext();)
		{
			posisionaNo((No)l_iteratorNos.next(), getSize());
		}

		repaint();
	}
	private void recalculaPosicaoDoNo()
	{
		for (Iterator l_iterator1Nos = getNos().iterator(); l_iterator1Nos.hasNext();)
		{
			No l_no1 = (No)l_iterator1Nos.next();
		
			double dx = 0;
			double dy = 0;
		
			for (Iterator l_iterator2Nos = getNos().iterator(); l_iterator2Nos.hasNext();)
			{
				No l_no2 = (No)l_iterator2Nos.next();
		
				if (l_no1.equals(l_no2))
				{
					continue;
				}
		
				double vx = l_no1.getX() - l_no2.getX();
				double vy = l_no1.getY() - l_no2.getY();
				double len = Math.pow(vx, 2) + Math.pow(vy, 2);
		
				if (len == 0)
				{
					dx += Math.random();
					dy += Math.random();
				} else if (len < 10000)
				{
					dx += vx / len;
					dy += vy / len;
				}
			}
		
			double dlen = Math.pow(dx, 2) + Math.pow(dy, 2);
			if (dlen > 0)
			{
				dlen = Math.sqrt(dlen) / 2;
		
				l_no1.setDx(l_no1.getDx() + dx / dlen);
				l_no1.setDy(l_no1.getDy() + dy / dlen);
		
			}
		}
	}

	/* (não-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run()
	{
		Thread me = Thread.currentThread();
		while (relaxer == me)
		{
			relax();
			try
			{
				Thread.sleep(100);
			} catch (InterruptedException e)
			{
				break;
			}
		}
	}

	/**
	 * @param p_d
	 */
	public void setDistanciaDaBorda(double p_d)
	{
		distanciaDaBorda = p_d;
	}

	/**
	 * @param p_font
	 */
	public void setFontePadraoDosNos(Font p_font)
	{
		fontePadraoDosNos = p_font;

	}

	/**
	 * @param p_windows
	 */
	public void setLinhaDeStatus(JTextComponent p_linhaDeStatus)
	{
		linhaDeStatus = p_linhaDeStatus;
	}
	public JTextComponent getLinhaDeStatus()
	{
		return linhaDeStatus;
	}

	/**
	 * @param p_i
	 */
	//	public void setNumNodes(int p_i)
	//	{
	//		numeroDeNos = p_i;
	//	}

	/**
	 * @param p_b
	 */
	public void setPickfixed(boolean p_b)
	{
		pickFixo = p_b;
	}

	public void iniciarExpancao()
	{
		relaxer = new Thread(this);
		relaxer.setName("Relaxer");
		relaxer.start();
	}

	public void stop()
	{
		relaxer = null;
	}
	/**
	 * @param p_b
	 */
	public void setPosicionarNosRandomicamente(boolean p_b)
	{
		posicionarNosRandomicamente = p_b;

	}
	/**
	 * @param p_i
	 */
	public void setDistanciaPadraoEntreNos(int p_i)
	{
		distanciaPadraoEntreNos = p_i;

	}
} //  @jve:visual-info  decl-index=0 visual-constraint="10,10"
