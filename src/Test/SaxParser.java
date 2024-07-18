package Test;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SaxParser extends DefaultHandler {
    private boolean inDataRecords = false;
    private boolean inDataRecord = false;
    private StringBuilder currentElementValue;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("DATA_RECORDS")) {
            inDataRecords = true;
        } else if (qName.equalsIgnoreCase("DATA_RECORD")) {
            inDataRecord = true;
            currentElementValue = new StringBuilder();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (currentElementValue != null) {
            currentElementValue.append(new String(ch, start, length));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("DATA_RECORDS")) {
            inDataRecords = false;
        } else if (qName.equalsIgnoreCase("DATA_RECORD")) {
            inDataRecord = false;
            System.out.println("Check Point Code: " + currentElementValue.toString());
        } else if (qName.equalsIgnoreCase("check_point_name")) {
            System.out.println("Check Point Name: " + currentElementValue.toString());
        }
    }
}

