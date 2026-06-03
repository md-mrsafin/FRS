package Model;

public abstract class CommonInFlight_User {
    protected String name;
    protected String id;
    public CommonInFlight_User(){};
    public CommonInFlight_User(String name,String id){
            this.name = name;
            this.id=id;
    }

    public CommonInFlight_User(String name){
        this.name = name;

    }
    public String getName(){
        return name;
    }
    public String getId(){
        return id;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setId(String id){
        this.id=id;
    }


}
