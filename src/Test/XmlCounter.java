package Test;

import org.w3c.dom.*;
import java.io.*;
import javax.xml.parsers.*;

public class XmlCounter {

	public boolean Count (String pathXML) {
        try {
        	int recordCount = 0, expectedCount = 0;
        	
            File xmlFile = new File(pathXML);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);

            Element muRpCheckPointsElement = (Element) doc.getElementsByTagName("mu_rp_check_points").item(0);
            NodeList dataRecordsList = muRpCheckPointsElement.getElementsByTagName("DATA_RECORDS");
            
            expectedCount = Integer.parseInt(muRpCheckPointsElement.getElementsByTagName("Count").item(0).getTextContent());

            for (int i = 0; i < dataRecordsList.getLength(); i++) {
                Element dataRecordsElement = (Element) dataRecordsList.item(i);
                NodeList recordList = dataRecordsElement.getElementsByTagName("DATA_RECORD");

                for (int j = 0; j < recordList.getLength(); j++) {
                    recordCount++;
                }
            }
            
            System.out.println(recordCount);
            System.out.println(expectedCount);

            if (recordCount == expectedCount) {
                //System.out.println("Количество записей в блоке DATA_RECORDS совпадает с ожидаемым значением.");
            	return true;
            } else {
                //System.out.println("Количество записей в блоке DATA_RECORDS не совпадает с ожидаемым значением.");
            	return false;
            }
        } catch (Exception e) {
            System.out.println("Ошибка при обработке XML файла: " + e.getMessage());
            return false;
        }
    }
}
