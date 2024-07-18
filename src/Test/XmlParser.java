package Test;

import org.w3c.dom.*;
import java.io.*;
import javax.xml.parsers.*;

public class XmlParser {

	public void Parser(String pathXML) {
        try {
            File xmlFile = new File(pathXML);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            
            doc.getDocumentElement().normalize();

            Element muRpCheckPointsElement = (Element) doc.getElementsByTagName("mu_rp_check_points").item(0);
            NodeList dataRecordsList = muRpCheckPointsElement.getElementsByTagName("DATA_RECORDS");
            
            for (int i = 0; i < dataRecordsList.getLength(); i++) {
                Element dataRecordsElement = (Element) dataRecordsList.item(i);
                NodeList recordList = dataRecordsElement.getElementsByTagName("DATA_RECORD");

                for (int j = 0; j < recordList.getLength(); j++) {
                    Element recordElement = (Element) recordList.item(j);
                    String checkPointCode = recordElement.getElementsByTagName("check_point_code").item(0).getTextContent();
                    String checkPointName = recordElement.getElementsByTagName("check_point_name").item(0).getTextContent();
                    
                    System.out.println("Check Point Code: " + checkPointCode);
                    System.out.println("Check Point Name: " + checkPointName);
                }
            }
            
            
            
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
