package by.epam.pavelshakhlovich.shape.inputdata;

import java.util.ArrayList;
import java.util.List;

public class ValidData {
    private List<FourPoints> validData = new ArrayList<>();

    public ValidData(List<FourPoints> validData) {
        this.validData = validData;
    }

    public List<FourPoints> getValidData() {
        return validData;
    }
}
