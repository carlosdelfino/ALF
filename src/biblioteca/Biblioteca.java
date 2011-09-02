/*
 * Created on 08/12/2003
 * 
 * Todo Codigo criado por min, pode ser reutilizado para qualquer fim
 * desde que seja para o bem da humanidade e n�o seja para fins lucra-
 * tivos capitalistas.
 * 
 */
package biblioteca;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Explique aqui o que � este tipo
 * Cria��o: 08/12/2003 - 21:37:47
 * @author: Carlos Delfino
 * 
 */
public class Biblioteca extends Hashtable
{

	/**
	 * Recebendo o nome do arquivo como argumento ele carrega
	 * o conteudo do arquivo para dentro do objeto Biblioteca;
	 *  
	 * Cria��o: 09/12/2003 - 19:54:14
	 * @autor: Carlos Delfino
	 * @param arqBiblioteca
	 */
	public Biblioteca(String arqBiblioteca)
	{
		
		// proforme para trabalhar com XML via DOM
		DocumentBuilderFactory fabObj = DocumentBuilderFactory.newInstance();

		// Carregar listas na mem�ria a partir dos arquivos Biblioteca e Topologia
		
		try
		{
			Document xml = fabObj.newDocumentBuilder().parse(
											new File(arqBiblioteca));
			putAll(getListaModelosComponente(xml));
			putAll(getListaModelosElemento(xml));
			putAll(getListaTipoSubElemento(xml,this));
		}
		catch (Exception e)
		{
			// 
			e.printStackTrace();
		}
		

	}


	/**
	 * Esplique aqui porque usar este construtor, e
	 * sua diferen�a em rela��o aos outros.
	 * Cria��o: 09/12/2003 - 20:04:29
	 * @autor: Carlos Delfino
	 * 
	 */
	public Biblioteca()
	{
	}

	/**
	 * Pega os componentes definido dentro da biblioteca xml.
	 * recebe como paramentro o arquivo xml
	 * com  as defini�oes dos componentes.
	 * retorna um hashtable com os componentes mapeados por
	 * seu idComp.
	 * 
	 */
	static private Biblioteca getListaModelosComponente(Document xml) {
		
		Biblioteca modelos = new Biblioteca();
		NodeList componentes = xml.getElementsByTagName("Componente");

		// conforme a quantidade de componentes definidos
		// associa os mesmos ao objeto componente e contabiliza
		// na console.
		for (int i = 0; i < componentes.getLength(); i++) {
			Element elemxml = (Element)componentes.item(i);
			
			Componente comp = null; 
			if ("fibra".equalsIgnoreCase(elemxml.getAttribute("id")))
			{
				System.out.println("Gera modelo Link");
				comp = new Link(elemxml);
			}else{
				System.out.println("Gera modelo Componente");				// Instanciar tipo de componente
				comp = Componente.geraModeloComponente(elemxml);
			}
			
			// Adicionar tipo de componente � lista Hashtable
			modelos.put(comp.getId(), comp);
		}
		
		
		return modelos;
	}

	static private Biblioteca getListaModelosElemento(Document xml){
		
		Biblioteca modelos = new Biblioteca();
		NodeList elementos = xml.getElementsByTagName("Elemento");
		
		for (int i = 0; i < elementos.getLength(); i++) {
			Element elemxml = (Element)elementos.item(i);
			// Instanciar tipo de componente
			No no = new No(elemxml);
			// Adicionar tipo de componente � lista Hashtable

			modelos.put(no.getId(), no);
		}
		return modelos;
	}
	static private Biblioteca getListaTipoSubElemento(Document xml, Biblioteca biblioteca){
		
		// Coletar subcomponentes para o caso de in�cio de canal
		NodeList subelementos = xml.getElementsByTagName("SubElemento");
		
		for (int j = 0; j < subelementos.getLength(); j++) {
				
			NodeList comp = null;

			// pega apenas os tipos de subelementos que forem 
			// de inicio do canal
			String pos =((Element)subelementos.item(j)).
											getAttribute("posicao");
											
			if ( "inicial".equalsIgnoreCase(pos)){
				//String idSubElem = ((Element)subelementos.item(j)).
				//			getAttribute("idComp");
				String tipoNo = ((Element)subelementos.item(j)).
							getAttribute("idElemento");

				// Resgata o Tipo Elemento da Biblioteca
				No modeloelemento = (No)biblioteca.remove(tipoNo);
				
				comp = ((Element)subelementos.item(j)).
								getElementsByTagName("Componentes");
				for (int i = 0; i < comp.getLength();i++){
					
					String idComp = ((Element)comp.item(i)).
										getAttribute("idComp");
					String idMas = ((Element)comp.item(i)).
										getAttribute("idMas");
					
					Componente tComp = null;
					try
					{
						Componente tmp = ((Componente)biblioteca.get(idComp));
						tComp = (Componente)tmp.copia();
					}
					catch (CloneNotSupportedException e)
					{
						// TODO Bloco Catch criado automaticamente para CloneNotSupportedException, coloque aqui o que  ele deve fazer
						e.printStackTrace();
					}
					tComp.setIdMas(idMas);					
					modeloelemento.adicionaMComponenteInicial(tComp);
					
				}
				biblioteca.put(tipoNo,modeloelemento);
			}

			// Agora pega os tipos de subelementos que forem
			// de passagem do canal
			if ( "passagem".equalsIgnoreCase(pos)){
				//String idSubElem = ((Element)subelementos.item(j)).
				//			getAttribute("idComp");
				String tipoNo = ((Element)subelementos.item(j)).
							getAttribute("idElemento");

				// Resgata o Tipo Elemento da Biblioteca
				No tElem = (No)biblioteca.remove(tipoNo);
				
				comp = ((Element)subelementos.item(j)).
								getElementsByTagName("Componentes");
				for (int i = 0; i < comp.getLength();i++){
					
					String idComp = ((Element)comp.item(i)).
										getAttribute("idComp");
					String idMas = ((Element)comp.item(i)).
										getAttribute("idMas");

					Componente modeloComp = null;
					try
					{
						Componente tmp = ((Componente)biblioteca.get(idComp));
						modeloComp = (Componente)tmp.copia();
						
					}
					catch (CloneNotSupportedException e)
					{
						// TODO Bloco Catch criado automaticamente para CloneNotSupportedException, coloque aqui o que  ele deve fazer
						e.printStackTrace();
					}
					modeloComp.setIdMas(idMas);					
					tElem.adicionaMComponentePassagem(modeloComp);
					
				}
				biblioteca.put(tipoNo,tElem);
			}
		
			// e agora apenas os finais de canal.
			if ( "final".equalsIgnoreCase(pos)){
				//String idSubElem = ((Element)subelementos.item(j)).
				//			getAttribute("idComp");
				String tipoNo = ((Element)subelementos.item(j)).
							getAttribute("idElemento");

				// Resgata o Tipo Elemento da Biblioteca
				No tElem = (No)biblioteca.remove(tipoNo);
				
				comp = ((Element)subelementos.item(j)).
								getElementsByTagName("Componentes");
				for (int i = 0; i < comp.getLength();i++){
					
					String idComp = ((Element)comp.item(i)).
										getAttribute("idComp");
					String idMas = ((Element)comp.item(i)).
										getAttribute("idMas");

					Componente tComp = null;
					try
					{
						Componente tmp = ((Componente)biblioteca.get(idComp));
						tComp = (Componente)tmp.copia();
					}
					catch (CloneNotSupportedException e)
					{
						// TODO Bloco Catch criado automaticamente para CloneNotSupportedException, coloque aqui o que  ele deve fazer
						e.printStackTrace();
					}
					tComp.setIdMas(idMas);					
					tElem.adicionaMComponenteFinal(tComp);
					
				}
				biblioteca.put(tipoNo,tElem);
			}
		}
		return biblioteca;
	}
	public Modelos get(String id){
		return (Modelos)super.get(id);
	}
	public String toString(){
		
		int numcomp = size();
		int count = 1;

		String print = "Foram definidos ao todo " + numcomp + " Hardwares de rede:\n";
		
		try{
		
		for(Enumeration idcomps = keys();
									 idcomps.hasMoreElements();){
			
			Modelos modelo = get((String)idcomps.nextElement());
			print += "\t" + count + " --> " + modelo.getId() + ", nome: " + modelo.getNome();

			if (modelo instanceof Componente){
				Componente tComp = (Componente)modelo;
				String idMas = tComp.getIdMas();
				if ( idMas != null)
							print += ", Cobinet: " + idMas;
			}
			print += "\n";
			if (modelo instanceof No){
				No tElem = (No)modelo;
				ArrayList compList = tElem.pegaListaModelosComponentesIniciais();

				print += "\t   SubElemento inicio de canal composto por:\n";
				
				for (int i = 0; i < compList.size();i++){
					Componente tComp = (Componente)compList.get(i);
					
					print += "\t\t" + (i + 1) + " --> " + tComp.getId();
					print += ", nome: " + tComp.getNome();
				 	String idMas = tComp.getIdMas();
					if ( idMas != null)
								print += ", Cobinet: " + idMas;
								
					print += "\n";
					
					
				}	

				print += "\t   SubElemento de passagem de canal composto por:\n";
				compList = tElem.pegaListaModelosComponentesPassagens();
				
				for (int i = 0; i < compList.size(); i++){
					Componente tComp = (Componente)compList.get(i);
					print += "\t\t" + (i + 1) + "--> " + tComp.getId();
					print += ", nome: " + tComp.getNome();
					String idMas = tComp.getIdMas();
					if ( idMas != null)
								print += ", Cobinet: " + idMas;
								
					print += "\n";
				}	
				
				print += "\t   SubElemento final de canal composto por:\n";
				compList = tElem.pegaListaModelosComponentesFinais();
				for (int i= 0; i< compList.size(); i++){
					
					Componente tComp = (Componente)compList.get(i);
					print += "\t\t" + (i + 1) + "--> " + tComp.getId();
					print += ", nome: " + tComp.getNome();
					
					String idMas = tComp.getIdMas();
					if ( idMas != null)
								print += ", Cobinet: " + idMas;
								
					print += "\n";
				}	
			}
			count++;			
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return print;
	}
	/**
	 * Este m�todo, deve ser envocado quando se quer criar um
	 * novo Elemento de Rede, seja ele um N� F�sico da Rede
	 * ou um Link de conex�o entre N�s (a Fibra).
	 * 
	 * Deve se tomar muito cuidado para n�o se confundir este 
	 * Elemento com o "Modelo de Elemento (MElemento)", utilizado
	 * para ger�-lo, j� que MElemento � o modelo que ser� 
	 * copiado (Clonado) para dar vida ao N� ou Link dentro da
	 * topologia da rede conforme os par�metros passado para o 
	 * m�todo.
	 * 
	 * Outra confus�o muito comun � a coincid�ncia de nomes entre
	 * este elemento e o Element da linguagem XML. O Element da 
	 * Linguagem XML � apenas uma tag que pode ter qualquer nome,
	 * quando a mesma tem o nome Elemento, ela est� definindo um
	 * Modelo de Elemento.
	 * 
	 * @param Element  Um Elemento XML definido no arquivo da topologia
	 * @return Elemento Um Elemento Fisico da Rede.
	 */
	public Elemento novoElemento(Element elemxml)
	{
		Elemento elemento = null;
		String tipo = elemxml.getAttribute("tipo");
		
		try
		{
			// Pega na biblioteca o modelo de Elemento
			// Especificado pelo atributo tipo
			Modelos modelo = get(tipo);
			if (modelo instanceof No){
				//Gera uma copia (clone) do Tipo de Elemento
				//e o transforma em uma elemento Fisico;
				elemento = (No)modelo.copia();
				modelo = null; 
			}else{
				//Gera uma copia (clone) do Tipo de Componente
				//e o transforma em uma elemento Fisico;
				elemento = (Link)modelo.copia();
				((Link)elemento).setCCW(elemxml.getAttribute("ccw"));
				modelo = null; 
			}

		}
		catch (CloneNotSupportedException e){
			// TODO Bloco Catch criado automaticamente para CloneNotSupportedException, coloque aqui o que  ele deve fazer
			e.printStackTrace();
		} 
		
		// sendo ele agora um elemento fisico, podemos
		// setar novamente seu ID para o ID do elemento fisico
		elemento.setId(elemxml.getAttribute("id"));
		// e tamb�m seu nome.
		elemento.setNome(elemxml.getAttribute("nome"));
		// e para finalizar, o sentido que ele trabalha
		
		return elemento;
	}	
}
