package ex_3;

public enum Type {
    SEDAN("sedan"),
    COUPE("coupe"),
    HATCHBACK("hatchback"),
    MINIVAN("minivan");

    private final String type;

    Type(String type) {
        this.type = type;
    }

    public String getTypeValue() {
        return type;
    }

    public static Type getTypeByString(String stringType) {
        return Type.valueOf(stringType.toUpperCase());
    }

}
