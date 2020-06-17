import classes.*;
import org.xml.sax.SAXException;
import parsers.*;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class Main {

    public static final String SETTING_PATH = "src/main/resources/settings.xml";
    public static final String SOURCE_DATA = "src/main/resources/source-data.tsv";

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {

        TSVParser parser = new TSVParser(SOURCE_DATA);
        List records = parser.loadRecordsFromFile();
        for (Object r: records){
            System.out.println(r);
        }
        System.out.println("------------------------");

        XMLParser p = new XMLParser();
        Settings set = p.loadSettingsFromFile(SETTING_PATH);
        System.out.println(set.getPage().toString());
        List<Column> s = set.getList();
        s.stream().forEach(System.out::println);
    }
}
