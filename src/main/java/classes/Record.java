package classes;

import lombok.Data;

@Data
public class Record {

    private Integer number;
    private String date;
    private String name;

    public Record (Integer number, String date, String name)
    {
        this.number = number;
        this.date = date;
        this.name = name;
    }


    @Override
    public String toString()
    {
        return number  + " - " + date + " - " + name;
    }
}
