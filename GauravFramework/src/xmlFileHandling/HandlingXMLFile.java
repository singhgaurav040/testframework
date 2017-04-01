package xmlFileHandling;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.xmlbeans.impl.soap.Node;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;

public class HandlingXMLFile {

	public static void readingXMLFile(){
	try
	{
		File file = new File("D:/WorkspaceJava/gauravframework/GauravFramework/src/Files/XMLFile.xml");

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(file);

		String usr = document.getElementsByTagName("user").item(0).getTextContent();
		String pwd = document.getElementsByTagName("password").item(0).getTextContent();
		System.out.println(usr);
		System.out.println(pwd);

	}

	catch(Exception e)
	{
		e.printStackTrace();
	}
	}
	
//	public static void main(String args[]){
//	//public static void writingXMLFile()
//        DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder icBuilder;
//        try {
//            icBuilder = icFactory.newDocumentBuilder();
//            Document doc = icBuilder.newDocument();
//            Element mainRootElement = doc.createElementNS("http://crunchify.com/CrunchifyCreateXMLDOM", "Companies");
//            doc.appendChild(mainRootElement);
// 
//            // append child elements to root element
//            mainRootElement.appendChild(getCompany(doc, "1", "Paypal", "Payment", "1000"));
//            mainRootElement.appendChild(getCompany(doc, "2", "eBay", "Shopping", "2000"));
//            mainRootElement.appendChild(getCompany(doc, "3", "Google", "Search", "3000"));
// 
//            // output DOM XML to console 
//            Transformer transformer = TransformerFactory.newInstance().newTransformer();
//            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
//            DOMSource source = new DOMSource(doc);
//            StreamResult console = new StreamResult(System.out);
//            transformer.transform(source, console);
// 
//            System.out.println("\nXML DOM Created Successfully..");
// 
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//	
//	}
}

