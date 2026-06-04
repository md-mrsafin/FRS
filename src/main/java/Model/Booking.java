package Model;

public class Booking {
    private String id;

    private String userId;
    private String seat;

    private String flightId;


    public Booking(){}

    public Booking(String id, String userId, String flightId, String seat) {
        this.id = id;

        this.userId = userId;
        this.flightId = flightId;
        this.seat = seat;

    }
    public Booking( String userId, String flightId, String seat) {
        this.userId = userId;
        this.flightId = flightId;
        this.seat = seat;

    }
    public String getId(){
        return id;
    }


    public String getUserId(){
        return userId;
    }
    public String getFlightId(){
        return flightId;
    }
    public String getSeat(){
        return seat;
    }

    public void setId(String id){
        this.id=id;
    }
}
