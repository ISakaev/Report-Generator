package parsers;

import classes.Record;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TSVParser {


    private String staffFile;

    public TSVParser(String staffFile) {
        this.staffFile = staffFile;
    }

    public  ArrayList<Record> loadRecordsFromFile()
    {
        ArrayList<Record> staff = new ArrayList<>();
        try
        {
            List<String> lines = Files.readAllLines(Paths.get(staffFile), StandardCharsets.UTF_16);
            for(String line : lines)
            {
                String[] fragments = line.split("\t");
                if(fragments.length != 3) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                staff.add(new Record(
                        Integer.parseInt(fragments[0]), fragments[1], fragments[2]
                ));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return staff;
    }
}
