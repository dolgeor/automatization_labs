package ex_2.parser;

import ex_2.model.Code;
import ex_2.model.Color;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static ex_2.parser.JsonParserInfo.*;

public class JsonColorParser {

    public static List<Color> parseJSON(String file) {
        List<Color> colorsList = new ArrayList<Color>();
        JSONParser parser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(file));
            JSONArray colors = (JSONArray) jsonObject.get(COLORS);
            colors.forEach(c -> colorsList.add(parseColor((JSONObject) c)));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return colorsList;
    }

    private static Color parseColor(JSONObject jsonObject) {
        return new Color(
                (String) jsonObject.get(COLOR),
                (String) jsonObject.get(CATEGORY),
                (String) jsonObject.get(TYPE),
                parseCode((JSONObject) jsonObject.get(CODE)));
    }

    private static Code parseCode(JSONObject jsonObject) {
        return new Code(
                (List<Long>) jsonObject.get(RGBA),
                (String) jsonObject.get(HEX)
        );
    }

}

