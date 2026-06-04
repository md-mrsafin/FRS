import Controller.bookingController;
import Controller.flightController;
import Controller.userController;

import com.sun.net.httpserver.HttpServer;


import java.io.IOException;

import java.net.InetSocketAddress;
public class Main {
    static void main(String[] args) throws IOEXception {
    String portStr = System.getenv("PORT");
        int port = (portStr != null) ? Integer.parseInt(portStr) : 9090;
        HttpServer server= HttpServer.create(new InetSocketAddress(port),0);

        server.createContext("/user",new userController());

        server.createContext("/flight",new flightController());
        server.createContext("/booking",new bookingController());

        server.start();
        System.out.println("Server started on port " + port);

    
    
     }
  }

}
