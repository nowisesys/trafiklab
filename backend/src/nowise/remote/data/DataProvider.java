package nowise.remote.data;

import java.io.FileNotFoundException;

public interface DataProvider {
    
    String getStopData() throws FileNotFoundException;
    String getJourData() throws FileNotFoundException;
    
}
