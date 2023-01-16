package nowise.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Level;
import java.util.logging.Logger;
import nowise.remote.DataService;
import nowise.remote.data.FileDataProvider;
import nowise.remote.data.HttpDataProvider;
import org.json.JSONObject;

public class TrafficInformationServer {

    private HttpServer server;
    private final InetSocketAddress address;

    public TrafficInformationServer() {
        this.address = new InetSocketAddress("localhost", 8081);
    }

    public TrafficInformationServer(String address, int port) {
        this.address = new InetSocketAddress(address, port);
    }

    public TrafficInformationServer(InetSocketAddress address) {
        this.address = address;
    }

    public void start() throws IOException {
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

        server = HttpServer.create(address, 0);
        server.createContext("/", new RequestHandler());
        server.setExecutor(threadPoolExecutor);
        server.start();

        System.out.println("Listening on " + address);
    }

    public void stop() {
        server.stop(0);
    }
}

class RequestHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            DataService service = new DataService();
            
            if (isCaching()) {
                service.setDataProvider(new FileDataProvider());
            } else {
                service.setDataProvider(new HttpDataProvider());
            }

            handle(exchange, service);
        } catch (IOException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void handle(HttpExchange exchange, DataService service) throws IOException {
        try (OutputStream stream = exchange.getResponseBody()) {

            JSONObject json = new JSONObject(service.getLines());
            byte[] response = json.toString().getBytes();

            exchange.sendResponseHeaders(200, response.length);

            stream.write(response);
            stream.flush();
        }
    }

    private boolean isCaching() {
        return true;
    }

}
