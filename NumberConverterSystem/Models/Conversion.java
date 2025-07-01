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
        this.errorMessage = "";
        this.result = "";
    }

    @Override
    public String toString() {
        return String.format("Value : %s - Source Base : %d - Target Base : %d - Result : %s - Error Message - %s\n",
                inputValue, sourceBase, targetBase, (result.isEmpty() ? "No Result" : result),
                (errorMessage.isEmpty() ? "No Error Message" : errorMessage));
    }
}
