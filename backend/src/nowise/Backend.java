package nowise;

import java.io.IOException;
import nowise.server.TrafficInformationServer;

public class Backend {

    public static void main(String[] args) throws IOException {

        TrafficInformationServer server;
        
        for(String arg: args) {
            System.out.println(arg);
        }
        
        if (args.length > 1) {
            String addr = args[0];
            int port = Integer.parseInt(args[1]);

            server = new TrafficInformationServer(addr, port);
            server.start();
        } else {
            server = new TrafficInformationServer();
            server.start();
        }

        System.out.println("Hit enter to shutdown server");
        System.in.read();

        server.stop();
    }
}
