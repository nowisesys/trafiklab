package nowise.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

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
    public void handle(HttpExchange he) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
