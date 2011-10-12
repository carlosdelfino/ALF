/**
 * 
 * Teste de javadoc
 * 
 */
package gui.actionlisteners;
import gui.PrincipalWindows;
import gui.grafos.Grafo;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import simuladordarede.erros.ExceptionTopologiaNaoDefinida;
import util.FiltroDeArquivos;

/*
 * ActionListenerCarregaTopologia.java Criado em 01/02/2004
 *
 * Todo Codigo criado abaixo, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e não seja para fins lucra-
 * tivos capitalistas.
 * 
 */

/**
 * @author Carlos Delfino
 * email suporte@carlosdelfino.eti.br
 * site http://www.carlosdelfino.eti.br
 * 
 * Digite aqui o que esta classe faz.
 * Nome: PrincipalWindows
 */
public class ActionListenerCarregaCanais extends ActionListenerCarregador
{
	public ActionListenerCarregaCanais(PrincipalWindows p_parente)
	{
		setParente(p_parente);
	}

	public void actionPerformed(ActionEvent e)
	{
		String l_cmd = e.getActionCommand();

		if (l_cmd.equalsIgnoreCase("cmd_carregarCanais_topologia"))
			pegaArquivoMudaTab(e, parente.TAB_CANAIS_GRAFICO);
		else
			pegaArquivoMudaTab(e, parente.TAB_CANAIS_TEXTO);

	}

	/* (não-Javadoc)
	 * @see gui.ActionListenerCarregador#pegaArquivoMudaTab(java.awt.event.ActionEvent, java.lang.String)
	 */
	public void pegaArquivoMudaTab(ActionEvent p_e, String p_tab)
	{
		JFileChooser l_jfc = new JFileChooser(parente.getDiretorioDeConfiguracao());
		l_jfc.setDialogTitle("Escolha o Arquivo de Canais!");

		FiltroDeArquivos l_ff = new FiltroDeArquivos();
		l_ff.addExtension("xml");
		l_ff.setDescription("Arquivos de Configuração do Sistema de Correção de Alarmes");
		l_jfc.setFileFilter(l_ff);

		int result = l_jfc.showOpenDialog(parente);
		if (result == JFileChooser.APPROVE_OPTION)
		{
			parente.setArqCanais(l_jfc.getSelectedFile().getAbsolutePath());
			try
			{
				parente.makeCanais();
			} catch (ExceptionTopologiaNaoDefinida e1)
			{
				if (p_tab != null)
				{
					parente.getJTabbedPaneGeral().setSelectedIndex(
						parente.getJTabbedPaneGeral().indexOfTab(parente.TAB_TOPOLOGIA));
					parente.getJTabbedPaneTopologia().setSelectedIndex(
						parente.getJTabbedPaneTopologia().indexOfTab(p_tab));
				}

				JOptionPane.showInternalMessageDialog(
					parente.getContentPane(),
					"Para você criar os Canais, carregue primeiro o arquivo da Topologia,\n"
						+ "clique OK para carregar o arquivo!",
					"Erro na Topologia!",
					JOptionPane.ERROR_MESSAGE);

				//  carrega a Topologia
				ActionListenerCarregador l_alc = new ActionListenerCarregaTopologia(parente);
				l_alc.actionPerformed(p_e);
				try
				{
					parente.makeCanais();
				} catch (ExceptionTopologiaNaoDefinida e2)
				{
					JOptionPane.showInternalMessageDialog(
						parente.getContentPane(),
						"Parece que o problema é mais grave, tente novamente, "
							+ "mas desta ves vá pelo menu biblioteca, e depois pelo Topologia!",
						"Erro na Topologia!",
						JOptionPane.ERROR_MESSAGE);

					e2.printStackTrace();
				}
			}

			parente.getJTreeCanais().setModel(parente.getCanais().getArvoreDaTopologia());

			if (p_tab != null) mudaTab(p_tab);
			
			montaGrafoCanais();
			
			parente.abilitaTab(parente.TAB_GERENCIA);

			parente.getJTreeCanais().setVisible(true);

			parente.getJButtonCanais().setVisible(false);
			parente.getJButtonGrafoCanais().setVisible(false);

		}
	}

	private void mudaTab(String p_tab)
	{
		parente.getJTabbedPaneGeral().setSelectedIndex(
			parente.getJTabbedPaneGeral().indexOfTab(parente.TAB_CANAIS));
		parente.getJTabbedPaneCanais().setSelectedIndex(
			parente.getJTabbedPaneCanais().indexOfTab(p_tab));
	}

	private void montaGrafoCanais()
	{
		Grafo l_grafoCanais = new Grafo();
		
		parente.getJScrollPaneGrafoCanais().setViewportView(l_grafoCanais);
		
		l_grafoCanais.setLinhaDeStatus(parente.getLinhaDeStatus());
		
		l_grafoCanais.setSize(new Dimension(1500, 445));
		l_grafoCanais.setPreferredSize(l_grafoCanais.getSize());
		l_grafoCanais.setMinimumSize(l_grafoCanais.getSize());
				
		l_grafoCanais.setDistanciaPadraoEntreNos(30);
		
		l_grafoCanais.adicionaNos(parente.getCanais().getNosGrafoPorComponentes());
		l_grafoCanais.adicionaArestas(parente.getCanais().getArestasGrafoPorComponentes());
		
		l_grafoCanais.iniciarExpancao();
		l_grafoCanais.setVisible(true);
	}

}