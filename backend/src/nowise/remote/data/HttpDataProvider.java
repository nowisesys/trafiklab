package nowise.remote.data;

import java.io.FileNotFoundException;

public class HttpDataProvider implements DataProvider {

    @Override
    public String getStopData() throws FileNotFoundException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getJourData() throws FileNotFoundException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
