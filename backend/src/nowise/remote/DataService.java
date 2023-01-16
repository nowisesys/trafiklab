package nowise.remote;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
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

            JSONArray jours = jour.getJSONObject("ResponseData").getJSONArray("Result");
            JSONArray stops = stop.getJSONObject("ResponseData").getJSONArray("Result");

            Map<Integer, Stop> ss = new HashMap<>();

            for (int i = 0; i < stops.length(); ++i) {
                JSONObject j = stops.getJSONObject(i);
                int number = j.getInt("StopPointNumber");
                
                ss.put(number, new Stop(
                        j.getString("StopPointName"),
                        j.getString("StopPointNumber")
                ));
            }

            for (int i = 0; i < jours.length(); ++i) {
                JSONObject j = jours.getJSONObject(i);
                int ln = j.getInt("LineNumber");
                int sn = j.getInt("JourneyPatternPointNumber");

                if (!lines.containsKey(ln)) {
                    lines.put(ln, new Line(ln));
                }
                
                lines.get(ln).addStop(ss.get(sn));
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
