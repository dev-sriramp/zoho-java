package mr.anonymous;

public class Booking {
    String userid,pnrid,travelled;
    int from,to,seat;
    public Booking(String userid,int from,int to,String pnrid,String travelled,int seat){
        this.userid=userid;
        this.from=from;
        this.to=to;
        this.pnrid=pnrid;
        this.travelled=travelled;
        this.seat=seat;
    }

}
