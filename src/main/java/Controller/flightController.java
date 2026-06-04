package Controller;
import Model.Flight;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

public class flightController implements HttpHandler  {

    public void sendingData(HttpExchange exchange,int statusCode,String response){
        try{
            exchange.sendResponseHeaders(statusCode,response.getBytes().length);
            OutputStream operation=exchange.getResponseBody();
            operation.write(response.getBytes());
            operation.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void handle(HttpExchange exchange) throws IOException {

        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
        exchange.getResponseHeaders().set("Content-Type", "application/json");

        Gson gson= new Gson();
        Flight[] flights=gson.fromJson(new FileReader("src/main/resources/flight.json")
                , Flight[].class);
        String method=exchange.getRequestMethod();

        exchange.getResponseHeaders().set("Content-Type","application/json");

        String path= exchange.getRequestURI().getPath();


        if(method.equals("GET") && path.equals("/flight")){
            String response=gson.toJson(flights);
            sendingData(exchange,200,response);

        }



    }

}