package report.tableCreation;

import classes.Settings;

public class Separator {

    private Settings settings;

    public Separator(Settings settings) {
        this.settings = settings;
    }

    public String createSeparator()
{
    String result = "";
    for(int i = 0; i < settings.getPage().getWidth(); i++) {
        result += "-";
    }
    result += "\n";
    return result;
}
}
