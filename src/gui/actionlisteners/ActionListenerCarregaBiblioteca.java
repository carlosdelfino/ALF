package gui.actionlisteners;



import gui.PrincipalWindows;

import javax.swing.JFileChooser;

import util.FiltroDeArquivos;

/*
 * ActionListenerCarregaBiblioteca.java Criado em 01/02/2004
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
public class ActionListenerCarregaBiblioteca extends ActionListenerCarregador
{
	/**
	 * 
	 * @param p_windows
	 * @param p_arqBiblioteca
	 * @param p_biblioteca
	 */
	public ActionListenerCarregaBiblioteca(PrincipalWindows p_parente)
	{
		setParente(p_parente);
	}
	public void actionPerformed(java.awt.event.ActionEvent p_e)
	{
	
		JFileChooser l_jfc = new JFileChooser(parente.getDiretorioDeConfiguracao());
		l_jfc.setDialogTitle("Escolha o Arquivo da Biblioteca!");

		FiltroDeArquivos l_ff = new FiltroDeArquivos();
		l_ff.addExtension("xml");
		l_ff.setDescription("Arquivos de Configuração do Sistema de Correção de Alarmes");
		l_jfc.setFileFilter(l_ff);
		
		int result = l_jfc.showOpenDialog(parente);

		if (result == JFileChooser.APPROVE_OPTION)
		{
			parente.setArqBiblioteca(l_jfc.getSelectedFile().getAbsolutePath());
			parente.makeBiblioteca();
			parente.getJTreeBiblioteca().setModel(parente.getBiblioteca().getArvoreDaBiblioteca());
			
			
			parente.getJTabbedPaneGeral().setSelectedIndex(parente.getJTabbedPaneGeral().indexOfTab(parente.TAB_BIBLIOTECA)); 
			
			parente.abilitaTab(parente.TAB_TOPOLOGIA);
			
			parente.getJScrollPaneBiblioteca().setVisible(true);
			parente.getJTreeBiblioteca().setVisible(true);
			parente.getJButtonBiblioteca().setVisible(false);
			
		}
	}
}