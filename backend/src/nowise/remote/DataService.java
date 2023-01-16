package nowise.remote;

import java.util.HashMap;
import java.util.Map;
import nowise.model.Line;
import nowise.remote.data.DataProvider;

public class DataService {

    private DataProvider provider;
    private Map<Integer, Line> lines = new HashMap<>();

    public DataService() {
        lines.put(0, new Line(0));
    }

    public void setDataProvider(DataProvider provider) {
        this.provider = provider;
    }

    public void clear() {
        lines.clear();
    }

    public Map<Integer, Line> getLines() {
        if (lines.isEmpty()) {
            setLines();
        }

        return lines;
    }

    private void setLines() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
