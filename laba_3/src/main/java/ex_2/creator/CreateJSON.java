package ex_2.creator;

import ex_2.model.Code;
import ex_2.model.Color;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static ex_2.parser.JsonParserInfo.*;
import static ex_2.parser.JsonParserInfo.HEX;

public class CreateJSON {

    public static void createFileJSON(String fileToCreate, List<Color> colors) {
        JSONObject colorsJSON = new JSONObject();
        colorsJSON.put(COLORS, createColorsListJSON(colors));
        try (FileWriter file = new FileWriter(FILE_TO_CREATE)) {
            file.write(colorsJSON.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JSONArray createColorsListJSON(List<Color> colors) {
        JSONArray colorsList = new JSONArray();
        colors.forEach(c -> colorsList.add(createColorJSON(c)));
        return colorsList;
    }

    private static JSONObject createColorJSON(Color color) {
        JSONObject colorJSON = new JSONObject();
        colorJSON.put(COLOR, color.getColor());
        colorJSON.put(CATEGORY, color.getCategory());
        colorJSON.put(TYPE, color.getType());
        colorJSON.put(CODE, createCodeJSON(color.getCode()));
        return colorJSON;
    }

    private static JSONObject createCodeJSON(Code code) {
        JSONObject codeJSON = new JSONObject();
        codeJSON.put(RGBA, code.getRgba());
        codeJSON.put(HEX, code.getHex());
        return codeJSON;
    }
}
