/**
 * 
 * Teste de javadoc
 * 
 */
package gui.actionlisteners;
import gui.PrincipalWindows;
import gui.grafos.Grafo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import util.FiltroDeArquivos;
import gerenciadordebiblioteca.erros.ExceptionBibliotecaNaoDefinida;

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
public class ActionListenerCarregaTopologia extends ActionListenerCarregador
{
	public ActionListenerCarregaTopologia(PrincipalWindows p_parente)
	{
		setParente(p_parente);
	}

	public void actionPerformed(ActionEvent e)
	{
		String l_cmd = e.getActionCommand();

		if (l_cmd.equalsIgnoreCase("cmd_carregarTopologia_Grafico"))
			pegaArquivoMudaTab(e, parente.TAB_TOPOLOGIA_GRAFICO);
		else
			pegaArquivoMudaTab(e, parente.TAB_TOPOLOGIA_TEXTO);

	}

	public void pegaArquivoMudaTab(ActionEvent p_e, String p_tab)
	{

		JFileChooser l_jfc = new JFileChooser(parente.getDiretorioDeConfiguracao());
		l_jfc.setDialogTitle("Escolha o Arquivo da Topologia!");

		FiltroDeArquivos l_ff = new FiltroDeArquivos();
		l_ff.addExtension("xml");
		l_ff.setDescription("Arquivos de Configuração do Sistema de Correção de Alarmes");
		l_jfc.setFileFilter(l_ff);

		int result = l_jfc.showOpenDialog(parente);
		if (result == JFileChooser.APPROVE_OPTION)
		{
			parente.setArqTopologia(l_jfc.getSelectedFile().getAbsolutePath());
			try
			{
				parente.makeTopologia();
			} catch (ExceptionBibliotecaNaoDefinida e1)
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
					"Para você criar a Topologia, carregue primeiro o arquivo da Biblioteca,\n"
						+ "clique OK para carregar o arquivo!",
					"Erro na Biblioteca!",
					JOptionPane.ERROR_MESSAGE);

				//  carrega a biblioteca
				ActionListenerCarregador l_alcB = new ActionListenerCarregaBiblioteca(parente);
				l_alcB.actionPerformed(p_e);
				try
				{
					parente.makeTopologia();
				} catch (ExceptionBibliotecaNaoDefinida e2)
				{
					JOptionPane.showInternalMessageDialog(
						parente.getContentPane(),
						"Parece que o problema é mais grave, tente novamente, "
							+ "mas desta ves vá pelo menu biblioteca!",
						"Erro na Biblioteca!",
						JOptionPane.ERROR_MESSAGE);

					e2.printStackTrace();
				}
			} finally
			{
				if (p_tab != null) mudaTab(parente.TAB_TOPOLOGIA_GRAFICO);
				
				parente.getJTreeTopologia().setModel(parente.getTopologia().getArvoreDaTopologia());
				
				montaGrafoTopologia();

				parente.abilitaTab(parente.TAB_CANAIS);

				parente.getJButtonTopologia().setVisible(false);
				parente.getJButtonGrafoTopologia().setVisible(false);
				parente.getJTreeTopologia().setVisible(true);

				if (p_tab != null)
				{
					mudaTab(p_tab);
				}

			}
		}
	}

	private void montaGrafoTopologia()
	{
		Grafo l_grafoTopologia = new Grafo();
		
		//l_grafoTopologia.setParente(getParente());
		
		l_grafoTopologia.setPreferredSize(new Dimension(775,445));
		
		l_grafoTopologia.setFontePadraoDosNos( new Font("Tahoma", Font.BOLD, 12));
		
		l_grafoTopologia.setCorFixa(Color.GREEN);
		
		l_grafoTopologia.setPosicionarNosRandomicamente(false);
		
		l_grafoTopologia.setLinhaDeStatus(parente.getLinhaDeStatus());
		
		parente.getJPanelGrafoTopologia().removeAll();
		parente.getJPanelGrafoTopologia().add(l_grafoTopologia);
		
		l_grafoTopologia.adicionaNos(parente.getTopologia().getNosGrafo());
		l_grafoTopologia.adicionaArestas(parente.getTopologia().getArestasGrafo());
		
		l_grafoTopologia.setVisible(true);
	}

	private void mudaTab(String p_tab)
	{
		parente.getJTabbedPaneGeral().setSelectedIndex(
			parente.getJTabbedPaneGeral().indexOfTab(parente.TAB_TOPOLOGIA));
		parente.getJTabbedPaneTopologia().setSelectedIndex(
			parente.getJTabbedPaneTopologia().indexOfTab(p_tab));
	}
}