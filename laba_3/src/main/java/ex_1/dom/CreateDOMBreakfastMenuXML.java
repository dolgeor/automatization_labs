package ex_1.dom;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import ex_1.BreakfastMenu;
import ex_1.Food;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.util.List;

public class CreateDOMBreakfastMenuXML {

    private static final String successMessage =
            "\n\n-----------------------------------------------\n"
                    + "You have successfully created new XML file ...\n"
                    + "-----------------------------------------------\n\n";
    private static String fileToCreate;
    private static List<Food> listOfFood;
    private static Document doc;

    public CreateDOMBreakfastMenuXML(List<Food> listOfFood, String fileToCreate) {
        this.fileToCreate = fileToCreate;
        this.listOfFood = listOfFood;
    }

    public static void create() {
        try {
            createNewDocument();
            addListToXml();
            writeToFile();
            System.out.print(successMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addListToXml() {

        Element rootElement = doc.createElement(BreakfastMenu.BREAKFAST_MENU);
        doc.appendChild(rootElement);

        for (Food newFood : listOfFood) {

            Element food = doc.createElement(BreakfastMenu.FOOD);
            rootElement.appendChild(food);

            Attr attr = doc.createAttribute(BreakfastMenu.TYPE);
            attr.setValue(newFood.getType());
            food.setAttributeNode(attr);

            Element name = doc.createElement(BreakfastMenu.NAME);
            name.appendChild(doc.createTextNode(newFood.getName()));
            food.appendChild(name);

            Element description = doc.createElement(BreakfastMenu.DESCRIPTION);
            description.appendChild(doc.createTextNode(newFood.getDescription()));
            food.appendChild(description);

            Element price = doc.createElement(BreakfastMenu.PRICE);
            price.appendChild(doc.createTextNode(String.format("$%.2f", newFood.getPrice())));
            food.appendChild(price);

            Element calories = doc.createElement(BreakfastMenu.CALORIES);
            calories.appendChild(doc.createTextNode(String.format("%d", newFood.getCalories())));
            food.appendChild(calories);
        }
    }

    private static void createNewDocument() throws ParserConfigurationException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        doc = dBuilder.newDocument();
    }

    private static void writeToFile() throws TransformerConfigurationException, TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(fileToCreate));
        transformer.transform(source, result);
    }

}
