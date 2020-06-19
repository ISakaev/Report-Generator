package report.tableCreation;

import classes.Column;
import classes.Record;
import classes.Settings;

import java.util.ArrayList;
import java.util.List;

public class Field {

    private List<Column> columnList;
    private Settings settings;

    public Field(List<Column> columnList, Settings settings) {
        this.columnList = columnList;
        this.settings = settings;
    }

    public List<String> createField(Record record) {
//        Расчет количества строк для колон для текущей записи
        List<String> listStringForPage = new ArrayList<>();
        //        Расчет первой колонки
        String[] index1 = createDataInColumn(record.getNumber().toString(), 0);
        //        Расчет второй колонки
        String[] index2 = createDataInColumn(record.getDate(), 1);
        //        Расчет третьей колонки
        String[] index3 = createDataInColumn(record.getName(), 2);

//        Заполнение коллекции строк на текущую запись
        int countLines = index3.length > index2.length ?
                Math.max(index3.length, index1.length) : Math.max(index2.length, index1.length);

        for (int i = 0; i < countLines; i++) {
            StringBuilder builder = new StringBuilder();

            builder.append("| ");
                // Заполняем колонку с индексов 0 , i-й строки
            builder.append(FillLine(index1, i, 0));
            builder.append(" | ");
                // Заполняем колонку с индексов 1 , i-й строки
            builder.append(FillLine(index2, i, 1));
            builder.append(" | ");
                // Заполняем колонку с индексов 2 , i-й строки
            builder.append(FillLine(index3, i, 2));
            builder.append(" |");

            listStringForPage.add(builder.toString());
        }
        return listStringForPage;
    }

        // Разделение текста на массив строк одинаковой длины
    private String[] createDataInColumn(String parameterOfRecord, Integer indexOfLIst){
        StringSeparator stringSeparator = new StringSeparator();
        String[] index;
        String name = parameterOfRecord;
        String nameString;

        if (name.length() < columnList.get(indexOfLIst).getWidth()) {       // Если текст влезает в одну ячейку
            index = new String[1];
            nameString = name;
            while (nameString.length() < columnList.get(indexOfLIst).getWidth()) {
                nameString += " ";
            }
            index[0] = nameString;
        } else {                                                            // Если текст не влезает в одну ячейку
            String textAfterSeparation = stringSeparator.
                    SeparateString(parameterOfRecord, settings.getList().get(indexOfLIst).getWidth());
            index = textAfterSeparation.split("\t");
            for (int i = 0; i < index.length; i++) {
                String example = index[i];
                while (example.length() < columnList.get(indexOfLIst).getWidth()) {
                    example += " ";
                }
                index[i] = example;
            }
        }
        return index;
    }

    // Заполнение поля в колонке
    private String FillLine(String[] arrayWithData, Integer indexFor, Integer indexColumn){
        StringBuilder builder = new StringBuilder();
        if (arrayWithData.length < indexFor + 1) {
            for (int j = 0; j < columnList.get(indexColumn).getWidth(); j++) {
                builder.append(" ");
            }
        } else {
            builder.append(arrayWithData[indexFor]);
        }
        return builder.toString();
    }
}
