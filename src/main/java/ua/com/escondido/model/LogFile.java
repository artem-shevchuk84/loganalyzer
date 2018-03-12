package ua.com.escondido.model;

import java.util.ArrayList;
import java.util.List;

public class LogFile {

    private List<LogLine> rows;

    public List<LogLine> getRows() {
        return rows;
    }

    public void setRows(List<LogLine> rows) {
        this.rows = rows;
    }

    public boolean put(LogLine row){
        if(rows == null){
            rows = new ArrayList<>();
        }
        return rows.add(row);
    }
}
