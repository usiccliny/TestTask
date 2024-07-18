package Test;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;
import org.w3c.dom.*;


public class XmlTasks {
	
	private String pathXSD;
	private String pathXML;
	private File xmlFile;
	private DocumentBuilderFactory factory;
	private DocumentBuilder builder;
	private Document doc;

	public XmlTasks(String pathXSD, String pathXML) throws ParserConfigurationException {
		try {
			this.pathXSD = pathXSD;
			this.pathXML = pathXML;
			xmlFile = new File(pathXML);
			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
			doc = builder.parse(xmlFile);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public boolean Validation() {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            
            Schema schema = factory.newSchema(new File(pathXSD));
            
            Validator validator = schema.newValidator();
            
            validator.validate(new StreamSource(new File(pathXML)));
            
            //System.out.println("XML файл валиден.");
            
            return true;
            
        } catch (Exception e) {
            System.out.println("Ошибка валидации XML файла: " + e.getMessage());
            return false;
        }
	}
	
	public boolean Count () {
        try {
        	int recordCount = 0, expectedCount = 0;

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
	
	public void Parser() {
        try {            
            doc.getDocumentElement().normalize();

            Element muRpCheckPointsElement = (Element) doc.getElementsByTagName("mu_rp_check_points").item(0);
            NodeList dataRecordsList = muRpCheckPointsElement.getElementsByTagName("DATA_RECORDS");
            
            System.out.println("   #   |  Check Point Code   |  Check Point Name");
            for (int i = 0; i < dataRecordsList.getLength(); i++) {
                Element dataRecordsElement = (Element) dataRecordsList.item(i);
                NodeList recordList = dataRecordsElement.getElementsByTagName("DATA_RECORD");

                for (int j = 0; j < recordList.getLength(); j++) {
                    Element recordElement = (Element) recordList.item(j);
                    String checkPointCode = recordElement.getElementsByTagName("check_point_code").item(0).getTextContent();
                    String checkPointName = recordElement.getElementsByTagName("check_point_name").item(0).getTextContent();
                    
                    System.out.printf("%d    |  %s    |     %s    \n", j, checkPointCode, checkPointName);
                }
            }
            
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
