package nowise.model;

public class Jour {

    private final String journeyPatternPointNumber;
    private final String lineNumber;
    
    public Jour(String journeyPatternPointNumber, String lineNumber) {
        this.journeyPatternPointNumber = journeyPatternPointNumber;
        this.lineNumber = lineNumber;
    }

    public String getJourneyPatternPointNumber() {
        return journeyPatternPointNumber;
    }
    
    public String getLineNumber() {
        return lineNumber;
    }
}
