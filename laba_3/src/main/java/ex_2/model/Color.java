package ex_2.model;

import ex_2.model.Code;

import java.util.List;

public class Color {
    private String color;
    private String category;
    private String type;
    private Code code;

    public Color(String color, String category, String type, Code code) {
        this.color = color;
        this.category = category;
        this.type = type;
        this.code = code;
    }

    public static void showAllColors(List<Color> colors) {
        colors.forEach(System.out::println);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Color{" +
                "color='" + color + '\'' +
                ", category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", code=" + code +
                '}';
    }
}
