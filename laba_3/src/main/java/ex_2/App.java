package ex_2;

import ex_2.model.Code;
import ex_2.model.Color;
import ex_2.parser.JsonColorParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static ex_2.creator.CreateJSON.createFileJSON;
import static ex_2.model.Color.showAllColors;
import static ex_2.parser.JsonParserInfo.*;

public class App {
    public static void main(String[] args) {
        List<Color> colors = JsonColorParser.parseJSON(FILE_TO_PARSE);
        showAllColors(colors);
        createFileJSON(FILE_TO_CREATE, colors);
    }
}
