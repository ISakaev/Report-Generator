package classes;

import lombok.Data;

@Data
public class Column {

    private int numberOfColumn;
    private String title;
    private int width;

    @Override
    public String toString() {
        return "Column №" + numberOfColumn +
                ", title = " + title +
                ", width = " + width +
                '.';
    }
}
