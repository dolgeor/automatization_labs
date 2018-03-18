package ex2;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class ValuteParser {
    private String url;

    public ValuteParser(String url) {
        this.url = url;
    }

    public List<Valute> getValuteList() {
        Document doc = getDocumentFromURL(url);
        NodeList nList = doc.getElementsByTagName("Valute");
        return parseNodeListToValuteList(nList);
    }

    public String generateFileNAme() {
        Document doc = getDocumentFromURL(url);
        StringJoiner stringJoiner = new StringJoiner("_")
                .add(doc.getDocumentElement().getNodeName())
                .add(doc.getDocumentElement().getAttribute("Date"))
                .add(doc.getDocumentElement().getAttribute("name").replaceAll(" ", "_"));
        return stringJoiner.toString();
    }

    private Document getDocumentFromURL(String url) {
        Document doc = null;
        try {
            String xml = getTextFromUrl(url);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            InputSource inputSource = new InputSource(new ByteArrayInputStream(xml.getBytes("utf-8")));
            doc = dBuilder.parse(inputSource);
        } catch (Exception e) {
            System.out.println("Can't get document from " + url);
        }
        return doc;
    }

    private List<Valute> parseNodeListToValuteList(NodeList nList) {
        List<Valute> valuteList = new ArrayList<>();
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                valuteList.add(parseNodeElementToValute((Element) nNode));
            }
        }
        return valuteList;
    }

    private Valute parseNodeElementToValute(Element eElement) {
        return new Valute(
                new Integer(eElement.getAttribute("ID")),
                new Integer(getContentOfElementByTagName(eElement, "NumCode")),
                getContentOfElementByTagName(eElement, "CharCode"),
                getContentOfElementByTagName(eElement, "Name"),
                new Integer(getContentOfElementByTagName(eElement, "Nominal")),
                new Double(getContentOfElementByTagName(eElement, "Value"))
        );
    }

    private String getContentOfElementByTagName(Element eElement, String tagName) {
        return eElement.getElementsByTagName(tagName).item(0).getTextContent();
    }


    private String getTextFromUrl(String url) {
        String target = null;
        try {
            URL website = new URL(url);
            URLConnection connection = website.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                response.append(inputLine);
            in.close();
            target = response.toString();
        } catch (Exception e) {
            System.out.println("Can't get data from " + url);
        }
        return target;
    }


}
