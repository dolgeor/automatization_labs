package ex_1.dom;

import java.util.ArrayList;
import java.util.List;

import ex_1.Food;

public class RunApp {

    private final static String xmlFileToParse = "src/main/resources/breakfast_menu.xml";
    private final static String xmlFileToCreate = "src/main/resources/new_breakfast_menu.xml";
    private static List<Food> foodList = new ArrayList<Food>();

    static {
        foodList.add(new Food("desert", "tort", "sweet", 100, 2000));
        foodList.add(new Food("lunch", "steak", "meal", 50, 3000));
        foodList.add(new Food("vegan", "tomato", "mr. Pomidor", 1, 100));
    }

    public static void main(String[] args) {
        HandleDOMBreakfastMenuXML handleDOMBreakfastMenuXML = new HandleDOMBreakfastMenuXML(xmlFileToParse);
        handleDOMBreakfastMenuXML.parse();
        handleDOMBreakfastMenuXML.query();

        CreateDOMBreakfastMenuXML createDOMBreakfastMenuXML = new CreateDOMBreakfastMenuXML(foodList, xmlFileToCreate);
        createDOMBreakfastMenuXML.create();
    }
}