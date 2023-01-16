package nowise.model;

import java.util.ArrayList;
import java.util.List;

public class Line {

    private final int number;
    private List<Stop> stops = new ArrayList<Stop>();

    public Line(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
    
    public void addStop(Stop stop) {
        stops.add(stop);
    }

    public List<Stop> getStops() {
        return stops;
    }
}
