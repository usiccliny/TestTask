package Test;

import javax.xml.parsers.ParserConfigurationException;

public class App {

	 public static void main(String[] args) throws ParserConfigurationException {
		 String pathXSD = "mu_rp_check_points.xsd";
		 String pathXML = "mu_rp_check_points.xml";
		 
		 //Task 1
		 /*var XMLValidator = new XmlValidator();
		 if (!XMLValidator.Validation(pathXSD, pathXML))
			 return;
		 System.out.println("ok");
		 
		 //Task 2
		 var XMLCounter = new XmlCounter();
		 if (!XMLCounter.Count(pathXML)) {
			 return;
		 }
		 System.out.println("ok");
		 
		 //Task 3
		 var XMLParser = new XmlParser();
		 XMLParser.Parser(pathXML);*/
		 
		 
		 //NEW CLASS
		 var TestTask = new XmlTasks(pathXSD, pathXML);
		 
		 //Task 1
		 if (!TestTask.Validation()) {
			 System.out.println("Ошибка валидации");
			 return;
		 }
		 
		 System.out.println("Успешная валидация!");
		 
		 //Task 2
		 if (!TestTask.Count()) {
			 System.out.println("Не совпали значения");
			 return;
		 }
		 
		 //Task 3
		 TestTask.Parser();
	    }
}
