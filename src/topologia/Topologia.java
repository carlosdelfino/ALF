/*
 * Created on 12/12/2003
 * 
 * Novo Arquivo, coloque aqui a licensa de uso deste
 * codigo.
 * 
 */
package topologia;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


import java.io.File;
import java.util.ArrayList;

import biblioteca.*;

/**
 * Explique aqui o que é este tipo
 * Criação: 12/12/2003 - 07:04:55
 * @author: Carlos Delfino
 * 
 */
public class Topologia
{
	ArrayList nos = null;
	ArrayList links = null;
	
	Biblioteca biblioteca = null;

	/**
	 * Este construtor recebe como argumento o arquivo topologia.xml
	 * e o objeto biblioteca criado anteriormente.
	 * Com estes dados ele monta a estrutura da rede.
	 * 
	 * Criação: 12/12/2003 - 07:20:12
	 * @autor: Carlos Delfino
	 * @param arqTopologia
	 * @param biblioteca
	 */
	public Topologia(String arqTopologia, Biblioteca biblioteca)
	{
		this.biblioteca = biblioteca;
		// proforme para trabalhar com XML via DOM
		DocumentBuilderFactory fabObj = DocumentBuilderFactory.newInstance();

		// Carregar listas na memória a partir dos arquivos Biblioteca e Topologia
		
		try
		{
			Document xml = fabObj.newDocumentBuilder().parse(
											new File(arqTopologia));
			nos		= getListaNos(xml);
			links 	= getListaLinks(xml);
		
		}
		catch (Exception e)
		{
			// 
			e.printStackTrace();
		}
		
	}
	private ArrayList getListaNos(Document xml) {
		NodeList elementos = xml.getElementsByTagName("Elemento");
		int numelementos = elementos.getLength();
		ArrayList lista = new ArrayList(numelementos);

		for (int i = 0; i < numelementos; i++) {
			Element elemxml = (Element)elementos.item(i);
			String tipo = elemxml.getAttribute("tipo");
			if ("nl".equalsIgnoreCase(tipo) ||
							"nc".equalsIgnoreCase(tipo))
			{

				No no = (No)biblioteca.novoElemento(elemxml);			
				lista.add(no);
			}
			
		}
		return lista;
	}
	private ArrayList getListaLinks(Document xml) {
		NodeList elementos = xml.getElementsByTagName("Elemento");

		int numelementos = elementos.getLength();
		ArrayList lista = new ArrayList(numelementos);

		for (int i = 0; i < numelementos; i++) {
			Element elemxml = (Element)elementos.item(i);
			

			String tipo = elemxml.getAttribute("tipo");
			if ("fibra".equalsIgnoreCase(tipo))
			{
				// crio um novo link com base no arquivo xml
				Link link = (Link)biblioteca.novoElemento(elemxml);
				
				System.out.println(" *** ID do Link: "+link.getId());
				
				// pego os Ids dos nos que este link liga
				// verifique que esta informação hoje é tirada do
				// id do link.
				String idnoanterior		= ((Link)link).getIdNoAnterior();
				String idnoposterior	= ((Link)link).getIdNoPosterior();
				System.out.println(" *** ID No Anterior " + idnoanterior);				
				System.out.println(" *** ID No Posterior " + idnoposterior);				
				System.out.println("**************************************");

				int indiceant = getNumeroDoIndice(idnoanterior);
				No noanterior = (No)nos.get(indiceant);	
				System.out.println(" *** Ele pegou o n— A " + noanterior.getId()+" usando o indice "+ indiceant);
				System.out.println(noanterior);
							
				int indicepos = getNumeroDoIndice(idnoposterior);
				No noposterior = (No)nos.get(indicepos);
				System.out.println(" *** Ele pegou o n— P " + noposterior.getId()+" usando o indice "+ indicepos);
				System.out.println(noposterior);
				

				noanterior.addNoPosterior(noposterior);
				
				lista.add(link);
				
				System.out.println("**************************************");
				
				No teste = (No)nos.get(indiceant);
				ArrayList testeps = teste.getNosPosteriores();
				System.out.println(" *** Tenho "+ teste.getNosPosteriores().size() + " Nós P cadastrados em " + teste.getId());
				System.out.println(testeps);

				System.out.println(" *** Ele pegou o nó Anterior " + teste.getId());
				System.out.println(teste);
								
				No testep = (No)testeps.get(testeps.size()-1);
				System.out.println(" *** Ele pegou o nó Posterior " + testep.getId());
				System.out.println(testep);
				System.out.println("**************************************\n\n");
				
				// aqui zero todos os objetos, pois estou tendo problemas
				// com a indexação dos nos posteriores e anteriores.
				link = null;
				noposterior = null;
				noanterior = null;
			}
		}
		return lista;
	}
	/**
	 * @param noposterior
	 * @return
	 */
	private int[] getNumeroDoID(No noposterior) {
		String id = noposterior.getId();
		
		int[] num = new int[2];
		num[0] = Integer.parseInt(id.substring(1,3));
		num[1] = Integer.parseInt(id.substring(3,5));
		return num;
	}
	/**
	 * Este metodo Recebe uma String que representa o ID do Nó
	 * então calcula o numero de identificação do Nó na biblioteca.
	 * @param id
	 * @return
	 */
	private int getNumeroDoIndice(String id)
	{
		int numero = Integer.parseInt(id.substring(1)) - 1;
		return numero;
	}
	


	private ArrayList getListaCanais(Document xml) {

		NodeList elementos = xml.getElementsByTagName("Canal");
		
		ArrayList lista = new ArrayList();

		Canal tipo = null;
		
		for (int i = 0; i < elementos.getLength(); i++) {
			Element elem = (Element)elementos.item(i);
			tipo = new Canal(elem);
			lista.add(tipo);	
		}
		
		return lista;
	}
	public String toString(){
		int numnos = nos.size();
		StringBuffer print = new StringBuffer();
		print.append("A topologia possui " + numnos + " Nós\n");
		for (int i = 0; i<numnos; i++){
			No no = (No)nos.get(i);
			print.append("\tO Nó " + no.getId() +" de nome ");
			print.append(no.getNome() + " é um nó " + no.getTipo()+"\n");
			ArrayList nosp = no.getNosPosteriores();
			int numnosp = nosp.size();
			print.append("\tEle está ligado a " + numnosp + " Nós:\n\t\t");
			for (int f = 0; f< numnosp; f++){
				String idnop = ((No)nosp.get(f)).getId();
				print.append("---> " + idnop +", ");
			}
			print.append("\n");
		}
		return print.toString();
			
	}
}
