package ex_2.model;

import java.util.List;

public class Code {
    private List<Long> rgba;
    private String hex;

    public Code(List<Long> rgba, String hex) {

        this.rgba = rgba;
        this.hex = hex;
    }

    public List<Long> getRgba() {
        return rgba;
    }

    public void setRgba(List<Long> rgba) {
        this.rgba = rgba;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    @Override
    public String toString() {
        return "Code{" +
                "rgba=" + rgba +
                ", hex='" + hex + '\'' +
                '}';
    }

}
