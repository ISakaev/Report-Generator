package report.tableCreation;

public class StringSeparator {


    public StringSeparator() {
    }

    //    Разделение текста фиксирофанной ширины
    public String SeparateString(String s, Integer widthColumn) {

        String inputText = s.trim();
        String outText = new String();

        if (inputText.length() < 1)
            return "";
        else if (widthColumn > inputText.length()) {
            while (inputText.length() < widthColumn){
                inputText += " ";
            }
            outText = inputText;
            return outText;
        }
        else {
            String[] longText;
            while (inputText.length() > widthColumn){           // Добавляем пробелы до необходимой длины колонки
                longText = SeparateText(inputText, widthColumn);
                outText += longText[0] + "/";
                inputText = longText[1];
            }
            outText += inputText;
            return outText;
        }
    }

    // Разделение текста размером не более ширины колонки
    public String[] SeparateText(String string, Integer widthColumn ){
        String[] separateTextString = new String[2];
        if(SeparateFindWhitespace(string, (widthColumn + 1)) > 0){  // Проверяем если ли в строке пробел
            int indexWhitespace = SeparateFindWhitespace(string, (widthColumn + 1));
            separateTextString[0] = string.substring(0, indexWhitespace);
            separateTextString[1] = string.substring(indexWhitespace + 1);

            return separateTextString;
        }
        else if (SeparateFindDash(string, widthColumn) > 0){      // Проверяем если ли в строке дефис
            int indexDash = SeparateFindDash(string, widthColumn);
            separateTextString[0] = string.substring(0, indexDash);
            separateTextString[1] = string.substring(indexDash);
            return separateTextString;
        }
        else{                                     // Если в строке нет пробела и дефиса, то разделяем по ширине колонки
            separateTextString[0] = string.substring(0, widthColumn);
            separateTextString[1] = string.substring(widthColumn);
            return separateTextString;

        }

    }
    // Поиск последнего индекса "дефис" в сроке
    public int SeparateFindDash(String string, Integer widthColumn){
        int index = 0;
        if (widthColumn > string.length()){
            widthColumn = string.length();
        }
        for(int i = (widthColumn-1); i >0 ; i--){
            if (string.charAt(i) == '-') {
                index = i;
                return index;
            }
        }
        return index;
    }

    // Поиск последнего индекса "пробела" в сроке
    public int SeparateFindWhitespace(String string, Integer widthColumn){
        int index = 0;
        if (widthColumn > string.length()){
            widthColumn = string.length();
        }
        for(int i = (widthColumn-1); i >0 ; i--){
            if (Character.isWhitespace(string.charAt(i))){
                index = i;
                return index;
            }
        }
        return index;
    }

}
