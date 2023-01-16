package nowise.remote.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpDataProvider implements DataProvider {

    private final String key = "be5f28cd8fe54a1eabc11a2c98540a1b";

    @Override
    public String getStopData() throws FileNotFoundException {
        return getJsonResponse("stop");
    }

    @Override
    public String getJourData() throws FileNotFoundException {
        return getJsonResponse("jour");
    }

    private String getJsonResponse(String model) {
        try {
            URI url = new URI("https://api.sl.se/api2/LineData.json?model=" + model + "&key=" + key + "&DefaultTransportModeCode=BUS");

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .header("Content-Encoding", "gzip")
                    .uri(url)
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

            return response.body();

        } catch (URISyntaxException | IOException | InterruptedException ex) {
            Logger.getLogger(HttpDataProvider.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "{}";
    }

}
