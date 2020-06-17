package classes;

import lombok.Data;

import java.util.List;

@Data
public class Settings {

    private Page page;
    private List<Column> list;

    public Settings(Page page, List<Column> list) {
        this.page = page;
        this.list = list;
    }

    @Override
    public String toString() {
        return "Settings{" +
                "page=" + page +
                ", list=" + list.toString() +
                '}';
    }
}
