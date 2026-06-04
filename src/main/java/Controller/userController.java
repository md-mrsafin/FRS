package Controller;


import Model.User;
import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import jdk.jshell.spi.ExecutionControl;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


public class userController implements HttpHandler  {
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
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
        exchange.getResponseHeaders().set("Content-Type", "application/json");



        String method= exchange.getRequestMethod();
        if (method.equalsIgnoreCase("OPTIONS")) {
            exchange.sendResponseHeaders(204, -1); // No content
            return;
        }
        String path=exchange.getRequestURI().getPath();

        Gson gson = new Gson();
        Type type=new TypeToken<ArrayList<User>>(){}.getType();
        ArrayList<User> users=gson.fromJson(new
                FileReader("src/main/resources/user.json"),type);

        if(users==null) {
            users= new ArrayList<>();
        }
        if(method.equals("GET") && path.equals("/user")) {

            String response=gson.toJson(users);
            sendingData(exchange,200,response);

        }
        if(method.equals("POST") && path.equals("/user/login")) {

            InputStream inputStream=exchange.getRequestBody();
            User user=gson.fromJson(new InputStreamReader(inputStream),User.class);
            try{
                User matchUser=new loginRegHandle().userIdentify(user.getEmail(),user.getPassword());
                if(matchUser!=null) {
                    String response=gson.toJson(matchUser);
                    sendingData(exchange,200,response);
                }
                else{
                    throw new UserException("Email or password is incorrect");
                }
            }
            catch (UserException e){
                String response=gson.toJson(e.getMessage());
                sendingData(exchange,400, response);

            }


        }


    }
}
