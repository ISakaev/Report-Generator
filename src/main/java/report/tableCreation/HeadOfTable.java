package report.tableCreation;

import classes.Column;

import java.util.List;

public class HeadOfTable {
    private List<Column> list;

    public HeadOfTable(List<Column> list) {
        this.list = list;
    }

    //    Подготовка заголовка
    public String createHead(){
        String result = "|";
        for (int i = 0; i < list.size(); i++) {
            result += " " + createFieldOfPage(list.get(i)) + " ";
            result += "|";
        }
        result += "\n";

        return result;
    }

    //    Подготовка полей для заголовка
    private String createFieldOfPage(Column column){
        String result = "";
        if (column.getTitle().length() <= column.getWidth()){
            result += column.getTitle();
            while (result.length() < column.getWidth())
                result += " ";
        }
        return result;
    }
}
