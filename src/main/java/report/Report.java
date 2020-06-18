package report;

import classes.Record;
import classes.Settings;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Report {


    private String path;
    private Settings settings;
    private List <Record> recordList;

    public Report(Settings settings, String pathOfReport, List<Record> recordList) {
        this.path = pathOfReport;
        this.settings = settings;
        this.recordList = recordList;
    }

    // Создание отчета
    public void createReport(){
        ReportPage reportPage = new ReportPage(settings, recordList);
        System.out.println("The creation of pages was started.");
        List<Pages> pagesOfReport = reportPage.createPages();

        System.out.println("The saving of pages was started.");
        saveReport(pagesOfReport, path);

        System.out.println("The report was created.");
    }

    // Сохранение отчета в выбранный файл
    public void saveReport(List<Pages> pages, String path){
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(path), StandardCharsets.UTF_16))) {

            String result = new String();
            for(int i = 0; i < pages.size(); i++)           // Выбираем каждую страницу и добавляем разграничитель в случае,
            {                                               // если следующая страница существует.
                    if(i != 0)
                {
                    result += "~\n";
                }
                List<String> string = pages.get(i).getPage();
                for(int j = 0; j < string.size(); j++ ){
                    result += string.get(j);
                }

            }
            writer.write(result);
            writer.flush();
        }
        catch (IOException ex) {
            System.out.println("IOException - " + ex.getMessage());
        }

    }

}
