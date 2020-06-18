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
        StringSeparator stringSeparator = new StringSeparator();
        List<String> listStringForPage = new ArrayList<>();
        String[] index1;
        String[] index2;
        String[] index3;

//        Расчет первой колонки
        Integer number = record.getNumber();
        String numberString;
        if (number.toString().length() < columnList.get(0).getWidth()) {
            index1 = new String[1];
            numberString = number.toString();
            while (numberString.length() < columnList.get(0).getWidth()) {
                numberString += " ";
            }
            index1[0] = numberString;
        } else {                            // Если цифра большая и не влезает в одну строку
            index1 = new String[2];
            index1[0] = number.toString().substring(0, 7);
            numberString = number.toString().substring(7, number.toString().length() - 1);
            while (numberString.length() < columnList.get(0).getWidth()) {
                numberString += " ";
            }
            index1[1] = numberString;
        }
//        Расчет второй колонки
        String date = record.getDate();
        String dateString;
        if (date.length() < columnList.get(1).getWidth()) {
            index2 = new String[1];
            dateString = date;
            while (dateString.length() < columnList.get(1).getWidth()) {
                dateString += " ";
            }
            index2[0] = dateString;
        } else {                // Если текст большой и не влезает в одну строку
            index2 = new String[2];
            index2[0] = date.substring(0, date.lastIndexOf('/'));
            while (index2[0].length() < columnList.get(1).getWidth()) {
                index2[0] += " ";
            }
            dateString = date.substring(date.lastIndexOf('/'));
            while (dateString.length() < columnList.get(1).getWidth()) {
                dateString += " ";
            }
            index2[1] = dateString;
        }

//        Расчет третьей колонки
        String name = record.getName();
        String nameString;
        if (name.length() < columnList.get(2).getWidth()) {
            index3 = new String[1];
            nameString = name;
            while (nameString.length() < columnList.get(2).getWidth()) {
                nameString += " ";
            }
            index3[0] = nameString;
        } else {
            String textAfterSeparation = stringSeparator.SeparateString(record.getName(), settings.getList().get(2).getWidth());
            index3 = textAfterSeparation.split("/");
            for (int i = 0; i < index3.length; i++) {
                String example = index3[i];
                while (example.length() < columnList.get(2).getWidth()) {
                    example += " ";
                }
                index3[i] = example;

            }
        }

//        Заполнение коллекции строк на текущую запись
        int countLines = index3.length > index2.length ?
                Math.max(index3.length, index1.length) : Math.max(index2.length, index1.length);

        for (int i = 0; i < countLines; i++) {
            StringBuilder builder = new StringBuilder();
            builder.append("| ");
            if (index1.length < i + 1) {
                for (int j = 0; j < columnList.get(0).getWidth(); j++) {
                    builder.append(" ");
                }
            } else {
                builder.append(index1[i]);
            }
            builder.append(" | ");

            if (index2.length < i + 1) {
                for (int j = 0; j < columnList.get(1).getWidth(); j++) {
                    builder.append(" ");
                }
            } else {
                builder.append(index2[i]);
            }
            builder.append(" | ");
            if (index3.length < i + 1) {
                for (int j = 0; j < columnList.get(2).getWidth(); j++) {
                    builder.append(" ");
                }
            } else {
                builder.append(index3[i]);
            }
            builder.append(" |");

            listStringForPage.add(builder.toString());
        }
        return listStringForPage;
    }
}
