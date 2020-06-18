package report;


import lombok.Data;

import java.util.List;

@Data
public class Pages {
    private List<String> page;
    private int height;

    public Pages(List<String> page) {
        this.page = page;
    }
}
