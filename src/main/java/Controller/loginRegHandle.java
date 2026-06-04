package Controller;

import Model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import java.util.UUID;

public class loginRegHandle {
    Gson gson =new Gson();
    private User user;

    public  User  userIdentify(String email,String password) throws IOException {
        User matchedUser=null;
        Type type = new TypeToken<ArrayList<User>>(){}.getType();
        ArrayList<User> users = gson.fromJson(new FileReader("src/main/resources/user.json"), type);

        if (users == null) {
            users = new ArrayList<>();
        }
        for(User u:users){
            if(u.getEmail().equals(email) && u.getPassword().equals(password)){
                matchedUser=u;
                break;
            }
        }
        return matchedUser;
    }
    public  User userIdentify(String email) throws IOException {
        User matchedUser=null;
        Type type = new TypeToken<ArrayList<User>>(){}.getType();
        ArrayList<User> users = gson.fromJson(new FileReader("src/main/resources/user.json"), type);

        if (users == null) {
            users = new ArrayList<>();
        }
        for(User u:users){
            if(u.getEmail().equals(email)){
                matchedUser=u;
                break;
            }
        }
        return matchedUser;
    }

    public  User handleReg(User newuser, ArrayList<User> users) throws IOException {
        String id=UUID.randomUUID().toString();newuser.setId(id);

        users.add(newuser);

        FileWriter file=new FileWriter("src/main/resources/user.json");
        file.write(gson.toJson(users));
        file.close();

        return newuser;
    }


}
