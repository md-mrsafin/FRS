package Model;

public  class User extends CommonInFlight_User{

    private String email;
    private String phone;
    private String password;
    private int age;
    private boolean isAdmin;

    public User(){}
    public User(String id,String name,String email,String password,String phone,int age){
        super(name,id);
        this.email=email;
        this.password=password;
        this.phone=phone;
        this.age=age;

    }
    public User(String id,String name,String email,String password,String phone,int age,boolean isAdmin){
        super(name,id);
        this.email=email;
        this.password=password;
        this.phone=phone;
        this.age=age;
        this.isAdmin=isAdmin;

    }
    public User(String name,String email,String password,String phone,int age){
        this.name=name;
        this.email=email;
        this.password=password;
        this.phone=phone;
        this.age=age;

    }
    public User(String email,String password){
        this.email=email;
        this.password=password;
    }
    public User(String name,String email,String password){
        this.name=name;
        this.email=email;
        this.password=password;

    }
    public User(String name,String email,String phone,int age){
        this.name=name;
        this.email=email;
        this.phone=phone;
        this.age=age;

    }
    public String getName(){

        return this.name;
    }
    public String getEmail(){
        return this.email;
    }
    public String getPhone(){

        return this.phone;
    }
    public String getPassword(){

        return this.password;
    }
    public int getAge(){

        return this.age;
    }




}
