import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

public class Colleges {

	String collegeString = null;

	public static void main(String args[]) {
		System.out.println(getDatumsOpVolgorde());
		createLes("3", "06-08-1996", "V1D", "AUI");
	}

	public static void createLes(String docentID, String date, String klasID, String vakID) {
		try {
			String filepath = "res/Colleges.xml";
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);

			// Clean the document from any hidden whitespaces
			doc = cleanUp(doc);
			
			// Get the root element
			Node rooster = doc.getFirstChild();

			// Add college node
			Element college = doc.createElement("college");
			college.setAttribute("docentID", docentID);
			college.setAttribute("klasID", klasID);
			college.setAttribute("vakID", vakID);

			// Add a "datum" node
			Element datum = doc.createElement("datum");
			datum.setTextContent(date);
			college.appendChild(datum);
			rooster.appendChild(college);

			// write the content into XML file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			// transformerFactory.setAttribute("indent-number", 2);
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Document cleanUp(Document doc) {
		try {
			doc.getDocumentElement().normalize();
			XPathExpression xpath = XPathFactory.newInstance().newXPath().compile("//text()[normalize-space(.) = '']");
			NodeList blankTextNodes = (NodeList) xpath.evaluate(doc, XPathConstants.NODESET);
			
			for (int i = 0; i < blankTextNodes.getLength(); i++) {
				blankTextNodes.item(i).getParentNode().removeChild(blankTextNodes.item(i));
			}
			return doc;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return doc;
		}
	}

	public static ArrayList<String> getDatumsOpVolgorde() {

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
			Document doc = Utils.getXMLDoc("res/Colleges.xml");
			NodeList nList = doc.getElementsByTagName("college");

			for (int item = 0; item < nList.getLength(); item++) {
				Node nNode = nList.item(item);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					String datumUitxml = eElement.getElementsByTagName("datum").item(0).getTextContent();
					String vakID = eElement.getAttribute("vakID");
					String klasID = eElement.getAttribute("klasID");
					String docentID = eElement.getAttribute("docentID");

					System.out.println(vakID + " " + klasID + " " + docentID + " " + datumUitxml);
					xmlLijst.add(vakID + " " + klasID + " " + docentID + " " + datumUitxml);

				}
			}
		} catch (Exception e) {
		}
		lengte_xmlLijst = xmlLijst.size();
		System.out.println(lengte_xmlLijst);
		int deindex = 0;

		while (opvolgorde.size() != lengte_xmlLijst) {
			huidigeHoogste = xmlLijst.get(0);
			dagHoogste = Integer.parseInt(huidigeHoogste.split(" ")[3].split("-")[0]);
			maandHoogste = Integer.parseInt(huidigeHoogste.split(" ")[3].split("-")[1]);
			jaarHoogste = Integer.parseInt(huidigeHoogste.split(" ")[3].split("-")[2]);

			deindex = 0;

			for (String item : xmlLijst) {

				if (!item.equals(huidigeHoogste)) {

					int dagitem = Integer.parseInt(item.split(" ")[3].split("-")[0]);
					int maanditem = Integer.parseInt(item.split(" ")[3].split("-")[1]);
					int jaaritem = Integer.parseInt(item.split(" ")[3].split("-")[2]);

					if ((jaaritem < jaarHoogste) || (jaaritem <= jaarHoogste & maanditem < maandHoogste)
							|| (jaaritem <= jaarHoogste & maanditem <= maandHoogste & dagitem < dagHoogste)) {
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
		for (String item : opvolgorde) {
			System.out.println(item);
		}
		return opvolgorde;
	}
}
