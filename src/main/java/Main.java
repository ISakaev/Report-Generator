import report.*;
import classes.*;
import org.xml.sax.SAXException;
import parsers.*;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class Main {

    public static final String SETTING_PATH = "src/main/resources/settings.xml";
    public static final String SOURCE_DATA = "src/main/resources/source-data.tsv";
    public static final String REPORT_PATH = "src/main/resources/report.txt";

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {

        List<Record> recordList;
        Settings settings;

        // Парсим файл с данными
        TSVParser parser = new TSVParser(SOURCE_DATA);
        recordList = parser.loadRecordsFromFile();

        // Парсим файл с настройками
        XMLParser p = new XMLParser();
        settings = p.loadSettingsFromFile(SETTING_PATH);

        // Создаем репорт
        Report report = new Report(settings,REPORT_PATH, recordList );
        report.createReport();

    }
}
