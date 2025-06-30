package Models;

import java.io.Serializable;

public class Conversion implements Serializable {
    private static final long serialVersionUID = 2L;

    public String inputValue;
    public String result;
    public String errorMessage;
    public int sourceBase;
    public int targetBase;

    public Conversion(String inputValue, int sourceBase, int targetBase) {
        this.inputValue = inputValue;
        this.sourceBase = sourceBase;
        this.targetBase = targetBase;
    }

    @Override
    public String toString() {
        return "Conversion{" +
                "inputValue='" + inputValue + '\'' +
                ", sourceBase=" + sourceBase +
                ", targetBase=" + targetBase +
                ", result='" + result + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
