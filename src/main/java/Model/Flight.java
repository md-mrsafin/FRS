package Model;

public class Flight extends CommonInFlight_User{

    private String from;
    private String to;
    private String date;
    private String time;
    private int price;

    public  Flight(String id,String name,int price,String from,String to,String date,String time){
        this.name=name;
        this.id=id;
        this.price=price;
        this.from=from;
        this.to=to;
        this.date=date;
        this.time=time;

    }


    public String getTo(){
        return this.to;
    }
    public String getDate(){
        return this.date;
    }
    public String getTime(){
        return this.time;
    }
    public int getPrice(){return this.price;}



}

