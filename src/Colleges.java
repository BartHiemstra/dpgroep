import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Colleges {
	
	String collegeString = null;
	

	public static void main(String args[]) {
		ArrayList<String> xmlLijst = new ArrayList<String>();
		ArrayList<String> opvolgorde = new ArrayList<String>();
		String huidigeHoogste = "";
		String select_xmlLijst = "";
		int lengte_xmlLijst = 0;
		int dagHoogste = 0;
		int maandHoogste = 0;
		int jaarHoogste = 0;
		

		try {
			DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			Document doc = Utils.getXMLDoc("C:/Users/Robin van Vlijmen/git/dpgroep/res/Colleges.xml"); //CHANGE TO YOUR OWN DIRECTORY //Requires method to find it dynamically.
			NodeList nList = doc.getElementsByTagName("College");
			

			for (int item = 0; item < nList.getLength(); item++) {
				Node nNode = nList.item(item);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					String datumUitxml =  eElement.getElementsByTagName("Datum").item(0).getTextContent();
					String vakID = eElement.getAttribute("vakID");
					
					System.out.println(vakID + " " + datumUitxml);
					xmlLijst.add(vakID + " " + datumUitxml);
	
				}
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		lengte_xmlLijst = xmlLijst.size();
		int deindex = 0;
		
		while (opvolgorde.size() != lengte_xmlLijst){
			huidigeHoogste = xmlLijst.get(0);
			dagHoogste =  Integer.parseInt(huidigeHoogste.split(" ")[1].split("-")[0]);
			maandHoogste = Integer.parseInt(huidigeHoogste.split(" ")[1].split("-")[1]);
			jaarHoogste = Integer.parseInt(huidigeHoogste.split(" ")[1].split("-")[2]);
			
			deindex = 0;
			
				for (String item : xmlLijst){
					
					if (!item.equals(huidigeHoogste)){
						
						int dagitem = Integer.parseInt(item.split(" ")[1].split("-")[0]);
						int maanditem = Integer.parseInt(item.split(" ")[1].split("-")[1]);
						int jaaritem = Integer.parseInt(item.split(" ")[1].split("-")[2]);
						
						
							if ((jaaritem < jaarHoogste) || (jaaritem <= jaarHoogste & maanditem < maandHoogste) || (jaaritem <= jaarHoogste & maanditem <= maandHoogste & dagitem < dagHoogste)){
								huidigeHoogste = item;
								deindex = xmlLijst.indexOf(item);
								System.out.println("Controle \n");
								
							}
					}
				}
				opvolgorde.add(huidigeHoogste);
				xmlLijst.remove(deindex);
				
		}
		System.out.println("Opvolgorde van datum");
		for (String item : opvolgorde){
			System.out.println(item);
		}
	}
}
