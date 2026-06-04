package Controller;

import Model.Booking;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.UUID;

public class bookingController implements HttpHandler {
    Gson gson=new Gson();
    public void sendingResponse(HttpExchange exchange,int status,String response) throws IOException {
        exchange.sendResponseHeaders(status,response.getBytes().length);
        OutputStream operation=exchange.getResponseBody();
        operation.write(response.getBytes());
        operation.close();
    }
    public Booking handleBooking(Booking booking,ArrayList<Booking> bookings) throws IOException{
        String id=UUID.randomUUID().toString();
        booking.setId(id);
        bookings.add(booking);
        FileWriter file=new FileWriter("src/main/resources/booking.json");
        file.write(gson.toJson(bookings));
        file.close();

        return booking;
    }
    public void handle(HttpExchange exchange) throws IOException{
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
        exchange.getResponseHeaders().set("Content-Type", "application/json");



        String method= exchange.getRequestMethod();


        if (method.equalsIgnoreCase("OPTIONS")) {
            exchange.sendResponseHeaders(204, -1);
            return;
        }
        Type type=new TypeToken<ArrayList<Booking>>(){}.getType();
        ArrayList<Booking> bookings=gson.fromJson(new FileReader("src/main/resources/booking.json"),type);

        if(bookings==null){
            bookings=new ArrayList<Booking>();
        }
        if (method.equalsIgnoreCase("GET")) {
            String path = exchange.getRequestURI().getPath();
            String query = exchange.getRequestURI().getQuery();

            if (path.equals("/booking")) {

                if (query != null && query.startsWith("userId=")) {
                    String targetedUserId = query.split("=")[1];

                    ArrayList<Booking> filteredBookings = new ArrayList<>();
                    for (Booking b : bookings) {

                        if (b.getUserId() != null && b.getUserId().equals(targetedUserId)) {
                            filteredBookings.add(b);
                        }
                    }

                    String response = gson.toJson(filteredBookings);
                    sendingResponse(exchange, 200, response);
                }

                else {
                    String response = gson.toJson(bookings);
                    sendingResponse(exchange, 200, response);
                }
            } else {
                sendingResponse(exchange, 404, "Not Found");
            }
        }
        else if(method.equals("POST")){
            InputStream is=exchange.getRequestBody();
            System.out.println(is);
            try{
                Booking booking=gson.fromJson(new InputStreamReader(is, StandardCharsets.UTF_8),Booking.class);
                handleBooking(booking,bookings);
                String response=gson.toJson(bookings);
                sendingResponse(exchange,200,response);
            }
            catch(Exception e){
                sendingResponse(exchange,500,e.getMessage());
            }


        }
    }

}