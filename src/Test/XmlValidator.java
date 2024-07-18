package Test;

import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

public class XmlValidator {

	public boolean Validation(String pathXSD, String pathXML) {
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

}
