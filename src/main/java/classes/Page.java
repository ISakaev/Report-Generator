package classes;

import lombok.Data;

@Data
public class Page {
    private int width;
    private int height;

    @Override
    public String toString() {
        return "Page parameters : " +
                "width = " + width +
                ", height = " + height +
                '.';
    }
}
