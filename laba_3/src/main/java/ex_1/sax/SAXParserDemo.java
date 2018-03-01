package ex_1.sax;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import ex_1.BreakfastMenu;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXParserDemo {
    private final static String xmlFileToParse = "src/main/resources/breakfast_menu.xml";


    public static void main(String[] args) {

        try {
            File inputFile = new File(xmlFileToParse);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            FoodHandler foodHandler = new FoodHandler();
            saxParser.parse(inputFile, foodHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class FoodHandler extends DefaultHandler {

    private static final String formattedOutputForElement = "\t%s : %s%n";
    private static final String formattedOutputForAttribute = "\n  %s : %s%n";

    boolean bType = false;
    boolean bName = false;
    boolean bDescription = false;
    boolean bPrice = false;
    boolean bCalories = false;

    @Override
    public void startElement(
            String uri, String localName, String qName, Attributes attributes)
            throws SAXException {

        if (qName.equalsIgnoreCase(BreakfastMenu.FOOD)) {
            System.out.format(BreakfastMenu.FOOD);
            String type = attributes.getValue(BreakfastMenu.TYPE);
            System.out.format(formattedOutputForAttribute, BreakfastMenu.TYPE, type);
        } else if (qName.equalsIgnoreCase(BreakfastMenu.NAME)) {
            bName = true;
        } else if (qName.equalsIgnoreCase(BreakfastMenu.PRICE)) {
            bPrice = true;
        } else if (qName.equalsIgnoreCase(BreakfastMenu.DESCRIPTION)) {
            bDescription = true;
        } else if (qName.equalsIgnoreCase(BreakfastMenu.CALORIES)) {
            bCalories = true;
        }
    }

    @Override
    public void endElement(String uri,
                           String localName, String qName) throws SAXException {

        if (qName.equalsIgnoreCase(BreakfastMenu.FOOD)) {
            System.out.println();
            System.out.println();
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {

        if (bName) {
            System.out.format(formattedOutputForElement, BreakfastMenu.NAME, new String(ch, start, length));
            bName = false;
        } else if (bPrice) {
            System.out.format(formattedOutputForElement, BreakfastMenu.PRICE, new String(ch, start, length));
            bPrice = false;
        } else if (bDescription) {
            System.out.format(formattedOutputForElement, BreakfastMenu.DESCRIPTION,
                    new String(ch, start, length)
                            .replaceAll("^\\s+", "")
                            .replaceAll("\\s+$", ""));
            bDescription = false;
        } else if (bCalories) {
            System.out.format(formattedOutputForElement, BreakfastMenu.CALORIES, new String(ch, start, length));
            bCalories = false;
        }
    }
}