package ex_1.dom;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import ex_1.BreakfastMenu;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class HandleDOMBreakfastMenuXML {

    private static String fileName;
    private static Document doc;
    private static Element eElement;
    private static final String formattedOutput = "%s : %s%n";
    private static final String formattedOutputForElement = "\t%s : %s%n";
    private static final String formattedOutputForAttribute = "  %s : %s%n";



    public HandleDOMBreakfastMenuXML(String fileName) {
        this.fileName = fileName;
    }

    public static void parse() {
        try {
            getDocument();
            System.out.format(formattedOutput, "Root element", BreakfastMenu.BREAKFAST_MENU);
            System.out.println("----------------------------");
            NodeList nList = doc.getElementsByTagName(BreakfastMenu.FOOD);
            printNodesByTagName(nList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void query() {
        try {
            getDocument();
            System.out.format(formattedOutput, "\nRoot element", BreakfastMenu.BREAKFAST_MENU);
            System.out.println("----------------------------");
            NodeList nList = doc.getElementsByTagName(BreakfastMenu.FOOD);
            printNodesByTagNameAndAttributes(nList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void create(){}

    private static void printNodesByTagName(NodeList nList) {
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.format(formattedOutput, "\nCurrent Element", nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                eElement = (Element) nNode;
                printNodeElements(nNode);
            }
        }
    }

    private static void printNodesByTagNameAndAttributes(NodeList nList) {
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.format(formattedOutput, "\nCurrent Element", nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                eElement = (Element) nNode;
                printElementByAttribute(BreakfastMenu.TYPE);
                printNodeElements(nNode);
            }
        }
    }

    private static void printNodeElements(Node nNode) {
        printElementByTag(BreakfastMenu.NAME);
        printElementByTag(BreakfastMenu.PRICE);
        printElementByTag(BreakfastMenu.DESCRIPTION);
        printElementByTag(BreakfastMenu.CALORIES);
    }

    private static void printElementByAttribute(String attribute) {
        final String elementValue = eElement.getAttribute(attribute);
        System.out.format(formattedOutputForAttribute, attribute, elementValue);
    }

    private static void printElementByTag(String tag) {

        final String elementValue = eElement
                .getElementsByTagName(tag)
                .item(0)
                .getTextContent()
                .replaceAll("^\\s+", "")
                .replaceAll("\\s+$", "");

        System.out.format(formattedOutputForElement, tag, elementValue);
    }

    private static void getDocument() throws IOException, SAXException, ParserConfigurationException {
        File inputFile = new File(fileName);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
    }

}
