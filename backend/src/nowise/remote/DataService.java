package nowise.remote;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import nowise.model.Line;
import nowise.model.Stop;
import nowise.remote.data.DataProvider;
import org.json.JSONArray;
import org.json.JSONObject;

public class DataService {

    private DataProvider provider;
    private Map<Integer, Line> lines = new HashMap<>();

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
        try {
            JSONObject jour = new JSONObject(provider.getJourData());
            JSONObject stop = new JSONObject(provider.getStopData());

            Extractor extractor = new Extractor(jour, stop);
            extractor.setLines(lines);

            Filter filter = new Filter(10);
            filter.apply(lines);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

class Extractor {

    private final JSONObject jour;
    private final JSONObject stop;

    public Extractor(JSONObject jour, JSONObject stop) {
        this.jour = jour;
        this.stop = stop;
    }

    public void setLines(Map<Integer, Line> lines) {
        JSONArray jours = getJours();
        JSONArray stops = getStops();

        Map<Integer, Stop> ss = new HashMap<>();

        setStops(stops, ss);
        setLines(jours, ss, lines);
    }

    private JSONArray getJours() {
        return jour.getJSONObject("ResponseData").getJSONArray("Result");
    }

    private JSONArray getStops() {
        return stop.getJSONObject("ResponseData").getJSONArray("Result");
    }

    private void setStops(JSONArray stops, Map<Integer, Stop> ss) {
        for (int i = 0; i < stops.length(); ++i) {
            JSONObject j = stops.getJSONObject(i);
            int number = j.getInt("StopPointNumber");

            ss.put(number, new Stop(
                    j.getString("StopPointName"),
                    j.getString("StopPointNumber")
            ));
        }
    }

    private void setLines(JSONArray jours, Map<Integer, Stop> ss, Map<Integer, Line> lines) {
        for (int i = 0; i < jours.length(); ++i) {
            JSONObject j = jours.getJSONObject(i);
            int ln = j.getInt("LineNumber");
            int sn = j.getInt("JourneyPatternPointNumber");

            if (!lines.containsKey(ln)) {
                lines.put(ln, new Line(ln));
            }

            lines.get(ln).addStop(ss.get(sn));
        }
    }
}

class Filter {

    private final int number;

    public Filter(int number) {
        this.number = number;
    }

    public void apply(Map<Integer, Line> lines) {
        List<Line> temp = new ArrayList<>(lines.values());
        lines.clear();

        Collections.sort(temp, (Line l1, Line l2) -> l2.getStops().size() - l1.getStops().size());

        for (int i = 0; i < number; ++i) {
            lines.put(temp.get(i).getNumber(), temp.get(i));
        }
    }
}
