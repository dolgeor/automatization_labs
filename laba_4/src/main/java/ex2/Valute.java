package ex2;

public class Valute {
    private Integer ID;
    private Integer NumCode;
    private Integer Nominal;
    private String CharCode;
    private String Name;
    private Double Value;

    public Valute(Integer ID, Integer numCode, String charCode,  String name,Integer nominal, Double value) {
        this.ID = ID;
        NumCode = numCode;
        CharCode = charCode;
        Name = name;
        Nominal = nominal;
        Value = value;
    }

    public Integer getID() {
        return ID;
    }

    public Integer getNumCode() {
        return NumCode;
    }

    public Integer getNominal() {
        return Nominal;
    }

    public String getCharCode() {
        return CharCode;
    }

    public String getName() {
        return Name;
    }

    public Double getValue() {
        return Value;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Valute{");
        sb.append("ID=").append(ID);
        sb.append(", NumCode=").append(NumCode);
        sb.append(", CharCode='").append(CharCode).append('\'');
        sb.append(", Name='").append(Name).append('\'');
        sb.append(", Nominal=").append(Nominal);
        sb.append(", Value=").append(Value);
        sb.append('}');
        return sb.toString();
    }
}
