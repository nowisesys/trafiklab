package nowise.model;

public class Stop {
    
    private final String stopPointName;
    private final String stopPointNumber;
    
    public Stop(String stopPointName, String stopPointNumber) {
        this.stopPointName = stopPointName;
        this.stopPointNumber = stopPointNumber;
    }
    
    public String getStopPointName() {
        return stopPointName;
    }
    
    public String getStopPointNumber() {
        return stopPointNumber;
    }
}
