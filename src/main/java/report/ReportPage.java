package report;

import classes.Column;
import classes.Record;
import classes.Settings;
import report.tableCreation.*;

import java.util.ArrayList;
import java.util.List;

public class ReportPage {

    private int heightTable;
    private List<Column> columnList;
    private Settings settings;
    private List<Record> listRecords;
    private List<Pages> listPages;


    public ReportPage(Settings settings, List<Record> listRecords) {
        this.settings = settings;
        this.listRecords = listRecords;
        this.heightTable = settings.getPage().getHeight();
        this.columnList = settings.getList();
    }

    // Создание списка страниц для отчета
    public List<Pages> createPages(){
        List<String> pages = new ArrayList<>();
        List<Pages> pagesList = new ArrayList<>();
        HeadOfTable head = new HeadOfTable(columnList);
        Separator separator = new Separator(settings);

        pages.add(head.createHead());                   // Добавляем заголовок

        int heightCurrentPage = 1;                      // Добавляем индекс для отслеживания высоты таблицы
        for (int i = 0; i < listRecords.size();  ){
            List <String> listOfRecord = (new Field(columnList, settings)).createField(listRecords.get(i));
            if (listOfRecord.size() + heightCurrentPage + 1 < heightTable){   // Проверяем хватит ли нам места если
                pages.add(separator.createSeparator());                       // рещим добавить след запись на текущую страницу.
                heightCurrentPage++;
                for (int j = 0; j < listOfRecord.size(); j++){          // Добавляем строки текущей записи
                    pages.add(listOfRecord.get(j) + "\n");
                }
                heightCurrentPage += listOfRecord.size();
                i++;
            }
           else{                                        // Если места не хватит, то сохраняем текущую страницу.
                pagesList.add(new Pages(pages));
                pages = new ArrayList<>();              // Создаем новую страницу и заполняем ее.
                pages.add(head.createHead());
                heightCurrentPage = 1;
            }
        }
        pagesList.add(new Pages(pages));
        return pagesList;

    }
}
